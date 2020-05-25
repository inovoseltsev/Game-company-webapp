package com.inovoseltsev.holloball.model.repository;

import com.inovoseltsev.holloball.model.entity.Skin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkinRepository extends CrudRepository<Skin, Long> {
    Skin findBySkinGameNumber(Integer skinGameNumber);
}
