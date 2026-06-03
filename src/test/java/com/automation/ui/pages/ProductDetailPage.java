package com.automation.ui.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class ProductDetailPage extends PageObject {

    @FindBy(className = "inventory_details_name")
    private WebElementFacade productName;

    @FindBy(className = "inventory_details_price")
    private WebElementFacade productPrice;

    @FindBy(className = "inventory_details_desc")
    private WebElementFacade productDescription;

    @FindBy(id = "add-to-cart")
    private WebElementFacade addToCartButton;

    public String getProductName() {
        return productName.getText();
    }

    public String getProductPrice() {
        return productPrice.getText();
    }

    public boolean isPriceDisplayed() {
        return productPrice.isCurrentlyVisible();
    }

    public void addToCart() {
        addToCartButton.click();
    }
}
