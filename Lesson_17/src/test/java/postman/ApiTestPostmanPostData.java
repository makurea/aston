package postman;

import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ApiTestPostmanPostData {

    @Test
    public void verifyPostRequestWithFormData() {
        given()
                .baseUri("https://postman-echo.com")
                .contentType("application/x-www-form-urlencoded")
                .formParam("name", "George Washington")
                .formParam("age", "57")
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .body("form.name", equalTo("George Washington"))
                .body("form.age", equalTo("57"))
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.x-forwarded-proto", equalTo("http"))
                .body("headers.connection", equalTo("close"))
                .body("url", equalTo("http://postman-echo.com/post"));
    }
}
