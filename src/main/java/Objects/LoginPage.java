package Objects;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    // Elements
    public SelenideElement userField = $("#user-name[placeholder = \"Username\"]");
    public SelenideElement passwordField = $("#password[placeholder = \"Password\"]");
    public SelenideElement loginButton = $("#login-button[value = \"Login\"]");

    public StandardUserHomePage loginFunctionality(String username, String password){
        // Valid username and password
        userField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
        System.out.println("Login functionality is working properly...");

        return new StandardUserHomePage();
    }

}
