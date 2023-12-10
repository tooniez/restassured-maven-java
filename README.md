# Rest Assured exercise

This is a Maven project that utilizes RestAssured and JUnit for testing and reporting.

## Prerequisites

Before running this project, make sure you have the following installed:

- Java Development Kit (JDK)
- Apache Maven

## Endpoints

The `/src/test/java/weather/Endpoints.java` file contains 2 endpoints used in this exercise. The `Endpoints` extends from `Base.java` where it contains the base url domain and versioning.

```java
    public static String getWeatherEndpoint(String city, String apiKey) {
        return "/weather?q=" + city + "&appid=" + apiKey;
    }

    public static String getAirPollutionEndpoint(double lat, double lon, String apiKey) {
        return "/air_pollution/forecast?lat=" + lat + "&lon=" + lon + "&appid=" + apiKey;
    }
```
