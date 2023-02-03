package ru.yandex.praktikum.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderDetailsScooter {
    By deliveryDateInput = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    By rentalPeriodInput = By.xpath(".//span[@class='Dropdown-arrow']");
    By userСommentInput = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    By periodList = By.xpath(".//div[@class='Dropdown-menu']"); // Список периодов аренды
    By orderButton = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[text() = 'Заказать']");
    private final WebDriver driver;
    public OrderDetailsScooter(WebDriver driver) {
        this.driver = driver;
    }

    public void fillOrderDetailForm(
            String deliveryDate,
            String rentalPeriod,
            String scooterColor,
            String userСomment
    ) {
        By scooterColorInput = By.xpath(".//label[text()=" + "'" + scooterColor + "'" + "]"); // Поле сцвета самоката
        By rentalPeriodButton = By.xpath(".//div[text()=" + "'" + rentalPeriod + "'" + "]"); // Срок аренды
        driver.findElement(deliveryDateInput).sendKeys(deliveryDate);
        driver.findElement(rentalPeriodInput).click();
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(periodList));
        driver.findElement(rentalPeriodButton).click();
        driver.findElement(scooterColorInput).click();
        driver.findElement(userСommentInput).sendKeys(userСomment);
    }

    public void clickOrderButton() {
        driver.findElement(orderButton).click();
    }


}
