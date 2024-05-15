import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class _04_Search {

    String apiKey = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3ODYxODNmYmE3MDMxMjM5NzM2Mzc5M2UyZTIzMDBiYyIsInN1YiI6IjY2MzUxZGVhOTU5MGUzMDEyY2JiOGQ5ZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.Anhzli1hmyToxEJmSVeTkzPM0fiPDui3xDha5S85AQM";
    String accessToken = "Bearer " + apiKey;
    RequestSpecification reqSpec;

    @BeforeClass
    public void Setup() {
        baseURI = "https://api.themoviedb.org/3/search"; // API_URL

        reqSpec = new RequestSpecBuilder()
                .addHeader("Authorization", accessToken)
                .setContentType(ContentType.JSON)
                .build();
    }

    @Test
    public void TC_14_1_SearchforMovie() {
        System.out.println();
        System.out.println("------------TC_14_1_Search for Movie------------");

        given()
                .spec(reqSpec)
                .param("query", "kutsal")
                .log().uri()

                .when()
                .get("/movie")

                .then()
                .statusCode(200)
                .log().body();
    }
}
