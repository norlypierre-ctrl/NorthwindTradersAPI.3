package com.pluralsight.NorthwindTradersAPI3.controllers;

import com.pluralsight.NorthwindTradersAPI3.models.Product;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductControllers {
    private final List<Product> products;

    public ProductControllers() {
        products = new ArrayList<>();

        products.add(new Product(1));
        products.add(new Product(2));
        products.add(new Product(3));
    }

    @RequestMapping(path = "/Product", method = RequestMethod.GET)
    public List<Product> getProducts (){
        return products;
    }

    @RequestMapping(path = "/Product", method = RequestMethod.GET)
    public Product getProducts(@PathVariable int productID) {
        for (Product product : products){
            if (product.getProductId() == productID){
                return product;
            }
        }
        return null;
    }
}
