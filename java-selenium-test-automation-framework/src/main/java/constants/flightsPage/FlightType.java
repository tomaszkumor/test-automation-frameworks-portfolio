package constants.flightsPage;

import lombok.Getter;

@Getter
public enum FlightType {
    ONE_WAY("One Way"),
    RETURN("Return");

    private final String flightDestination;

    FlightType(String flightDestiny) {
        this.flightDestination = flightDestiny;
    }
}
