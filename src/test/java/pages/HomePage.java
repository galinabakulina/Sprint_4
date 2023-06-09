package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;

public class HomePage {
    private final WebDriver driver;
    private static final String URL = "https://qa-scooter.praktikum-services.ru/";
    private static final By FAQ_SECTION = By.xpath("//div[contains(text(), 'Вопросы о важном')]");
    private static final By FAQ_ITEM = By.className("accordion__item");
    private static final By QUESTION = By.className("accordion__button");
    private static final By ANSWER = By.className("accordion__panel");
    private static final By COOKIE_BUTTON = By.className("App_CookieButton__3cvqF");
    private static final By TOP_ORDER_BUTTON = By.className("Button_Button__ra12g");
    private static final By MIDDLE_ORDER_BUTTON = By.cssSelector("div.Home_FinishButton__1_cWm > button");
    private static final By MAIN_PAGE = By.className("Home_Header__iJKdX");
    private static final By YANDEX_LOGO = By.cssSelector("img[alt='Yandex']");
    private static final By STATUS_BUTTON = By.className("Header_Link__1TAG7");
    private static final By GO_BUTTON = By.xpath(".//button[contains(text(), 'Go')]");
    private static final By STATUS_FIELD= By.xpath(".//input[@placeholder='Введите номер заказа']");
    private static final By NOT_FOUND_IMG = By.cssSelector("img[alt='Not found']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void openMaximizeAndAcceptCookies(){
        driver.get(URL);
        driver.manage().window().maximize();
        Util.clickElement(driver, COOKIE_BUTTON);
    }

    public void clickTopOrderButton(){
        Util.clickElement(driver, TOP_ORDER_BUTTON);
    }

    public void clickMiddleOrderButton(){
        Util.clickElement(driver, MIDDLE_ORDER_BUTTON);
    }

    public void clickYandexLogo(){
        Util.clickElement(driver, YANDEX_LOGO);
    }

    public String getPageHeaderText(){
        return driver.findElement(MAIN_PAGE).getText();
    }

    public void clickStatusButton(){
        Util.clickElement(driver, STATUS_BUTTON);
    }

    public void waitForStatusField(){
        Util.waitForElementToBeClickable(driver, STATUS_FIELD);
    }

    public void waitForFoundImage()
    {
        Util.waitForPresenceOfElementLocated(driver, NOT_FOUND_IMG);
    }
    public void enterOrderNumber(String orderNumber){
        driver.findElement(STATUS_FIELD).clear();
        driver.findElement(STATUS_FIELD).sendKeys(orderNumber);
    }
    public void clickGoButton(){
        Util.clickElement(driver, GO_BUTTON);
    }

    public boolean isNotFoundImageDisplayed(){
        return driver.findElement(NOT_FOUND_IMG).isDisplayed();
    }

    public List<List<String>> getAllQuestionAndAnswers() {
        Util.scrollToElement(driver, FAQ_SECTION);
        List<List<String>> result = new ArrayList<>();
        List<WebElement> elements = driver.findElements(FAQ_ITEM);
        for (WebElement e : elements) {
            String question = e.findElement(QUESTION).getText();
            e.findElement(QUESTION).click();
            Util.waitForPresenceOfElementLocated(driver, ANSWER);
            String answer = e.findElement(ANSWER).getText();
            result.add(Arrays.asList(question, answer));
        }
        return result;
    }
}