package org.example.csv;

import net.datafaker.Faker;
import net.datafaker.Name;
import org.example.models.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataGenerator {

    private static final int low = 1;
    private static final int high = Integer.MAX_VALUE;

    public static List<Person> generatePersons(int count){
        Faker faker = new Faker();
        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Person p = new Person();
            Name name = faker.name();
            String firstName = name.firstName();
            String lastName = name.lastName();
            p.setId(new Random().nextInt(high - low) + low);
            p.setFirstName(firstName);
            p.setLastName(lastName);
            p.setEmail(faker.internet().emailAddress(firstName + "." + lastName).toLowerCase());
            p.setCompanyId(new Random().nextInt(high - low) + low);
            persons.add(p);
        }
        return persons;

    }
}
