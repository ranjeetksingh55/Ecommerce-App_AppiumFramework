package org.ranjeet;

import org.ranjeet.PageObjects.Android.ProductCatalogue;
import org.ranjeet.PageObjects.Android.CartPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class eCommerceTc4_HybridApp extends AndroidBaseTest {

    @Test
    public void FillForm() throws InterruptedException {
        formPage.setNameField("Ranjeet Kumar Singh");
        formPage.setGender("Female");
        formPage.setCountrySelection("Austria");
        ProductCatalogue productCatalogue = formPage.submitForm();
        productCatalogue.addItemToCartBYindex(0);
        productCatalogue.addItemToCartBYindex(0);
        CartPage cartPage = productCatalogue.goToCart();

        // submit button
        Thread.sleep(2000);
        double totalSum = cartPage.getProductsSum();
        double displayFormattedSum = cartPage.getTotalAmountDisplayed();

        Assert.assertEquals(totalSum, displayFormattedSum);
        Thread.sleep(3000);
        cartPage.acceptTermsConditions();
        Thread.sleep(3000);
        cartPage.submitOrder();
        Thread.sleep(3000);

    }

    @DataProvider
    public Object[][] formData() {
        return new Object[][]{
                {"Ranjeet Kumar Singh", "Female", "Austria"},
                {"", "Male", "India"},
                {"John Doe", "Female", "United States"}
        };
    }
}









