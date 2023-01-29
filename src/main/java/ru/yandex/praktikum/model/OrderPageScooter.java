package ru.yandex.praktikum.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPageScooter {
    By userNameInput = By.xpath(".//input[@placeholder='* Имя']");
    By userLastNameInput = By.xpath(".//input[@placeholder='* Фамилия']");
    By userAdressInput = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    By metroStationInput = By.xpath(".//input[@placeholder='* Станция метро']");
    By userPhoneNumberInput = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    By nextButton = By.xpath(".//button[text() = 'Далее']"); // Кнопка на форме заказа
    By yesButton = By.xpath(".//button[text() = 'Да']"); // Кнопка последнего окна (где баг)
    By modalHeader = By.xpath(".//div[text() = 'Заказ оформлен']"); // Заголовок последней станицы
    private final WebDriver driver;

    public OrderPageScooter(WebDriver driver) {
        this.driver = driver;
    }

    public void fillOrderForm(
            String userName,
            String userLastName,
            String userAdress,
            String metroStation,
            String userPhoneNumber
    ) {
        By listMetroStation = By.xpath(".//div[@class='select-search__select']"); // Список станций
        By metroStationButton = By.xpath(".//div[text()=" + "'" + metroStation + "'" + "]"); // Станция
        driver.findElement(userNameInput).sendKeys(userName);
        driver.findElement(userLastNameInput).sendKeys(userLastName);
        driver.findElement(userAdressInput).sendKeys(userAdress);
        driver.findElement(metroStationInput).click();
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(listMetroStation));
        driver.findElement(metroStationButton).click();
        driver.findElement(userPhoneNumberInput).sendKeys(userPhoneNumber);
    }

    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }

    public void clickYesButton() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(yesButton));
        driver.findElement(yesButton).click();
    }

    public void hasOrderPlaced() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.textToBePresentInElementLocated(modalHeader, "Заказ оформлен"));
    }

}
