package com.automation.api.runners;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.*;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features/api/petstore.feature")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty, io.cucumber.core.plugin.SerenityReporterParallel")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.automation.api.steps, com.automation.hooks")
@ConfigurationParameter(key = FILTER_TAGS_PROPERTY_NAME, value = "@petstore")
public class PetstoreRunner {
}
