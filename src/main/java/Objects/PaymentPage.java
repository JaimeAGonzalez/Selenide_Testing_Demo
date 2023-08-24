package Objects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.Hashtable;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PaymentPage {
    public SelenideElement paymentValidation = $(".title");
    public ElementsCollection importantInformation = $$(".summary_info > *");
    public SelenideElement finishButton = $("#finish");
    public SelenideElement totalAmount = $(".summary_info_label.summary_total_label ");

    public boolean fieldsVerification(List<String> targetElements){
        Hashtable<String, SelenideElement> possibleFields = new Hashtable<>();
        for (SelenideElement field:importantInformation){
            possibleFields.put(field.getText(), field);
        }

        boolean visibility = false;
        for (String element:targetElements){
            if (possibleFields.get(element).is(Condition.visible)){
                visibility = true;
                System.out.println(element + " field is visible...");
            }
            else{
                System.out.println(element + " field is not visible...");
            }
        }
        return visibility;
    }

    public FinishPaymentPage finishButton(){
        finishButton.shouldBe(Condition.visible);
        finishButton.click();
        System.out.println("Finish button clicked...");

        return new FinishPaymentPage();
    }


}
