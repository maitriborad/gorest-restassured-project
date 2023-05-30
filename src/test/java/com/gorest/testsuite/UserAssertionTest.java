package com.gorest.testsuite;

import com.gorest.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserAssertionTest extends TestBase {
    static ValidatableResponse response;
    public UserAssertionTest() {
        response = given()
                .queryParam("page",1)
                .queryParam("per_page",20)
                .when()
                .get()
                .then().log().body().statusCode(200);
    }
    //1. Verify the if the total record is 20
    @Test
    public void test001(){
        response.body("size", equalTo(20));
    }
    //2. Verify the if the name of id = 5487 is equal to ”Hamsini Trivedi”
    @Test
    public void test002(){
        response.body("findAll{it.id==2272610}.name",hasItem("Mani Naik"));
    }
    @Test
    //3. Check the single ‘Name’ in the Array list (Subhashini Talwar)
    public void test003(){
        response.body("name",hasItem("Chandraayan Chopra"));
    }
    //4. Check the multiple ‘Names’ in the ArrayList (Mrs. Menaka Bharadwaj, Msgr. BodhanGuha, Karthik Dubashi IV)
    @Test
    public void test004(){
        response.body("name",hasItems("Chandraayan Chopra","Gorakhnath Adiga","Ganesh Guha"));
    }
    //5. Verify the email of userid = 5471 is equal “adiga_aanjaneya_rep@jast.org”
    @Test
    public void test005(){
        response.body("findAll{it.id==2272630}.email",hasItem("varma_bhaumik@hodkiewicz.example"));
    }
    //6. Verify the status is “Active” of user name is “Shanti Bhat V”
    @Test
    public void test006(){
        response.body("findAll{it.name=='Esha Bharadwaj'}.status",hasItem("active"));
    }
    //7. Verify the Gender = male of user name is “Niro Prajapat”
    @Test
    public void test007(){
        response.body("findAll{it.name=='Esha Bharadwaj'}.gender",hasItem("male"));
    }
}
