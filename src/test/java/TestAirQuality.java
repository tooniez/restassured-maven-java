import providers.CityProvider;
import weather.Endpoints;
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
        }
    }
}