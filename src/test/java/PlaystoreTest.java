import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class PlaystoreTest {

    AndroidDriver driver;
    @Test
    public void setup() throws MalformedURLException, InterruptedException {
        DesiredCapabilities caps = new DesiredCapabilities();

        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11.0");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "ZY22CSJFXR");
//        caps.setCapability("appPackage", "com.airbnb.android");
//        caps.setCapability("appActivity", "com.android.activities.MainActivity");

        caps.setCapability("appPackage", "com.facebook.katana");
        caps.setCapability("appActivity", "com.facebook.katana.dbl.activity.FacebookLoginActivity");

        caps.setCapability("waitForAppScript", true);
        caps.setCapability("locationServicesEnabled", true);
        caps.setCapability("autoAcceptAlerts", true);
        caps.setCapability("noReset", true);
        caps.setCapability("sendKeyStrategy", "setValue");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);


        Thread.sleep(5000);


        WebElement plus = driver.findElementById(" com.android.vending:id/nested_parent_recycler_view");
        plus.click();

    }

}
