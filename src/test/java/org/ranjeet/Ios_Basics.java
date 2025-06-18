

// correct code

package org.ranjeet;

import org.ranjeet.PageObjects.ios.AlertViews;
import org.ranjeet.TestUtils.Ios_BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Ios_Basics extends Ios_BaseTest {

    @Test
    public void IosBasicsTest() throws InterruptedException {
        // Navigate to Alert Views page
        AlertViews alertViews = homePage.selectAlertViews();

        // Fill the text label with "hello"
        alertViews.fillTextLabel("hello");

        // Get the confirmation message
        String actualMessage = alertViews.getConfirmMessage();

        // Assert that the message is as expected
        Assert.assertEquals(actualMessage, "A message should be a short, complete sentence.");
    }
}
