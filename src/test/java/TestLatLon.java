import org.junit.Test;
import providers.CityProvider;
import java.util.List;
import weather.Endpoints;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class TestLatLon extends BaseTest{

    @Test
    public void testLatLon() {
        // Get for Melbourne and Manchester from CityProvider
        List<String> cities = CityProvider.getCities();

        for (String city : cities) {

            // Test for lat lon data
            given()
                // .log()
                // .all()
                .when()
                .get(Endpoints.getBaseUrl() + Endpoints.getWeatherEndpoint(city, API_KEY))
                .then()
                .assertThat()
                .statusCode(STATUS_OK)
                .body("coord", hasKey("lat"))
                .body("coord", hasKey("lon"))
                .log()
                .body();
                

        }
    }
}