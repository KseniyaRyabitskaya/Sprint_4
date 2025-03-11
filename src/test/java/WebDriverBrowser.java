import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverBrowser {

    public static WebDriver getWebDriver(TypeBrowsers browser) {
        switch (browser) {
            case CHROME:
                return new ChromeDriver();
            case FIREFOX:
                return new FirefoxDriver();
            default:
                throw new RuntimeException("Incorrect TypeBrowsers");
        }
    }
}
