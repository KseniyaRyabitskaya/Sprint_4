package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderScooterPOM {

    private WebDriver driver;

    private By mainScreen = By.cssSelector("[id='root']");
    private By buttonOrderBottom = By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM");
    private By buttonOrderTop = By.xpath(".//button[1][@class='Button_Button__ra12g']");
    private By buttonCookieAccept = By.cssSelector("[id='rcc-confirm-button']");

    private By titleFirstInformationScreen = By.cssSelector("[class='Order_Header__BZXOb']");
    private By nameField = By.xpath(".//input[@placeholder='* Имя']");
    private By surnameField = By.xpath(".//input[@placeholder='* Фамилия']");
    private By addressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private By metroStationField = By.xpath(".//input[@class='select-search__input']");
    private By metroStationItem = By.xpath(".//li[@data-index='0']");
    private By phoneField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private By buttonFirstContinue = By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM");

    private By titleSecondInformationScreen = By.xpath(".//div[@class='Order_Header__BZXOb']");
    private By dateDeliveryField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private By durationDropDownMenu = By.cssSelector(".Dropdown-placeholder");
    private By durationItemSeven = By.xpath(".//div[@class='Dropdown-menu']//div[7]");
    private By checkBoxBlackColor = By.xpath(".//label[@class='Checkbox_Label__3wxSf' and @for='black']");
    private By commentField = By.xpath(".//div[@class='Input_InputContainer__3NykH']//input[@class='Input_Input__1iN_Z Input_Responsible__1jDKN']");
    private By buttonSecondContinue = By.xpath(".//div[@class='Order_Buttons__1xGrp']//button[2]");

    private By cofirmOrderButton = By.xpath(".//div[@class='Order_Modal__YZ-d3']//div[@class='Order_Buttons__1xGrp']//button[2]");

    private By finishPopUpText = By.xpath(".//div[@class='Order_ModalHeader__3FDaJ']");

    public OrderScooterPOM(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOrderButton(Boolean isClickOnTopButton) {
        waitForLoadMainPage();
        if (isClickOnTopButton) {
            clickOnButtonDeliveryTop();
        } else {
            scrollToButtonDeliveryBottom();
            clickOnButtonDeliveryBottom();
        }
        waitForLoadFirstInformationScreen();
    }

    public void fillInformationFirstScreenOrder(String name, String surname, String address, String metroStation, String mobilePhone) {
        ifNeedClickCookieButton();
        setNameField(name);
        setSurnameField(surname);
        setAddressField(address);
        setMetroStationField(metroStation);
        clickMetroStationItem();
        setMobilePhoneField(mobilePhone);
        clickButtonFirstContinue();
        waitForLoadSecondInformationScreen();
    }

    public void fillInformationSecondScreenOrder(String date, String comment) {
        setDateDeliveryField(date);
        hideCalendar();
        clickDurationDropDownMenu();
        scrollToItem();
        clickOnItemDropDownMenu();
        clickCheckBoxColor();
        setCommentField(comment);
        clickButtonSecondContinue();
        waitForLoadConfirmationPopUp();
    }

    public void clickFinish() {
        driver.findElement(cofirmOrderButton).click();
    }

    public String geTextFinishBlock() {
        return driver.findElement(finishPopUpText).getText();
    }

    private void hideCalendar() {
        driver.findElement(titleSecondInformationScreen).click();
    }

    private void ifNeedClickCookieButton() {
        if (driver.findElement(buttonCookieAccept).isDisplayed()) driver.findElement(buttonCookieAccept).click();
    }

    private void scrollToButtonDeliveryBottom() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(buttonOrderBottom));
    }

    private void waitForLoadConfirmationPopUp() {
        new WebDriverWait(driver, Duration.ofSeconds(3L)).until(
                driver -> (
                        driver.findElement(cofirmOrderButton) != null
                                && driver.findElement(cofirmOrderButton).isDisplayed()
                )
        );
    }

    private void clickButtonSecondContinue() {
        driver.findElement(buttonSecondContinue).click();
    }

    private void setCommentField(String comment) {
        driver.findElement(commentField).sendKeys(comment);
    }

    private void clickCheckBoxColor() {
        driver.findElement(checkBoxBlackColor).click();
    }

    private void clickOnItemDropDownMenu() {
        driver.findElement(durationItemSeven).click();
    }

    private void scrollToItem() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(durationItemSeven));
    }

    private void clickDurationDropDownMenu() {
        driver.findElement(durationDropDownMenu).click();
    }

    private void setDateDeliveryField(String date) {
        driver.findElement(dateDeliveryField).sendKeys(date);
    }

    private void setNameField(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    private void setSurnameField(String surname) {
        driver.findElement(surnameField).sendKeys(surname);
    }

    private void setAddressField(String address) {
        driver.findElement(addressField).sendKeys(address);
    }

    private void setMetroStationField(String metroStation) {
        driver.findElement(metroStationField).sendKeys(metroStation);
    }

    private void clickMetroStationItem() {
        driver.findElement(metroStationItem).click();
    }

    private void clickButtonFirstContinue() {
        driver.findElement(buttonFirstContinue).click();
    }

    private void setMobilePhoneField(String phone) {
        driver.findElement(phoneField).sendKeys(phone);
    }

    private void waitForLoadSecondInformationScreen() {
        new WebDriverWait(driver, Duration.ofSeconds(3L)).until(
                driver -> (
                        driver.findElement(titleSecondInformationScreen) != null
                                && driver.findElement(titleSecondInformationScreen).isDisplayed()
                )
        );
    }

    private void waitForLoadMainPage() {
        new WebDriverWait(driver, Duration.ofSeconds(3L)).until(
                driver -> (
                        driver.findElement(mainScreen) != null
                                && driver.findElement(mainScreen).isDisplayed()
                )
        );
    }

    private void clickOnButtonDeliveryTop() {
        driver.findElement(buttonOrderTop).click();
    }

    private void clickOnButtonDeliveryBottom() {
        driver.findElement(buttonOrderBottom).click();
    }

    private void waitForLoadFirstInformationScreen() {
        new WebDriverWait(driver, Duration.ofSeconds(3L)).until(
                driver -> (
                        driver.findElement(titleFirstInformationScreen) != null
                                && driver.findElement(titleFirstInformationScreen).isDisplayed()
                )
        );
    }
}
