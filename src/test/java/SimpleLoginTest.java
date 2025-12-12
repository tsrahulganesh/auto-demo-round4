
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SimpleLoginTest {

        private WebDriver getDriver() {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless=new"); // Headless for CI
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--window-size=1920,1080");
                return new ChromeDriver(options);
        }

        @Test
        void loginTest() {
                WebDriver driver = getDriver();
                try {
                        // 1) Open login page
                        driver.get("https://bankubt.onlinebank.com/Service/UserManager.aspx");

                        // 2) Enter username and password
                        WebElement(By.id("M$layout$content$PCDZ$MW2NO7V$ctl00$webInputForm$txtLoginName")); // Replace
                                                                                                            // with
                                                                                                            // actual
                                                                                                            // id/name
                        WebElement password = driver.findElement(By.id("Password")); // Replace with actual id/name
                        WebElement loginBtn = driver.findElement(By.cssSelector("input[type='submit']")); // Adjust if
                                                                                                          // needed

                        username.sendKeys("Pawaradmin01");
                        password.sendKeys("Test@2025");

                        // 3) Click login
                        loginBtn.click();

                        // 4) Print page title and URL after login
                        Thread.sleep(5000); // Simple wait for page load
                        System.out.println("Title after login: " + driver.getTitle());
                        System.out.println("Current URL: " + driver.getCurrentUrl());

                } catch (Exception e) {
                        e.printStackTrace();
                } finally {
                        driver.quit();
                }
        }
}
