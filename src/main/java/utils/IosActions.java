package utils;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import java.util.HashMap;
import java.util.Map;

public class IosActions extends AppiumUtils {
    IOSDriver driver;

    public IosActions(IOSDriver driver) {
        this.driver = driver;
    }

    public void LongPressAction(WebElement ele) {
        Map<String, Object> params = new HashMap<>();
        params.put("element",((RemoteWebElement)ele).getId());
        params.put("duration", 5);
    }

    public void scrollToEndAction() {
        boolean canScrollMore;
        do
            canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
                    "left", 100, "top", 100, "width", 200, "height", 200,
                    "direction", "down",
                    "percent", 3.0
            )); while (canScrollMore);

    }
    public void scrollToWebElement(WebElement ele)
    {
        Map<String, Object> params = new HashMap<>();
        params.put("direction", "down");
        params.put("element", ((RemoteWebElement) ele).getId());
    }

    public Double getFormattedAmount(String amount) {
        Double price = Double.parseDouble(amount.substring(1));
        return price;
    }

    public void swipeAction(WebElement ele, String direction) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("bundleId","com.apple.mobileslideshow");
    }

}