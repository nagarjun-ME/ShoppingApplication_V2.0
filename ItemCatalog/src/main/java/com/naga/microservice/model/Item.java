package com.naga.microservice.model;


import java.util.List;

public class Item {

    private String itemId;
    private String itemName;
    private int quantity;
    private double totalPrice;
    private List<Product> productList;


    public Item(String itemId, String itemName, int quantity, double totalPrice, List<Product> productList) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.productList = productList;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemId='" + itemId + '\'' +
                ", itemName='" + itemName + '\'' +
                ", quantity=" + quantity +
                ", totalPrice=" + totalPrice +
                ", productList=" + productList +
                '}';
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Item() {

    }


    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }




}
