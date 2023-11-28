package org.example.Model.Class;

import org.example.Model.Type.measureType;

public class Product {

    private String name;
    private String category;
    private double price;
    private measureType measureType;
    public Product(String name, String category, measureType measureType, double price) {
        this.name = name;
        this.category = category;
        this.measureType = measureType;
        this.price = price;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public measureType getMeasureType() {
        return measureType;
    }
    public void setMeasureType(org.example.Model.Type.measureType measureType) {
        this.measureType = measureType;
    }
}
