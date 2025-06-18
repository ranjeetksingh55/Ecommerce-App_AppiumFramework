package utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

public abstract class AppiumUtils {
    private AppiumDriverLocalService service;


    public Double getFormattedAmount(String amount) {
        Double price = Double.parseDouble(amount.substring(1));
        return price;
    }

    public List<HashMap<String, String>> getJsonData(String jsonFilePath) throws IOException {
        // Read the JSON file content as a string
        String jsonContent = FileUtils.readFileToString(new File(jsonFilePath), StandardCharsets.UTF_8);

        // Convert JSON string to a List of HashMaps
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
        });

        return data;
    }

    public AppiumDriverLocalService startAppiumServer(String ipAddress, int port) {
        service = new AppiumServiceBuilder()
                .withAppiumJS(new File("//usr//local//lib//node_modules//appium//build//lib//main.js"))
                .withIPAddress(ipAddress).usingPort(port).build();
        service.start();
        return service;
    }



    public void waitForElementToAppear(WebElement ele, AppiumDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.attributeContains((ele), "text", "Cart"));
    }
   public File getScreenShot(String testCaseName, AppiumDriver driver) throws IOException {
    File source = driver.getScreenshotAs(OutputType.FILE);
    File destinationFile = new File(System.getProperty("user.dir") + "//reports" + testCaseName + ".png");
    FileUtils.copyFile(source, destinationFile);
    return destinationFile;
    // 1. capture and place in the folder. 2.extent report pick file and attach the report.

}
}
