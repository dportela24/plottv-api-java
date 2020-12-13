package com.dportela.PlotTVapi.entities;

import com.dportela.IMDBScraper.Modules.Season;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "seasons")
@Getter @Setter @NoArgsConstructor
public class SeasonEntity {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int number;

    @JsonIgnore
    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="tvseries_id")
    private TVSeriesEntity tvSeries;

    @OneToMany(mappedBy="season",
            fetch=FetchType.LAZY,
            cascade=CascadeType.ALL)
    private List<EpisodeEntity> episodes;

    public SeasonEntity(Season season) {
        number = season.getNumber();
    }

    public void addEpisode(EpisodeEntity new_episode) {
        if (episodes == null) {
            episodes = new ArrayList<>();
        }

        episodes.add(new_episode);

        new_episode.setSeason(this);
    }


    public void setTvSeries(TVSeriesEntity new_tvSeries) {
        this.tvSeries = new_tvSeries;
    }
}
