package Objects;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class StandardUserHomePage {
    // Elements
    public SelenideElement logoHome = $(".app_logo");
    public SelenideElement cartButton = $("#shopping_cart_container");
    public SelenideElement carButtonNumber = $(".shopping_cart_link > span");
    public ElementsCollection productsName = $$(".inventory_item_name");
    public ElementsCollection productsAddDeleteButton = $$(".inventory_item_price ~ button");

    public void addRemoveProducts(List<String> products){
        Hashtable<String, SelenideElement> possibleProducts = new Hashtable<>();
        for (int i = 0; i < productsName.size(); i++ ){
            possibleProducts.put(productsName.get(i).getText(), productsAddDeleteButton.get(i));
        }

        // Actions
        for (String product:products) {
            try{
                possibleProducts.get(product).click();
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }

    }

    public Boolean statusUpdate(List<String> products){
        Hashtable<String, SelenideElement> possibleProducts = new Hashtable<>();
        for (int i = 0; i < productsName.size(); i++ ){
            possibleProducts.put(productsName.get(i).getText(), productsAddDeleteButton.get(i));
        }

        // Actions
        boolean statusAdd = false;
        for (String product: products){
            if(possibleProducts.get(product).getText().equalsIgnoreCase("remove")){
                statusAdd = true;
                System.out.println(product + " was added...");
            }
            else{
                System.out.println(product + " was removed...");
            }
        }
        return statusAdd;
    }

    public List<String> getProductsAdded(){
        List <String> actualProducts = new ArrayList<>();
        Hashtable<String, SelenideElement> possibleProducts = new Hashtable<>();
        for (int i = 0; i < productsName.size(); i++ ){
            possibleProducts.put(productsName.get(i).getText(), productsAddDeleteButton.get(i));
        }

        // Actions
        for (Map.Entry<String, SelenideElement> product: possibleProducts.entrySet()){
            if (product.getValue().getText().equalsIgnoreCase("remove")){
                actualProducts.add(product.getKey());
            }
        }
        return actualProducts;

    }
    public CartPage cartButtonClick(){
        cartButton.scrollIntoView(true);
        cartButton.shouldBe(Condition.visible).click();
        System.out.println("Cart button is working...");
        return new CartPage();
    }

}
