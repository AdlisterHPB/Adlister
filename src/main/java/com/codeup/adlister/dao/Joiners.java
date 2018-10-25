package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.Category;
import com.codeup.adlister.models.User;
import com.codeup.adlister.models.Joiner;

import java.util.List;

public interface Joiners {
    List<Joiner> allJoiners();
    List<Joiner> findJoiners(long joiner);
    Long insert(long ad_id, long category_id);


}