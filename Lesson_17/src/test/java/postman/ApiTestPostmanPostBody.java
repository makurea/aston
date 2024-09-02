package postman;

import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ApiTestPostmanPostBody {

    @Test
    public void verifyPostRequestWithBody() {
        given()
                .baseUri("https://postman-echo.com")
                .body("{\"name\": \"George Washington\", \"age\": 57}")
                .contentType("application/json")
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .body("data.name", equalTo("George Washington"))
                .body("data.age", equalTo(57))
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.x-forwarded-proto", equalTo("http"))
                .body("headers.connection", equalTo("close"))
                .body("url", equalTo("http://postman-echo.com/post"));
    }
}
