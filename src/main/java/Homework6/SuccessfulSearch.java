package Homework6;

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

    public int amountSum() {
        return numberOfAllDiaries = Integer.parseInt(numberOfDiariesFound.getText());

    }

}
