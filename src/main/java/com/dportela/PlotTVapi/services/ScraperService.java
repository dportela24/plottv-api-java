package com.dportela.PlotTVapi.services;

import com.dportela.IMDBScraper.Exceptions.SeriesIdNotFoundException;
import com.dportela.IMDBScraper.Exceptions.TVSeriesNotFoundException;
import com.dportela.IMDBScraper.IMDBScraper;
import com.dportela.IMDBScraper.Modules.TVSeries;
import org.springframework.stereotype.Service;

@Service
public class ScraperService {
    public TVSeries getTVSeries(String query) throws TVSeriesNotFoundException, SeriesIdNotFoundException {
        IMDBScraper scraper = new IMDBScraper();
        return scraper.scrap(query);
    }
}
