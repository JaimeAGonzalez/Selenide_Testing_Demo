package TestScenarios;


import Objects.*;
import com.codeborne.selenide.Condition;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class functionalTest extends Config.testConfig{
    // Objects
    LoginPage loginPage = new LoginPage();
    BurgerComponent burgerComponent = new BurgerComponent();

    // Testing
    @Test
    public void generalTest(){
        // Valid username and password
        String username = "standard_user";
        String password = "secret_sauce";
        StandardUserHomePage standardHomePage = loginPage.loginFunctionality(username, password);
        standardHomePage.logoHome.shouldBe(Condition.visible);
        Reporter.log("Logo is visible...");

        // Burger Button
        burgerComponent.burgerButton.click();
        burgerComponent.closeButton.shouldBe(Condition.visible);
        Assert.assertTrue(burgerComponent.visibleBurgerOptions());
        burgerComponent.closeButton.click();
        Reporter.log("Menu elements are visible...");

        // Add items
        List<String> productsAdd = new ArrayList<>();
        productsAdd.add("Sauce Labs Backpack");
        productsAdd.add("Sauce Labs Bike Light");
        productsAdd.add("Sauce Labs Bolt T-Shirt");
        productsAdd.add("Sauce Labs Fleece Jacket");
        productsAdd.add("Sauce Labs Onesie");
        productsAdd.add("Test.allTheThings() T-Shirt (Red)");

        standardHomePage.addRemoveProducts(productsAdd);
        Assert.assertTrue(standardHomePage.statusUpdate(productsAdd));
        Reporter.log("Target products were added");

        // Remove items
        List<String> productsRemove = new ArrayList<>();
        productsRemove.add("Sauce Labs Fleece Jacket");
        productsRemove.add("Sauce Labs Onesie");
        productsRemove.add("Test.allTheThings() T-Shirt (Red)");

        standardHomePage.addRemoveProducts(productsRemove);
        Assert.assertFalse(standardHomePage.statusUpdate(productsRemove));
        Reporter.log("Target products were removed");

        // Cart icon verification
        String numberVerificaton = Integer.toString(productsAdd.size() - productsRemove.size());
        Assert.assertEquals(standardHomePage.carButtonNumber.getText(), numberVerificaton);
        Reporter.log("Number of added products matches with the cart number...");

        // Cart
        CartPage cartPage = standardHomePage.cartButtonClick();
        Assert.assertEquals(cartPage.cartVerification.getText(), "Your Cart");
        Assert.assertTrue(cartPage.productsVerification());
        Reporter.log("Added products are in the cart...");

        // Checkout
        String firstName = "Jaime";
        String lastName = "Núñez";
        String postalCode = "110110";

        CheckoutPage checkoutPage = cartPage.checkoutButton();
        Assert.assertEquals("Checkout: Your Information", checkoutPage.checkoutverification.getText());
        checkoutPage.setCheckout(firstName, lastName, postalCode);

        // Payment
        PaymentPage paymentPage = checkoutPage.continueClick();
        Reporter.log("Data in the checkout filled...");
        Assert.assertEquals("Checkout: Overview", paymentPage.paymentValidation.getText());

        List<String> fields = new ArrayList<>();
        fields.add("Payment Information");
        fields.add(paymentPage.totalAmount.getText());
        Assert.assertTrue(paymentPage.fieldsVerification(fields));

        // Finish confirmation
        FinishPaymentPage finishPaymentPage = paymentPage.finishButton();
        Reporter.log("Target fields are visible...");
        Assert.assertTrue( finishPaymentPage.logoValidaton.is(Condition.visible));
        Assert.assertTrue(finishPaymentPage.stringValidation.is(Condition.visible));
        System.out.println("The order has been patched");
        Reporter.log("The test is completed...");
    }

}
