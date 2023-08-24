package Objects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class CheckoutPage {

    public SelenideElement checkoutverification = $(".title");
    public SelenideElement firstNameInput = $("#first-name");
    public SelenideElement lastNameInput = $("#last-name");
    public SelenideElement postalInput = $("#postal-code");
    public SelenideElement continueButton = $("#continue");

    public void setCheckout(String firstName, String lastName, String postalCode){
        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
        postalInput.sendKeys(postalCode);
    }

    public PaymentPage continueClick(){
        continueButton.shouldBe(Condition.visible);
        continueButton.click();
        System.out.print("Checkout verification complete....");

        return new PaymentPage();
    }

}
