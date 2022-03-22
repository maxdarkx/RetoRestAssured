package com.juancarlosmaya.runner.updateservice;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(snippets = CucumberOptions.SnippetType.CAMELCASE,
        publish = true,
        features = {"src/test/resources/features/update.feature"},
        glue = "com.juancarlosmaya.stepdefinition.updateservice",
        tags = ""
)
public class UpdateService {
}
