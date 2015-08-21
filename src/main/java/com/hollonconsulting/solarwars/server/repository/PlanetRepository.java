package com.hollonconsulting.solarwars.server.repository;

import com.hollonconsulting.solarwars.server.entity.Planet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlanetRepository extends JpaRepository<Planet, Integer> {

    @Query("SELECT p FROM Planet p WHERE p.starId = :starId")
    List<Planet> findByStarId(@Param("starId") Integer starId);
}
