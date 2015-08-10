package com.hollonconsulting.solarwars.server.service;

import com.hollonconsulting.solarwars.server.entity.Player;

public interface PlayerService extends BaseService<Player> {

    Player findByUsername(String username);

    Player findByEmail(String email);
}
