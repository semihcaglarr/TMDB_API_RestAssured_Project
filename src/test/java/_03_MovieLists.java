import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.*;

import static io.restassured.RestAssured.*;

public class _03_MovieLists {

    String apiKey = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3ODYxODNmYmE3MDMxMjM5NzM2Mzc5M2UyZTIzMDBiYyIsInN1YiI6IjY2MzUxZGVhOTU5MGUzMDEyY2JiOGQ5ZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.Anhzli1hmyToxEJmSVeTkzPM0fiPDui3xDha5S85AQM";
    String accessToken = "Bearer " + apiKey;
    RequestSpecification reqSpec;

    @BeforeClass
    public void TC_1_ValidLogin() {
        baseURI = "https://api.themoviedb.org/3/movie"; // API_URL

        reqSpec = new RequestSpecBuilder()
                .addHeader("Authorization", accessToken)
                .setContentType(ContentType.JSON)
                .build();

    }

    @Test
    public void TC_10_GetNowPlayingMovies() {
        System.out.println();
        System.out.println("------------TC_10_Get Now Playing Movies------------");

        given()
                .spec(reqSpec)

                .when()
                .get("/now_playing")

                .then()
                .log().body()
                .statusCode(200);
    }

    @Test(dependsOnMethods = "TC_10_GetNowPlayingMovies")
    public void TC_11_GetPopularMovies() {
        System.out.println();
        System.out.println("------------TC_11_Get Popular Movies------------");

        given()
                .spec(reqSpec)

                .when()
                .get("/popular")

                .then()
                .log().body()
                .statusCode(200);
    }



}
