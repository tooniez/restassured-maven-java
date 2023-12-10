# Rest Assured exercise

This is a Maven project that utilizes RestAssured and JUnit for testing and reporting.

## Prerequisites

Before running this project, make sure you have the following installed:

- Java Development Kit (JDK)
- Apache Maven
- JUnit
- RestAssured
- Hamcrest

## Installation

1. Clone this repository to your locally.
2. Add Open Weather API key into `.env`
2. Install/Test the project using the Makefile: `make install`.


## Data Providers

The `/src/test/java/providers/CityProvider.java` returns a list of states used in this exercise.

```java
    public static List<String> getCities() {
        return Arrays.asList("Melbourne", "Manchester");
    }
```

## Endpoints

The `/src/test/java/weather/Endpoints.java` file contains 2 endpoints used in this exercise. The `Endpoints` extends from `Base.java` where we store the base url domain and versioning.

```java
    public static String getWeatherEndpoint(String city, String apiKey) {
        return "/weather?q=" + city + "&appid=" + apiKey;
    }

    public static String getAirPollutionEndpoint(double lat, double lon, String apiKey) {
        return "/air_pollution/forecast?lat=" + lat + "&lon=" + lon + "&appid=" + apiKey;
    }
```

## Tests

### Test config

A `BaseTest` class is used to extend on all tests. Currently its configured to load api key stored in `.env` file using a `@BeforeClass` decorator from `JUnit`

```
    protected static Dotenv dotenv;
    protected static String API_KEY;
```

### Test Cases

The `TestLatlon.java` is a simple test script mainly used to explore openweather api and the shape of data it returns.
