package com.hollonconsulting.solarwars.server.repository;

import org.hibernate.metamodel.relational.Identifier;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

public abstract class BaseJpaDAO< T extends Serializable> {
    private Class< T > entityType;

    @PersistenceContext
    EntityManager entityManager;

    protected BaseJpaDAO( Class<T> entityClass) {
        this.entityType = entityClass;
    }

    public T findOne( Identifier id ){
        return entityManager.find( entityType, id );
    }
    public List< T > findAll(){
        return entityManager.createQuery( "FROM " + entityType.getName() )
                .getResultList();
    }

    public T create( T entity ){
        entityManager.persist( entity );
        return entity;
    }

    public T update( T entity ){
        return entityManager.merge( entity );
    }

    public void delete( T entity ){
        entityManager.remove( entity );
    }
    public void deleteById( Identifier entityId ){
        T entity = findOne( entityId );
        delete(entity);
    }
    public void deleteAll(){
        entityManager.createQuery( "DELETE FROM " + entityType.getName() ).executeUpdate();
    }
}
