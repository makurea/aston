package postman;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ApiPostmanTests {

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = "https://postman-echo.com";
    }

    @Test
    public void testGet() {
        given()
                .queryParam("name", "Thomas")
                .queryParam("occupation", "President")
                .when()
                .get("/get")
                .then()
                .statusCode(200)
                .body("args.name", equalTo("Thomas"))
                .body("args.occupation", equalTo("President"))
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.x-forwarded-proto", equalTo("http"))
                .body("headers.connection", equalTo("close"))
                .body("url", equalTo("http://postman-echo.com/get?name=Thomas&occupation=President"));
    }

    @Test
    public void testDelete() {
        given()
                .queryParam("item", "12345")
                .when()
                .delete("/delete")
                .then()
                .statusCode(200)
                .body("args.item", equalTo("12345"))
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.x-forwarded-proto", equalTo("http"))
                .body("headers.connection", equalTo("close"))
                .body("url", equalTo("http://postman-echo.com/delete?item=12345"));
    }

    @Test
    public void testPatch() {
        given()
                .contentType("application/json")
                .body("{\"title\": \"Emancipation Proclamation\"}")
                .when()
                .patch("/patch")
                .then()
                .statusCode(200)
                .body("data.title", equalTo("Emancipation Proclamation"))
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.x-forwarded-proto", equalTo("http"))
                .body("headers.connection", equalTo("close"))
                .body("url", equalTo("http://postman-echo.com/patch"));
    }

    @Test
    public void testPostBody() {
        given()
                .body("{\"event\": \"Inauguration\", \"year\": 1861}")
                .contentType("application/json")
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .body("data.event", equalTo("Inauguration"))
                .body("data.year", equalTo(1861))
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.x-forwarded-proto", equalTo("http"))
                .body("headers.connection", equalTo("close"))
                .body("url", equalTo("http://postman-echo.com/post"));
    }

    @Test
    public void testPostData() {
        given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("occupation", "Commander-in-Chief")
                .formParam("year", "1861")
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .body("form.occupation", equalTo("Commander-in-Chief"))
                .body("form.year", equalTo("1861"))
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.x-forwarded-proto", equalTo("http"))
                .body("headers.connection", equalTo("close"))
                .body("url", equalTo("http://postman-echo.com/post"));
    }

    @Test
    public void testPut() {
        given()
                .contentType("application/json")
                .body("{\"role\": \"Leader\", \"term\": 4}")
                .when()
                .put("/put")
                .then()
                .statusCode(200)
                .body("data.role", equalTo("Leader"))
                .body("data.term", equalTo(4))
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.x-forwarded-proto", equalTo("http"))
                .body("headers.connection", equalTo("close"))
                .body("url", equalTo("http://postman-echo.com/put"));
    }
}
