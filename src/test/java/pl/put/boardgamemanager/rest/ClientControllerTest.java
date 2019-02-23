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
public class ClientControllerTest {

    @LocalServerPort
    int port;

    @Before
    public void setUp() {
        RestAssured.port = port;
    }


    @Test
    public void should_find_all_clients() {
        given()
                .header("Accept-Encoding", "application/json")
                .header("Content-Type", "application/json; charset=UTF-8")
                .log().all()
                .when().get("/clients")
                .then().log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("values", hasSize(10))
        ;
    }

    @Test
    public void should_get_client_by_id() {

        //1, 'Jan', 'Kowalski', 'jan.kowalski@poczta.pl', '123456789'
        given()
                .header("Accept-Encoding", "application/json")
                .header("Content-Type", "application/json; charset=UTF-8")
                .log().all()
                .when().get("/clients/1")
                .then().log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", notNullValue())
                .body("id", equalTo(1))
                .body("name", equalTo("Jan"))
                .body("surname", equalTo("Kowalski"))
                .body("email", equalTo("jan.kowalski@poczta.pl"))
                .body("phoneNumber", equalTo("123456789"))
        ;
    }

    @Test
    public void should_get_client_by_email() {

        JSONObject requestBody = new JSONObject();
        requestBody.put("value", "jan.kowalski@poczta.pl");

        given()
                .header("Accept-Encoding", "application/json")
                .header("Content-Type", "application/json; charset=UTF-8")
                .body(requestBody.toJSONString())
                .log().all()
                .when().post("/clients/get-by-email")
                .then().log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", notNullValue())
                .body("id", equalTo(1))
                .body("name", equalTo("Jan"))
                .body("surname", equalTo("Kowalski"))
                .body("email", equalTo("jan.kowalski@poczta.pl"))
                .body("phoneNumber", equalTo("123456789"))
        ;
    }

    @Test
    public void should_create_and_delete_client() {
        Long id = should_create_client();
        should_delete_client(id);
    }


    @Test
    public void should_get_client_reservations_by_id() {
        given()
                .header("Accept-Encoding", "application/json")
                .header("Content-Type", "application/json; charset=UTF-8")
                .log().all()
                .when().get("/clients/{id}/reservations", 10)
                .then().log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("values", hasSize(1));

        given()
                .header("Accept-Encoding", "application/json")
                .header("Content-Type", "application/json; charset=UTF-8")
                .log().all()
                .when().get("/clients/{id}/reservations", 11)
                .then().log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("values", hasSize(1));


        given()
                .header("Accept-Encoding", "application/json")
                .header("Content-Type", "application/json; charset=UTF-8")
                .log().all()
                .when().get("/clients/{id}/reservations", 3)
                .then().log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("values", hasSize(0));

    }

    @Test
    public void should_get_available_tournaments() {
        given()
                .header("Accept-Encoding", "application/json")
                .header("Content-Type", "application/json; charset=UTF-8")
                .log().all()
                .when().get("/clients/{id}/available-tournaments", 10)
                .then().log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("values", hasSize(3));
    }

    @Test
    public void should_get_rentals() {
        given()
                .header("Accept-Encoding", "application/json")
                .header("Content-Type", "application/json; charset=UTF-8")
                .log().all()
                .when().get("/clients/{id}/rentals", 10)
                .then().log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("values", hasSize(0));

        given()
                .header("Accept-Encoding", "application/json")
                .header("Content-Type", "application/json; charset=UTF-8")
                .log().all()
                .when().get("/clients/{id}/rentals", 2)
                .then().log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("values", hasSize(1));
    }

    private Long should_create_client() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("name", "Ewa");
        requestBody.put("surname", "Nowak");
        requestBody.put("email", "ewa.nowak@poczta.pl");
        requestBody.put("phoneNumber", "618222222");

        Long id = given()
                .header("Accept-Encoding", "application/json")
                .header("Content-Type", "application/json; charset=UTF-8")
                .body(requestBody.toJSONString())
                .log().all()
                .when().post("/clients")
                .then().log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", notNullValue())
                .body("name", equalTo("Ewa"))
                .body("surname", equalTo("Nowak"))
                .body("email", equalTo("ewa.nowak@poczta.pl"))
                .body("phoneNumber", equalTo("618222222"))
                .extract().jsonPath().getLong("id")
        ;
        return id;
    }

    private void should_delete_client(Long id) {

        given()
                .header("Accept-Encoding", "application/json")
                .header("Content-Type", "application/json; charset=UTF-8")
                .log().all()
                .when().delete("/clients/{id}", id)
                .then().log().all()
                .statusCode(200)
        ;
    }

    @Test
    public void should_update_client() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("id", 1);
        requestBody.put("name", "Jan");
        requestBody.put("surname", "Kowal");
        requestBody.put("email", "jan.kowal@poczta.pl");
        requestBody.put("phoneNumber", "123456798");

        given()
                .header("Accept-Encoding", "application/json")
                .header("Content-Type", "application/json; charset=UTF-8")
                .body(requestBody.toJSONString())
                .log().all()
                .when().put("/clients")
                .then().log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", notNullValue())
                .body("name", equalTo("Jan"))
                .body("surname", equalTo("Kowal"))
                .body("email", equalTo("jan.kowal@poczta.pl"))
                .body("phoneNumber", equalTo("123456798"))
        ;
        // przywrócenie
        JSONObject requestBody1 = new JSONObject();
        requestBody1.put("id", 1);
        requestBody1.put("name", "Jan");
        requestBody1.put("surname", "Kowalski");
        requestBody1.put("email", "jan.kowalski@poczta.pl");
        requestBody1.put("phoneNumber", "123456789");
        given()
                .header("Accept-Encoding", "application/json")
                .header("Content-Type", "application/json; charset=UTF-8")
                .body(requestBody1.toJSONString())
                .log().all()
                .when().put("/clients")
                .then().log().all()
                .statusCode(200)
        ;

    }

    @Test
    public void should_check_if_client_exists() {
        given()
                .header("Accept-Encoding", "application/json")
                .header("Content-Type", "application/json; charset=UTF-8")
                .log().all()
                .when().get("/clients/{id}/exists", 1)
                .then().log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("value", equalTo(true));

        given()
                .header("Accept-Encoding", "application/json")
                .header("Content-Type", "application/json; charset=UTF-8")
                .log().all()
                .when().get("/clients/{id}/exists", 7)
                .then().log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("value", equalTo(true));

        given()
                .header("Accept-Encoding", "application/json")
                .header("Content-Type", "application/json; charset=UTF-8")
                .log().all()
                .when().get("/clients/{id}/exists", 28)
                .then().log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("value", equalTo(false));
    }




}

