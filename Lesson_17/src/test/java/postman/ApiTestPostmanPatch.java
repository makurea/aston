package postman;

import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ApiTestPostmanPatch {

    @Test
    public void verifyPatchRequest() {
        given()
                .baseUri("https://postman-echo.com")
                .contentType("application/json")
                .body("{\"name\": \"George Washington\"}")
                .when()
                .patch("/patch")
                .then()
                .statusCode(200)
                .body("data.name", equalTo("George Washington"))
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.x-forwarded-proto", equalTo("http"))
                .body("headers.connection", equalTo("close"))
                .body("url", equalTo("http://postman-echo.com/patch"));
    }
}
