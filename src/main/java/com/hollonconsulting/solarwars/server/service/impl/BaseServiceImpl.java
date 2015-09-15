package com.hollonconsulting.solarwars.server.service.impl;

import com.hollonconsulting.solarwars.server.appconfig.ApplicationContextHolder;
import com.hollonconsulting.solarwars.server.entity.BaseEntity;
import com.hollonconsulting.solarwars.server.service.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.GenericTypeResolver;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import java.util.List;

public abstract class BaseServiceImpl< R extends JpaRepository, E extends BaseEntity> {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseServiceImpl.class);
    private R repository;

    public BaseServiceImpl(){
    }

    protected void setRepository(R repository){
        this.repository = repository;
    }

    @Transactional
    public E create(E entity){
        return (E) repository.save(entity);
    }

    @Transactional
    public E save(E entity){
        return (E) repository.save(entity);
    }

    @Transactional(rollbackOn=NotFoundException.class)
    public E delete(int id) throws NotFoundException {
        E deletedEntity = (E) repository.findOne(id);

        if(deletedEntity == null){
            throw new NotFoundException();
        }

        repository.delete(deletedEntity);
        return deletedEntity;
    }

    @Transactional
    public void deleteAll() {
        repository.deleteAll();
    }

    @Transactional
    public List<E> findAll() {
        return repository.findAll();
    }

    @Transactional
    public long countAll(){
        return repository.count();
    }

    @Transactional(rollbackOn=NotFoundException.class)
    public E update(E entity) throws NotFoundException {
        if(entity.getId() == null || !repository.exists(entity.getId())){
            throw new NotFoundException();
        }
        return (E) repository.save(entity);
    }

    @Transactional
    public E findById(int id) {
        return (E) repository.findOne(id);
    }
}
