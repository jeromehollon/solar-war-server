package com.hollonconsulting.solarwars.server.repository;

import com.hollonconsulting.solarwars.server.entity.Star;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StarRepository extends JpaRepository<Star, Integer> {
}
