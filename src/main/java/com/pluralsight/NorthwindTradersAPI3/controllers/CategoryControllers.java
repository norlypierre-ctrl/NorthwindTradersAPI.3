package com.pluralsight.NorthwindTradersAPI3.controllers;

import com.pluralsight.NorthwindTradersAPI3.models.Category;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoryControllers {
    private final List<Category> categories;

    public  CategoryControllers() {
        categories = new ArrayList<>();

        categories.add(new Category(1));
        categories.add(new Category(2));
        categories.add(new Category(3));
    }

    @RequestMapping(path = "/Category", method = RequestMethod.GET)
    public List<Category> getCategories (){
        return categories;
    }

    @RequestMapping(path = "/Category", method = RequestMethod.GET)
    public Category getCategories(@PathVariable int categoryID) {
        for (Category category : categories){
            if (category.getCategoryID() == categoryID){
                return category;
            }
        }
        return null;
    }
}