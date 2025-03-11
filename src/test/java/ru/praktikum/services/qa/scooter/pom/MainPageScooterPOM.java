package ru.praktikum.services.qa.scooter.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MainPageScooterPOM {
    private WebDriver driver;

    // Главный экран
    private By mainScreen = By.cssSelector("[id='root']");

    // Кнопка заказа внизу главной страницы
    private By buttonOrderBottom = By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM");
    // Кнопка заказа вверху главной страницы
    private By buttonOrderTop = By.xpath(".//button[1][@class='Button_Button__ra12g']");
    // Кнопка принятия Cookie
    private By buttonCookieAccept = By.cssSelector("[id='rcc-confirm-button']");

    // 1 вопрос
    private By questionOrderPrice = By.cssSelector("[id='accordion__heading-0']");
    // Ответ на 1 вопрос
    private By answerOrderPrice = By.cssSelector("[id='accordion__panel-0']");

    // 2 вопрос
    private By questionNumberOfScooters = By.cssSelector("[id='accordion__heading-1']");
    // Ответ на 2 вопрос
    private By answerNumberOfScooters = By.cssSelector("[id='accordion__panel-1']");

    // 3 вопрос
    private By questionRentDuration = By.cssSelector("[id='accordion__heading-2']");
    // Ответ на 3 вопрос
    private By answerRentDuration = By.cssSelector("[id='accordion__panel-2']");

    // 4 вопрос
    private By questionTodayOrder = By.cssSelector("[id='accordion__heading-3']");
    // Ответ на 4 вопрос
    private By answerTodayOrder = By.cssSelector("[id='accordion__panel-3']");

    // 5 вопрос
    private By questionChangeRentDuration = By.cssSelector("[id='accordion__heading-4']");
    // Ответ на 5 вопрос
    private By answerChangeRentDuration = By.cssSelector("[id='accordion__panel-4']");

    // 6 вопрос
    private By questionChargeScooter = By.cssSelector("[id='accordion__heading-5']");
    // Ответ на 6 вопрос
    private By answerChargeScooter = By.cssSelector("[id='accordion__panel-5']");

    // 7 вопрос
    private By questioCancelOrder = By.cssSelector("[id='accordion__heading-6']");
    // Ответ на 7 вопрос
    private By answerCancelOrder = By.cssSelector("[id='accordion__panel-6']");

    // 8 вопрос
    private By questionDeliveryMKAD = By.cssSelector("[id='accordion__heading-7']");
    // Ответ на 8 вопрос
    private By answerDeliveryMKAD = By.cssSelector("[id='accordion__panel-7']");


    private List<By> listQuestion = List.of(questionOrderPrice, questionNumberOfScooters, questionRentDuration, questionTodayOrder, questionChangeRentDuration, questionChargeScooter, questioCancelOrder, questionDeliveryMKAD);
    private List<By> listAnswer = List.of(answerOrderPrice, answerNumberOfScooters, answerRentDuration, answerTodayOrder, answerChangeRentDuration, answerChargeScooter, answerCancelOrder, answerDeliveryMKAD);

    public MainPageScooterPOM(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForLoadMainPageScooter() {
        new WebDriverWait(driver, Duration.ofSeconds(3L)).until(
                driver -> (
                        driver.findElement(mainScreen) != null
                                && driver.findElement(mainScreen).isDisplayed()
                )
        );
    }

    public void clickOnQuestion(int numberOfQuestion) {
        driver.findElement(getQuestion(numberOfQuestion)).click();
    }

    public void scrollToQuestion(int numberOfQuestion) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(getQuestion(numberOfQuestion)));
    }

    public void waitForLoadAnswer(int numberOfAnswer) {
        new WebDriverWait(driver, Duration.ofSeconds(3L)).until(
                driver -> (
                        driver.findElement(getAnswer(numberOfAnswer)) != null
                                && driver.findElement(getAnswer(numberOfAnswer)).isDisplayed()
                )
        );
    }

    public String getTextFromAnswer(int numberOfAnswer) {
        return driver.findElement(getAnswer(numberOfAnswer)).getText();
    }

    public String getTextFromQuestion(int numberOfQuestion) {
        return driver.findElement(getQuestion(numberOfQuestion)).getText();
    }

    public void clickOrderButton(Boolean isClickOnTopButton) {
        if (isClickOnTopButton) {
            clickOnButtonDeliveryTop();
        } else {
            scrollToButtonDeliveryBottom();
            clickOnButtonDeliveryBottom();
        }
    }

    public void ifNeedClickCookieButton() {
        if (driver.findElement(buttonCookieAccept).isDisplayed()) driver.findElement(buttonCookieAccept).click();
    }

    private By getQuestion(int numberOfQuestion) {
        return listQuestion.get(numberOfQuestion);
    }

    private By getAnswer(int numberOfAnswer) {
        return listAnswer.get(numberOfAnswer);
    }

    private void scrollToButtonDeliveryBottom() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(buttonOrderBottom));
    }

    private void clickOnButtonDeliveryTop() {
        driver.findElement(buttonOrderTop).click();
    }

    private void clickOnButtonDeliveryBottom() {
        driver.findElement(buttonOrderBottom).click();
    }

}

