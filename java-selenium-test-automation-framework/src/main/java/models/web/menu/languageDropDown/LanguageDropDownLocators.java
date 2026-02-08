package models.web.menu.languageDropDown;

import models.web.navigation.NavigationHeaderAndFooter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class LanguageDropDownLocators extends NavigationHeaderAndFooter {
    @FindBy(xpath = "//div[@class = 'nav-item--right']//li[contains(@class, 'nav-item')][1]//ul[contains(@class, 'dropdown-menu')]")
    WebElement languageDropDown;
    @FindBy(xpath = "//a[contains(@href, 'English')]")
    WebElement englishLanguageButton;
    @FindBy(xpath = "//a[contains(@href, 'Arabic')]")
    WebElement arabicLanguageButton;
    @FindBy(xpath = "//a[contains(@href, 'Turkish')]")
    WebElement turkishLanguageButton;
    @FindBy(xpath = "//a[contains(@href, 'Russian')]")
    WebElement russianLanguageButton;
    @FindBy(xpath = "//a[contains(@href, 'French')]")
    WebElement frenchLanguageButton;
    @FindBy(xpath = "//a[contains(@href, 'Chinese')]")
    WebElement chineseLanguageButton;
    @FindBy(xpath = "//a[contains(@href, 'German')]")
    WebElement germanLanguageButton;
    @FindBy(xpath = "//a[contains(@href, 'language')]/span")
    List<WebElement> languages;
}
