package constants.staysPage;

import lombok.Getter;

@Getter
public enum Accommodation {
    ROOMS("Rooms"),
    ADULTS("Adults"),
    CHILDREN("Childs");

    private final String accommodationName;

    Accommodation(String accommodationName) {
        this.accommodationName = accommodationName;
    }
}
