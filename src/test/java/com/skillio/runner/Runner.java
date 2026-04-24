package com.skillio.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/",glue = "com.skillio")
public class Runner extends AbstractTestNGCucumberTests {	

}
