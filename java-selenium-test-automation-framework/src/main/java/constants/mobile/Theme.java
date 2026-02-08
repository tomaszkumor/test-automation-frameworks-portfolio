package constants.mobile;

import lombok.Getter;

@Getter
public enum Theme {
    LIGHT("Light"),
    SEPIA("Sepia"),
    DARK("Dark"),
    BLACK("Black");

    private final String name;

    Theme(String name) {
        this.name = name;
    }
}
