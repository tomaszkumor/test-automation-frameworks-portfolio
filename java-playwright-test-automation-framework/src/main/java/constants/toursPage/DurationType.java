package constants.toursPage;

import lombok.Getter;

@Getter
public enum DurationType {
    ANY_DURATION("Any Duration"),
    DAY_1("1 Day"),
    DAYS_2_TO_3("2-3 Days"),
    DAYS_4_TO_7("4-7 Days"),
    DAYS_15_PLUS("15+ Days"),
    WEEKS_1_TO_2("1-2 Weeks");

    private final String durationName;

    DurationType(String durationName) {
        this.durationName = this.name();
    }
}
