package constants.carsPage;

import lombok.Getter;

@Getter
public enum DateType {
    PICK_UP("Pick up"),
    DROP_OFF("Drop off");

    private final String dateTypeName;

    DateType(String dateTypeName) {
        this.dateTypeName = dateTypeName;
    }
}
