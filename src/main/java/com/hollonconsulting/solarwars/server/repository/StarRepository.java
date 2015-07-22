package com.hollonconsulting.solarwars.server.repository;

import com.hollonconsulting.solarwars.server.entity.Star;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StarRepository extends JpaRepository<Star, Integer> {
}
