package ru.yandex.praktikum.model;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class HomePageScooter {
    public static final String[] QUESTIONS = {
            "Сколько это стоит? И как оплатить?",
            "Хочу сразу несколько самокатов! Так можно?",
            "Как рассчитывается время аренды?",
            "Можно ли заказать самокат прямо на сегодня?",
            "Можно ли продлить заказ или вернуть самокат раньше?",
            "Вы привозите зарядку вместе с самокатом?",
            "Можно ли отменить заказ?",
            "Я жизу за МКАДом, привезёте?"
    };
    public static final String[] ANSWERS = {
            "Сутки — 400 рублей. Оплата курьеру — наличными или картой.",
            "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.",
            "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.",
            "Только начиная с завтрашнего дня. Но скоро станем расторопнее.",
            "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.",
            "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.",
            "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.",
            "Да, обязательно. Всем самокатов! И Москве, и Московской области."
    };
    By homeFaq = By.xpath(".//div[@class='accordion']"); // Поле с вопросами
    By cookieButton = By.id("rcc-confirm-button"); // Кнопка кук
    private final WebDriver driver;


    public HomePageScooter(WebDriver driver) {
        this.driver = driver;
    }

    public void clickCookieButton() {
        driver.findElement(cookieButton).click();
    }

    public void scrollHomePage() {
        WebElement homeFaqEle = driver.findElement(homeFaq);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", homeFaqEle);
    }

    public void checkAnswers() {
        for (int i = 0; i < QUESTIONS.length; i++) {
            By question = By.xpath(".//div[text() =" + "'" + QUESTIONS[i] + "'" + "]"); // Поле впороса
            By answer = By.xpath(".//p[text() =" + "'" + ANSWERS[i] + "'" + "]"); // Поле ответа
            driver.findElement(question).click();
            new WebDriverWait(driver, 3)
                    .until(ExpectedConditions.elementToBeClickable(answer));
            Assert.assertEquals(driver.findElement(answer).getText(), ANSWERS[i]);
        }

    }

    public void clickOrderButton(String xpath) {
        By title = By.xpath(".//div[text() = 'Для кого самокат']"); // Заголовок формы заказа
        driver.findElement(By.xpath(xpath)).click();
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(title));

    }


}
