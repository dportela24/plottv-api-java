package com.dportela.PlotTVapi.entities;

import com.dportela.IMDBScraper.Modules.Episode;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "episodes")
@Getter @Setter @NoArgsConstructor
public class EpisodeEntity {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int number;
    private String imdbID;
    private String name;
    private String airdate;
    private double ratingValue;
    private int ratingCount;
    private String summary;

    @JsonIgnore
    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="season_id")
    private SeasonEntity season;

    public EpisodeEntity (Episode episode) {
        number      = episode.getNumber();
        imdbID      = episode.getImdbID();
        name        = episode.getName();
        airdate     = episode.getAirdate();
        ratingValue = episode.getRatingValue();
        ratingCount = episode.getRatingCount();
        summary     = episode.getSummary();
    }

    public void setSeason(SeasonEntity new_season) {
        this.season = new_season;
    }
}
