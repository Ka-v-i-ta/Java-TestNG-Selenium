import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;


public class App {
    public static String userName = System.getenv("LT_USERNAME");
    public static String accessKey = System.getenv("LT_ACCESS_KEY");
    
        public static void main(String args[]) throws MalformedURLException, InterruptedException {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("build","SmokeTest");
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("deviceName", "Galaxy S9");
            capabilities.setCapability("isRealMobile", true);
            capabilities.setCapability("platformVersion","10");
            capabilities.setCapability("app","lt://APP1001911638197708442473");
            capabilities.setCapability("deviceOrientation", "PORTRAIT");
            capabilities.setCapability("console",true);
            capabilities.setCapability("network",true);
            capabilities.setCapability("visual",true);
    
    
            RemoteWebDriver driver = new RemoteWebDriver(new
                    URL("https://"+userName+":"+accessKey+"@beta-hub.lambdatest.com/wd/hub"), capabilities);
            try {
                AndroidElement searchElement = (AndroidElement) new WebDriverWait(driver, 30).until(
                        ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("Search Wikipedia")));
                searchElement.click();
                AndroidElement insertTextElement = (AndroidElement) new WebDriverWait(driver, 30).until(
                        ExpectedConditions.elementToBeClickable(MobileBy.id("org.wikipedia.alpha:id/search_src_text")));
                insertTextElement.sendKeys("LambdaTest"+ Keys.ENTER);
                Thread.sleep(5000);List allProductsName = driver.findElementsByClassName("android.widget.TextView");
                assert(allProductsName.size() > 0);
    //            ((JavascriptExecutor) driver).executeScript("lambda-status=passed");
            }catch (Exception e){
    //            ((JavascriptExecutor) driver).executeScript("lambda-status=failed");
                e.printStackTrace();
            }/*catch (AssertionError a){
                ((JavascriptExecutor) driver).executeScript("lambda-status=failed");
                a.printStackTrace();
            }*/
    // The driver.quit statement is required, otherwise the test continues to execute, leading to timeout
    
            driver.quit();
        }
