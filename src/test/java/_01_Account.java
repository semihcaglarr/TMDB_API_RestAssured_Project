import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.*;

import java.util.*;

import static io.restassured.RestAssured.*;

public class _01_Account {
    String authenticityToken = "u6pDXt9AfgarEzvuwCUOLQTWCBRA2twh06XA0v-vrG8=";
    String apiKey = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3ODYxODNmYmE3MDMxMjM5NzM2Mzc5M2UyZTIzMDBiYyIsInN1YiI6IjY2MzUxZGVhOTU5MGUzMDEyY2JiOGQ5ZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.Anhzli1hmyToxEJmSVeTkzPM0fiPDui3xDha5S85AQM";
    String accessToken = "Bearer " + apiKey;
    RequestSpecification reqSpec;
    String userName = "bugfighters";
    String password = "1234";
    int accountId;


    @BeforeClass
    public void TC_1_ValidLogin() {
        baseURI = "https://api.themoviedb.org/3/account"; // API_URL

        reqSpec = new RequestSpecBuilder()
                .addHeader("Authorization", accessToken)
                .setContentType(ContentType.JSON)
                .build();

//        Map<String, String> credentials = new HashMap<>();
//        credentials.put("authenticity_token", authenticityToken);
//        credentials.put("username", userName);
//        credentials.put("password", password);
//
//        given()
//                .spec(reqSpec)
//                .body(credentials)
//                .contentType(ContentType.TEXT)
//
//                .when()
//                .post("/login")
//
//                .then()
//                .log().body()
//                .statusCode(200);
    }

//    @Test
//    public void TC_2_InvalidValidLogin() {
//    }


    @Test
    public void TC_3_GetAccountDetails() {
        System.out.println();
        System.out.println("------------TC_3_Get Account Details------------");

        Response returnData =
                given()
                        .spec(reqSpec)

                        .when()
                        .get()

                        .then()
                        .log().body()
                        .statusCode(200)
                        .extract().response();

        int accountId = returnData.getBody().jsonPath().getInt("id");
        System.out.println("accountId = " + accountId);
    }

    @Test(dependsOnMethods = "TC_3_GetAccountDetails")
    public void TC_4_AddMovietoFavorites() {
        System.out.println();
        System.out.println("------------TC_4_Add Movie to Favorites------------");

        Map<String, Object> addMovietoFavoritesMap = new HashMap<>();
        addMovietoFavoritesMap.put("media_type", "movie");
        addMovietoFavoritesMap.put("media_id", 278);
        addMovietoFavoritesMap.put("favorite", true);

        given()
                .spec(reqSpec)
                .body(addMovietoFavoritesMap)
                .contentType(ContentType.JSON)

                .when()
                //.post("/account/" + accountId + "/favorite")
                .post("/" + accountId + "/favorite")

                .then()
                .statusCode(201)
                .log().body();
    }

    @Test(dependsOnMethods = "TC_4_AddMovietoFavorites")
    public void TC_5_AddMovietoWatchList() {
        System.out.println();
        System.out.println("------------TC_5_Add Movie to Watch List------------");

        Map<String, Object> addMovietoWatchListMap = new HashMap<>();
        addMovietoWatchListMap.put("media_type", "movie");
        addMovietoWatchListMap.put("media_id", 296098);
        addMovietoWatchListMap.put("watchlist", true);


        given()
                .spec(reqSpec)
                .body(addMovietoWatchListMap)
                .contentType(ContentType.JSON)

                .when()
                .post("/" + accountId + "/watchlist")


                .then()
                .statusCode(201)
                .log().body();
    }

    @Test(dependsOnMethods = "TC_5_AddMovietoWatchList")
    public void TC_6_GetFavoriteMovies() {
        System.out.println();
        System.out.println("------------TC_6_Get Favorite Movies------------");

        given()
                .spec(reqSpec)

                .when()
                .get("/" + accountId + "/favorite/movies")

                .then()
                .log().body()
                .statusCode(200);
    }

    @Test(dependsOnMethods = "TC_6_GetFavoriteMovies")
    public void TC_7_GetRatedMovies() {
        System.out.println();
        System.out.println("------------TC_7_Get Rated Movies------------");

        given()
                .spec(reqSpec)

                .when()
                .get("/" + accountId + "/rated/movies")

                .then()
                .statusCode(200)
                .log().body();
    }

    @Test(dependsOnMethods = "TC_7_GetRatedMovies")
    public void TC_8_GetWatchlistMovies() {
        System.out.println();
        System.out.println("------------TC_8_Get Watch List Movies------------");

        given()
                .spec(reqSpec)

                .when()
                .get("/" + accountId + "/watchlist/movies")

                .then()
                .statusCode(200)
                .log().body();
    }
}
