package com.codeup.adlister.models;

public class Category {
        private Long id;
        private String category;
    public Category(Long id, String category) {
        this.id = id;
        this.category = category;

    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }
}
