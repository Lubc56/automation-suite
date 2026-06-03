package com.automation.ui.runners;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.*;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features/ui/login.feature")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty, io.cucumber.core.plugin.SerenityReporterParallel")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.automation.ui.steps, com.automation.hooks")
@ConfigurationParameter(key = FILTER_TAGS_PROPERTY_NAME, value = "@login")
public class LoginRunner {
}
