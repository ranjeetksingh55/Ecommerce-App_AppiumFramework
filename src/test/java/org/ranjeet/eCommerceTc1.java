package org.ranjeet;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.ranjeet.TestUtils.AndroidBaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class eCommerceTc1 extends AndroidBaseTest {

////    @BeforeMethod
////    public void preSetup() {
////        ((JavascriptExecutor) driver).executeScript("mobile: startActivity", ImmutableMap.of(
////                "appPackage", "com.androidsample.generalstore",
////                "appActivity", "com.androidsample.generalstore.MainActivity"));
//    }

    // Test Strategy
    @Test
    public void FillForm_ErrorValidation() throws InterruptedException {
        driver.hideKeyboard();
        driver.findElement(By.xpath("//android.widget.RadioButton[@resource-id=\"com.androidsample.generalstore:id/radioMale\"]")).click();
        driver.hideKeyboard();

        // Dropdown
        driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Austria\"));"));
        driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"android:id/text1\" and @text=\"Austria\"]")).click();

        // submit button
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        String toastMessage = driver.findElement(By.xpath("(//android.widget.Toast)[1]")).getAttribute("name");
        Assert.assertEquals(toastMessage, "Please enter your name");
    }

    @Test
    public void FillForm_PositiveFlow() throws InterruptedException {
        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Ranjeet Kumar Singh");
        driver.hideKeyboard();
        driver.findElement(By.xpath("//android.widget.RadioButton[@resource-id=\"com.androidsample.generalstore:id/radioMale\"]")).click();
        driver.hideKeyboard();

        // Dropdown
        driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Austria\"));"));
        driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"android:id/text1\" and @text=\"Austria\"]")).click();

        // submit button
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        Assert.assertTrue(driver.findElements(By.xpath("(//android.widget.Toast)[1]")).size() < 1);
    }
}