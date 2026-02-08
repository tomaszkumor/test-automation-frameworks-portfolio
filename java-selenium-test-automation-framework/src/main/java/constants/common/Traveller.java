package constants.common;

import lombok.Getter;

@Getter
public enum Traveller {
    ADULTS("Adults"),
    CHILDS("Childs"),
    INFANTS("Infants");

    private final String traveller;

    Traveller(String traveller) {
        this.traveller = traveller;
    }
}
