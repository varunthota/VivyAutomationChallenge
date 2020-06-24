package apiTests;

import core.BaseAPI;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class FetchDoctorsListAndVerify {

    private Response response1;
    private Response response2;
    private String lastKey;
    List<String> doctorList;
    List<String> subsequentDoctorList;


    //Doctors API endpoint to fetch the first 20 doctors and store them in a json file
    @Test
    public void getDoctorsListAndSaveInJson() throws IOException {
        response1 = given().when().get(BaseAPI.URL+BaseAPI.GET_DOCTORS+".json");
        response1.then().statusCode(200);
        JsonPath jsonPath = new JsonPath(response1.prettyPrint());
        lastKey = jsonPath.getString("lastKey");
        FileWriter file = new FileWriter("doctorsList1.json");
        file.write(String.valueOf(response1.prettyPrint()));
        file.flush();
        //storing response id's in List
        doctorList =  response1.jsonPath().getList("doctors.id");
    }

    //Subsequent-Doctors API endpoint to fetch the 20 doctors and compare id's with response1
    @Test(dependsOnMethods={"getDoctorsListAndSaveInJson"})
    public void getDoctorsSubsequentListAndCompare() throws IOException {
        response2 = given().when().get(BaseAPI.URL+BaseAPI.GET_DOCTORS+"-"+lastKey+".json");
        response2.then().statusCode(200);
        FileWriter file = new FileWriter("doctorsList2.json");
        file.write(String.valueOf(response2.prettyPrint()));
        file.flush();
        //Storing subsequent response id's
        subsequentDoctorList = response2.jsonPath().getList("doctors.id");
        doctorList.retainAll(subsequentDoctorList);
        assertEquals(0,doctorList.size());
    }

    @AfterClass
    public void preTest() throws IOException {
        Files.deleteIfExists(Paths.get("doctorsList1.json"));
        Files.deleteIfExists(Paths.get("doctorsList2.json"));
    }

}
