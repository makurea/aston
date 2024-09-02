package postman;

import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ApiTestPostmanPut {

    @Test
    public void verifyPutRequest() {
        given()
                .baseUri("https://postman-echo.com")
                .contentType("application/json")
                .body("{\"name\": \"George Washington\", \"age\": 61}")
                .when()
                .put("/put")
                .then()
                .statusCode(200)
                .body("data.name", equalTo("George Washington"))
                .body("data.age", equalTo(61))
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.x-forwarded-proto", equalTo("http"))
                .body("headers.connection", equalTo("close"))
                .body("url", equalTo("http://postman-echo.com/put"));
    }
}
