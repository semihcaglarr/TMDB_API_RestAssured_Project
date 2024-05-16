import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.testng.annotations.*;

import java.util.*;

import static io.restassured.RestAssured.*;


public class Login_Try {

    String apiKey = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3ODYxODNmYmE3MDMxMjM5NzM2Mzc5M2UyZTIzMDBiYyIsInN1YiI6IjY2MzUxZGVhOTU5MGUzMDEyY2JiOGQ5ZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.Anhzli1hmyToxEJmSVeTkzPM0fiPDui3xDha5S85AQM";
    String accessToken = "Bearer " + apiKey;
    RequestSpecification reqSpec;
    String value;

    @BeforeClass
    public void test() {

        baseURI = "https://api.themoviedb.org/3";

        reqSpec = new RequestSpecBuilder()
                .addHeader("Authorization", accessToken)
                .setContentType(ContentType.JSON).build();

        Response response =
                given()
                        .spec(reqSpec)
                        .when()
                        .get("https://www.themoviedb.org/login")

                        .then()
                        //.log().body()
                        .statusCode(200)
                        .extract().response();


        String html = response.asString();

        Document doc = Jsoup.parse(html);

        Element inputElement = doc.selectFirst("input[name=authenticity_token]");

        value = inputElement.attr("value");

        System.out.println("value = " + value);
    }

    @Test
    public void ValidLogin() {

        Map<String, Object> map = new HashMap<>();
        map.put("authenticity_token", value);
        map.put("username", "bugfighters");
        map.put("password", "1234");


        given()
                .spec(reqSpec)
                .body(map)
                .contentType(ContentType.TEXT)

                .when()
                .post("https://www.themoviedb.org/login")

                .then()
                .log().body();


    }


}
