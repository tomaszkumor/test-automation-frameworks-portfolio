package constants.flightsPage;

import lombok.Getter;

@Getter
public enum FlightClass {
    ECONOMY("Economy"),
    ECONOMY_PREMIUM("Economy Premium"),
    BUSINESS("Business"),
    FIRST("First");

    private final String cabinClass;

    FlightClass(String cabinClass) {
        this.cabinClass = cabinClass;
    }
}
