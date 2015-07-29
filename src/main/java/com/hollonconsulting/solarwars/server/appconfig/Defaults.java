package com.hollonconsulting.solarwars.server.appconfig;

public class Defaults {

    /* initialization settings */
    public static final int NUMBER_OF_STARS = 2000;
    public static final int MAX_STAR_DISTANCE = 200;
    public static final int MIN_STAR_DISTANCE = 100;
    public static final int MAX_PLANET_DISTANCE_FROM_STAR = 40;
    public static final int MIN_PLANET_DISTANCE_FROM_STAR = 20;
    public static final int MIN_PLANET_DISTANCE_FROM_PLANET = 30;

    public static final double QUALITY_VARIATION = 0.2;

    public static final int MILLISECONDS_PER_TICK = 500;

    //rules
    public static final double MAX_QUALITY = 160;
    public static final double MIN_QUALITY = 20;

    private Defaults(){

    }
}
