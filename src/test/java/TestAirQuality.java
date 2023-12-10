import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import providers.CityProvider;
import weather.Endpoints;
import java.util.List;
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

        }
    }
}