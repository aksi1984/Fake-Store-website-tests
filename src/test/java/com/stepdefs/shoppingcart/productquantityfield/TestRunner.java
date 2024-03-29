package com.stepdefs.shoppingcart.productquantityfield;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "./src/main/resources/features/shoppingCart/quantityField.feature",
        plugin = { "pretty", "html:target/cucumber-reports/productQuantityField.html" },
        glue = { "com.stepdefs.shoppingcart.productquantityfield",
                 "com.commonstepdefs",
                 "qa.testutil" }
)
public class TestRunner {
}
