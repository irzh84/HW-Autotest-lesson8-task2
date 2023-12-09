package ru.netology.sql.data;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static ru.netology.sql.data.DataHelper.getTransferData;

public class APIHelper {
    private static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON) // тип отправляемого и принимаего контента
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL) // уровень логирования
            .build();

    private static DataHelper.TransferData sendPostTransfer(DataHelper.TransferData transfer) {
        given()
                .spec(requestSpec)
                .body(transfer)
                .when()
                .post("/api/transfer")
                .then()
                .statusCode(200);
        return transfer;
    }

    public static DataHelper.TransferData postTransfer(int amount) {
        return sendPostTransfer(getTransferData(amount));
    }
}








