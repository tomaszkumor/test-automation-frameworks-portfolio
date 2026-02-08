package constants.header;

import lombok.Getter;

@Getter
public enum HeaderDropDown {
//    SEARCHES("Searches"),
    LANGUAGE("Language"),
    CURRENCY("Currency"),
    AGENTS("Agents"),
    CUSTOMER("Customer");

    private final String name;

    HeaderDropDown(String name) {
        this.name = name;
    }
}
