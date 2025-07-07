package com.shaiksnet.runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features", // Path to feature files
        glue = "com.shaiksnet.stepdefintions", // Path to step definitions
        plugin = {
                "pretty",
                "json:target/cucumber/cucumber.json", // JSON report
                "html:target/cucumber/cucumber.html", // HTML report
                "rerun:target/cucumber/rerun.txt" // Rerun failed tests
        },
        monochrome = true // Makes console output more readable
)
public class TestRunner {

    @BeforeClass
    public static void setup() {
        System.out.println("Test Suite Execution Started.");
    }

    @AfterClass
    public static void generateReport() {
        System.out.println("Test Suite Execution Ended.");

        // Generate the custom report
        Collection<File> jsonFiles = FileUtils.listFiles(new File(System.getProperty("user.dir")+"/target/cucumber"),
                new String[]{"json"}, true);
        List<String> jsonPaths = new ArrayList<>(jsonFiles.size());
        jsonFiles.forEach(file -> jsonPaths.add(file.getAbsolutePath()));

        Configuration config = new Configuration(new File("target"), "ApplyMate"); // Adjust "Shaiks Project" as needed
        ReportBuilder reportBuilder = new ReportBuilder(jsonPaths, config);
        reportBuilder.generateReports();
    }
}
