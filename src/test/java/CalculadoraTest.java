import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class CalculadoraTest {
    public static final String APP_PACKAGE = "io.appium.android.apis";
    public static final String APP_ACTIVITY = ".ApiDemos";

    //para las apliaciones nativas, se necesita el appPAckage y el appActivity para eso, lo mejor es descargar el apk info
    AndroidDriver driver;
    @Test
    public void setup() throws MalformedURLException, InterruptedException {
        DesiredCapabilities caps = new DesiredCapabilities();

        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11.0");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "ZY22CSJFXR");
        caps.setCapability("appPackage", "com.google.android.calculator");
        caps.setCapability("appActivity", "com.android.calculator2.Calculator");
        //caps.setCapability("appActivity", ".ApiDemos");
        //  caps.setCapability("app", "/Users/Selenium/Documents/AppiumEmiliano/netflix.apk");

        caps.setCapability("automationName", "UiAutomator2");
        //caps.setCapability("appActivity", "com.google.android.apps.chrome.Main");
        caps.setCapability("waitForAppScript", true);
        caps.setCapability("locationServicesEnabled", true);
        caps.setCapability("autoAcceptAlerts", true);
        caps.setCapability("noReset", true);
        caps.setCapability("sendKeyStrategy", "setValue");

        //appActivity
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);


        Thread.sleep(5000);

        // locate the Text on the calculator by using By.name()
        WebElement seven = driver.findElementById("com.google.android.calculator:id/digit_7");

        seven.click();
        WebElement plus = driver.findElementById("com.google.android.calculator:id/op_add");
        plus.click();
        WebElement three = driver.findElementById("com.google.android.calculator:id/digit_3");
        three.click();
        WebElement equalTo = driver.findElementById("com.google.android.calculator:id/eq");
        equalTo.click();

        Thread.sleep(3000);

        // locate the edit box
        // WebElement results = driver.findElementById("com.google.android.calculator:id/formula");
        WebElement results = driver.findElementByXPath("//android.widget.TextView[@content-desc=\"Sin fórmula\"]");

        if(results.getText().equals("10"))
        {
            System.out.println("Test Passed...");
        }
        else
        {
            System.out.println("Test Failed...");
        }
    }

}
