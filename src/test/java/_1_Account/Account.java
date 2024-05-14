package _1_Account;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.*;

import java.util.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Account {
    String authenticityToken = "U0h_1pqfGunI13REsaWZfSweeaitWnj6XoLtGoNk4BI=";
    String apiKey = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3ODYxODNmYmE3MDMxMjM5NzM2Mzc5M2UyZTIzMDBiYyIsInN1YiI6IjY2MzUxZGVhOTU5MGUzMDEyY2JiOGQ5ZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.Anhzli1hmyToxEJmSVeTkzPM0fiPDui3xDha5S85AQM";
    String accessToken = "Bearer " + apiKey;
    RequestSpecification reqSpec;
    String userName = "bugfighters";
    String password = "1234";


    @BeforeClass
    public void TC_1_ValidLogin() {
        baseURI = "https://www.themoviedb.org";

        reqSpec = new RequestSpecBuilder()
                .addHeader("Authorization", accessToken)
                .setContentType(ContentType.JSON)
                .build();

        Map<String, String> credentials = new HashMap<>();
        credentials.put("authenticity_token", authenticityToken);
        credentials.put("username", userName);
        credentials.put("password", password);

        given()
                .spec(reqSpec)
                .body(credentials)
                .contentType(ContentType.TEXT)

                .when()
                .post("/login")

                .then()
                .log().body()
                .statusCode(200)

        ;


    }
}
