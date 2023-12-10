import io.github.cdimascio.dotenv.Dotenv;
import org.junit.BeforeClass;

public class BaseTest {
    protected static Dotenv dotenv;
    protected static String API_KEY;
    protected static final int STATUS_OK = 200;

    @BeforeClass
    public static void setup() {
        dotenv = Dotenv.load();
        API_KEY = dotenv.get("API_KEY");
    }
}