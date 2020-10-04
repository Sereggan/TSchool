package org.tsystems.tschool.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@SuppressWarnings("unchecked")
public abstract class AbstractJpaDao<T, V extends Serializable> implements GenericDAO<T, V>{

    private Class<T> clazz;

    public AbstractJpaDao() {
        this.clazz = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

    @PersistenceContext
    EntityManager entityManager;

    public List<T> findAll() {
        return entityManager.createQuery("from " + clazz.getName(), clazz).getResultList();
    }

    public T findById(V id) {
        return entityManager.find(clazz, id);
    }

    public boolean remove(V id) {
        T obj = findById(id);
        entityManager.remove(obj);
        return entityManager.find(clazz, id) == null;
    }

    public T update(T obj) {
        entityManager.merge(obj);
        return obj;
    }

    public T save(T obj) {
        entityManager.persist(obj);
        return obj;
    }
}
