package com.hollonconsulting.solarwars.server.service.impl;

import com.hollonconsulting.solarwars.server.entity.Player;
import com.hollonconsulting.solarwars.server.repository.PlayerRepository;
import com.hollonconsulting.solarwars.server.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Repository
public class PlayerServiceImpl extends BaseServiceImpl<PlayerRepository, Player> implements PlayerService{
    @Autowired
    PlayerRepository repository;

    public PlayerServiceImpl(){
        super();
        super.setRepository(repository);
    }

    @Override
    public Player findByUsername(String username) {
        List<Player> players = repository.findByUsername(username);
        return players.size() == 0 ? null : players.get(0);
    }

    @Override
    public Player findByEmail(String email) {
        List<Player> players = repository.findByEmail(email);
        return players.size() == 0 ? null : players.get(0);
    }
}
