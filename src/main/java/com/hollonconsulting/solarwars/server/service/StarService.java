package com.hollonconsulting.solarwars.server.service;

import com.hollonconsulting.solarwars.server.entity.Star;

import javax.ws.rs.NotFoundException;
import java.util.List;

public interface StarService {

    public Star create(Star star);
    public Star delete(int id) throws NotFoundException;
    public void deleteAll();
    public List<Star> findAll();
    public Star update(Star star) throws NotFoundException;
    public Star findById(int id);
}
