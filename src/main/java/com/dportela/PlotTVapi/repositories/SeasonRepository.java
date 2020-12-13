package com.dportela.PlotTVapi.repositories;

import com.dportela.PlotTVapi.entities.SeasonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeasonRepository extends JpaRepository<SeasonEntity, Integer> {
}
