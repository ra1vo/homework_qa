package org.example.context;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.example.http.CsvHttpService;
import org.example.models.Person;
import org.example.csv.CsvWriter;
import org.example.csv.CsvReader;
import org.example.stepdefimpl.CsvStepdefImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

@Configuration
@PropertySource("classpath:application.properties")
public class Config {

    @Bean
    public CsvWriter<Person> csvHomeService(){
        return new CsvWriter<>();
    }

    @Bean
    public CsvReader<Person> csvPersonReader(){
        return new CsvReader<>(Person.class);
    }

    @Bean
    public Gson gson(){
        return new Gson();
    }

    @Value("${api.base_url}")
    String basePath;

    @Bean
    @Scope("cucumber-glue")
    public ScenarioInfo testInfo(){
        return new ScenarioInfo();
    }

    @Bean
    @Scope("cucumber-glue")
    public CsvStepdefImpl csvStepdef(){
        return new CsvStepdefImpl();
    }

    @Bean
    @Scope("cucumber-glue")
    public CsvHttpService csvHttpService(){
        return new CsvHttpService(gson(), requestSpecification());
    }

    @Bean
    @Scope("cucumber-glue")
    public RequestSpecification requestSpecification(){
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.baseUri(basePath);
        return requestSpecification;
    }
}
