package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PostsAssertionTest {
    static ValidatableResponse response;
    @BeforeClass
    public static void inIt() {

        RestAssured.baseURI = "https://gorest.co.in/";
        RestAssured.basePath = "public/v2";

        response = given()
                .queryParam("page", 1)
                .queryParam("per_page", 25)
                .when()
                .get("/posts")
                .then().log().body().statusCode(200);
    }
//1. Verify the if the total record is 25
@Test
public void verifyTotal(){
    response.body("size()",equalTo(25));
}
//2. Verify the if the title of id = 2730 is equal to ”Ad ipsa coruscus ipsam eos demitto
//    centum.”
@Test
public void verifyTitle(){
    response.body("findAll{it.id==39292}.title",hasItem("Suadeo ducimus qui calculus creo viridis amplexus coadunatio."));
}
//3. Check the single user_id in the Array list (5522)
@Test
public void verifyUserId(){
    response.body("user_id",hasItem(2272633));
}
//4. Check the multiple ids in the ArrayList (2693, 2684,2681)
@Test
public void verifyUserIds(){
    response.body("id",hasItems(39264,39265,39266));
}
//5. Verify the body of userid = 2678 is equal “Carus eaque voluptatem. Calcar
//    spectaculum coniuratio. Abstergo consequatur deleo. Amiculum advenio dolorem.
//    Sollers conservo adiuvo. Concedo campana capitulus. Adfectus tibi truculenter.
//    Canto temptatio adimpleo. Ter degenero animus. Adeo optio crapula. Abduco et
//    antiquus. Chirographum baiulus spoliatio. Suscipit fuga deleo. Comburo aequus
//    cuppedia. Crur cuppedia voluptates. Argentum adduco vindico. Denique undique
//    adflicto. Assentator umquam pel."”
@Test
public void verifyBody(){
    response.body("findAll{it.user_id==2272684}.body",hasItem("Adhuc crebro odit. Tres tredecim cubo. Adfectus universe crustulum. Thorax altus varius. Defigo utor succurro. Denique enim aliqua. Similique torqueo cogo. Succurro triginta thymum. Delectatio desolo atrox. Damno expedita accendo. Nemo cursim tenetur. Utor tamen qui. Ambitus quos baiulus. Sollicito vicissitudo cras. Voluptas numquam sperno. Cultellus auris curriculum. Cogito sodalitas quia. Adaugeo blandior amplus. Tolero libero capio."));
}
}
