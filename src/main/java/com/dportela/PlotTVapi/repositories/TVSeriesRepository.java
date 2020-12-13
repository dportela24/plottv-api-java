package com.dportela.PlotTVapi.repositories;

import com.dportela.PlotTVapi.entities.TVSeriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TVSeriesRepository extends JpaRepository<TVSeriesEntity, Integer> {
    Optional<TVSeriesEntity> findByImdbID(String imdbID);

    Optional<TVSeriesEntity> findByNameIgnoreCase(String name);
}
