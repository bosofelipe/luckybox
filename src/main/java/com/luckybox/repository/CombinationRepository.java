package com.luckybox.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.luckybox.domain.Combination;

@Component
public interface CombinationRepository extends CrudRepository<Combination, Integer> {

}
