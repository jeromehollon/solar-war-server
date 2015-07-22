package com.hollonconsulting.solarwars.server.service.impl;

import com.hollonconsulting.solarwars.server.entity.Planet;
import com.hollonconsulting.solarwars.server.repository.PlanetRepository;
import com.hollonconsulting.solarwars.server.service.PlanetService;
import org.jvnet.hk2.annotations.Service;
import org.springframework.stereotype.Repository;

@Service
@Repository
public class PlanetServiceImpl extends BaseServiceImpl<PlanetRepository, Planet> implements PlanetService {

}
