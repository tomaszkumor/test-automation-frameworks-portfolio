package constants.header;

import lombok.Getter;

@Getter
public enum HeaderEntrypoint {
    LANDING_PAGE("Landing page"),
    FLIGHTS("Flights"),
    STAYS("Stays"),
    TOURS("Tours"),
    CARS("Cars"),
    VISA("Visa"),
    BLOGS("Blogs");

    private final String name;

    HeaderEntrypoint(String name) {
        this.name = name;
    }
}
