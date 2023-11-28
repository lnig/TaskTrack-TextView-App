package org.example.Controller;

import org.example.Model.Class.Product;


import java.util.ArrayList;

public class productController {


    private static ArrayList<Product> listOfProduct = new ArrayList<>();

    public static void addProduct(Product product){
        listOfProduct.add(product);
    }


    public static void removeProduct(int selected){
        listOfProduct.remove(selected);
    }

    public static ArrayList<Product> getProducts() {
        return listOfProduct;
    }





}
