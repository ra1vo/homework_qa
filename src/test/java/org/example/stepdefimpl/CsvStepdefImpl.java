package org.example.stepdefimpl;

import org.example.http.CsvHttpService;
import org.example.models.Person;
import org.example.context.ScenarioInfo;
import org.example.csv.CsvWriter;
import org.example.csv.CsvReader;
import org.example.csv.DataGenerator;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.util.List;

public class CsvStepdefImpl {

    @Autowired
    CsvReader<Person> csvReader;

    @Autowired
    CsvWriter<Person> csvWriter;

    @Autowired
    ScenarioInfo scenarioInfo;

    @Value("${csv_filename}")
    private String csvFilename;

    @Autowired
    CsvHttpService csvHttpService;

    public void csvFileWithPersonsIsCreated(int count) throws IOException {
        List<Person> personList = DataGenerator.generatePersons(count);
        String csv = csvWriter.beansToCsv(personList, Person.class);
        csvWriter.writeToFile(csvFilename, csv);
    }

    public void eachCsvRowIsSentToEndpoint(){
        List<Person> personList = csvReader.csvToBean(csvFilename);
        personList.forEach(person -> scenarioInfo.responses.add(csvHttpService.postContacts(person)));
    }

    public void eachResponseCodeIs(int expectedStatusCode){
        scenarioInfo.responses.forEach(response ->
                Assertions.assertEquals(expectedStatusCode,
                        response.statusCode(),
                        response.body().asPrettyString()));
    }

}
