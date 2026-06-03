package com.automation.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import net.thucydides.core.webdriver.DriverSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * DriverSource personalizado que usa WebDriverManager (bonigarcia) para
 * resolver y configurar el ChromeDriver antes de entregárselo a Serenity.
 *
 * Se activa desde serenity.conf:
 *   webdriver.driver = provided
 *   webdriver.provided.type = chrome
 *   webdriver.provided.driver = com.automation.utils.ChromeDriverSource
 */
public class ChromeDriverSource implements DriverSource {

    @Override
    public WebDriver newDriver() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments(
                "--headless=new",
                "--no-sandbox",
                "--disable-dev-shm-usage",
                "--disable-gpu",
                "--window-size=1920,1080",
                "--remote-allow-origins=*"
        );

        return new ChromeDriver(options);
    }

    @Override
    public boolean takesScreenshots() {
        return true;
    }
}
