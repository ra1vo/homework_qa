package org.example.stepdefs;

import io.cucumber.spring.CucumberContextConfiguration;
import org.example.context.Config;
import org.springframework.test.context.ContextConfiguration;

@CucumberContextConfiguration
@ContextConfiguration(classes = Config.class)
public class CucumberContext {
}
