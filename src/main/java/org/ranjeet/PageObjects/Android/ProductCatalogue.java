package org.ranjeet.PageObjects.Android;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utils.AndroidActions;

import java.util.List;

public class ProductCatalogue extends AndroidActions {
    AndroidDriver driver;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='ADD TO CART']")
   public List<WebElement> addToCart;
    @AndroidFindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
   public WebElement cart;

    public ProductCatalogue(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void addItemToCartBYindex(int Index) {
        addToCart.get(Index).click();

    }

    public CartPage goToCart() throws InterruptedException {
        cart.click();
        Thread.sleep(2000);
        return new CartPage(driver);
    }
}
