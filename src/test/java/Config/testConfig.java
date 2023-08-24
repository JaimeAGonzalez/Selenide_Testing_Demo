package Config;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;


public class testConfig {

    @BeforeTest
    public static void  setupAll(){Configuration.browserSize = "1280x800";}

    @BeforeClass
    public void setup(){
        Configuration.browserCapabilities = new ChromeOptions().addArguments("--remote-allow-origins=*");
        Configuration.browser = "chrome";
        Selenide.open("https://www.saucedemo.com/");
    }

}