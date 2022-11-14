package org.example.unit;

import org.example.csv.CsvWriter;
import org.example.csv.DataGenerator;
import org.example.models.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CsvWriterTest {

    @Test
    public void testGenerateHeader(){
        CsvWriter<Person> csvWriter = new CsvWriter<>();
        String header = csvWriter.generateHeader(Person.class);
        Assertions.assertEquals("id|firstName|lastName|email|companyId", header);
    }

    @Test
    public void whenGenerate5Persons_then5PersonsReturned(){
        List<Person> personList = DataGenerator.generatePersons(5);
        Assertions.assertEquals(5, personList.size());
    }

}
