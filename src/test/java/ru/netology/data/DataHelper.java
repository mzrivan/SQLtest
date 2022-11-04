package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Data;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;

@Data
public class DataHelper {

    private DataHelper() {
    }

    public static User getValidUser() throws SQLException {
        var faker = new Faker();
        var runner = new QueryRunner();
        var dataSQL = "INSERT INTO users(id, login, password, status) VALUES (?, ?,?,?);";
        String id = faker.regexify("[0-9]{10}");
        String login = faker.name().username();
        String password = "$2a$10$7yHUvStzBubZ1s7kZrwYGujHyNzGt5rmv29o.vgNBkqwQ.5bzKH9i";
        String status = "active";
        try (
                var conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "pass"
                );
        ) {
            runner.update(conn, dataSQL, id, login, password, status);
        }
        return new User(id, login, "qwerty123", status);
    }

    public static User getUserInDBwrongLogin() throws SQLException {
        var faker = new Faker();
        var runner = new QueryRunner();
        var dataSQL = "INSERT INTO users(id, login, password, status) VALUES (?, ?,?,?);";
        String id = faker.regexify("[0-9]{10}");
        String login = faker.name().username();
        String password = "$2a$10$7yHUvStzBubZ1s7kZrwYGujHyNzGt5rmv29o.vgNBkqwQ.5bzKH9i";
        String status = "active";
        try (
                var conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "pass"
                );
        ) {
            runner.update(conn, dataSQL, id, login, password, status);
        }
        return new User(id, faker.regexify("[0-9]{10}"), "qwerty123", status);
    }

    public static User getUserInDBwrongPass() throws SQLException {
        var faker = new Faker();
        var runner = new QueryRunner();
        var dataSQL = "INSERT INTO users(id, login, password, status) VALUES (?, ?,?,?);";
        String id = faker.regexify("[0-9]{10}");
        String login = faker.name().username();
        String password = "$2a$10$7yHUvStzBubZ1s7kZrwYGujHyNzGt5rmv29o.vgNBkqwQ.5bzKH9i";
        String status = "active";
        try (
                var conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "pass"
                );
        ) {
            runner.update(conn, dataSQL, id, login, password, status);
        }
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


    public static String getVerificationCode(User user) throws SQLException {
        var runner = new QueryRunner();
        var dataSQL = "SELECT code FROM auth_codes WHERE user_id = ?";
        try (
                var conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "pass"
                );
        ) {
            return runner.query(conn, dataSQL, new ScalarHandler<>(), user.getId());
        }
    }

    public static String getNotVerificationCode() {
        Faker faker = new Faker(new Locale("ru"));
        return faker.regexify("[0-9]{6}");
    }
}
