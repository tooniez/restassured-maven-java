package weather;

public class Endpoints extends Base{

    public static String getWeatherEndpoint(String city, String apiKey) {
        return "/weather?q=" + city + "&appid=" + apiKey;
    }

    public static String getAirPollutionEndpoint(double lat, double lon, String apiKey) {
        return "/air_pollution/forecast?lat=" + lat + "&lon=" + lon + "&appid=" + apiKey;
    }
}