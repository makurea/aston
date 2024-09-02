package postman;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ApiTestPostmanGet {

    @BeforeAll
    public static void setup() {

    }

    @Test
    public void verifyGetRequest() {
        given()
                .baseUri("https://postman-echo.com")
                .param("firstName", "George")
                .param("lastName", "Washington")
                .when()
                .get("/get")
                .then()
                .statusCode(200)
                .body("args.firstName", equalTo("George"))
                .body("args.lastName", equalTo("Washington"))
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.x-forwarded-proto", equalTo("http"))
                .body("headers.connection", equalTo("close"))
                .body("url", equalTo("http://postman-echo.com/get?firstName=George&lastName=Washington"));
    }
}
