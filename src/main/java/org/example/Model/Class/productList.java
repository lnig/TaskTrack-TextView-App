package org.example.Model.Class;

import java.util.ArrayList;

public class productList {

    private static ArrayList<Product> prodList = new ArrayList<>();


    private void addProduct(Product product){
        prodList.add(product);
    }


}
