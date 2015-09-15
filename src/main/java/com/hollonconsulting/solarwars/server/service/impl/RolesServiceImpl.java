package com.hollonconsulting.solarwars.server.service.impl;

import com.hollonconsulting.solarwars.server.entity.Roles;
import com.hollonconsulting.solarwars.server.repository.RolesRepository;
import com.hollonconsulting.solarwars.server.service.RolesService;
import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Service
@Repository
public class RolesServiceImpl extends BaseServiceImpl<RolesRepository, Roles> implements RolesService {
    @Autowired
    RolesRepository repository;

    public RolesServiceImpl(){
        super();
        super.setRepository(repository);
    }
}
