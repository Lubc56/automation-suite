package com.automation.ui.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class InventoryPage extends PageObject {

    @FindBy(className = "inventory_list")
    private WebElementFacade inventoryList;

    @FindBy(css = ".shopping_cart_badge")
    private WebElementFacade cartBadge;

    @FindBy(css = ".inventory_item_name")
    private List<WebElementFacade> productNames;

    public boolean isInventoryDisplayed() {
        return inventoryList.isCurrentlyVisible();
    }

    public String getCartCount() {
        return cartBadge.getText();
    }

    public boolean isCartBadgeVisible() {
        return cartBadge.isCurrentlyVisible();
    }

    public void clickAddToCartForProduct(String productName) {
        List<WebElement> items = getDriver().findElements(By.className("inventory_item"));
        for (WebElement item : items) {
            String name = item.findElement(By.className("inventory_item_name")).getText();
            if (name.equalsIgnoreCase(productName)) {
                item.findElement(By.cssSelector("[data-test^='add-to-cart']")).click();
                return;
            }
        }
        throw new RuntimeException("Producto no encontrado: " + productName);
    }

    public void clickOnProduct(String productName) {
        List<WebElement> items = getDriver().findElements(By.className("inventory_item_name"));
        for (WebElement item : items) {
            if (item.getText().equalsIgnoreCase(productName)) {
                item.click();
                return;
            }
        }
        throw new RuntimeException("Producto no encontrado: " + productName);
    }
}
