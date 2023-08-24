package Objects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import java.util.Hashtable;

import static com.codeborne.selenide.Selenide.$;

public class CartPage extends StandardUserHomePage{

    public SelenideElement cartVerification = $(".title");
    public SelenideElement checkoutButton = $("#checkout");


    public boolean productsVerification(){
        Hashtable<String, SelenideElement> possibleProducts = new Hashtable();
        for (int i = 0; i < productsName.size(); i++ ){
            possibleProducts.put(productsName.get(i).getText(), productsName.get(i));
        }

        boolean verification = false;
        for (String product:getProductsAdded()){
            if (possibleProducts.get(product).is(Condition.visible)){
                verification = true;
                System.out.println(product + " is in the cart...");
            }
            else{
                System.out.println(product + "is not in the cart...");
            }
        }
        return verification;
    }

    public CheckoutPage checkoutButton(){
        checkoutButton.scrollIntoView(true);
        checkoutButton.click();
        System.out.println("Checkout button is working properly...");

        return new CheckoutPage();
    }


}
