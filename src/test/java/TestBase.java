import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.HomePage;

public class TestBase {
    protected HomePage homePage;
    protected WebDriver driver;

    @Before
    public void beforeTest() {
        driver = new ChromeDriver();
        //driver = new FirefoxDriver();
        homePage = new HomePage(driver);
        homePage.openMaximizeAndAcceptCookies();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
