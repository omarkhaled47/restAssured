package restAssured.testcases;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import restAssured.pojo.addUserPojo;

import java.io.File;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class addUserTest {

    @Test
    public void addUser (){

        File userInfo = new File("src/test/resources/user.json");

        given()
                .baseUri("https://643089673adb1596515cac02.mockapi.io")
                .contentType(ContentType.JSON)
                .body(userInfo)
        .when()
                .post("/api/v1/users")
        .then()
                .log().all()
                .assertThat().statusCode(201);

    }

    @Test
    public void addUserPojo (){

        addUserPojo user = new addUserPojo("Omar Test","Egypt");

        given()
                .baseUri("https://643089673adb1596515cac02.mockapi.io")
                .contentType(ContentType.JSON)
                .body(user)
        .when()
                .post("/api/v1/users")
        .then()
                .log().all()
                .assertThat().statusCode(201);

    }
}
