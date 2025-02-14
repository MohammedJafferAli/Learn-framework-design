package tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.*;
import testcomponents.BaseTest;
import testcomponents.RetryListener;

import java.io.IOException;
import java.util.List;

public class SubmitOrderTest extends BaseTest {
    public static String productName = "IPHONE 13 PRO";

@Test(retryAnalyzer = RetryListener.class)
    public void submitOrder() throws IOException {

        ProductCatalogue productCatalogue = loginPage.loginToApplication("MohammedJaffer1@practise.com", "Password.1001");

        //Get the list of products
        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(productName);

        // Select Cart from global header
        CartPage cartPage = productCatalogue.goToCartPage();

        //Verify added product available in MyCart
        Boolean match = cartPage.verifyCartProducts(productName);
        Assert.assertTrue(match);
        CheckOutPage checkOutPage = cartPage.goToCheckOut();//Checkout from Mycart page

        //Checkout/Payment page
        checkOutPage.selectCountry("India");
        //Place order
        ConfirmationPage confirmationPage = checkOutPage.placeOrder();

        // Thank you / Order confirmation page
        String confirmMessage = confirmationPage.getConfirmaitonMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));

        //Close the application
        confirmationPage.signOut();
    }

    @Test(dependsOnMethods = {"submitOrder"})
    public void verifyOrderHistory()
    {
        ProductCatalogue productCatalogue = loginPage.loginToApplication("MohammedJaffer1@practise.com", "Password.1001");
        OrderPage orderPage = productCatalogue.goToOrdersPage();
        Assert.assertTrue(orderPage.verifyOrderDisplay(productName));

    }
}
