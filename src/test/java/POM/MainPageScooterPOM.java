package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MainPageScooterPOM {
    private WebDriver driver;

    private By mainScreen = By.cssSelector("[id='root']");

    private By question1 = By.cssSelector("[id='accordion__heading-0']");
    private By answer1 = By.cssSelector("[id='accordion__panel-0']");

    private By question2 = By.cssSelector("[id='accordion__heading-1']");
    private By answer2 = By.cssSelector("[id='accordion__panel-1']");

    private By question3 = By.cssSelector("[id='accordion__heading-2']");
    private By answer3 = By.cssSelector("[id='accordion__panel-2']");

    private By question4 = By.cssSelector("[id='accordion__heading-3']");
    private By answer4 = By.cssSelector("[id='accordion__panel-3']");

    private By question5 = By.cssSelector("[id='accordion__heading-4']");
    private By answer5 = By.cssSelector("[id='accordion__panel-4']");

    private By question6 = By.cssSelector("[id='accordion__heading-5']");
    private By answer6 = By.cssSelector("[id='accordion__panel-5']");

    private By question7 = By.cssSelector("[id='accordion__heading-6']");
    private By answer7 = By.cssSelector("[id='accordion__panel-6']");

    private By question8 = By.cssSelector("[id='accordion__heading-7']");
    private By answer8 = By.cssSelector("[id='accordion__panel-7']");

    private List<By> listQuestion = List.of(question1, question2, question3, question4, question5, question6, question7, question8);
    private List<By> listAnswer = List.of(answer1, answer2, answer3, answer4, answer5, answer6, answer7, answer8);

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

    private By getQuestion(int numberOfQuestion) {
        return listQuestion.get(numberOfQuestion);
    }

    private By getAnswer(int numberOfAnswer) {
        return listAnswer.get(numberOfAnswer);
    }

}

