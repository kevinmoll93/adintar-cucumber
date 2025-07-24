package runner;

import org.junit.platform.suite.api.*;

import static io.cucumber.junit.platform.engine.Constants.*;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features") // Ejecutar desde el directorio de features
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "steps")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "json:target/cucumber-reports/cucumber.json, pretty, html:target/cucumber-reports/report.html, com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:, listeners.CustomReportListener")
@ConfigurationParameter(key = FILTER_TAGS_PROPERTY_NAME, value = "@ordenDEBIN") // Ejecutar solo escenarios marcados
public class RunOrdenDEBIN {
}
