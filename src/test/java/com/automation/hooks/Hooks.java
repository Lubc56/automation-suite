package com.automation.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Hooks {

    private static final Logger log = LoggerFactory.getLogger(Hooks.class);

    /**
     * Se ejecuta UNA sola vez por JVM gracias al flag estático.
     * WebDriverManager setea webdriver.chrome.driver antes de que Serenity
     * intente instanciar el ChromeDriver por primera vez.
     */
    private static volatile boolean driverReady = false;

    @Before(value = "@ui", order = 1)
    public void beforeUiScenario(Scenario scenario) {
        log.info("==> Iniciando escenario UI: {}", scenario.getName());
        if (!driverReady) {
            synchronized (Hooks.class) {
                if (!driverReady) {
                    log.info("==> Configurando ChromeDriver con WebDriverManager...");
                    WebDriverManager.chromedriver().setup();
                    driverReady = true;
                    log.info("==> ChromeDriver listo en: {}",
                            System.getProperty("webdriver.chrome.driver"));
                }
            }
        }
    }

    @After(value = "@ui", order = 1)
    public void afterUiScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            log.error("==> Escenario UI FALLIDO: {}", scenario.getName());
        } else {
            log.info("==> Escenario UI EXITOSO: {}", scenario.getName());
        }
    }

    @Before(value = "@api", order = 1)
    public void beforeApiScenario(Scenario scenario) {
        log.info("==> Iniciando escenario API: {}", scenario.getName());
        RestAssured.reset();
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
