package com.hollonconsulting.solarwars.server.service.impl;

import com.hollonconsulting.solarwars.server.entity.Star;
import com.hollonconsulting.solarwars.server.repository.StarRepository;
import com.hollonconsulting.solarwars.server.service.StarService;
import org.jvnet.hk2.annotations.Service;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import java.util.List;

@Service
@Repository
public class StarServiceImpl implements StarService{
    @Resource
    private StarRepository starRepository;

    @Override
    @Transactional
    public Star create(Star star) {
        return starRepository.save(star);
    }

    @Override
    @Transactional(rollbackOn=NotFoundException.class)
    public Star delete(int id) throws NotFoundException {
        Star deletedStar = starRepository.findOne(id);

        if(deletedStar == null){
            throw new NotFoundException();
        }

        starRepository.delete(deletedStar);
        return deletedStar;
    }

    @Override
    @Transactional
    public void deleteAll() {
        starRepository.deleteAll();
    }

    @Override
    @Transactional
    public List<Star> findAll() {
        return starRepository.findAll();
    }

    @Override
    @Transactional(rollbackOn=NotFoundException.class)
    public Star update(Star star) throws NotFoundException {
        if(star.getId() == null || !starRepository.exists(star.getId())){
            throw new NotFoundException();
        }
        return starRepository.save(star);
    }

    @Override
    @Transactional
    public Star findById(int id) {
        return null;
    }
}
