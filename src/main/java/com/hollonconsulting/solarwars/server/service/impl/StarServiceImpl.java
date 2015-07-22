package com.hollonconsulting.solarwars.server.service.impl;

import com.hollonconsulting.solarwars.server.entity.Star;
import com.hollonconsulting.solarwars.server.repository.StarRepository;
import com.hollonconsulting.solarwars.server.service.StarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@Repository
public class StarServiceImpl extends BaseServiceImpl<StarRepository, Star> implements StarService{
    private static final Logger LOGGER = LoggerFactory.getLogger(StarServiceImpl.class);
}
