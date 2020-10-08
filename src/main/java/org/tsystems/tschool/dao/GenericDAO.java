package org.tsystems.tschool.dao;

import org.tsystems.tschool.entity.Article;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

public interface GenericDAO <T, V extends Serializable> {

    List<T> findAll();

    T findById(V id);

    boolean remove(V id);

    T update(T obj);

    T save(T obj);
}