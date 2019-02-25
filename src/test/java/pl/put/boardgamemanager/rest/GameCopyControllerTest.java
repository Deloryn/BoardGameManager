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
public class GameCopyControllerTest {

    @LocalServerPort
    int port;

    @Before
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    public void should_count_games_by_gameId() {
        given()
                .header("Accept-Encoding", "application/json")
                .header("Content-Type", "application/json; charset=UTF-8")
                .log().all()
                .when().get("/game_copies/count/{gameId}", 1)
                .then().log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("value", equalTo(3));

        given()
                .header("Accept-Encoding", "application/json")
                .header("Content-Type", "application/json; charset=UTF-8")
                .log().all()
                .when().get("/game_copies/count/{gameId}", 2)
                .then().log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("value", equalTo(2));

        given()
                .header("Accept-Encoding", "application/json")
                .header("Content-Type", "application/json; charset=UTF-8")
                .log().all()
                .when().get("/game_copies/count/{gameId}", 5)
                .then().log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("value", equalTo(0));

        given()
                .header("Accept-Encoding", "application/json")
                .header("Content-Type", "application/json; charset=UTF-8")
                .log().all()
                .when().get("/game_copies/count/{gameId}", 20)
                .then().log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("errorMessage", equalTo("There is no game with the given id"));
    }

    @Test
    public void should_find_copies_available_at_private() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("startTime", "2019-02-18T10:00:00");
        requestBody.put("duration", 90);
        requestBody.put("targetId", 1);

        given()
                .header("Accept-Encoding", "application/json")
                .header("Content-Type", "application/json; charset=UTF-8")
                .body(requestBody.toJSONString())
                .log().all()
                .when().post("/game_copies/available-at-private")
                .then().log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("values", hasSize(5))
                .body("values[0].game.id", equalTo(1))
                .body("values[0].gameCopies", hasSize(2));
    }

    @Test
    public void should_find_copies_available_at_tournament() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("startTime", "2019-02-18T15:00:00");
        requestBody.put("duration", 90);
        requestBody.put("targetId", 1);

        given()
                .header("Accept-Encoding", "application/json")
                .header("Content-Type", "application/json; charset=UTF-8")
                .body(requestBody.toJSONString())
                .log().all()
                .when().post("/game_copies/available-at-tournament")
                .then().log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("values", hasSize(5))
                .body("values[0].game.id", equalTo(1))
                .body("values[0].gameCopies", hasSize(3));
    }

    @Test
    public void should_create_getById_update_findAll_delete() {
        Long id = should_create();
        should_getById(id);
        should_update(id);
        should_find_all(11);
        should_delete(id);
        should_find_all(10);
    }

    private Long should_create() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("gameId", 5);
        return given()
                .header("Accept-Encoding", "application/json")
                .header("Content-Type", "application/json; charset=UTF-8")
                .body(requestBody.toJSONString())
                .log().all()
                .when().post("/game_copies")
                .then().log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", notNullValue())
                .body("gameId", equalTo(5))
                .extract().jsonPath().getLong("id")
                ;

    }

    private void should_getById(Long id) {
        given()
                .header("Accept-Encoding", "application/json")
                .header("Content-Type", "application/json; charset=UTF-8")
                .log().all()
                .when().get("/game_copies/{id}", id)
                .then().log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", notNullValue())
                .body("gameId", equalTo(5))
        ;

    }

    private void should_update(Long id) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("id", id);
        requestBody.put("gameId", 3);
        given()
                .header("Accept-Encoding", "application/json")
                .header("Content-Type", "application/json; charset=UTF-8")
                .body(requestBody.toJSONString())
                .log().all()
                .when().put("/game_copies")
                .then().log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", notNullValue())
                .body("gameId", equalTo(3))
        ;
    }


    private void should_find_all(Integer howMany) {
        given()
                .header("Accept-Encoding", "application/json")
                .header("Content-Type", "application/json; charset=UTF-8")
                .log().all()
                .when().get("/game_copies")
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
                .when().delete("/game_copies/{id}", id)
                .then().log().all()
                .statusCode(200)
        ;
    }

}
