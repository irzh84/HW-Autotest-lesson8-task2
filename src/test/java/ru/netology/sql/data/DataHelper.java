package ru.netology.sql.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;

public class DataHelper {
    private static final Faker faker = new Faker(new Locale("en"));

    private DataHelper() {
    }

    public static AuthInfo getAuthInfoWithTestData() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static TransferData getTransferData(int amount) {
        return new TransferData("5559 0000 0000 0002", "5559 0000 0000 0008", "5000");
    }

    @Value
    public static class AuthInfo {
        String login;
        String password;
    }

    @Value
    public static class VerificationCode {
        String code;
    }

    @Value
    public static class CardSecondInfoBalance {
        int balance;
    }

    @Value
    public static class TransferData {
        String fromSecondCard;
        String toAnyCard;
        String amount;
    }
}
