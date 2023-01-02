package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;
import ru.netology.data.User;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static ru.netology.data.DBhelper.getVerificationCode;

public class VerificationPage {
    private SelenideElement codeField = $("[data-test-id=code] input");
    private SelenideElement verifyButton = $("[data-test-id=action-verify]");
    private SelenideElement verifyNotification = $("[data-test-id=code] .input__sub");
    private SelenideElement verifyErrorNotification = $("[data-test-id=error-notification] .notification__content");

    public VerificationPage() {
        codeField.shouldBe(visible);
    }

    public void validCodeVerify(User user) {
        codeField.setValue(getVerificationCode(user));
        verifyButton.click();
        new DashboardPage();
    }

    public void EmptyCodeVerify() {
        verifyButton.click();
        verifyNotification.shouldHave(Condition.text("Поле обязательно для заполнения"))
                .shouldBe(Condition.visible);
    }

    public void invalidCodeVerify() {
        codeField.setValue(DataHelper.getNotVerificationCode());
        verifyButton.click();
        verifyErrorNotification
                .shouldHave(Condition.text("Неверно указан код! Попробуйте ещё раз."))
                .shouldBe(Condition.visible);
    }
}