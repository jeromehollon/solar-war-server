package com.hollonconsulting.solarwars.server.service;

import com.hollonconsulting.solarwars.server.entity.Planet;

import java.util.List;

public interface PlanetService extends BaseService<Planet> {
    List<String> findNameByStar(Integer starId);
}
