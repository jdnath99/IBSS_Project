package glue;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class W {
    private static W instance = null;

    public static W get() {
        if (instance == null) {
            instance = new W();
        }
        return instance;
    }

    protected WebDriver driver;

    private W() {

        //ChromeDriver version is 114 and browser version is 126 which is not compatible.
       /* String dir = System.getProperty("user.dir");
        String pathToDriver = dir + "\\chromedriver.exe";
        if (pathToDriver == null || pathToDriver.isEmpty()) {
            throw new RuntimeException("define a path to the chrome driver using system property 'webdriver.chrome.driver'");
        }
        System.setProperty("webdriver.chrome.driver", pathToDriver);*/

        //Added Webdriver manager

        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
    }

    public static void close() {
        if (instance != null) {
            instance.driver.close();
            instance = null;
        }
    }

}
