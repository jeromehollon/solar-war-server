package com.hollonconsulting.solarwars.server.gamerules.entityrules.planets;

import com.hollonconsulting.solarwars.server.appconfig.Defaults;
import com.hollonconsulting.solarwars.server.entity.Planet;
import com.hollonconsulting.solarwars.server.entity.Star;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PlanetRules {
    private static final Logger LOGGER = LoggerFactory.getLogger(PlanetRules.class);

    /**
     * Calculates base quality of a planet.
     *
     * Inverse squareroot based on distance from (0,0)
     * It has a linear function as well to even stuff out a bit
     *
     * Quality = Sqrt(x) * 2
     *
     * This creates a range of (0,0) to (141,5000)
     * We then figure invert it to get the actual quality value.
     *
     * We do the inversion by subtracting the formula value from a number
     * higher than it. A base number, no planet can go below this quality.
     *
     * Base value = 160
     *
     * Quality for X of
     *  0:      160
     *  1250:   90
     *  2500:   60
     *  3750:   38
     *  5000:   19
     *
     *
     * The reason this formula is chosen is because it makes the distance to
     * the center of the galaxy critically important. Only the very closest
     * planets get high quality ratings. The quality should fall rapidly with
     * distance, and then level off. With this formula it does.
     *
     *
     *
     * NEW FORMULA
     * Linear decrease in quality based on star's distance from the core.
     * This value is modified by a bell curve based on the orbit of the planet
     * Planets in the goldilock's region get a boost to their quality, other planets
     * get smaller or non-existent boosts.
     *
     * @param planet
     * @return
     */
    public static double GetBaseQuality(Planet planet, Star star){
        final double QUALITY_RANGE = Defaults.MAX_QUALITY - Defaults.MIN_QUALITY;

        double distance = Math.sqrt(star.getX() * star.getX() + star.getY() * star.getY());
        double starQuality = (-1*QUALITY_RANGE*distance)/Defaults.MAX_GALACTIC_DISTANCE + Defaults.MAX_QUALITY;

        //figure out planet quality
        // y = ax^2+bx
        double h = Defaults.MAX_QUALITY_BONUS_GOLDILOCKS;
        double w = Defaults.PLANET_ORBITS.length;
        double a =  (h * -4) / (w*w);
        double b = -w * a;

        double planetQuality = a * planet.getOrbit() * planet.getOrbit() + b * planet.getOrbit();

        if(starQuality == 200){
            LOGGER.debug("Orbit {}: {}", planet.getOrbit(), planetQuality);
        }

        return starQuality + planetQuality;
    }

    /**
     * Max Population is just a factor of quality and planet adjustments
     *
     * MaxPop = 100 * Quality
     *
     * Modifiers:
     * EARTH_LIKE           +25%
     * GIANT                +10%
     * ICE, MOLTEN, SMALL   -10%
     * BARREN               -25%
     *
     * @param planet
     * @param star
     * @return
     */
    public static int GetMaxPopulation(Planet planet, Star star){
        final int POP_TO_QUALITY_RATIO = 100;

        int maxPop = (int)(POP_TO_QUALITY_RATIO * planet.getQuality());
        switch(planet.getPlanetType()){
            case EARTH_LIKE:
                maxPop *= 1.25;
                break;
            case GIANT:
                maxPop *= 1.1;
                break;
            case ICE:
            case NORMAL:
            case SMALL:
                maxPop *= 0.9;
                break;
            case BARREN:
                maxPop *= 0.75;
                break;
        }

        return maxPop;
    }

    /**
     * Maximum factories is a factor of quality and planet adjustments
     *
     * MaxFactories = 10 * Quality
     *
     * Modifiers:
     * GIANT    +25%
     * MOLTEN   +10%
     * BARREN   -10%
     * SMALL    -15%
     *
     * @param planet
     * @param star
     * @return
     */
    public static int GetMaxFactories(Planet planet, Star star){
        final int FACTORIES_TO_QUALITY_RATIO = 10;

        int maxFactories = (int)(FACTORIES_TO_QUALITY_RATIO * planet.getQuality());
        switch (planet.getPlanetType()){
            case GIANT:
                maxFactories *= 1.25;
                break;
            case MOLTEN:
                maxFactories *= 1.1;
                break;
            case BARREN:
                maxFactories *= 0.9;
                break;
            case SMALL:
                maxFactories *= 0.85;
                break;
        }
        return maxFactories;
    }

    /**
     * If a planet's fleet has a support count of higher than the planet's support max, truncate it.
     *
     * Planetary Cap is determined by population, factories, and quality
     *
     * Cap = 100 * Quality
     *  + Population * 1
     *  + Factories * 5
     *
     * Modifiers
     * Planet.GIANT:    +10%
     * Planet.SMALL:    -10%
     * Star.HEAVY:      -10%
     * Star.WEAK:       +10%
     *
     * @param planet
     * @param star
     * @return
     */
    public static int GetPlanetarySupport(Planet planet, Star star){
        final int CAP_TO_QUALITY_RATIO = 100;
        final int CAP_TO_POPLUATION_RATIO = 1;
        final int CAP_TO_FACTORIES_RATIO = 5;

        double cap = CAP_TO_QUALITY_RATIO * planet.getQuality()
                + CAP_TO_POPLUATION_RATIO * planet.getPopulation()
                + CAP_TO_FACTORIES_RATIO * planet.getFactories();

        switch(planet.getPlanetType()){
            case GIANT:
                cap *= 1.1;
                break;
            case SMALL:
                cap *= 0.9;
        }

        switch (star.getStarType()){
            case HEAVEY:
                cap *= 0.9;
                break;
            case WEAK:
                cap *= 1.1;
                break;
        }

        return (int) cap;
    }
}
