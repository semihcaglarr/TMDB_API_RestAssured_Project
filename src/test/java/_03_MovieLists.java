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
    public void Setup() {
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
                //.log().body()
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
                //.log().body()
                .statusCode(200);
    }

    @Test(dependsOnMethods = "TC_11_GetPopularMovies")
    public void TC_12_GetTopRatedMovies() {
        System.out.println();
        System.out.println("------------TC_12_Get Top Rated Movies------------");

        given()
                .spec(reqSpec)

                .when()
                .get("/top_rated")

                .then()
                //.log().body()
                .statusCode(200);
    }

    @Test(dependsOnMethods = "TC_12_GetTopRatedMovies")
    public void TC_13_UpComingMovies() {
        System.out.println();
        System.out.println("------------TC_13_Up Coming Movies------------");

        given()
                .spec(reqSpec)

                .when()
                .get("/upcoming")

                .then()
                .log().body()
                .statusCode(200);
    }
}
