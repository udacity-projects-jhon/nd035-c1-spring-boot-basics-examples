package com.udacity.jwdnd.c1.review;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class ChatPage {

    private static final String SECRET = "shhh_very_secret";
    private static final String USERNAME = "jhont285";
    @FindBy(id = "inputFirstName")
    private WebElement inputFirstName;

    @FindBy(id = "inputLastName")
    private WebElement inputLastName;

    @FindBy(id = "inputUsername")
    private WebElement inputUsername;

    @FindBy(id = "inputPassword")
    private WebElement inputPassword;

    @FindBy(id = "submit-button")
    private WebElement submitButton;

    @FindBy(id = "messageText")
    private WebElement messageText;

    @FindBy(id = "messageType")
    private WebElement messageType;

    @FindBy(id = "signup-link")
    private WebElement signupLink;

    @FindBy(id = "login-link")
    private WebElement loginLink;

    @FindBy(tagName = "span")
    private List<WebElement> messages;

    @FindBy(tagName = "form")
    private WebElement form;

    public ChatPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void registerUser(String userName) {
        signupLink.click();
        inputFirstName.sendKeys("jhon");
        inputLastName.sendKeys("torres");
        inputUsername.sendKeys(userName);
        inputPassword.sendKeys(SECRET);
        submitButton.click();
        loginLink.click();
    }

    public void loginUser(String userName) {
        inputUsername.sendKeys(userName);
        inputPassword.sendKeys(SECRET);
        submitButton.submit();
    }

    public List<String> writeAndGetMessages(String message, String type) {
        messageText.sendKeys(message);
        messageType.sendKeys(type);
        form.submit();
        return messages.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}
