package constants.hotelsPage;

import lombok.Getter;

@Getter
public enum Accommodation {
    ROOMS("Rooms"),
    ADULTS("Adults"),
    CHILDS("Childs");

    private final String accommodationName;

    Accommodation(String accommodationName) {
        this.accommodationName = accommodationName;
    }
}
