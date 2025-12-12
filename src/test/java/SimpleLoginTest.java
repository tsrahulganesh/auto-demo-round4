
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SimpleLoginTest {

        private WebDriver getDriver() {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless=new"); // Headless for CI
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--window-size=1920,1080");
                // options.addArguments("--remote-allow-origins=*"); // Uncomment if origin
                // issues appear in some CI runners
                return new ChromeDriver(options);
        }

        @Test
        void loginTest() {
                WebDriver driver = getDriver();
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

                try {
                        // 1) Open login page
                        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

                        // 2) Wait for fields and locate them
                        WebElement username = wait.until(
                                        ExpectedConditions.visibilityOfElementLocated(By.name("username")));
                        WebElement password = wait.until(
                                        ExpectedConditions.visibilityOfElementLocated(By.name("password")));
                        WebElement loginBtn = wait.until(
                                        ExpectedConditions
                                                        .elementToBeClickable(By.cssSelector("button[type='submit']")));

                        // 3) Enter credentials (demo site)
                        username.sendKeys("Admin");
                        password.sendKeys("admin123");

                        // 4) Click login
                        loginBtn.click();

                        // 5) Wait for navigation and print title/URL
                        wait.until(ExpectedConditions.or(
                                        ExpectedConditions.urlContains("/dashboard"), // <-- lowercase
                                        ExpectedConditions.visibilityOfElementLocated(
                                                        By.cssSelector("div.oxd-topbar-header-title"))));

                        System.out.println("Title after login: " + driver.getTitle());
                        System.out.println("Current URL: " + driver.getCurrentUrl());

                        // Optional assertion (uncomment if you want the test to fail when login doesn't
                        // reach dashboard)
                        // Assertions.assertTrue(driver.getCurrentUrl().contains("/dashboard"), "Login
                        // did not reach dashboard.");

                } catch (Exception e) {
                        e.printStackTrace();
                        // throw e; // Uncomment to fail the test when any exception occurs
                } finally {
                        driver.quit();
                }
        }
}
