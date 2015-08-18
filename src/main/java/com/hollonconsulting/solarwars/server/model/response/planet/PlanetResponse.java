package com.hollonconsulting.solarwars.server.model.response.planet;

import com.hollonconsulting.solarwars.server.entity.Planet;
import com.hollonconsulting.solarwars.server.entity.Player;
import com.hollonconsulting.solarwars.server.entity.Star;
import com.hollonconsulting.solarwars.server.gamerules.entityrules.planets.PlanetRules;

import javax.ws.rs.BadRequestException;

public class PlanetResponse {

    private Integer id;

    //values during generation
    private int x;
    private int y;
    private double quality;
    private Planet.PlanetType planetType;
    private int starId;
    private String starName;
    private String name;

    //values during game
    private Integer population;
    private Integer maxPopulation;
    private Integer owner;
    private String ownerName;
    private Integer factories;
    private Integer maxFactories;
    private Planet.BuildingType buildingType;

    public PlanetResponse(){

    }

    public PlanetResponse(Planet planet, Player player, Star star, boolean doScrub){
        if(planet.getStarId() != star.getId()){
            throw new BadRequestException("Planet's star id does not match the id of the provided star.");
        }
        if((player == null && planet.getPlayerId() != 0) || (
                player != null && planet.getPlayerId() != player.getId()
                )){
            throw new BadRequestException("Planet's owner id does not match the id of the provided player.");
        }

        this.id = planet.getId();
        this.x = planet.getX();
        this.y = planet.getY();
        this.quality = planet.getQuality();
        this.planetType = planet.getPlanetType();
        this.population = planet.getPopulation();
        this.maxPopulation = PlanetRules.GetMaxPopulation(planet, star);
        this.name = planet.getName();

        this.starId = planet.getStarId();
        this.starName = star.getName();

        this.owner = planet.getPlayerId();
        this.ownerName = (this.owner == 0) ? "Neutral" : player.getUsername();

        if(!doScrub){
            this.factories = planet.getFactories();
            this.maxFactories = PlanetRules.GetMaxFactories(planet, star);
        }

        //TODO: Fleets, Garrison, Support Cap
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getQuality() {
        return quality;
    }

    public void setQuality(double quality) {
        this.quality = quality;
    }

    public Planet.PlanetType getPlanetType() {
        return planetType;
    }

    public void setPlanetType(Planet.PlanetType planetType) {
        this.planetType = planetType;
    }

    public int getStarId() {
        return starId;
    }

    public void setStarId(int starId) {
        this.starId = starId;
    }

    public String getStarName() {
        return starName;
    }

    public void setStarName(String starName) {
        this.starName = starName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public Integer getMaxPopulation() {
        return maxPopulation;
    }

    public void setMaxPopulation(Integer maxPopulation) {
        this.maxPopulation = maxPopulation;
    }

    public Integer getOwner() {
        return owner;
    }

    public void setOwner(Integer owner) {
        this.owner = owner;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public Integer getFactories() {
        return factories;
    }

    public void setFactories(Integer factories) {
        this.factories = factories;
    }

    public Integer getMaxFactories() {
        return maxFactories;
    }

    public void setMaxFactories(Integer maxFactories) {
        this.maxFactories = maxFactories;
    }

    public Planet.BuildingType getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(Planet.BuildingType buildingType) {
        this.buildingType = buildingType;
    }

    @Override
    public String toString() {
        return "PlanetResponse{" +
                "id=" + id +
                ", x=" + x +
                ", y=" + y +
                ", quality=" + quality +
                ", planetType=" + planetType +
                ", starId=" + starId +
                ", starName='" + starName + '\'' +
                ", name='" + name + '\'' +
                ", population=" + population +
                ", maxPopulation=" + maxPopulation +
                ", owner=" + owner +
                ", ownerName='" + ownerName + '\'' +
                ", factories=" + factories +
                ", maxFactories=" + maxFactories +
                ", buildingType=" + buildingType +
                '}';
    }
}
