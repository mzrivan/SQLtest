package ru.netology.test;

import org.apache.commons.dbutils.QueryRunner;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.*;
import ru.netology.page.*;

import java.sql.DriverManager;
import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;

public class AuthTest {

    LoginPage loginPage;
    User user;
    VerificationPage verificationPage;

    @BeforeEach
    void setUpAll() {
        open("http://localhost:9999");
    }


    @Test
    void shouldAuthorizationValidUserValidCode() throws SQLException {
        loginPage = new LoginPage();
        user = DataHelper.getValidUser();
        verificationPage = loginPage.validLogin(user);
        verificationPage.validCodeVerify(user);
    }

    @Test
    void shouldNotAuthorizationValidUserInValidCode() throws SQLException {
        loginPage = new LoginPage();
        user = DataHelper.getValidUser();
        verificationPage = loginPage.validLogin(user);
        verificationPage.validCodeVerify(user);
    }

    @Test
    void shouldNotAuthorUserInDBwrongPass() throws SQLException{
        loginPage = new LoginPage();
        user = DataHelper.getUserInDBwrongPass();
        loginPage.invalidLogin(user);
    }

    @Test
    void shouldNotAuthorizationUserInDBwrongLogin() throws SQLException{
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
    void shouldNotAuthorizationPasswordEmpty() throws SQLException {
        loginPage = new LoginPage();
        user = DataHelper.getValidUser();
        loginPage.passwordEmpty(user);
    }

    @Test
    void shouldNotAuthorizationLoginEmpty() throws SQLException {
        loginPage = new LoginPage();
        user = DataHelper.getValidUser();
        loginPage.loginEmpty(user);
    }

    @Test
    void shouldNotAuthorizationValidUserInvalidCode() throws SQLException {
        loginPage = new LoginPage();
        user = DataHelper.getValidUser();
        verificationPage = loginPage.validLogin(user);
        verificationPage.invalidCodeVerify();
    }

    @Test
    void shouldNotAuthorizationValidUserCodeEmpty() throws SQLException {
        loginPage = new LoginPage();
        user = DataHelper.getValidUser();
        verificationPage = loginPage.validLogin(user);
        verificationPage.EmptyCodeVerify();
    }

    @AfterAll
    public static void deleteData() throws SQLException {
        var delTabletTransfer = "DELETE FROM card_transactions;";
        var delTabletCard = "DELETE FROM cards;";
        var delTabletCode = "DELETE FROM auth_codes;";
        var delTabletUser = "DELETE FROM users;";
        var runner = new QueryRunner();
        try (
                var conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "pass"
                );
        ) {
            runner.update(conn, delTabletTransfer);
            runner.update(conn, delTabletCard);
            runner.update(conn, delTabletCode);
            runner.update(conn, delTabletUser);
        }
    }
}
