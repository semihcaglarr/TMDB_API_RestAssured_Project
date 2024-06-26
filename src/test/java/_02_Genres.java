import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.*;

import static io.restassured.RestAssured.*;

public class _02_Genres {

    String apiKey = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3ODYxODNmYmE3MDMxMjM5NzM2Mzc5M2UyZTIzMDBiYyIsInN1YiI6IjY2MzUxZGVhOTU5MGUzMDEyY2JiOGQ5ZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.Anhzli1hmyToxEJmSVeTkzPM0fiPDui3xDha5S85AQM";
    String accessToken = "Bearer " + apiKey;
    RequestSpecification reqSpec;

    @BeforeClass
    public void Setup() {
        baseURI = "https://api.themoviedb.org/3/genre"; // API_URL

        reqSpec = new RequestSpecBuilder()
                .addHeader("Authorization", accessToken)
                .setContentType(ContentType.JSON)
                .build();
    }

    @Test
    public void TC_9_GetMovieGenres() {
        System.out.println();
        System.out.println("------------TC_9_Get Movie Genres------------");

        given()
                .spec(reqSpec)

                .when()
                .get("/movie/list")

                .then()
                .statusCode(200)
                .log().body();
    }

    @Test(dependsOnMethods = "TC_9_GetMovieGenres")
    public void TC_9_1_GetTvShowGenres() {
        System.out.println();
        System.out.println("------------TC_9_1_Get Tv Show Genres------------");

        given()
                .spec(reqSpec)

                .when()
                .get("/tv/list")

                .then()
                .statusCode(200)
                .log().body();
    }
}
