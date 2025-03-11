import ru.praktikum.services.qa.scooter.pom.MainPageScooterPOM;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;


@RunWith(Parameterized.class)
public class DropDownQuestionsTest {

    private final String expectedQuestion;
    private final String expectedAnswer;
    private final int numberOfQuestion;

    private WebDriver driver;
    private final String url = "https://qa-scooter.praktikum-services.ru/";


    public DropDownQuestionsTest(String expectedQuestion, String expectedAnswer, int numberOfQuestion) {
        this.expectedQuestion = expectedQuestion;
        this.expectedAnswer = expectedAnswer;
        this.numberOfQuestion = numberOfQuestion;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {
                        "Сколько это стоит? И как оплатить?",
                        "Сутки — 400 рублей. Оплата курьеру — наличными или картой.",
                        0
                },
                {
                        "Хочу сразу несколько самокатов! Так можно?",
                        "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.",
                        1
                },
                {
                        "Как рассчитывается время аренды?",
                        "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.",
                        2
                },
                {
                        "Можно ли заказать самокат прямо на сегодня?",
                        "Только начиная с завтрашнего дня. Но скоро станем расторопнее.",
                        3
                },
                {
                        "Можно ли продлить заказ или вернуть самокат раньше?",
                        "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.",
                        4
                },
                {
                        "Вы привозите зарядку вместе с самокатом?",
                        "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.",
                        5
                },
                {
                        "Можно ли отменить заказ?",
                        "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.",
                        6
                },
                {
                        "Я жизу за МКАДом, привезёте?",
                        "Да, обязательно. Всем самокатов! И Москве, и Московской области.",
                        7
                },
        };
    }

    @Test
    public void questionsAndAnswersIsOkTest() {
        driver = WebDriverBrowser.getWebDriver(TypeBrowsers.CHROME);
        driver.get(url);
        MainPageScooterPOM mainPageScooterPOM = new MainPageScooterPOM(driver);
        mainPageScooterPOM.waitForLoadMainPageScooter();
        mainPageScooterPOM.scrollToQuestion(numberOfQuestion);
        String actualQuestion = mainPageScooterPOM.getTextFromQuestion(numberOfQuestion);
        assertEquals(expectedQuestion, actualQuestion);
        mainPageScooterPOM.clickOnQuestion(numberOfQuestion);
        mainPageScooterPOM.waitForLoadAnswer(numberOfQuestion);
        String actualAnswer = mainPageScooterPOM.getTextFromAnswer(numberOfQuestion);
        assertEquals(expectedAnswer, actualAnswer);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}