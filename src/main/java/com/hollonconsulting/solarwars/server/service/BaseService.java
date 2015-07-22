package com.hollonconsulting.solarwars.server.service;

import com.hollonconsulting.solarwars.server.entity.BaseEntity;

import javax.ws.rs.NotFoundException;
import java.util.List;

public interface BaseService<E extends BaseEntity> {
    public E create(E star);
    public E delete(int id) throws NotFoundException;
    public void deleteAll();
    public List<E> findAll();
    public long countAll();
    public E update(E star) throws NotFoundException;
    public E findById(int id);
}
