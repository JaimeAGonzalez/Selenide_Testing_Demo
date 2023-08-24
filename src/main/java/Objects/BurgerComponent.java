package Objects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.$;

public class BurgerComponent {

    public SelenideElement burgerButton = $(".bm-burger-button > button");
    public ElementsCollection burgerOptions = $(".bm-item-list").$$("*");
    public SelenideElement closeButton = $("#react-burger-cross-btn");

    public boolean visibleBurgerOptions() {
        // Verification elements
        ArrayList<String> targetElements = new ArrayList<>();
        targetElements.add("All Items");
        targetElements.add("About");
        targetElements.add("Logout");
        targetElements.add("Reset App State");

        // Flag
        boolean notVisible = false;
        for (SelenideElement burgerElement : burgerOptions) {
            if (!burgerElement.is(Condition.visible)) {
                notVisible = true;
                System.out.println("Menu element is not visible");
                break;
            }
        }
        if (!notVisible) {
            System.out.println("Menu elements are visible...");
            return true;
        }
        return  false;
    }

}
