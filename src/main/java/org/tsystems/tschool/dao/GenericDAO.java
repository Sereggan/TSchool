package org.tsystems.tschool.dao;


import org.tsystems.tschool.entity.Article;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class GenericDAO<T extends Object> {


    @PersistenceContext
    EntityManager entityManager;

    public List<T> findAll(){
        return null;
    }
}
