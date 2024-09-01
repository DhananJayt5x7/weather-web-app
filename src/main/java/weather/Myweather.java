package weather;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class Myweather
 */
public class Myweather extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Myweather() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String city = request.getParameter("city");
        //validate the city parameter
        if (city == null || city.trim().isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().append("Error: 'city' parameter is missing or empty.");
            return;
        }
     // Encode the city name to handle spaces and special characters
        String encodedCity = URLEncoder.encode(city.trim(), StandardCharsets.UTF_8.toString());
        String apiKey = "a6fefa4cd6aab1b832386c6ec911947c";
        String apiurl = "https://api.openweathermap.org/data/2.5/weather?q=" + encodedCity + "&appid=" + apiKey;

        HttpURLConnection connection = null;
        try {
            URL url = new URL(apiurl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Set the response content type to JSON
            //response.setContentType("application/json");

            // Reading data from the network using a try-with-resources block
            try (InputStream inputStream = connection.getInputStream();
                 InputStreamReader reader = new InputStreamReader(inputStream);
                 Scanner sc = new Scanner(reader)) {

                StringBuilder str = new StringBuilder();
                while (sc.hasNext()) {
                    str.append(sc.nextLine());
                }

                // Output the response to the client
                //response.getWriter().append(str.toString());
                Gson gson = new Gson();
                JsonObject jsonObject = gson.fromJson(str.toString(), JsonObject.class);
                
                //Date & Time
                long dateTimestamp = jsonObject.get("dt").getAsLong() * 1000;
                String date = new Date(dateTimestamp).toString();
                
                //Temperature
                double temperatureKelvin = jsonObject.getAsJsonObject("main").get("temp").getAsDouble();
                int temperatureCelsius = (int) (temperatureKelvin - 273.15);
               
                //Humidity
                int humidity = jsonObject.getAsJsonObject("main").get("humidity").getAsInt();
                
                //Wind Speed
                double windSpeed = jsonObject.getAsJsonObject("wind").get("speed").getAsDouble();
                
                //Weather Condition
                String weatherCondition = jsonObject.getAsJsonArray("weather").get(0).getAsJsonObject().get("main").toString();
                
             // Set the data as request attributes (for sending to the jsp page)
                request.setAttribute("date", date);
                request.setAttribute("city", city);
                request.setAttribute("temperature", temperatureCelsius);
                request.setAttribute("weatherCondition", weatherCondition); 
                request.setAttribute("humidity", humidity);    
                request.setAttribute("windSpeed", windSpeed);
                request.setAttribute("weatherData",str.toString());
                connection.disconnect();
                
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
            response.getWriter().append("Invalid URL: ").append(e.getMessage());
        } catch (ProtocolException e) {
            e.printStackTrace();
            response.getWriter().append("Protocol error: ").append(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            response.getWriter().append("I/O error: ").append(e.getMessage());
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        request.getRequestDispatcher("index.jsp").forward(request, response);
		
		
		
	}

}
