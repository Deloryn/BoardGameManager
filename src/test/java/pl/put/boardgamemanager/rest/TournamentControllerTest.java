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

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TournamentControllerTest {

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
        List<Long> tableIds = new ArrayList<>();
        List<Long> copyIds = new ArrayList<>();

        tableIds.add(1L);
        copyIds.add(1L);

        requestBody.put("gameId", 1);
        requestBody.put("startTime", "2019-02-14T15:00:00");
        requestBody.put("duration", 45);
        requestBody.put("maxPlayers", 8);
        requestBody.put("tableIds", tableIds);
        requestBody.put("copyIds", copyIds);

        return given()
                .header("Accept-Encoding", "application/json")
                .header("Content-Type", "application/json; charset=UTF-8")
                .body(requestBody.toJSONString())
                .log().all()
                .when().post("/tournaments")
                .then().log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", notNullValue())
                .body("gameId", equalTo(1))
                .body("startTime", equalTo("2019-02-14T15:00:00"))
                .body("duration", equalTo(45))
                .body("maxPlayers", equalTo(8))
                .extract().jsonPath().getLong("id")
                ;

    }

    private void should_getById(Long id) {
        given()
                .header("Accept-Encoding", "application/json")
                .header("Content-Type", "application/json; charset=UTF-8")
                .log().all()
                .when().get("/tournaments/{id}", id)
                .then().log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", notNullValue())
                .body("gameId", equalTo(1))
                .body("startTime", equalTo("2019-02-14T15:00:00"))
                .body("duration", equalTo(45))
                .body("maxPlayers", equalTo(8))
        ;

    }

    private void should_update(Long id) {
        JSONObject requestBody = new JSONObject();

        requestBody.put("id", id);
        requestBody.put("gameId", 2);
        requestBody.put("startTime", "2019-02-14T15:00:00");
        requestBody.put("duration", 45);
        requestBody.put("maxPlayers", 8);
        given()
                .header("Accept-Encoding", "application/json")
                .header("Content-Type", "application/json; charset=UTF-8")
                .body(requestBody.toJSONString())
                .log().all()
                .when().put("/tournaments")
                .then().log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", notNullValue())
                .body("gameId", equalTo(2))
                .body("startTime", equalTo("2019-02-14T15:00:00"))
                .body("duration", equalTo(45))
                .body("maxPlayers", equalTo(8));
    }


    private void should_find_all(Integer howMany) {
        given()
                .header("Accept-Encoding", "application/json")
                .header("Content-Type", "application/json; charset=UTF-8")
                .log().all()
                .when().get("/tournaments")
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
                .when().delete("/tournaments/{id}", id)
                .then().log().all()
                .statusCode(200)
        ;
    }
}
