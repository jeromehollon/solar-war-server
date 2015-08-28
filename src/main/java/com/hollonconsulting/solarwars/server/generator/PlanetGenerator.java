package com.hollonconsulting.solarwars.server.generator;

import com.hollonconsulting.solarwars.server.appconfig.Defaults;
import com.hollonconsulting.solarwars.server.entity.Planet;
import com.hollonconsulting.solarwars.server.entity.Star;
import com.hollonconsulting.solarwars.server.gamerules.entityrules.planets.PlanetRules;
import com.hollonconsulting.solarwars.server.generator.util.CosSinTable;
import com.hollonconsulting.solarwars.server.generator.util.MarkovNameGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlanetGenerator {
    private static Logger LOGGER = LoggerFactory.getLogger(PlanetGenerator.class);
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
            int planetsInSystem = random.nextInt(5) + 2;
            Planet[] siblings = new Planet[planetsInSystem];
            for(int i = 0; i < planetsInSystem; i++){
                try {
                    Planet newPlanet = this.generatePlanet(star, siblings);
                    planets.add(newPlanet);
                    siblings[i] = newPlanet;
                }catch(RuntimeException e){
                    continue;
                }
            }
        }

        return planets.toArray(new Planet[0]);
    }

    private Planet generatePlanet(Star star, Planet[] siblings) {
        int x;
        int y;
        int orbit;

        int placementTries = 0;
        do{
            orbit = getOrbit(siblings, star);
            int distance = Defaults.PLANET_ORBITS[orbit];
            int angle = random.nextInt(5) * (360/5);


            x = (int) (distance * CosSinTable.getInstance().getCos(angle));
            y = (int) (distance * CosSinTable.getInstance().getSine(angle));

            x += star.getX();
            y += star.getY();

            placementTries++;

            if(placementTries > 1000){
                throw new RuntimeException("Unable to place planet");
            }

        }while(isTooClose(x,y));

        Planet planet = new Planet(
            x,
            y,
            MarkovNameGenerator.getInstance().getName(),
            0,  //get quality after
            false,
            false,
            getPlanetType(orbit),
            0,  //get population later
            0,
            0,  //get factories later
            Planet.BuildingType.NONE,
            star.getId(),
            orbit);

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

    private int getOrbit(Planet[] siblings, Star star) {
        int orbit = 0;

        boolean tooClose = false;
        int tries = 0;
        do{
            tries++;
            if(tries > 1000){
                throw new RuntimeException("Unable to place planet");
            }

            orbit = random.nextInt(Defaults.PLANET_ORBITS.length);

            tooClose = false;
            for(Planet sibling : siblings){
                if(sibling == null){
                    continue;
                }

                int sibOrb = sibling.getOrbit();

                if(sibOrb == orbit || (sibOrb + 1) == orbit || (sibOrb - 1) == orbit){
                    tooClose = true;
                }
            }

        }while(tooClose);

        return orbit;
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

    private Planet.PlanetType getPlanetType(int orbit) {
        if(orbit < 3) {
            return Planet.PlanetType.MOLTEN;
        }else if(orbit <  6){
            int type = random.nextInt(4);
            switch (type){
                case 0:
                    return Planet.PlanetType.EARTH_LIKE;
                case 1:
                    return Planet.PlanetType.BARREN;
                case 2:
                    return Planet.PlanetType.MOLTEN;
                case 3:
                    return Planet.PlanetType.NORMAL;
                default:
                    return Planet.PlanetType.BARREN;
            }
        }else{
            int type = random.nextInt(5);
            switch (type){
                case 0:
                    return Planet.PlanetType.ICE;
                case 1:
                    return Planet.PlanetType.BARREN;
                case 2:
                    return Planet.PlanetType.SMALL;
                case 3:
                    return Planet.PlanetType.NORMAL;
                case 4:
                    return Planet.PlanetType.GIANT;
                default:
                    return Planet.PlanetType.BARREN;
            }
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
