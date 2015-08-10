package com.hollonconsulting.solarwars.server.repository;

import com.hollonconsulting.solarwars.server.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Integer> {

    @Query("SELECT p FROM Player p WHERE p.username = :username")
    List<Player> findByUsername(@Param("username") String username);

    @Query("SELECT p FROM Player p WHERE p.email = :email")
    List<Player> findByEmail(@Param("email") String email);
}
