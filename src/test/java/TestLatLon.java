import org.junit.Test;
import providers.CityProvider;
import java.util.List;
public class TestLatLon extends BaseTest{

    @Test
    public void testLatLon() {
        // Get weather data for Melbourne and Manchester from CityProvider
        List<String> cities = CityProvider.getCities();

    }
}