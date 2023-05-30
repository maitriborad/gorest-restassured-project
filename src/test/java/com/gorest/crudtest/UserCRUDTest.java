package com.gorest.crudtest;

import com.gorest.model.UserPojo;
import com.gorest.testbase.TestBase;
import com.gorest.utils.TestUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class UserCRUDTest extends TestBase {
    static String name="Maitri"+ TestUtils.getRandomValue();
    static String email="Maitri"+TestUtils.getRandomValue()+"@gmail.com";
    static String gender="Female";
    static String status="active";
    static int id;
    @Test
    public void test001(){
        UserPojo userPojo=new UserPojo();
        userPojo.setName(name);
        userPojo.setEmail(email);
        userPojo.setGender(gender);
        userPojo.setStatus(status);
        ValidatableResponse response= given()
                .contentType(ContentType.JSON)
                .header("Accept","application/json")
                .header("Authorization","Bearer 13eb5d72be985bf927680ae4be97ec5c6f76dcffe0aaa2235c207d32925d495e")
                .when()
                .body(userPojo)
                .post()
                .then().log().body().statusCode(201);
        id=response.extract().path("id");
    }
    @Test
    public void test002(){
        int uId=given()
                .header("Authorization","Bearer 13eb5d72be985bf927680ae4be97ec5c6f76dcffe0aaa2235c207d32925d495e")
                .pathParams("id",id)
                .when()
                .get("/{id}")
                .then().statusCode(200)
                .extract()
                .path("id");
        Assert.assertEquals(uId,id);
    }
    @Test
    public void test003(){
         UserPojo userPojo=new UserPojo();
        userPojo.setName("Milan");
        userPojo.setEmail("Milan"+TestUtils.getRandomValue()+"@gmail.com");
        userPojo.setGender("male");
        userPojo.setStatus("inactive");
        Response response= given()
                .header("Content-Type", "application/json")
                .header("Authorization","Bearer 13eb5d72be985bf927680ae4be97ec5c6f76dcffe0aaa2235c207d32925d495e")
                .pathParams("id", id)
                .when()
                .body(userPojo)
                .put("/{id}");
        response.then().log().body().statusCode(200);
    }
    @Test
    public void test004(){
        given()
                .header("Authorization","Bearer 13eb5d72be985bf927680ae4be97ec5c6f76dcffe0aaa2235c207d32925d495e")
                .pathParam("id", id)
                .when()
                .delete("/{id}")
                .then()
                .statusCode(204);

        given()
                .header("Authorization","Bearer 13eb5d72be985bf927680ae4be97ec5c6f76dcffe0aaa2235c207d32925d495e")
                .pathParam("id", id)
                .when()
                .get("/{id}")
                .then().statusCode(404);
    }
}
