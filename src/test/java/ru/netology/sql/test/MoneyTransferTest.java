package ru.netology.sql.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.sql.data.APIHelper;
import ru.netology.sql.data.DataHelper;
import ru.netology.sql.data.SQLHelper;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTransferTest {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    void shouldTransferFromSecondCard() {
        var authInfo = DataHelper.getAuthInfoWithTestData();
        $("[data-test-id=login] input").setValue(authInfo.getLogin());
        $("[data-test-id=password] input").setValue(authInfo.getPassword());
        $("[data-test-id=action-login]").click();
        var verificationCode = SQLHelper.getVerificationCode();
        $("[data-test-id=code] input").setValue(String.valueOf(verificationCode));
        $("[data-test-id=action-verify]").click();
        var balanceSecondCard = SQLHelper.getBalanceSecondCard();
        var transferOperation = APIHelper.postTransfer(5000);
        var actualBalanceSecondCard = SQLHelper.getBalanceSecondCard();
        assertEquals(5000, actualBalanceSecondCard);
    }
}
