package com.dportela.PlotTVapi.controllers;

import com.dportela.IMDBScraper.Exceptions.SeriesIdNotFoundException;
import com.dportela.IMDBScraper.Exceptions.TVSeriesNotFoundException;
import com.dportela.IMDBScraper.Modules.TVSeries;
import com.dportela.PlotTVapi.entities.TVSeriesEntity;
import com.dportela.PlotTVapi.services.DatabaseService;
import com.dportela.PlotTVapi.services.ScraperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/tvseries")
public class TVSeriesController {
    @Autowired
    DatabaseService dbService;

    @Autowired
    ScraperService scraperService;

    @GetMapping
    public ResponseEntity getTvSeries(@RequestParam(name = "q") String query){
        //IMDBScraper scraper = new IMDBScraper();
        //TVSeries tvSeries = scraper.scrap(query);
        TVSeriesEntity tvSeries;

        try {
            // Check if requested tv series already on database
            Optional<TVSeriesEntity> tvSeries_db = dbService.getTVSeries(query);

            // If not on database, use scraper to fetch information
            if (tvSeries_db.isEmpty()) {
                TVSeries tvSeries_scraper = scraperService.getTVSeries(query);

                // Save to database new entry for future requests
                tvSeries = dbService.save(tvSeries_scraper);
            } else {
                tvSeries = tvSeries_db.get();
            }
        } catch (SeriesIdNotFoundException e) { 
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("IMDB ID not found");
        } catch (TVSeriesNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("TV Series not found");
        }

        return ResponseEntity.ok(tvSeries);
    }
}
