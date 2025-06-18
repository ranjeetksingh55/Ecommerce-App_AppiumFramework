package org.ranjeet;


import org.ranjeet.PageObjects.Android.CartPage;
import org.ranjeet.PageObjects.Android.ProductCatalogue;
import org.ranjeet.TestUtils.AndroidBaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;



public class eCommerceTc4_HybridApp extends AndroidBaseTest {


    @Test(dataProvider = "formData")
    public void FillForm(HashMap<String, String> input) throws InterruptedException {

        formPage.setNameField(input.get("name"));
        formPage.setGender(input.get("gender"));
        formPage.setCountrySelection(input.get("country"));
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

//    @BeforeMethod
//    public void preSteup() {
//      formPage.setActivity();
//    }


    @DataProvider
    public Object[][] formData() throws IOException {
        List<HashMap<String, String>> data = getJsonData(System.getProperty("user.dir") + "//src//test//java//org//ranjeet//testData//eCommerce.json");
        // return new Object[][]{{"Ranjeet Kumar Singh", "Female", "Austria"}};
        return new Object[][]{{data.get(0)}, {data.get(1)}};

    }


}
















