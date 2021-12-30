import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class AirbnbTest {

    AndroidDriver driver;

    @BeforeTest
    public void setup() throws MalformedURLException, InterruptedException {
        DesiredCapabilities caps = new DesiredCapabilities();

        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11.0");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "ZY22CSJFXR");
        caps.setCapability("appPackage", "com.airbnb.android");
        caps.setCapability("appActivity", "com.airbnb.android.feat.homescreen.HomeActivity");
        //caps.setCapability("appActivity", "com.airbnb.android.activities.MainActivity");

        //caps.setCapability("appPackage", "com.facebook.katana");
        //  caps.setCapability("appActivity", "com.facebook.katana.dbl.activity.FbMainTabActivity");

        caps.setCapability("waitForAppScript", true);
        caps.setCapability("autoGrantPermissions", true);
        caps.setCapability("locationServicesEnabled", true);
        caps.setCapability("autoAcceptAlerts", true);
        caps.setCapability("noReset", true);
        caps.setCapability("sendKeyStrategy", "setValue");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);


        //com.airbnb.android:id/2131428387

        Thread.sleep(3000);

    }

    @Test
    public void test1() throws MalformedURLException, InterruptedException {

        WebElement titulo = driver.findElementById("com.airbnb.android:id/2131432641");
        System.out.println("--- " + titulo.getText());

        WebElement subtitulo = driver.findElementById("com.airbnb.android:id/text");
        System.out.println("--- " + subtitulo.getText());
        System.out.println(" tagname " + subtitulo.getTagName());

        // WebElement plus = driver.findElementById("com.airbnb.android:id/2131428876");
       // plus.click();
        //Thread.sleep(3000);

        //click en dropdown
        WebElement x = driver.findElementById("com.airbnb.android:id/2131432514");
        System.out.println("---> " + x.getTagName());

        x.click();
        Thread.sleep(3000);

        //seleccionar el pais
        //driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[8]").click();
       // WebElement xp = driver.findElementById("android:id/text1"));

       // System.out.println("---> " + xp.isDisplayed());

        List<WebElement> listaPaises = driver.findElementsByClassName("android.widget.CheckedTextView");
        //List<WebElement> listaPaises = driver.findElementsById("android:id/text1");

        for (int i = 0; i <=5 ; i++){
            System.out.println(listaPaises.get(i).getText());
        }


        WebElement phone = driver.findElementById("com.airbnb.android:id/2131428862");
        phone.sendKeys("123123");

        List<WebElement> listaElementos = driver.findElementsByClassName("android.widget.Button");
        System.out.println("---> " + listaElementos.size());

        for(WebElement buttons : listaElementos){
            System.out.println(":  + " + buttons.getText() + " esta habilitado? "+  buttons.isEnabled());
        }


    }


    @Test
    public void invalidEmailTest() throws InterruptedException {

        WebElement continuarBtn = driver.findElementById("com.airbnb.android:id/2131429395");
        Assert.assertFalse(continuarBtn.isEnabled(),"Error: el btn no deberia estar desplegado");

        WebElement loginCorreoElectronicoElement = driver.findElementById("com.airbnb.android:id/2131428876");
        loginCorreoElectronicoElement.click();

        WebElement correoElectronico = driver.findElementById("com.airbnb.android:id/2131428862");
        correoElectronico.sendKeys("testing@test.con");

        continuarBtn = driver.findElementById("com.airbnb.android:id/2131429395");
        //Assert.assertTrue(continuarBtn.isEnabled(),"Error: el btn deberia estar desplegado");
        continuarBtn.click();

        WebElement errorElement = driver.findElementById("com.airbnb.android:id/title");
        Assert.assertEquals(errorElement.getText(), "Correo electrónico no válido.", "Error: se esperaba un mensaje de email no valido");

    }

    @Test
    public void loginWithValidEmailButNoAccountRelatedTest() throws InterruptedException {

        WebElement continuarBtn = driver.findElementById("com.airbnb.android:id/2131429395");
        Assert.assertFalse(continuarBtn.isEnabled(),"Error: el btn no deberia estar desplegado");

        WebElement loginCorreoElectronicoElement = driver.findElementById("com.airbnb.android:id/2131428876");
        loginCorreoElectronicoElement.click();

        WebElement correoElectronico = driver.findElementById("com.airbnb.android:id/2131428862");
        correoElectronico.sendKeys("test@test.com");

        continuarBtn = driver.findElementById("com.airbnb.android:id/2131429395");
        //Assert.assertTrue(continuarBtn.isEnabled(),"Error: el btn deberia estar desplegado");
        continuarBtn.click();

        WebElement errorElement = driver.findElementById("com.airbnb.android:id/title");
        Assert.assertEquals(errorElement.getText(), "Todavía no estableces una contraseña. Acabamos de enviar un enlace para crear una a test@test.com", "Error: se esperaba un mensaje de email no valido");
    }

    @Test
    public void mostrarContraseniaTest() throws InterruptedException {
        //validar que el boton de continuar no esté habilitado
        WebElement continuarBtn = driver.findElementById("com.airbnb.android:id/2131429395");
        Assert.assertFalse(continuarBtn.isEnabled(),"Error: el btn no deberia estar desplegado");

        //tap en el login con correo btn
        WebElement loginCorreoElectronicoElement = driver.findElementById("com.airbnb.android:id/2131428876");
        loginCorreoElectronicoElement.click();

        // se completa el campo correo
        WebElement correoElectronico = driver.findElementById("com.airbnb.android:id/2131428862");
        correoElectronico.sendKeys("emiliano.gnocchi@gmail.com");

        //click en continuar btn
        continuarBtn = driver.findElementById("com.airbnb.android:id/2131429395");
        Assert.assertTrue(continuarBtn.isEnabled(),"Error: el btn deberia estar desplegado");
        continuarBtn.click();

        Thread.sleep(4000);
        //validacion que el boton de continuar no deberaia estar desplegado
        continuarBtn = driver.findElementById("com.airbnb.android:id/2131429395");
        Assert.assertFalse(continuarBtn.isEnabled(),"Error: el btn deberia estar desplegado");

        //se completa la contraseña
        WebElement contraseniaElement = driver.findElementById("com.airbnb.android:id/2131428862");
        contraseniaElement.sendKeys("holamundo");

        //validacion que el boton de continuar deberaia estar desplegado
        continuarBtn = driver.findElementById("com.airbnb.android:id/2131429395");
        Assert.assertTrue(continuarBtn.isEnabled(),"Error: el btn deberia estar desplegado");

        //click en continuar btn
        continuarBtn.click();

        //validar que el link de recordar contrasenia se encuentra presente
        WebElement olvidasteContraseniaElement = driver.findElementById("com.airbnb.android:id/2131427813");
        Assert.assertTrue(olvidasteContraseniaElement.getText().contains( "Olvidaste"));

    }

    @Test
    public void loginConGoogleTest() throws InterruptedException {

        //hacer click en el boton de Continuar con Google
        WebElement loginConGoogleElement = driver.findElementById("com.airbnb.android:id/2131429362");
        System.out.println(loginConGoogleElement.getText());
        loginConGoogleElement.click();

        Thread.sleep(3000);

        //validar que el titulo sea Elige una cuenta
        WebElement eligeUnaCuentaElement = driver.findElementById("com.google.android.gms:id/main_title");
        System.out.println(eligeUnaCuentaElement.getText());

        //validar el texto del subtitulo
        WebElement subtituloElement = driver.findElementById("com.google.android.gms:id/subtitle");
        System.out.println(subtituloElement.getText());

        //validar que este el boton agregar otra cuenta
        WebElement agregarAccountElement = driver.findElementById("com.google.android.gms:id/add_account_chip_title");
        Assert.assertTrue(agregarAccountElement.getText().contains("Agregar otra cuenta"), "Error: se esperaba el texto: agregar otra cuenta");

        //obtener el texto de plicita de consentimiento, y validar que se encuentre el texto Antes de usar esta app, consulta su política de privacidad
        WebElement consentTextElement = driver.findElementById("com.google.android.gms:id/consent_text");
        Assert.assertTrue(consentTextElement.getText().contains( "Antes de usar esta app, consulta su política de privacidad"), "Errror: se esperaba otro mensaje");

    }


    @Test
    public void androidTest() throws InterruptedException {
        driver.findElementById("com.airbnb.android:id/2131427667").click();
        Thread.sleep(9000);


        System.out.println("--->  " + driver.getTitle());
        WebElement x = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout[2]/android.webkit.WebView/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.TextView[1]");
        System.out.println(x.getText());
    }
}
