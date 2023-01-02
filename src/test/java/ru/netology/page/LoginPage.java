package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.User;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    private SelenideElement userField = $("[data-test-id=login] input");
    private SelenideElement passwordField = $("[data-test-id=password] input");
    private SelenideElement loginButton = $("[data-test-id=action-login]");
    private SelenideElement loginNotification = $("[data-test-id=login] .input__inner .input__sub");
    private SelenideElement passwordNotification = $("[data-test-id=password] .input__inner .input__sub");
    private SelenideElement errorUserNotification = $("[data-test-id=error-notification] .notification__content");

    public VerificationPage validLogin(User user) {
        userField.setValue(user.getLogin());
        passwordField.setValue(user.getPassword());
        loginButton.click();
        return new VerificationPage();
    }

    public void passwordEmpty(User user) {
        userField.setValue(user.getLogin());
        loginButton.click();
        passwordNotification.shouldHave(Condition.text("Поле обязательно для заполнения"))
                .shouldBe(Condition.visible);
    }

    public void loginEmpty(User user) {
        passwordField.setValue(user.getPassword());
        loginButton.click();
        loginNotification.shouldHave(Condition.text("Поле обязательно для заполнения"))
                .shouldBe(Condition.visible);
    }

    public void invalidLogin(User user) {
        userField.setValue(user.getLogin());
        passwordField.setValue(user.getPassword());
        loginButton.click();
        errorUserNotification.shouldHave(Condition.text("Ошибка! "))
                .shouldHave(Condition.text("Неверно указан логин или пароль"))
                .shouldBe(Condition.visible);
    }
}
