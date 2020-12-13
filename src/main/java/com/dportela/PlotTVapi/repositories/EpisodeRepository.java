package com.dportela.PlotTVapi.repositories;

import com.dportela.PlotTVapi.entities.EpisodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EpisodeRepository extends JpaRepository<EpisodeEntity, Integer> {
}
