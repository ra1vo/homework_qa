package org.example.http;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.example.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class CsvHttpService {

    Gson gson;
    RequestSpecification requestSpecification;
    @Value("${api.endpoints.contacts}") String endpoint;

    @Autowired
    public CsvHttpService(Gson gson, RequestSpecification requestSpecification){
        this.gson = gson;
        this.requestSpecification = requestSpecification;
    }

    public Response postContacts(Person person){
        RequestSpecification request = RestAssured.given(requestSpecification);
        request.body(gson.toJson(person));
        return request.post(endpoint);
    }
}
