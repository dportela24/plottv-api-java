package com.dportela.PlotTVapi.services;

import com.dportela.IMDBScraper.Exceptions.SeriesIdNotFoundException;
import com.dportela.IMDBScraper.IMDBScraper;
import com.dportela.IMDBScraper.Modules.Episode;
import com.dportela.IMDBScraper.Modules.Season;
import com.dportela.IMDBScraper.Modules.TVSeries;
import com.dportela.PlotTVapi.entities.EpisodeEntity;
import com.dportela.PlotTVapi.entities.SeasonEntity;
import com.dportela.PlotTVapi.entities.TVSeriesEntity;
import com.dportela.PlotTVapi.repositories.TVSeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class DatabaseService {
    private ExecutorService executorService;

    @Autowired
    private TVSeriesRepository repository;

    @PostConstruct
    private void create() {
        executorService = Executors.newSingleThreadExecutor();
    }

    @PreDestroy
    private void destroy() {
        executorService.shutdown();
    }

    public TVSeriesEntity save(TVSeries tvSeries) {
        TVSeriesEntity tvSeriesEntity = new TVSeriesEntity(tvSeries);

        for (Season season : tvSeries.getSeasons()) {
            SeasonEntity seasonEntity = new SeasonEntity(season);

            for (Episode episode : season.getEpisodes()) {
                EpisodeEntity episodeEntity = new EpisodeEntity(episode);

                seasonEntity.addEpisode(episodeEntity);
            }

            tvSeriesEntity.addSeason(seasonEntity);
        }

        repository.save(tvSeriesEntity);

        return tvSeriesEntity;
    }

    public Optional<TVSeriesEntity> getTVSeries(String query) throws SeriesIdNotFoundException {
        Optional<TVSeriesEntity> tvSeries;

        // Check if request is for id or name
        boolean isId = query.matches("tt\\d+");

        // Search the repository for imdbID
        if (isId) {
            tvSeries = repository.findByImdbID(query);
        // Search the repository for name
        } else {
            tvSeries = repository.findByNameIgnoreCase(query);

            // Name not found, try id
            if (tvSeries.isEmpty())
            {
                IMDBScraper scraper = new IMDBScraper();
                String series_id = scraper.getIdFromName(query);
                tvSeries = repository.findByImdbID(series_id);
            }
        }

        return tvSeries;
    }
}

