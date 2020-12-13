package com.dportela.PlotTVapi.entities;

import com.dportela.IMDBScraper.Modules.TVSeries;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tvseries")
@Getter @Setter @NoArgsConstructor
public class TVSeriesEntity {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String imdbID;
    private String name;
    private String originalName;
    private String summary;
    private String episodeDuration;
    private String runtime;
    private double ratingValue;
    private int ratingCount;
    private String poster;
    private int numberOfSeasons;

    @OneToMany(mappedBy="tvSeries",
            fetch=FetchType.LAZY,
            cascade=CascadeType.ALL)
    private List<SeasonEntity> seasons;

    public TVSeriesEntity(TVSeries tvSeries) {
        imdbID = tvSeries.getImdbID();
        name = tvSeries.getName();
        originalName = tvSeries.getOriginalName();
        summary = tvSeries.getSummary();
        episodeDuration = tvSeries.getEpisodeDuration();
        runtime = tvSeries.getRuntime();
        ratingValue = tvSeries.getRatingValue();
        ratingCount = tvSeries.getRatingCount();
        poster = tvSeries.getPoster();
        numberOfSeasons = tvSeries.getNumberOfSeasons();
    }

    public void addSeason(SeasonEntity new_season) {
        if (seasons == null) {
            seasons = new ArrayList<>();
        }

        seasons.add(new_season);

        new_season.setTvSeries(this);
    }
}
