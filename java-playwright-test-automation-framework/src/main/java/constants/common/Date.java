package constants.common;

import lombok.Getter;

@Getter
public enum Date {
    YEAR("year"),
    MONTH("month"),
    DAY("day");

    private final String name;

    Date(String name) {
        this.name = name;
    }
}
