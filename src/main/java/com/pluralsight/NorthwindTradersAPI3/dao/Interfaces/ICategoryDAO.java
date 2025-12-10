package com.pluralsight.NorthwindTradersAPI3.dao.Interfaces;

import com.pluralsight.NorthwindTradersAPI3.models.Category;

import java.util.List;

public interface ICategoryDAO {

    List<Category> getAll();

    Category getById(int category);
}
