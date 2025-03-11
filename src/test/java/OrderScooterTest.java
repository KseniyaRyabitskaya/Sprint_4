import ru.praktikum.services.qa.scooter.pom.MainPageScooterPOM;
import ru.praktikum.services.qa.scooter.pom.OrderScooterPOM;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.CoreMatchers.startsWith;

@RunWith(Parameterized.class)
public class OrderScooterTest {

    private final String name;
    private final String surname;
    private final String address;
    private final String metroStation;
    private final String mobilePhone;
    private final String date;
    private final String comment;
    private final Boolean isClickOnOrderTopButton;

    private final String url = "https://qa-scooter.praktikum-services.ru/";
    private WebDriver driver;

    private final String textFinalBlock = "Заказ оформлен";

    public OrderScooterTest(String name, String surname, String address, String metroStation, String mobilePhone, String date, String comment, Boolean isClickOnOrderTopButton) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metroStation = metroStation;
        this.mobilePhone = mobilePhone;
        this.date = date;
        this.comment = comment;
        this.isClickOnOrderTopButton = isClickOnOrderTopButton;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {
                        "Иван",
                        "Иванов",
                        "Москва, улица Большая Якиманка, 32",
                        "Октябрьская",
                        "+79237431122",
                        "13.03.2025",
                        "Пишите СМС",
                        true
                },
                {
                        "Петр",
                        "Александров",
                        "Москва, улица Александра Солженицына, 19",
                        "Марксистская",
                        "+79205460923",
                        "12.03.2025",
                        "Привозите скорее",
                        false
                },
                {
                        "Елена",
                        "Петухова",
                        "Москва, Красная площадь, 9",
                        "Охотный Ряд",
                        "+79999999999",
                        "11.03.2025",
                        "Не звонить",
                        false
                }
        };
    }

    @Test
    public void finishOrderBlockIsVisibleTest() {
        driver = WebDriverBrowser.getWebDriver(TypeBrowsers.CHROME);
        driver.get(url);
        MainPageScooterPOM mainPageScooterPOM = new MainPageScooterPOM(driver);
        OrderScooterPOM orderScooterPOM = new OrderScooterPOM(driver);
        orderScooterPOM.waitForLoadMainPage();
        mainPageScooterPOM.clickOrderButton(isClickOnOrderTopButton);
        orderScooterPOM.fillInformationFirstScreenOrder(name, surname, address, metroStation, mobilePhone);
        mainPageScooterPOM.ifNeedClickCookieButton();
        orderScooterPOM.clickButtonFirstContinue();
        orderScooterPOM.fillInformationSecondScreenOrder(date, comment);
        orderScooterPOM.clickFinish();
        String actualFinishBlockText = orderScooterPOM.geTextFinishBlock();
        MatcherAssert.assertThat(actualFinishBlockText, startsWith(textFinalBlock));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
