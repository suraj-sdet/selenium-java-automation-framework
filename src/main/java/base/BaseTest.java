package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import config.ConfigReader;
import utils.DriverFactory;

public class BaseTest {
    protected WebDriver driver;
    protected ConfigReader config;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        config = new ConfigReader();
        driver = DriverFactory.getDriver();
        driver.manage().window().maximize();
        String baseUrl = config.getBaseUrl();
        if (baseUrl != null && !baseUrl.isEmpty()) {
            driver.get(baseUrl);
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
