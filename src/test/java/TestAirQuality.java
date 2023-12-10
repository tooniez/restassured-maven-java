import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import providers.CityProvider;
import weather.Endpoints;
import java.util.List;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
public class TestAirQuality extends BaseTest {
    @Test
    public void testAirQuality() {

        List<String> cities = CityProvider.getCities();

        for (String city : cities) {
            // Get weather data for cities
            Response weatherResponse = RestAssured.given()
                    .when()
                    .get(Endpoints.getBaseUrl() + Endpoints.getWeatherEndpoint(city, API_KEY));

            System.out.println(weatherResponse.getBody().asString());
            // Extract lat and lon for city
            JSONObject weatherData = new JSONObject(weatherResponse.getBody().asString());
            double lat = weatherData.getJSONObject("coord").getDouble("lat");
            double lon = weatherData.getJSONObject("coord").getDouble("lon");

            // Check if status code is 200
            if (weatherResponse.getStatusCode() != STATUS_OK) {
                System.out.println("Error: " + weatherResponse.getStatusCode());
                return;
            } else {
                System.out.println("Coordinates for " + city + ": " + lat + ", " + lon);
            }

            // Test for air pollution data
                given()
                    // .log()
                    // .all()
                    .when()
                    .get(Endpoints.getBaseUrl() + Endpoints.getAirPollutionEndpoint(lat, lon, API_KEY))
                    .then()
                    .assertThat()
                    .statusCode(STATUS_OK)
                    .body("list", hasSize(greaterThan(0)))
                    .body("list[0].main", hasKey("aqi"))
                    .log()
                    .body();

            // Save air pollution data
            Response airPollutionResponse = RestAssured.given()
                    .when()
                    .get(Endpoints.getBaseUrl() + Endpoints.getAirPollutionEndpoint(lat, lon, API_KEY));

            JSONArray airPollutionData = new JSONObject(airPollutionResponse.getBody().asString()).getJSONArray("list");
            // System.out.println(airPollutionData.toString());

            // Store aqi value in a variable
            int aqi = airPollutionData.getJSONObject(0).getJSONObject("main").getInt("aqi");
            System.out.println("AQI for " + city + ": " + aqi);
        }
    }
}