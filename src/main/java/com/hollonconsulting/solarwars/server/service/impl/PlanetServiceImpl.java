package com.hollonconsulting.solarwars.server.service.impl;

import com.hollonconsulting.solarwars.server.entity.Planet;
import com.hollonconsulting.solarwars.server.repository.PlanetRepository;
import com.hollonconsulting.solarwars.server.service.PlanetService;
import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Service
@Repository
public class PlanetServiceImpl extends BaseServiceImpl<PlanetRepository, Planet> implements PlanetService {
    @Autowired
    PlanetRepository repository;

    @Override
    public List<String> findNameByStar(Integer starId) {
        List<Planet> planets = repository.findByStarId(starId);
        List<String> names = new ArrayList<>(planets.size());

        for(Planet p : planets){
            names.add(p.getName());
        }
        return names;
    }
}
