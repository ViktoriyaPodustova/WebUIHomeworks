package Homework6;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DiarySearch extends BaseView {

    public DiarySearch(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "listsearchform-online")
    public WebElement checkBoxSearchOnline;

    @FindBy(id = "form_search_list_submit")
    public WebElement searchButton;

    @Step("Нажать на чекбокс 'Искать тех, кто онлайн' и выполнить поиск по кнопке 'Найти' ")
    public SuccessfulSearch searchPeopleOnline() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(checkBoxSearchOnline));
        checkBoxSearchOnline.click();
        searchButton.click();
        webDriverWait.until(ExpectedConditions.urlContains("ListSearchForm"));
        return new SuccessfulSearch(driver);
    }
}
