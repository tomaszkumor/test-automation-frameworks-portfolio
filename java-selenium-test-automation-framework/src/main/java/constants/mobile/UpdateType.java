package constants.mobile;

import lombok.Getter;

@Getter
public enum UpdateType {
    DAY("day", "daily"),
    WEEK("week", "weekly"),
    MONTH("month", "monthly");

    private final String updateTypeNoun;
    private final String updateTypeAdjective;


    UpdateType(String updateType, String updateTypeAdjective) {
        this.updateTypeNoun = updateType;
        this.updateTypeAdjective = updateTypeAdjective;
    }
}
