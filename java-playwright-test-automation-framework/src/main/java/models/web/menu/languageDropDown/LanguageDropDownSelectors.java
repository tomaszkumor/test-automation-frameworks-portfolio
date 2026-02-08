package models.web.menu.languageDropDown;

import models.web.navigation.NavigationHeaderAndFooter;

public class LanguageDropDownSelectors extends NavigationHeaderAndFooter {
    String languageDropDownSelector = "//div[@class = 'nav-item--right']//li[contains(@class, 'nav-item')][1]//ul[contains(@class, 'dropdown-menu')]";
    String englishLanguageButtonSelector = "//a[contains(@href, 'English')]";
    String arabicLanguageButtonSelector = "//a[contains(@href, 'Arabic')]";
    String turkishLanguageButtonSelector = "//a[contains(@href, 'Turkish')]";
    String russianLanguageButtonSelector = "//a[contains(@href, 'Russian')]";
    String frenchLanguageButtonSelector = "//a[contains(@href, 'French')]";
    String chineseLanguageButtonSelector = "//a[contains(@href, 'Chinese')]";
    String germanLanguageButtonSelector = "//a[contains(@href, 'German')]";
    String languagesSelector = "//a[contains(@href, 'language')]/span";
}
