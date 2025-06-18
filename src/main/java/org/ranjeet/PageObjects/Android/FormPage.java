package org.ranjeet.PageObjects.Android;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utils.AndroidActions;

public class FormPage extends AndroidActions {
    AndroidDriver driver;

    public FormPage(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
    private WebElement nameField;
    //   driver.findElement(By.id("com.androidsample.generalstore:id/nameField"))

    @AndroidFindBy(xpath = "//android.widget.RadioButton[@resource-id='com.androidsample.generalstore:id/radioMale']")
    private WebElement femaleOption;
//    // driver.findElement(By.xpath("//android.widget.RadioButton[@resource-id=\"com.androidsample.generalstore:id/" +
//            "radioMale\"]"))

    @AndroidFindBy(xpath = "//android.widget.RadioButton[@resource-id='com.androidsample.generalstore:id/radioFemale']")
    private WebElement MaleOption;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/spinnerCountry")
    private WebElement countrySelection;


    @AndroidFindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
    private WebElement shopButton;


    public void setNameField(String name) {
        nameField.sendKeys(name);
        driver.hideKeyboard();
    }

    public void setGender(String gender) {
        if (gender.contains("female"))
            femaleOption.click();
        else
            MaleOption.click();
    }

    public void setCountrySelection(String countryName) {
        countrySelection.click();
        scrollToText(countryName);
        driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"android:id/text1\" and @text=\"" + countryName + "\"]")).click();

    }
 public void setActivity() {
    // Start the main activity via the app's launcher activity
    ((JavascriptExecutor) driver).executeScript("mobile: startActivity", ImmutableMap.of(
            "appPackage", "com.androidsample.generalstore",
            "appActivity", "com.androidsample.generalstore.MainActivity"));
}
    public ProductCatalogue submitForm() {
        shopButton.click();
        return new ProductCatalogue(driver);
    }
}

