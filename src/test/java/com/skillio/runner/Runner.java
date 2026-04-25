package com.skillio.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources",
    glue = {"com.skillio"},
    plugin = {"pretty", "html:target/cucumber-report.html"},
    dryRun = false
    
)
public class Runner extends AbstractTestNGCucumberTests {
}