package com.hollonconsulting.solarwars.server.repository;

import com.hollonconsulting.solarwars.server.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Roles, Integer> {
}
