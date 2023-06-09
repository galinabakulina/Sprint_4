package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPage {
    private WebDriver driver;
    private final By NAME_FIELD= By.xpath(".//input[@placeholder='* Имя']");
    private final By SURNAME_FIELD= By.xpath(".//input[@placeholder='* Фамилия']");
    private final By ADDRESS_FIELD= By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By SUBWAY_FIELD= By.xpath(".//input[@placeholder='* Станция метро']");
    private final String SUBWAY_ST = ".//div[contains(text(), '";
    private final String SUBWAY_END = "')]";
    private final By PHONE_FIELD= By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By NEXT_BUTTON = By.xpath(".//button[contains(text(), 'Далее')]");
    private final By BACK_BUTTON = By.xpath(".//button[contains(text(), 'Назад')]");
    private final By FINISH_ORDER_BUTTON = By.xpath(".//button[contains(text(), 'Да')]");
    private final By CANCEL_ORDER_BUTTON = By.xpath(".//button[contains(text(), 'Нет')]");
    private final By ORDER_BUTTON = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    private final By DATE_FIELD = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private final By RENT_PERIOD_FIELD = By.className("Dropdown-root");
    private final By RENT_PERIOD_MENU = By.className("Dropdown-menu");
    private final String RENT_PERIOD_ST = ".//div[@role='option' and text() = '";
    private final String RENT_PERIOD_END = "']";
    private final By COMMENT_FIELD = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    private final By MESSAGE = By.xpath(".//div[contains(text(), 'Заказ оформлен')]");
    private static final By SCOOTER_LOGO = By.cssSelector("img[alt='Scooter']");

    public OrderPage(WebDriver driver){
        this.driver = driver;
    }

    public void waitForNextButton(){
        Util.waitForPresenceOfElementLocated(driver, NEXT_BUTTON);
    }

    public void clickScooterLogo(){
        Util.clickElement(driver, SCOOTER_LOGO);
    }

    public void clickNextButton(){
        Util.clickElement(driver, NEXT_BUTTON);
    }

    public boolean isNextButtonDisplayed(){
        return driver.findElement(NEXT_BUTTON).isDisplayed();
    }

    public void waitForBackButton(){
        Util.waitForPresenceOfElementLocated(driver, BACK_BUTTON);
    }

    public void clickOrderButton(){
        Util.clickElement(driver, ORDER_BUTTON);
    }

    public void waitForCancelButton(){
        Util.waitForPresenceOfElementLocated(driver, CANCEL_ORDER_BUTTON);
    }

    public void clickFinishButton(){
        Util.clickElement(driver, FINISH_ORDER_BUTTON);
    }
    public void waitForOrderMessage(){
        Util.waitForPresenceOfElementLocated(driver, MESSAGE);
    }

    public String getOrderMessage(){
        return driver.findElement(MESSAGE).getText();
    }

    public void waitForPresenceOfElementLocated(By locator) {
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    private void fillName(String name) {
        driver.findElement(NAME_FIELD).clear();
        driver.findElement(NAME_FIELD).sendKeys(name);
    }

    private void fillSurname(String surname) {
        driver.findElement(SURNAME_FIELD).clear();
        driver.findElement(SURNAME_FIELD).sendKeys(surname);
    }

    private void fillAddress(String address) {
        driver.findElement(ADDRESS_FIELD).clear();
        driver.findElement(ADDRESS_FIELD).sendKeys(address);
    }

    private void fillSubway(String subway) {

        driver.findElement(SUBWAY_FIELD).clear();
        driver.findElement(SUBWAY_FIELD).sendKeys(subway);
        By subwayLocator = By.xpath(SUBWAY_ST + subway + SUBWAY_END);
        driver.findElement(subwayLocator).click();
    }

    private void fillPhone(String phone) {
        driver.findElement(PHONE_FIELD).clear();
        driver.findElement(PHONE_FIELD).sendKeys(phone);
    }

    private void fillDate(String date) {
        driver.findElement(DATE_FIELD).clear();
        driver.findElement(DATE_FIELD).sendKeys(date);
        driver.findElement(DATE_FIELD).sendKeys(Keys.ENTER);
    }

    private void fillRentPeriod(String period) {
        driver.findElement(RENT_PERIOD_FIELD).click();
        waitForPresenceOfElementLocated(RENT_PERIOD_MENU);
        By rentPeriodLocator = By.xpath(RENT_PERIOD_ST + period + RENT_PERIOD_END);
        driver.findElement(rentPeriodLocator).click();
    }

    private void fillColour(String colour) {
        driver.findElement(By.id(colour)).click();
    }

    private void fillComment(String comment) {
        driver.findElement(COMMENT_FIELD).clear();
        driver.findElement(COMMENT_FIELD).sendKeys(comment);
    }

    public void fillPersonalDetailsForm(String name, String surname, String address,
                      String subway, String phone){
        fillName(name);
        fillSurname(surname);
        fillAddress(address);
        fillSubway(subway);
        fillPhone(phone);
    }
    public void fillRentDetailsForm(String date,String period, String colour, String comment){
        fillDate(date);
        fillRentPeriod(period);
        fillColour(colour);
        fillComment(comment);
    }
}