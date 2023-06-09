import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.OrderPage;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class HomePageTests extends TestBase {
    private void waitNewWindowOpens() {
        new WebDriverWait(driver, 20).until(ExpectedConditions.numberOfWindowsToBe(2));
    }

    @Test
    public void faqSection(){
        List<List<String>> expected = asList(
                asList("Сколько это стоит? И как оплатить?","Сутки — 400 рублей. Оплата курьеру — наличными или картой."),
                asList("Хочу сразу несколько самокатов! Так можно?","Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."),
                asList("Как рассчитывается время аренды?","Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."),
                asList("Можно ли заказать самокат прямо на сегодня?","Только начиная с завтрашнего дня. Но скоро станем расторопнее."),
                asList("Можно ли продлить заказ или вернуть самокат раньше?","Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."),
                asList("Вы привозите зарядку вместе с самокатом?","Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."),
                asList("Можно ли отменить заказ?","Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."),
                asList("Я жизу за МКАДом, привезёте?","Да, обязательно. Всем самокатов! И Москве, и Московской области."));

        List<List<String>> actual = homePage.getAllQuestionAndAnswers();
        assertEquals(expected, actual);
    }

    @Test
    public void clickScooterLogoGoMainPage(){
        // В этом тесте сначала уходим с главной страница на страниу заказа.
        // Далее тест проверяет, что если нажать на лого "Самокат", то возвращаемся на главную страницу.
        homePage.clickTopOrderButton();

        OrderPage orderPage = new OrderPage(driver);
        orderPage.waitForNextButton();
        orderPage.clickScooterLogo();

        // На главной странице в заголовке есть текст "на пару дней"
        String expected = "на пару дней";
        String actual = homePage.getPageHeaderText();
        assertThat(actual, containsString(expected));
    }

    @Test
    public void clickYandexLogoOpenYandexPage(){
        String expected = "Дзен";
        homePage.clickYandexLogo();
        waitNewWindowOpens();
        driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());
        new WebDriverWait(driver, 10).until(ExpectedConditions.titleIs(expected));
        String actual = driver.getTitle();
        assertEquals(expected, actual);
    }
}
