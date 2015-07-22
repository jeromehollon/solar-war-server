package com.hollonconsulting.solarwars.server.model.response.map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hollonconsulting.solarwars.server.entity.Planet;
import com.hollonconsulting.solarwars.server.entity.Star;

import java.util.List;

public class MapResponse {
    private List<Planet> planets;
    private List<Star> stars;

    public MapResponse() {
    }

    public MapResponse(List<Planet> planets, List<Star> stars) {
        this.planets = planets;
        this.stars = stars;
    }

    @JsonProperty("planets")
    public List<Planet> getPlanets() {
        return planets;
    }

    public void setPlanets(List<Planet> planets) {
        this.planets = planets;
    }

    @JsonProperty("stars")
    public List<Star> getStars() {
        return stars;
    }

    public void setStars(List<Star> stars) {
        this.stars = stars;
    }
}
