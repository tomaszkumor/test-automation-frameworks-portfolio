package constants.common;

import lombok.Getter;

@Getter
public enum Location {
    BERLIN("Brandenburg", "Berlin", "Germany", "BER"),
    NEW_YORK_CITY_AA("All airports", "New York", "United States", "NYC");

    private final String airportName;
    private final String airportCity;
    private final String airportCountry;
    private final String airportCode;

    Location(String airportName, String airportCity, String airportCountry, String airportCode) {
        this.airportName = airportName;
        this.airportCity = airportCity;
        this.airportCountry = airportCountry;
        this.airportCode = airportCode;
    }
}
