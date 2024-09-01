# Weather Web Application

## Project Overview
This project is a web-based weather application that provides real-time weather information for various locations. The application retrieves data from the OpenWeatherMap API and displays it to the user in an easy-to-understand format.

## Features
- **Real-Time Weather Data:** Fetches and displays current weather conditions including temperature, humidity, and weather description.
- **Location-Based Search:** Allows users to search for weather data by city name.
- **User-Friendly Interface:** Simple and intuitive UI for searching and viewing weather information.

## Technologies Used
- **Java:** Core language for back-end processing.
- **Servlets:** Manages requests to the OpenWeatherMap API and processes the response.
- **JSP (JavaServer Pages):** Renders the weather data to the user.
- **HTML/CSS:** Provides the basic structure and styling of the web pages.
- **OpenWeatherMap API:** External API used for retrieving weather data.

## Project Structure
- **src/main/java/com/yourpackage/servlets/**
  - `WeatherServlet.java`: Handles user requests and communicates with the OpenWeatherMap API.
- **src/main/webapp/**
  - `index.jsp`: Main page for user input.
  - `weather.jsp`: Displays the weather information retrieved from the API.
- **src/main/resources/**
  - `application.properties`: Configuration file for API keys and other settings.

## Setup and Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/DhananJayt5x7/weather-webapp.git
2. Navigate to the project directory
   ```bash
   cd weather-webapp
3. Import the project into your preferred IDE (Eclipse, IntelliJ IDEA, etc.).
4. Obtain an API key from OpenWeatherMap.
5. Add your API key to the application.properties file
   ```bash
   openweathermap.api.key=your_api_key_here
6. Run the application using your IDE's built-in server or a local Tomcat server.
7. Access the application in your web browser at
   ```bash
   http://localhost:8080/weather/index.html
