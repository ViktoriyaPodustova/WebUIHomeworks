package Homework6;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SuccessfulSearch extends BaseView {
    public int numberOfAllDiaries;
    public ListFindedDiaries listFindedDiaries;
    public AdvancedSearch advancedSearch;

    public SuccessfulSearch(WebDriver driver) {
        super(driver);
        advancedSearch = new AdvancedSearch(driver);
        listFindedDiaries = new ListFindedDiaries(driver);
    }

    @FindBy(xpath = "//h1[contains(.,'Найдено')]/span")
    public WebElement numberOfDiariesFound;

    @Step("Приведение к числовому значению строки с колличеством найденных дневников")
    public int amountSum() {
        return numberOfAllDiaries = Integer.parseInt(numberOfDiariesFound.getText());

    }

}
