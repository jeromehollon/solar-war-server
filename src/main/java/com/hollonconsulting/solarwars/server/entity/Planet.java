package com.hollonconsulting.solarwars.server.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table
public class Planet extends BaseEntity {

    @Id
    @GeneratedValue
    private Integer id;

    //values during generation
    private int x;
    private int y;
    private double quality;
    private boolean isCore;
    private boolean isOldEarth;
    private PlanetType planetType;
    private int starId;
    private String name;

    //values during game
    private Integer population;
    private Integer playerId;    //owner
    private Integer factories;
    private BuildingType buildingType;

    public enum BuildingType {
        NONE,
        INDUSTRIAL_COMPLEX, //increases max factories
        GOVERNMENT_CENTER,  //increases tax revenue
        MILITARY_BARRACKS,  //increases max ships
        SHIELD_GENERATOR,   //increases shield or orbital fleet
        GROUND_DEFENSES     //significantly damages fleet after successful takeover attempt
    }

    public enum PlanetType {
        NORMAL,     //no bonus
        EARTH_LIKE, //plus population
        ICE,        //minus population, plus to defense
        MOLTEN,     //minus population, plus to defense, minor boost to factory count
        GIANT,      //plus to factory, plus to population, plus to max ships
        SMALL,      //minus to all
        BARREN,     //minus to population, minus to defense, minor minus to factory count
    }

    public Planet(){

    }

    public Planet(int x, int y, String name, double quality, boolean isCore, boolean isOldEarth, PlanetType planetType, Integer population, Integer playerId, Integer factories, BuildingType buildingType, int starId) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.quality = quality;
        this.isCore = isCore;
        this.isOldEarth = isOldEarth;
        this.planetType = planetType;
        this.population = population;
        this.playerId = playerId;
        this.factories = factories;
        this.buildingType = buildingType;
        this.starId = starId;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getQuality() {
        return quality;
    }

    public void setQuality(double quality) {
        this.quality = quality;
    }

    public boolean isCore() {
        return isCore;
    }

    public void setIsCore(boolean isCore) {
        this.isCore = isCore;
    }

    public boolean isOldEarth() {
        return isOldEarth;
    }

    public void setIsOldEarth(boolean isOldEarth) {
        this.isOldEarth = isOldEarth;
    }

    public PlanetType getPlanetType() {
        return planetType;
    }

    public void setPlanetType(PlanetType planetType) {
        this.planetType = planetType;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public Integer getFactories() {
        return factories;
    }

    public void setFactories(Integer factories) {
        this.factories = factories;
    }

    public BuildingType getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(BuildingType buildingType) {
        this.buildingType = buildingType;
    }

    public int getStarId() {
        return starId;
    }

    public void setStarId(int starId) {
        this.starId = starId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Planet)) return false;

        Planet planet = (Planet) o;

        if (getX() != planet.getX()) return false;
        if (getY() != planet.getY()) return false;
        if (Double.compare(planet.getQuality(), getQuality()) != 0) return false;
        if (isCore() != planet.isCore()) return false;
        if (isOldEarth() != planet.isOldEarth()) return false;
        if (getId() != null ? !getId().equals(planet.getId()) : planet.getId() != null) return false;
        if (getPlanetType() != planet.getPlanetType()) return false;
        if (getPopulation() != null ? !getPopulation().equals(planet.getPopulation()) : planet.getPopulation() != null)
            return false;
        if (getPlayerId() != null ? !getPlayerId().equals(planet.getPlayerId()) : planet.getPlayerId() != null)
            return false;
        if (getFactories() != null ? !getFactories().equals(planet.getFactories()) : planet.getFactories() != null)
            return false;
        return getBuildingType() == planet.getBuildingType();

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + getX();
        result = 31 * result + getY();
        temp = Double.doubleToLongBits(getQuality());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (isCore() ? 1 : 0);
        result = 31 * result + (isOldEarth() ? 1 : 0);
        result = 31 * result + (getPlanetType() != null ? getPlanetType().hashCode() : 0);
        result = 31 * result + (getPopulation() != null ? getPopulation().hashCode() : 0);
        result = 31 * result + (getPlayerId() != null ? getPlayerId().hashCode() : 0);
        result = 31 * result + (getFactories() != null ? getFactories().hashCode() : 0);
        result = 31 * result + (getBuildingType() != null ? getBuildingType().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Planet{" +
                "id=" + id +
                ", x=" + x +
                ", y=" + y +
                ", quality=" + quality +
                ", isCore=" + isCore +
                ", isOldEarth=" + isOldEarth +
                ", planetType=" + planetType +
                ", population=" + population +
                ", playerId=" + playerId +
                ", factories=" + factories +
                ", buildingType=" + buildingType +
                '}';
    }
}
