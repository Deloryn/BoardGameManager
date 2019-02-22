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
public class TableControllerTest {

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
        should_find_all();
        should_delete(id);
        should_find_without_one();
    }

    @Test
    public void should_find_available_at() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("startTime", "2019-02-18T15:00:00");
        requestBody.put("duration", "90");

        given()
                .header("Accept-Encoding", "application/json")
                .header("Content-Type", "application/json; charset=UTF-8")
                .body(requestBody.toJSONString())
                .log().all()
                .when().post("/tables/available-at")
                .then().log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("$", hasSize(6))
        ;
    }

    private Long should_create() {
        //Long id;
        //Short numberOfSits;
        JSONObject requestBody = new JSONObject();
        requestBody.put("numberOfSits", 4);
        return given()
                .header("Accept-Encoding", "application/json")
                .header("Content-Type", "application/json; charset=UTF-8")
                .body(requestBody.toJSONString())
                .log().all()
                .when().post("/tables")
                .then().log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", notNullValue())
                .body("numberOfSits", equalTo(4))
                .extract().jsonPath().getLong("id")
        ;

    }

    private void should_getById(Long id) {
        given()
                .header("Accept-Encoding", "application/json")
                .header("Content-Type", "application/json; charset=UTF-8")
                .log().all()
                .when().get("/tables/{id}", id)
                .then().log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", notNullValue())
                .body("numberOfSits", equalTo(4))
        ;

    }

    private void should_update(Long id) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("id", id);
        requestBody.put("numberOfSits", 2);
        given()
                .header("Accept-Encoding", "application/json")
                .header("Content-Type", "application/json; charset=UTF-8")
                .body(requestBody.toJSONString())
                .log().all()
                .when().put("/tables")
                .then().log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", notNullValue())
                .body("numberOfSits", equalTo(2))
         ;
    }


    private void should_find_all() {
        given()
                .header("Accept-Encoding", "application/json")
                .header("Content-Type", "application/json; charset=UTF-8")
                .log().all()
                .when().get("/tables")
                .then().log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("$", hasSize(10))
        ;
    }

    private void should_delete(Long id) {
        given()
                .header("Accept-Encoding", "application/json")
                .header("Content-Type", "application/json; charset=UTF-8")
                .log().all()
                .when().delete("/tables/{id}", id)
                .then().log().all()
                .statusCode(200)
        ;
    }

    private void should_find_without_one() {
        given()
                .header("Accept-Encoding", "application/json")
                .header("Content-Type", "application/json; charset=UTF-8")
                .log().all()
                .when().get("/tables")
                .then().log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("$", hasSize(9))
        ;
    }
}
