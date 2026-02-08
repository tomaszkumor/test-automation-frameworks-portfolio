package constants.flightsPage;

import lombok.Getter;

@Getter
public enum FlightType {
    ONE_WAY("One Way"),
    ROUND_TRIP("Round Trip");

    private final String flightDestination;

    FlightType(String flightDestiny) {
        this.flightDestination = flightDestiny;
    }
}
