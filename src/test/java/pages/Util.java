package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Util {
    private static final String SCROLL_INTO_VIEW = "arguments[0].scrollIntoView();";
    public static void waitForPresenceOfElementLocated(WebDriver driver, By locator) {
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(locator));
    }
    public static void waitForElementToBeClickable(WebDriver driver, By locator) {
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static void scrollToElement(WebDriver driver, By locator) {
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript(SCROLL_INTO_VIEW, element);
    }
    public static void clickElement(WebDriver driver, By locator) {
        scrollToElement(driver, locator);
        waitForPresenceOfElementLocated(driver, locator);
        driver.findElement(locator).click();
    }
}
