package pl.put.boardgamemanager.rest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PrivateRentalControllerTest {

    @LocalServerPort
    int port;

    @Before
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    public void should_create_getById_update_findAll_delete() {
        Long id = should_create();
        should_getById(id);
        should_update(id);
        should_find_all(4);
        should_delete(id);
        should_find_all(3);
    }

    private Long should_create() {
        JSONObject requestBody = new JSONObject();

        requestBody.put("clientId", 1);
        requestBody.put("copyId", 2);
        requestBody.put("startTime", "2019-02-16T15:00:00");
        requestBody.put("duration", 45);
        requestBody.put("status", "Everything is great");


        return given()
                .header("Accept-Encoding", "application/json")
                .header("Content-Type", "application/json; charset=UTF-8")
                .body(requestBody.toJSONString())
                .log().all()
                .when().post("/private_rentals")
                .then().log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", notNullValue())
                .body("clientId", equalTo(1))
                .body("startTime", equalTo("2019-02-16T15:00:00"))
                .body("duration", equalTo(45))
                .body("status", equalTo("Everything is great"))
                .body("readOnlyGameName", equalTo("Business Tour"))
                .extract().jsonPath().getLong("id")
                ;

    }

    private void should_getById(Long id) {
        given()
                .header("Accept-Encoding", "application/json")
                .header("Content-Type", "application/json; charset=UTF-8")
                .log().all()
                .when().get("/private_rentals/{id}", id)
                .then().log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", notNullValue())
                .body("clientId", equalTo(1))
        ;

    }

    private void should_update(Long id) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("id", id);
        requestBody.put("clientId", 7);
        requestBody.put("copyId", 5);
        requestBody.put("startTime", "2019-02-10T15:00:00");
        requestBody.put("duration", 60);
        requestBody.put("status", "Everything is not great");
        given()
                .header("Accept-Encoding", "application/json")
                .header("Content-Type", "application/json; charset=UTF-8")
                .body(requestBody.toJSONString())
                .log().all()
                .when().put("/private_rentals")
                .then().log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", notNullValue())
                .body("clientId", equalTo(7))
                .body("copyId", equalTo(5))
                .body("startTime", equalTo("2019-02-10T15:00:00"))
                .body("duration", equalTo(60))
                .body("status", equalTo("Everything is not great"));
    }


    private void should_find_all(Integer howMany) {
        given()
                .header("Accept-Encoding", "application/json")
                .header("Content-Type", "application/json; charset=UTF-8")
                .log().all()
                .when().get("/private_rentals")
                .then().log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("$", hasSize(howMany))
        ;
    }

    private void should_delete(Long id) {
        given()
                .header("Accept-Encoding", "application/json")
                .header("Content-Type", "application/json; charset=UTF-8")
                .log().all()
                .when().delete("/private_rentals/{id}", id)
                .then().log().all()
                .statusCode(200)
        ;
    }
}
