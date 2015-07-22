package com.hollonconsulting.solarwars.server.repository;

import com.hollonconsulting.solarwars.server.entity.Planet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanetRepository extends JpaRepository<Planet, Integer> {
}
