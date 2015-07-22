package com.hollonconsulting.solarwars.server.gamerules.entityrules.fleet.ships;

public abstract class Ship {
    //combat
    protected static int firepower;
    protected static int armor;
    protected static int shield;
    protected static double speed;

    //production
    protected static int cost;
    protected static int capContribution;

    //troops
    protected static int maxTroops;

    public static int getFirepower() {
        return firepower;
    }

    public static int getArmor() {
        return armor;
    }

    public static int getShield() {
        return shield;
    }

    public static double getSpeed() {
        return speed;
    }

    public static int getCost() {
        return cost;
    }

    public static int getCapContribution() {
        return capContribution;
    }

    public static int getMaxTroops() {
        return maxTroops;
    }
}
