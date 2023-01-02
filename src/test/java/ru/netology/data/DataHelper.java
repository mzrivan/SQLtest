package ru.netology.data;

import com.github.javafaker.Faker;

import java.util.Locale;

import static ru.netology.data.DBhelper.queryUpdate;

public class DataHelper {

    private DataHelper() {
    }

    public static User getValidUser() {
        var faker = new Faker();
        var dataSQL = "INSERT INTO users(id, login, password, status) VALUES (?, ?,?,?);";
        String id = faker.regexify("[0-9]{10}");
        String login = faker.name().username();
        String password = "$2a$10$7yHUvStzBubZ1s7kZrwYGujHyNzGt5rmv29o.vgNBkqwQ.5bzKH9i";
        String status = "active";
        String[] userParams= {id, login, password, status};
        queryUpdate(dataSQL, userParams);
        return new User(id, login, "qwerty123", status);
    }

    public static User getUserInDBwrongLogin(){
        var faker = new Faker();
        var dataSQL = "INSERT INTO users(id, login, password, status) VALUES (?, ?,?,?);";
        String id = faker.regexify("[0-9]{10}");
        String login = faker.name().username();
        String password = "$2a$10$7yHUvStzBubZ1s7kZrwYGujHyNzGt5rmv29o.vgNBkqwQ.5bzKH9i";
        String status = "active";
        String[] userParams= {id, login, password, status};
        queryUpdate(dataSQL, userParams);
        return new User(id, faker.regexify("[0-9]{10}"), "qwerty123", status);
    }

    public static User getUserInDBwrongPass() {
        var faker = new Faker();
        var dataSQL = "INSERT INTO users(id, login, password, status) VALUES (?, ?,?,?);";
        String id = faker.regexify("[0-9]{10}");
        String login = faker.name().username();
        String password = "$2a$10$7yHUvStzBubZ1s7kZrwYGujHyNzGt5rmv29o.vgNBkqwQ.5bzKH9i";
        String status = "active";
        String[] userParams= {id, login, password, status};
        queryUpdate(dataSQL, userParams);
        return new User(id, login, faker.regexify("[0-9]{10}"), status);
    }

    public static User getUserNotInDB() {
        var faker = new Faker();
        String id = faker.regexify("[0-9]{10}");
        String login = faker.name().username();
        String password = "$2a$10$7yHUvStzBubZ1s7kZrwYGujHyNzGt5rmv29o.vgNBkqwQ.5bzKH9i";
        String status = "active";
        return new User(id, login, password, status);
    }

    public static String getNotVerificationCode() {
        Faker faker = new Faker(new Locale("ru"));
        return faker.regexify("[0-9]{6}");
    }
}
