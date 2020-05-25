package com.inovoseltsev.holloball.model.service;

import com.inovoseltsev.holloball.model.entity.Skin;

import java.util.List;

public interface SkinService {
    void create(Skin skin);

    void update(Skin skin);

    void delete(Skin skin);

    Skin findBySkinGameNumber(Integer skinGameNUmber);

    List<Skin> findAll();
}
