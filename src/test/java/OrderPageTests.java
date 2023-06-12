import static org.junit.Assert.assertTrue;

import org.junit.Test;
import pages.OrderPage;

public class OrderPageTests extends TestBase {
    @Test
    public void orderDetailsNotCorrectCannotMakeOrder () {
        String wrongInput = "0";

        OrderPage orderPage = new OrderPage(driver);
        homePage.clickTopOrderButton();
        orderPage.fillPersonalDetailsForm(wrongInput,wrongInput,wrongInput,wrongInput,wrongInput);
        orderPage.clickNextButton();

        // NEXT_BUTTON (Далее) все еще видна, так как в форме ошибки
        assertTrue(orderPage.isNextButtonDisplayed());
    }

    @Test
    public void enterWrongOrderNumberGetMessage()  {
        String wrongOrderNumber = "0";

        homePage.clickStatusButton();
        homePage.waitForStatusField();
        homePage.enterOrderNumber(wrongOrderNumber);
        homePage.clickGoButton();
        homePage.waitForFoundImage();
        //Ожидаем, что при вводе неправильного номера заказа появится картинка с надписью "Такого заказа нет"
        assertTrue(homePage.isNotFoundImageDisplayed());
    }
}
