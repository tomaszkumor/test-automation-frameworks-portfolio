package constants.hotelsPage;

import lombok.Getter;

@Getter
public enum CheckType {
    IN("Check in"),
    OUT("Check out");

    private final String checkTypeName;

    CheckType(String checkTypeName) {
        this.checkTypeName = checkTypeName;
    }
}
