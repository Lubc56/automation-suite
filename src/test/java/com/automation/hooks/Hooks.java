package com.automation.hooks;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.restassured.RestAssured;
import net.thucydides.core.webdriver.ThucydidesWebDriverSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Hooks {

    private static final Logger log = LoggerFactory.getLogger(Hooks.class);

    @Before(value = "@api", order = 1)
    public void beforeApiScenario(Scenario scenario) {
        log.info("==> Iniciando escenario API: {}", scenario.getName());
        RestAssured.reset();
    }

    @Before(value = "@ui", order = 1)
    public void beforeUiScenario(Scenario scenario) {
        log.info("==> Iniciando escenario UI: {}", scenario.getName());
    }

    @After(value = "@ui", order = 1)
    public void afterUiScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            log.error("==> Escenario UI FALLIDO: {}", scenario.getName());
        } else {
            log.info("==> Escenario UI EXITOSO: {}", scenario.getName());
        }
    }

    @After(value = "@api", order = 1)
    public void afterApiScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            log.error("==> Escenario API FALLIDO: {}", scenario.getName());
        } else {
            log.info("==> Escenario API EXITOSO: {}", scenario.getName());
        }
        RestAssured.reset();
    }
}
