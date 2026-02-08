package constants.visaPage;

import lombok.Getter;

@Getter
public enum ProcessingSpeedType {
    STANDARD("Standard"),
    EXPRESS("Express"),
    RUSH("Rush"),
    SUPER_RUSH("Super Rush");

    private final String processingSpeedType;

    ProcessingSpeedType(String processingSpeedType) {
        this.processingSpeedType = processingSpeedType;
    }
}
