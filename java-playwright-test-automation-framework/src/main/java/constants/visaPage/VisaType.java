package constants.visaPage;

import lombok.Getter;

@Getter
public enum VisaType {
    TOURIST("Tourist Visa"),
    BUSINESS("Business Visa"),
    STUDENT("Student Visa"),
    WORK("Work Visa"),
    TRANSIT("Transit Visa"),
    MEDICAL("Medical Visa");

    private final String visaType;

    VisaType(String visaType) {
        this.visaType = visaType;
    }
}
