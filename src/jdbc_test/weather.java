package jdbc_test;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class weather {
    private static final String API_KEY = "your_api_key"; // Replace with your actual OpenWeatherMap API key

    public static void main(String[] args) {
        String city = "New York";
        String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + API_KEY;

        try {
            // Make the API request
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Check for successful response
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Read the response
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // Parse JSON response
                JSONObject weatherData = new JSONObject(response.toString());
                displayWeather(weatherData);
            } else {
                System.out.println("Failed to retrieve weather data. HTTP Response Code: " + responseCode);
            }
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void displayWeather(JSONObject weatherData) {
        if (weatherData != null) {
            System.out.println("Current Weather:");
            System.out.println("City: " + weatherData.getString("name"));
            System.out.println("Temperature: " + weatherData.getJSONObject("main").getDouble("temp") + " K");
            System.out.println("Description: " + weatherData.getJSONArray("weather").getJSONObject(0).getString("description"));
            System.out.println("Wind Speed: " + weatherData.getJSONObject("wind").getDouble("speed") + " m/s");
        } else {
            System.out.println("Failed to retrieve weather data.");
        }
    }
}