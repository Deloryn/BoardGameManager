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
public class TournamentRentalControllerTest {

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
        should_find_all(5);
        should_delete(id);
        should_find_all(4);
    }

    private Long should_create() {
        JSONObject requestBody = new JSONObject();

        requestBody.put("copyId", 7);
        requestBody.put("tournamentId", 3);

        return given()
                .header("Accept-Encoding", "application/json")
                .header("Content-Type", "application/json; charset=UTF-8")
                .body(requestBody.toJSONString())
                .log().all()
                .when().post("/tournament_rentals")
                .then().log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", notNullValue())
                .body("copyId", equalTo(7))
                .body("tournamentId", equalTo(3))
                .extract().jsonPath().getLong("id")
                ;

    }

    private void should_getById(Long id) {
        given()
                .header("Accept-Encoding", "application/json")
                .header("Content-Type", "application/json; charset=UTF-8")
                .log().all()
                .when().get("/tournament_rentals/{id}", id)
                .then().log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", notNullValue())
                .body("copyId", equalTo(7))
                .body("tournamentId", equalTo(3))
        ;

    }

    private void should_update(Long id) {
        JSONObject requestBody = new JSONObject();

        requestBody.put("id", id);
        requestBody.put("copyId", 8);
        requestBody.put("tournamentId", 3);

        given()
                .header("Accept-Encoding", "application/json")
                .header("Content-Type", "application/json; charset=UTF-8")
                .body(requestBody.toJSONString())
                .log().all()
                .when().put("/tournament_rentals")
                .then().log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", notNullValue())
                .body("copyId", equalTo(8))
                .body("tournamentId", equalTo(3));
    }


    private void should_find_all(Integer howMany) {
        given()
                .header("Accept-Encoding", "application/json")
                .header("Content-Type", "application/json; charset=UTF-8")
                .log().all()
                .when().get("/tournament_rentals")
                .then().log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("values", hasSize(howMany))
        ;
    }

    private void should_delete(Long id) {
        given()
                .header("Accept-Encoding", "application/json")
                .header("Content-Type", "application/json; charset=UTF-8")
                .log().all()
                .when().delete("/tournament_rentals/{id}", id)
                .then().log().all()
                .statusCode(200)
        ;
    }

}
