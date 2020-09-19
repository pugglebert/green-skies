package com.mypackage;

import io.cucumber.core.backend.Glue;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "classpath:com/mypackage/upload.feature"}, plugin = {"pretty", "html:target/cucumber.html"}, snippets = CucumberOptions.SnippetType.CAMELCASE)
public class uploadTest {

}
