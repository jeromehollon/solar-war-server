package com.hollonconsulting.solarwars.server.generator;

import com.fasterxml.jackson.jaxrs.json.annotation.JSONP;
import com.hollonconsulting.solarwars.server.appconfig.Defaults;
import com.hollonconsulting.solarwars.server.entity.Planet;
import com.hollonconsulting.solarwars.server.entity.Star;
import com.hollonconsulting.solarwars.server.gamerules.entityrules.planets.PlanetRules;
import com.hollonconsulting.solarwars.server.generator.util.MarkovNameGenerator;
import com.hollonconsulting.solarwars.server.generator.util.NameGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlanetGenerator {
    Star[] stars;
    Random random;
    ArrayList<Planet> planets;

    public PlanetGenerator(List<Star> stars) {
        this.stars = stars.toArray(new Star[0]);
        this.random = new Random();
    }

    public Planet[] generate() {
        planets = new ArrayList<>(stars.length * 3);
        for(Star star : stars){
            int planetsInSystem = random.nextInt(3) + 1;
            Planet[] siblings = new Planet[planetsInSystem];
            for(int i = 0; i < planetsInSystem; i++){
                planets.add(this.generatePlanet(star));
            }
        }

        return planets.toArray(new Planet[0]);
    }

    private Planet generatePlanet(Star star) {
        int x;
        int y;

        final int DISTANCE_MIN = Defaults.MIN_PLANET_DISTANCE_FROM_STAR;
        final int DISTANCE_MAX = Defaults.MAX_PLANET_DISTANCE_FROM_STAR - DISTANCE_MIN;

        do{
            int deltaX = random.nextInt(DISTANCE_MAX) + DISTANCE_MIN;
            int deltaY = random.nextInt(DISTANCE_MAX) + DISTANCE_MIN;

            if(random.nextBoolean()){
                deltaX *= -1;
            }
            if(random.nextBoolean()){
                deltaY *= -1;
            }

            x = star.getX() + deltaX;
            y = star.getY() + deltaY;

        }while(isTooClose(x,y));

        Planet planet = new Planet(
            x,
            y,
            MarkovNameGenerator.getInstance().getName(),
            0,  //get quality after
            false,
            false,
            getPlanetType(),
            0,  //get population later
            0,
            0,  //get factories later
            Planet.BuildingType.NONE,
            star.getId()
        );

        planet.setQuality(getQuality(planet, star));
        //neutral planets start with 75% of their maximum
        planet.setPopulation((int) (PlanetRules.GetMaxPopulation(planet, star) * .75));
        planet.setFactories((int)(PlanetRules.GetMaxFactories(planet,star) * .75));

        //check for name reuse
        while(isNameInUse(planet.getName())){
            planet.setName(MarkovNameGenerator.getInstance().getName());
        }

        return planet;
    }

    private boolean isNameInUse(String name) {
        for(Planet p : this.planets){
            if(p == null) {
                break;
            }

            if(p.getName().equals(name))  {
                return true;
            }
        }

        return false;
    }

    private double getQuality(Planet planet, Star star) {
        final double VARRIANCE_RANGE = (Defaults.MAX_QUALITY - Defaults.MAX_QUALITY) * Defaults.QUALITY_VARIATION;
        double quality = PlanetRules.GetBaseQuality(planet,star);

        double variation = random.nextDouble() * VARRIANCE_RANGE;
        if(random.nextBoolean()){
            variation *= -1;
        }

        return quality + variation;
    }

    private Planet.PlanetType getPlanetType() {
        int type = random.nextInt(15);

        switch (type){
            case 5:
                return Planet.PlanetType.EARTH_LIKE;
            case 6:
            case 7:
                return Planet.PlanetType.ICE;
            case 8:
                return Planet.PlanetType.MOLTEN;
            case 9:
                return Planet.PlanetType.SMALL;
            case 10:
                return Planet.PlanetType.GIANT;
            case 11:
            case 12:
                return Planet.PlanetType.BARREN;
            default:
                return Planet.PlanetType.NORMAL;
        }
    }

    private boolean isTooClose(int x, int y) {
        final int MIN_DISTANCE_SQUARE = Defaults.MIN_PLANET_DISTANCE_FROM_PLANET * Defaults.MIN_PLANET_DISTANCE_FROM_PLANET;

        for(Planet p : this.planets){
            if(p == null) {
                break;
            }

            int dx = x - p.getX();
            int dy = y - p.getY();

            int distance = dx * dx + dy * dy;

            if(distance <= MIN_DISTANCE_SQUARE) {
                return true;
            }
        }

        return false;
    }


}
