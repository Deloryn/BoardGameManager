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

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TournamentParticipantControllerTest {

    @LocalServerPort
    int port;

    @Before
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    public void should_create_getById_update_findAll_delete() {
        Integer clientId = 7;
        Integer tournamentId = 3;

        should_create(clientId, tournamentId);
        should_getByPK(clientId, tournamentId);
        should_find_all(11);
        should_delete(clientId, tournamentId);
        should_find_all(10);
    }

    private void should_create(Integer clientId, Integer tournamentId) {
        JSONObject requestBody = new JSONObject();

        requestBody.put("clientId", clientId);
        requestBody.put("tournamentId", tournamentId);

        given()
                .header("Accept-Encoding", "application/json")
                .header("Content-Type", "application/json; charset=UTF-8")
                .body(requestBody.toJSONString())
                .log().all()
                .when().post("/tournament_participants")
                .then().log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("clientId", equalTo(clientId))
                .body("tournamentId", equalTo(tournamentId));
    }

    private void should_getByPK(Integer clientId, Integer tournamentId) {
        given()
                .header("Accept-Encoding", "application/json")
                .header("Content-Type", "application/json; charset=UTF-8")
                .log().all()
                .when().get("/tournament_participants/{clientId}/{tournamentId}", clientId, tournamentId)
                .then().log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("clientId", equalTo(clientId))
                .body("tournamentId", equalTo(tournamentId));
    }

    private void should_find_all(Integer howMany) {
        given()
                .header("Accept-Encoding", "application/json")
                .header("Content-Type", "application/json; charset=UTF-8")
                .log().all()
                .when().get("/tournament_participants")
                .then().log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("values", hasSize(howMany))
        ;
    }

    private void should_delete(Integer clientId, Integer tournamentId) {
        given()
                .header("Accept-Encoding", "application/json")
                .header("Content-Type", "application/json; charset=UTF-8")
                .log().all()
                .when().delete("/tournament_participants/{clientId}/{tournamentId}", clientId, tournamentId)
                .then().log().all()
                .statusCode(200)
        ;
    }

}
