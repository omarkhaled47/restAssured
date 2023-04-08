package restAssured.testcases;

import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class getUserTest
{

    @Test
    public void checkUsers() {
        given().
                baseUri("https://643089673adb1596515cac02.mockapi.io")
        .when()
                .get("/api/v1/users")
        .then()
                .log().body()
                .assertThat().statusCode(200);

    }

    @Test
    public void testExtract () {
        String name = given()
                .baseUri("https://643089673adb1596515cac02.mockapi.io")
        .when()
                .get("/api/v1/users")
        .then()
                .assertThat().statusCode(200)
                .extract().response().path("[3].name");

        System.out.println(name);
    }

    @Test
    public void testBody() {
        given().
                baseUri("https://643089673adb1596515cac02.mockapi.io")
        .when()
                .get("/api/v1/users")
        .then()
                .assertThat().statusCode(200)
                .assertThat().body("name",is(not(empty())))
                .assertThat().body("[3].name",is(equalTo("Omar Khaled")))
                .assertThat().body("createdAt",everyItem(startsWith("2023")));
    }

    @Test
    public void testQueryParameters() {

        HashMap<String,String> user = new HashMap<>();
        user.put("name","Omar Khaled");

        given()
                .baseUri("https://643089673adb1596515cac02.mockapi.io")
                .queryParams(user)
        .when()
                .get("/api/v1/users")
        .then()
                .log().all()
                .assertThat().statusCode(200);
    }


}
