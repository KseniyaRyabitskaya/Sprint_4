package ru.praktikum.services.qa.scooter.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderScooterPOM {

    private WebDriver driver;

    private By mainScreen = By.cssSelector("[id='root']");

    // Заголовок "Для кого самокат"
    private By titleFirstInformationScreen = By.cssSelector("[class='Order_Header__BZXOb']");
    // Поля для ввода имени
    private By nameField = By.xpath(".//input[@placeholder='* Имя']");
    // Поля для ввода фамилии
    private By surnameField = By.xpath(".//input[@placeholder='* Фамилия']");
    // Поля для ввода адреса
    private By addressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    // Поля для ввода станции метро
    private By metroStationField = By.xpath(".//input[@class='select-search__input']");
    // Станция метро в списке
    private By metroStationItem = By.xpath(".//li[@data-index='0']");
    // Поля для ввода телефона
    private By phoneField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    // Кнопка "далее" на экране "Для кого самокат"
    private By buttonFirstContinue = By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM");

    // Заголовок "Про аренду"
    private By titleSecondInformationScreen = By.xpath(".//div[@class='Order_Header__BZXOb']");
    // Поля для ввода даты аренды
    private By dateDeliveryField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    // Поле "Срок аренды"
    private By durationDropDownMenu = By.cssSelector(".Dropdown-placeholder");
    // "Семь дней" в выпадающем списке срока аренды
    private By durationItemSeven = By.xpath(".//div[@class='Dropdown-menu']//div[7]");
    // Чекбокс для выбора цвета "черный жемчуг"
    private By checkBoxBlackColor = By.xpath(".//label[@class='Checkbox_Label__3wxSf' and @for='black']");
    // Поля для ввода комментария
    private By commentField = By.xpath(".//div[@class='Input_InputContainer__3NykH']//input[@class='Input_Input__1iN_Z Input_Responsible__1jDKN']");
    // Кнопка "Заказать" на экране "Про аренду"
    private By buttonSecondContinue = By.xpath(".//div[@class='Order_Buttons__1xGrp']//button[2]");

    // Кнопка "Да"
    private By confirmOrderButton = By.xpath(".//div[@class='Order_Modal__YZ-d3']//div[@class='Order_Buttons__1xGrp']//button[2]");
    // Текст о том, что оформления заказа выполнено
    private By finishPopUpText = By.xpath(".//div[@class='Order_ModalHeader__3FDaJ']");

    public OrderScooterPOM(WebDriver driver) {
        this.driver = driver;
    }

    public void fillInformationFirstScreenOrder(String name, String surname, String address, String metroStation, String mobilePhone) {
        waitForLoadFirstInformationScreen();
        setNameField(name);
        setSurnameField(surname);
        setAddressField(address);
        setMetroStationField(metroStation);
        clickMetroStationItem();
        setMobilePhoneField(mobilePhone);
    }

    public void clickButtonFirstContinue() {
        driver.findElement(buttonFirstContinue).click();
    }

    public void fillInformationSecondScreenOrder(String date, String comment) {
        waitForLoadSecondInformationScreen();
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
        driver.findElement(confirmOrderButton).click();
    }

    public String geTextFinishBlock() {
        return driver.findElement(finishPopUpText).getText();
    }

    private void hideCalendar() {
        driver.findElement(titleSecondInformationScreen).click();
    }

    private void waitForLoadConfirmationPopUp() {
        new WebDriverWait(driver, Duration.ofSeconds(3L)).until(
                driver -> (
                        driver.findElement(confirmOrderButton) != null
                                && driver.findElement(confirmOrderButton).isDisplayed()
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

    public void waitForLoadMainPage() {
        new WebDriverWait(driver, Duration.ofSeconds(3L)).until(
                driver -> (
                        driver.findElement(mainScreen) != null
                                && driver.findElement(mainScreen).isDisplayed()
                )
        );
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
