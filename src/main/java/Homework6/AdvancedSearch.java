package Homework6;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AdvancedSearch extends BaseView {
    public AdvancedSearch(WebDriver driver) {
        super(driver);
    }

    public String searchResults;
    @FindBy(id = "extend_search")
    public WebElement extendSearchButton;

    @FindBy(id = "listsearchform-sex")
    public WebElement genderSelection;

    @FindBy(xpath = "//select[@name='ListSearchForm[sex]']/option")
    public List<WebElement> genderOption;

    @FindBy(id = "listsearchform-from_age")
    public WebElement fromAge;

    @FindBy(id = "listsearchform-to_age")
    public WebElement toAge;

    @FindBy(id = "form_search_list_submit")
    public WebElement searchButton;

    public SuccessfulSearch advancedSearchFromOnline(String gender, String ageFrom, String ageTo) throws InterruptedException {
        extendSearchButton.click();
        genderOption.stream().filter(g -> g.getText().contains(gender)).findFirst().get().click();
        fromAge.sendKeys(ageFrom);
        toAge.sendKeys(ageTo);
        searchButton.click();
        Thread.sleep(5000);
        return new SuccessfulSearch(driver);
    }
}
