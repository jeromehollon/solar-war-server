package com.hollonconsulting.solarwars.server.appconfig;

public class Defaults {

    /* initialization settings */
    public static final int NUMBER_OF_STARS = 4000;
    public static final int MAX_GALACTIC_DISTANCE = 8000;
    public static final int MAX_STAR_DISTANCE = 300;
    public static final int MIN_STAR_DISTANCE = 150;
    public static final int[] PLANET_ORBITS = {15, 20, 25, 30, 35, 40, 45, 55, 65, 75, 85};
    public static final int MAX_QUALITY_BONUS_GOLDILOCKS = 30;
    public static final int MIN_PLANET_DISTANCE_FROM_PLANET = 20;

    public static final double QUALITY_VARIATION = 0.2;

    public static final int MILLISECONDS_PER_TICK = 500;

    //rules
    public static final double MAX_QUALITY = 200;
    public static final double MIN_QUALITY = 20;

    private Defaults(){

    }
}
