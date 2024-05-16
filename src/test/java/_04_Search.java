import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.*;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class _04_Search {

    String apiKey = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3ODYxODNmYmE3MDMxMjM5NzM2Mzc5M2UyZTIzMDBiYyIsInN1YiI6IjY2MzUxZGVhOTU5MGUzMDEyY2JiOGQ5ZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.Anhzli1hmyToxEJmSVeTkzPM0fiPDui3xDha5S85AQM";
    String accessToken = "Bearer " + apiKey;
    RequestSpecification reqSpec;
    int zombieProjectId;
    int breakingBadId;
    int mariaBadmajewId;

    @BeforeClass
    public void Setup() {
        baseURI = "https://api.themoviedb.org/3"; // API_URL

        reqSpec = new RequestSpecBuilder()
                .addHeader("Authorization", accessToken)
                .setContentType(ContentType.JSON)
                .build();
    }


    @Test
    public void TC_14_1_SearchforMovie() {
        System.out.println();
        System.out.println("------------TC_14_1_Search for Movie------------");

        Response returnData =
                given()
                        .spec(reqSpec)
                        .param("query", "kutsal")
                        .log().uri()

                        .when()
                        .get("/search/movie")

                        .then()
                        //.log().body()
                        .statusCode(200)
                        .extract().response();

        zombieProjectId = returnData.path("results[5].id");
        System.out.println("zombieProjectId = " + zombieProjectId);
    }


    @Test(dependsOnMethods = "TC_14_1_SearchforMovie")
    public void TC_14_2_GetMovieDetails() {
        System.out.println();
        System.out.println("------------TC_15_1_Get Movie Details------------");

        given()
                .spec(reqSpec)
                .log().uri()

                .when()
                .get("movie/" + zombieProjectId)

                .then()
                .log().body()
                .statusCode(200);
    }


    @Test
    public void TC_15_1_SearchforTvShows() {
        System.out.println();
        System.out.println("------------TC_14_2_Search for Tv Shows------------");

        breakingBadId =
                given()
                        .spec(reqSpec)
                        .param("query", "bad")
                        .log().uri()

                        .when()
                        .get("/search/tv")

                        .then()
                        // .log().body()
                        .statusCode(200)
                        .extract().response().path("results[0].id");

        System.out.println("breakingBadId = " + breakingBadId);

    }


    @Test(dependsOnMethods = "TC_15_1_SearchforTvShows")
    public void TC_15_2_GetTvShowsDetails() {
        System.out.println();
        System.out.println("------------TC_15_2_Get Tv Shows Details------------");

        given()
                .spec(reqSpec)
                .log().uri()

                .when()
                .get("tv/" + breakingBadId)

                .then()
                .log().body()
                .statusCode(200);
    }


    @Test
    public void TC_16_1_SearchforPersons() {
        System.out.println();
        System.out.println("------------TC_14_3_Search for Persons------------");

        mariaBadmajewId =
                given()
                        .spec(reqSpec)
                        .param("query", "badem")
                        .log().uri()

                        .when()
                        .get("/search/person")

                        .then()
                        //.log().body()
                        .statusCode(200)
                        .extract().jsonPath().getInt("results[18].id");

        System.out.println("mariaBadmajewId = " + mariaBadmajewId);

    }


    @Test(dependsOnMethods = "TC_16_1_SearchforPersons")
    public void TC_16_2_GetTvShowsDetails() {
        System.out.println();
        System.out.println("------------TC_16_2_Get Tv Shows Details------------");

        given()
                .spec(reqSpec)
                .log().uri()

                .when()
                .get("person/" + mariaBadmajewId)

                .then()
                .log().body()
                .statusCode(200);
    }


    @Test
    public void TC_16_3_SearchforKeywords() {
        System.out.println();
        System.out.println("------------TC_16_3_Search for Keywords------------");

        given()
                .spec(reqSpec)
                .param("query", "in")
                .log().uri()

                .when()
                .get("/search/keyword")

                .then()
                //.log().body()
                .statusCode(200);
    }


    @Test(dependsOnMethods = "TC_14_1_SearchforMovie")
    public void TC_17_1_AddMovieRating() {
        System.out.println();
        System.out.println("------------TC_17_1_Add Movie Rating------------");

        Map<String, Double> movieRating = new HashMap<>();
        movieRating.put("value", 5.5);

        given()
                .spec(reqSpec)
                .body(movieRating)
                .contentType(ContentType.JSON)

                .when()
                .post("/movie/" + zombieProjectId + "/rating")

                .then()
                .log().body()
                .statusCode(201);
    }


    @Test(dependsOnMethods = "TC_15_1_SearchforTvShows")
    public void TC_17_2_AddTvShowRating() {
        System.out.println();
        System.out.println("------------TC_17_1_Add Tv Show Rating------------");

        Map<String, Double> movieRating = new HashMap<>();
        movieRating.put("value", 9.5);

        given()
                .spec(reqSpec)
                .body(movieRating)
                .contentType(ContentType.JSON)

                .when()
                .post("/tv/" + breakingBadId + "/rating")

                .then()
                .log().body()
                .statusCode(201);
    }
}
