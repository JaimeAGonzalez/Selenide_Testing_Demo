package Objects;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class FinishPaymentPage {

    public SelenideElement stringValidation = $("#checkout_complete_container > h2");
    public SelenideElement logoValidaton = $("#checkout_complete_container > img");
}
