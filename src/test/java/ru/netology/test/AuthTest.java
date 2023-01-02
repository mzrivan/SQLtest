package ru.netology.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.*;
import ru.netology.page.*;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.DBhelper.deleteData;

public class AuthTest {

    LoginPage loginPage;
    User user;
    VerificationPage verificationPage;

    @BeforeEach
    void setUpAll() {
        open("http://localhost:9999");
    }

    @AfterAll
    static void deleteAllData() {
        deleteData();
    }

    @Test
    void shouldAuthorizationValidUserValidCode() {
        loginPage = new LoginPage();
        user = DataHelper.getValidUser();
        verificationPage = loginPage.validLogin(user);
        verificationPage.validCodeVerify(user);
    }

    @Test
    void shouldNotAuthorizationValidUserInValidCode() {
        loginPage = new LoginPage();
        user = DataHelper.getValidUser();
        verificationPage = loginPage.validLogin(user);
        verificationPage.validCodeVerify(user);
    }

    @Test
    void shouldNotAuthorUserInDBwrongPass() {
        loginPage = new LoginPage();
        user = DataHelper.getUserInDBwrongPass();
        loginPage.invalidLogin(user);
    }

    @Test
    void shouldNotAuthorizationUserInDBwrongLogin() {
        loginPage = new LoginPage();
        user = DataHelper.getUserInDBwrongLogin();
        loginPage.invalidLogin(user);
    }

    @Test
    void shouldNotAuthorizationUserNotInDB() {
        loginPage = new LoginPage();
        user = DataHelper.getUserNotInDB();
        loginPage.invalidLogin(user);
    }

    @Test
    void shouldNotAuthorizationPasswordEmpty() {
        loginPage = new LoginPage();
        user = DataHelper.getValidUser();
        loginPage.passwordEmpty(user);
    }

    @Test
    void shouldNotAuthorizationLoginEmpty() {
        loginPage = new LoginPage();
        user = DataHelper.getValidUser();
        loginPage.loginEmpty(user);
    }

    @Test
    void shouldNotAuthorizationValidUserInvalidCode() {
        loginPage = new LoginPage();
        user = DataHelper.getValidUser();
        verificationPage = loginPage.validLogin(user);
        verificationPage.invalidCodeVerify();
    }

    @Test
    void shouldNotAuthorizationValidUserCodeEmpty() {
        loginPage = new LoginPage();
        user = DataHelper.getValidUser();
        verificationPage = loginPage.validLogin(user);
        verificationPage.EmptyCodeVerify();
    }
}
