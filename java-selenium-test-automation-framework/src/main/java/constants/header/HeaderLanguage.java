package constants.header;

import lombok.Getter;

@Getter
public enum HeaderLanguage {
    EN("English"),
    AR("Arabic"),
    TR("Turkish"),
    RU("Russian"),
    FR("French"),
    ZH("Chinese"),
    DE("Germany");

    private final String language;

    HeaderLanguage(String language) {
        this.language = language;
    }
}
