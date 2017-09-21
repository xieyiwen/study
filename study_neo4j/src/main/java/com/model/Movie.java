package com.model;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * Created by 谢益文 on 2017/7/13.
 */
@NodeEntity(label = "Movie")
public class Movie {
    @GraphId Long graphId;

    @Index
    private Long movieId;
    private String name;

    public Long getGraphId() {
        return graphId;
    }

    public void setGraphId(Long graphId) {
        this.graphId = graphId;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Movie{" + "graphId=" + graphId + ", movieId=" + movieId + ", name='" + name + '\''
               + '}';
    }
}
