package constants.toursPage;

import lombok.Getter;

@Getter
public enum TourType {
    ANY_TYPE("Any Type"),
    CULTURAL("Cultural"),
    ADVENTURE("Adnventure"),
    WILDLIFE("Wildlife"),
    CITY_TOURS("City Tours"),
    BEACH("Beach"),
    HISTORICAL("Historical"),
    FOOD_AND_DRINK("Food & Drink"),
    SHOPPING("Shopping");

    private final String tourType;

    TourType(String tourType) {
        this.tourType = tourType;
    }
}
