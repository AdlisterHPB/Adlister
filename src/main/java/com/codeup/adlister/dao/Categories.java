package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.Category;
import com.codeup.adlister.models.User;


import java.util.List;

public interface Categories {
    List<Category> allCategories();
    List<Category> findCategories(long id);
    Category findCategoryByName(String category);

}