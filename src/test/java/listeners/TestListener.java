package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.DriverFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * TestNG listener that captures a screenshot on failure.
 */
public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = DriverFactory.getDriver();
        if (driver == null) return;

        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            byte[] data = ts.getScreenshotAs(OutputType.BYTES);
            String testName = result.getName();
            Path screenshotsDir = Paths.get("target", "screenshots");
            Files.createDirectories(screenshotsDir);
            Path dest = screenshotsDir.resolve(testName + ".png");
            Files.write(dest, data);
            System.out.println("Saved screenshot: " + dest.toAbsolutePath());
        } catch (IOException | ClassCastException e) {
            System.err.println("Failed to capture screenshot: " + e.getMessage());
        }
    }

    // Other listener methods can be left empty or implemented as needed
    @Override public void onTestStart(ITestResult result) {}
    @Override public void onTestSuccess(ITestResult result) {}
    @Override public void onTestSkipped(ITestResult result) {}
    @Override public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}
    @Override public void onStart(ITestContext context) {}
    @Override public void onFinish(ITestContext context) {}
}
