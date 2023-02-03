import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.praktikum.model.HomePageScooter;
import ru.yandex.praktikum.model.OrderDetailsScooter;
import ru.yandex.praktikum.model.OrderPageScooter;
import ru.yandex.praktikum.model.constants.TestData;

@RunWith(Parameterized.class)
public class ScooterServicesTest {
    private final String userName;
    private final String userLastName;
    private final String userAdress;
    private final String metroStation;
    private final String userPhoneNumber;
    private final String orderButtonXPath;
    private final String deliveryDate;
    private final String rentalPeriod;
    private final String scooterColor;
    private final String userСomment;
    WebDriver driver;

    public ScooterServicesTest(
            String userName,
            String userLastName,
            String userAdress,
            String metroStation,
            String userPhoneNumber,
            String orderButtonXPath,
            String deliveryDate,
            String rentalPeriod,
            String scooterColor,
            String userСomment
    ) {
        this.userName = userName;
        this.userLastName = userLastName;
        this.userAdress = userAdress;
        this.metroStation = metroStation;
        this.userPhoneNumber = userPhoneNumber;
        this.orderButtonXPath = orderButtonXPath;
        this.deliveryDate = deliveryDate;
        this.rentalPeriod = rentalPeriod;
        this.scooterColor = scooterColor;
        this.userСomment = userСomment;
    }

    @Parameterized.Parameters
    public static Object[][] getParams() {
        return TestData.DATA;
    }

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
    }

    @Test
    public void checkAnswersPositiveResult() {
        driver = new ChromeDriver();
        driver.get(TestData.URL);
        HomePageScooter homePageScooter = new HomePageScooter(driver);
        homePageScooter.clickCookieButton();
        homePageScooter.scrollHomePage();
        homePageScooter.checkAnswers();

    }

    @Test
    public void checkOrderScooter() {
        driver = new ChromeDriver();
        HomePageScooter homePageScooter = new HomePageScooter(driver);
        OrderPageScooter orderPageScooter = new OrderPageScooter(driver);
        OrderDetailsScooter orderDetailsScooter = new OrderDetailsScooter(driver);
        driver.get(TestData.URL);
        homePageScooter.clickCookieButton();
        homePageScooter.clickOrderButton(orderButtonXPath);
        orderPageScooter.fillOrderForm(userName, userLastName, userAdress, metroStation, userPhoneNumber);
        orderPageScooter.clickNextButton();
        orderDetailsScooter.fillOrderDetailForm(deliveryDate, rentalPeriod, scooterColor, userСomment);
        orderDetailsScooter.clickOrderButton();
        orderPageScooter.clickYesButton();
        orderPageScooter.hasOrderPlaced();
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
