package org.example.stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.stepdefimpl.CsvStepdefImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class CsvStepdefs {
    @Autowired
    CsvStepdefImpl csvStepdef;

    @Given("csv file with {int} persons is created")
    public void csvFileWithPersonsIsCreated(int count) throws IOException {
        csvStepdef.csvFileWithPersonsIsCreated(count);
    }

    @When("each csv row is sent to endpoint")
    public void eachCsvRowIsSentToEndpoint() {
        csvStepdef.eachCsvRowIsSentToEndpoint();
    }

    @Then("each response code is {int}")
    public void eachResponseCodeIs(int statusCode) {
        csvStepdef.eachResponseCodeIs(statusCode);
    }

}
