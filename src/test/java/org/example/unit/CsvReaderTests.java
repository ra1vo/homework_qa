package org.example.unit;

import org.example.csv.CsvReader;
import org.example.csv.CsvWriter;
import org.example.models.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvReaderTests {

    private static final String csvfilename = "test.csv";

    @Test
    public void readCsv() throws IOException {
        List<Person> expectedPersons = samplePersons();
        createCsvfile(expectedPersons);
        CsvReader<Person> csvReader = new CsvReader<>(Person.class);
        List<Person> personList = csvReader.csvToBean("test.csv");

        Assertions.assertEquals(expectedPersons.size(), personList.size());
        for (int i = 0; i < personList.size(); i++) {
            Person actualPerson = personList.get(i);
            Person expectedPerson = expectedPersons.get(i);

            Assertions.assertEquals(expectedPerson.getId(), actualPerson.getId());
            Assertions.assertEquals(expectedPerson.getFirstName(), actualPerson.getFirstName());
            Assertions.assertEquals(expectedPerson.getLastName(), actualPerson.getLastName());
            Assertions.assertEquals(expectedPerson.getEmail(), actualPerson.getEmail());
            Assertions.assertEquals(expectedPerson.getCompanyId(), actualPerson.getCompanyId());

        }

    }

    private void createCsvfile(List<Person> expectedPersons) throws IOException {
        CsvWriter<Person> personCsvWriter = new CsvWriter<>();
        String csv = personCsvWriter.beansToCsv(expectedPersons, Person.class);
        personCsvWriter.writeToFile(csvfilename, csv);
    }

    private List<Person> samplePersons(){
        List<Person> persons = new ArrayList<>();

        Person p1 = new Person();
        p1.setId(1);
        p1.setFirstName("test");
        p1.setLastName("testerson");
        p1.setEmail("t.testerson@mail.com");
        p1.setCompanyId(432);

        Person p2 = new Person();
        p2.setId(5312);
        p2.setFirstName("dev");
        p2.setLastName("developer");
        p2.setEmail("d.devel@mail.com");
        p2.setCompanyId(993);

        persons.add(p1);
        persons.add(p2);

        return persons;
    }


}
