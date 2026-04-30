package com.skillio.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/FeatureFile",
    glue = {
        "com.skillio.base",
        "com.skillio.stepdefinitions"
    },
    plugin = {
        "pretty",
        "html:target/cucumber-report.html"
    },
    dryRun = false,
    monochrome = true
)
public class Runner extends AbstractTestNGCucumberTests {
}