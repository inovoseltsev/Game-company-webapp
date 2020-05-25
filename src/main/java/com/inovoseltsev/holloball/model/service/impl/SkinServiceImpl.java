package com.inovoseltsev.holloball.model.service.impl;

import com.inovoseltsev.holloball.model.entity.Skin;
import com.inovoseltsev.holloball.model.repository.SkinRepository;
import com.inovoseltsev.holloball.model.service.SkinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SkinServiceImpl implements SkinService {

    @Autowired
    private SkinRepository skinRepository;

    @Override
    @Transactional
    public void create(Skin skin) {
        skinRepository.save(skin);
    }

    @Override
    @Transactional
    public void update(Skin skin) {
        skinRepository.save(skin);
    }

    @Override
    @Transactional
    public void delete(Skin skin) {
        skinRepository.delete(skin);
    }

    @Override
    @Transactional(readOnly = true)
    public Skin findBySkinGameNumber(Integer skinGameNUmber) {
        return skinRepository.findBySkinGameNumber(skinGameNUmber);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Skin> findAll() {
        return (List<Skin>) skinRepository.findAll();
    }
}
