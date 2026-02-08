package constants.common;

import lombok.Getter;

@Getter
public enum LocationType {
    DEPARTURE("Departure"),
    ARRIVAL("Arrival");

    private final String locationTypeName;

    LocationType(String locationTypeName) {
        this.locationTypeName = locationTypeName;
    }
}
