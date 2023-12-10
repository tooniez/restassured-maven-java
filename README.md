# Rest Assured exercise

[![ci runs](https://github.com/tooniez/restassured-maven-java/actions/workflows/ci.yml/badge.svg)](https://github.com/tooniez/restassured-maven-java/actions/workflows/ci.yml)

This is a Maven project that utilizes RestAssured and JUnit to evaluate how to achieve some tasks in `./.idea/notes.md` 

The final test script that demonstrates the tasks is in - `./src/test/java/TestAirQuality.java`


## Prerequisites

Before running this project, make sure you have the following installed:

- Java Development Kit (JDK)
- Apache Maven

## Installation

1. Clone this repository to your locally.
2. Add Open Weather API key into `.env`
2. Install/Test the project using the Makefile: `make install`.


## Data Providers

The `/src/test/java/providers/CityProvider.java` returns a list of cities used.

```java
    public static List<String> getCities() {
        return Arrays.asList("Melbourne", "Manchester");
    }
```

## Endpoints

The `/src/test/java/weather/Endpoints.java` contains 2 endpoints used in this exercise. The `Endpoints` extends from `Base.java` where we store the base url domain and versioning.

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

A `./src/test/java/BaseTest.java` class should be used to extend on all tests. Currently its configured to load api_key stored in `.env` file using a `@BeforeClass` decorator.

```
    protected static Dotenv dotenv;
    protected static String API_KEY;
```

### Test Cases

The `./src/test/java/TestLatlon.java` is a simple test script used to explore openweather api and see the shape of data it returns.

The `./src/test/java/TestAirQuality.java` contains flow where it:
1. Uses weatherEndpoint to get lat lon response for each city
2. Uses airPollutionEndpoint to get air quality (agi) using lat lon
3. Checks to see if the captured agi is greater or equal to the threshold we check for (2)

```shell
[INFO] Running TestAirQuality
Coordinates for Melbourne: 28.0836, -80.6081
AQI for Melbourne: 2
City with AQI 2 or above: Melbourne, AQI: 2
Coordinates for Manchester: 53.4809, -2.2374
AQI for Manchester: 1
City with AQI below 2: Manchester, AQI: 1
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 6.494 s -- in TestAirQuality
```

### Test Reporting

The project is currently configured to use junit with the `surefire` plugin to save the xml report. Reports are stored under `./reports`

A CI workflow is also configured in this project for reporting/triggers
