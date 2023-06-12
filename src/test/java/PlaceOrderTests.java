import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.OrderPage;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class PlaceOrderTests extends TestBase {
    private String expected = "Заказ оформлен";
    private String actual;
    private final String name;
    private final String surname;
    private final String address;
    private final String subway;
    private final String phone;
    private final String date;
    private final String period;
    private final String colour;
    private final String comment;

    public PlaceOrderTests(String name, String surname, String address,
                           String subway, String phone, String date,
                           String period, String colour, String comment) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.subway = subway;
        this.phone = phone;
        this.date = date;
        this.period = period;
        this.colour = colour;
        this.comment = comment;
    }

    private void placeOrder() {
        OrderPage orderPage = new OrderPage(driver);
        orderPage.fillPersonalDetailsForm(name, surname, address, subway, phone);
        orderPage.clickNextButton();
        orderPage.waitForBackButton();
        orderPage.fillRentDetailsForm(date, period, colour, comment);
        orderPage.clickOrderButton();
        orderPage.waitForCancelButton();
        orderPage.clickFinishButton();
        orderPage.waitForOrderMessage();

        actual = orderPage.getOrderMessage().split("\n")[0];
        assertEquals(expected, actual);
    }

    @Parameterized.Parameters
    public static Object[][] orderDetails() {

        return new Object[][] {
                {"Галина", "Бакулина", "Ходынский бульвар, 13-219", "Полежаевская", "+79031111111", "20.06.2023", "двое суток", "black", "Не звоните, напишите смс, пожалуйста"},
                {"Василий", "Кравцов", "Пресненская набережная, 12", "Деловой центр", "+79261111111", "01.07.2023", "сутки", "grey", ""}
        };
    }

    @Test
    public void topOrderButtonTest() {
        homePage.clickTopOrderButton();
        placeOrder();
    }

    @Test
    public void middleOrderButtonTest() {
        homePage.clickMiddleOrderButton();
        placeOrder();
    }
}
