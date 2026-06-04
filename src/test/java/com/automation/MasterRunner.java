package com.automation;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.*;

/**
 * Runner maestro: ejecuta TODOS los features (UI + API) en un solo proceso
 * con paralelismo interno a nivel de scenarios.
 *
 * Úsalo directamente desde el IDE o con:
 *   mvn verify -Dit.test=MasterRunner
 *
 * Para ejecución por módulo usa los runners individuales:
 *   mvn verify -Dit.test=LoginRunner
 *   mvn verify -Dit.test=ProductRunner
 *   mvn verify -Dit.test=PetstoreRunner
 *
 * NOTA: Failsafe NO incluye este runner en su ciclo por defecto para evitar
 * duplicación de resultados con los runners individuales.
 */
@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty, io.cucumber.core.plugin.SerenityReporterParallel")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.automation")
@ConfigurationParameter(key = PARALLEL_EXECUTION_ENABLED_PROPERTY_NAME, value = "true")
@ConfigurationParameter(key = PARALLEL_CONFIG_STRATEGY_PROPERTY_NAME, value = "fixed")
@ConfigurationParameter(key = PARALLEL_CONFIG_FIXED_PARALLELISM_PROPERTY_NAME, value = "2")
public class MasterRunner {
}
