package com.hollonconsulting.solarwars.server.gamerules.entityrules.fleet;

import com.hollonconsulting.solarwars.server.entity.Fleet;
import com.hollonconsulting.solarwars.server.gamerules.entityrules.fleet.ships.*;

import java.util.Arrays;
import java.util.Random;

public class FleetRules {

    Fleet parent;

    public FleetRules(Fleet parent){
        this.parent = parent;
    }

    public int getTotalDefense(){
        int defense = 0;

        defense += parent.getOrbitalStations() * OrbitalStation.getArmor();
        defense += parent.getTroopTransports() * TroopTransport.getArmor();
        defense += parent.getAgeisCruisers() * AgeisCruiser.getArmor();
        defense += parent.getBattleships() * Battleship.getArmor();
        defense += parent.getFrigates() * Frigate.getArmor();
        defense += parent.getFighters() * Fighter.getArmor();

        return defense;
    }

    public int getTotalFirepower() {
        int firepower = 0;

        firepower += parent.getOrbitalStations() * OrbitalStation.getFirepower();
        firepower += parent.getTroopTransports() * TroopTransport.getFirepower();
        firepower += parent.getAgeisCruisers() * AgeisCruiser.getFirepower();
        firepower += parent.getBattleships() * Battleship.getFirepower();
        firepower += parent.getFrigates() * Frigate.getFirepower();
        firepower += parent.getFighters() * Fighter.getFirepower();

        return firepower;
    }

    public int getTotalShielding() {
        int sheilding = 0;

        sheilding += parent.getOrbitalStations() * OrbitalStation.getShield();
        sheilding += parent.getTroopTransports() * TroopTransport.getShield();
        sheilding += parent.getAgeisCruisers() * AgeisCruiser.getShield();
        sheilding += parent.getBattleships() * Battleship.getShield();
        sheilding += parent.getFrigates() * Frigate.getShield();
        sheilding += parent.getFighters() * Fighter.getShield();

        return sheilding;
    }

    /**
     * Deals damange to the fleet and reduces it's size. Returns undealt damange
     *
     * @param damage damage to be taken, should be reduced by shielding prior to being called.
     * @return untaken damage
     */
    public int takeDamage(int damage){
        while(damage > 0 && this.getTotalDefense() > 0){
            damage = randomizeDamage(damage);
        }
        return damage;
    }

    private int randomizeDamage(int damage){
        //check to make sure there are actually ships in this fleet
        if(this.getTotalDefense() == 0){
            return damage;
        }

        Random random = new Random();

        int shipType = random.nextInt(6);
        switch (shipType){
            case 0:
                if(parent.getOrbitalStations() > 0){
                    int[] remenants = doSpecificDamage(OrbitalStation.getArmor(), parent.getOrbitalStations(), damage);

                    parent.setOrbitalStations(remenants[0]);
                    return (remenants[1] > 0) ? 0 : -1 * remenants[1];
                }else{
                    //reroll
                    return randomizeDamage(damage);
                }
            case 1:
                if(parent.getFighters() > 0){
                    int[] remenants = doSpecificDamage(Fighter.getArmor(), parent.getFighters(), damage);

                    parent.setFighters(remenants[0]);
                    return (remenants[1] > 0) ? 0 : -1 * remenants[1];
                }else{
                    //reroll
                    return randomizeDamage(damage);
                }
            case 2:
                if(parent.getFrigates() > 0){
                    int[] remenants = doSpecificDamage(Frigate.getArmor(), parent.getFrigates(), damage);

                    parent.setFrigates(remenants[0]);
                    return (remenants[1] > 0) ? 0 : -1 * remenants[1];
                }else{
                    //reroll
                    return randomizeDamage(damage);
                }
            case 3:
                if(parent.getAgeisCruisers() > 0){
                    int[] remenants = doSpecificDamage(AgeisCruiser.getArmor(), parent.getAgeisCruisers(), damage);

                    parent.setAgeisCruisers(remenants[0]);
                    return (remenants[1] > 0) ? 0 : -1 * remenants[1];
                }else{
                    //reroll
                    return randomizeDamage(damage);
                }
            case 4:
                if(parent.getBattleships() > 0){
                    int[] remenants = doSpecificDamage(Battleship.getArmor(), parent.getBattleships(), damage);

                    parent.setBattleships(remenants[0]);
                    return (remenants[1] > 0) ? 0 : -1 * remenants[1];
                }else{
                    //reroll
                    return randomizeDamage(damage);
                }
            case 5:
                if(parent.getTroopTransports() > 0){
                    int[] remenants = doSpecificDamage(TroopTransport.getArmor(), parent.getTroopTransports(), damage);

                    parent.setTroopTransports(remenants[0]);
                    return (remenants[1] > 0) ? 0 : -1 * remenants[1];
                }else{
                    //reroll
                    return randomizeDamage(damage);
                }

        }

        throw new IllegalArgumentException("Unexpected damage attempt. Damage should to sent to a ship group.");
    }

    /**
     * Hackish way of uncomplicating code.
     * Deals damange to a specific armor class
     *
     * @param armor Amount of armor per ship
     * @param ships Amount of ships
     * @param damage Amount of Damange
     * @return {remainingShips, remainingArmor}
     */
    private static int[] doSpecificDamage(int armor, int ships, int damage){
        int totalArmor = ships * armor;
        int remainingArmor = totalArmor - damage;

        int destroyed = (int) Math.floor(damage / AgeisCruiser.getArmor());   //gotta kill it completely
        int remaining = Math.max(0, ships - destroyed);

        int[] remenants = {remaining, remainingArmor};
        return remenants;
    }

    /**
     * Calculates the speed of the fleet.
     *
     * Speed is constrained by slowest ship
     */
    public double getVelocity(){
        if(parent.getOrbitalStations() > 0) {
            return OrbitalStation.getSpeed();
        }else if(parent.getTroopTransports() > 0){
            return TroopTransport.getSpeed();
        }else if(parent.getAgeisCruisers() > 0 || parent.getBattleships() > 0){
            return AgeisCruiser.getSpeed();
        }else if(parent.getFrigates() > 0) {
            return Frigate.getSpeed();
        }else if(parent.getFighters() > 0) {
            return Fighter.getSpeed();
        }else {
            //nothing in the fleet
            return 8;
        }
    }

    /**
     * Combat rules:
     *  Attacks happen at the same time
     *  Shielding between fleets is shared
     *
     *  Those within the same alliance defend, everyone else is attacking
     *
     * Call this function once per battle tick.
     */
    public static void doBattle(FleetRules[] sideA, FleetRules[] sideB){
        //sort the fleets by the amount of damage they can take
        Arrays.sort(sideA, (o1, o2) -> o1.getTotalDefense() - o2.getTotalDefense());
        Arrays.sort(sideB, (o1, o2) -> o1.getTotalDefense() - o2.getTotalDefense());

        int fleetATotalFirepower = 0;
        int fleetATotalDamage = 0;
        int fleetATotalShield = 0;

        int fleetBTotalFirepower = 0;
        int fleetBTotalDamage = 0;
        int fleetBTotalShield = 0;

        for(FleetRules part : sideA){
            fleetATotalFirepower += part.getTotalFirepower();
            fleetATotalShield += part.getTotalShielding();
        }

        for(FleetRules part : sideB){
            fleetBTotalFirepower += part.getTotalFirepower();
            fleetBTotalShield += part.getTotalShielding();
        }

        fleetATotalDamage = fleetBTotalFirepower - fleetATotalShield;
        fleetBTotalDamage = fleetATotalFirepower - fleetBTotalShield;

        if(fleetATotalDamage > 0){
            int damagePerFleet = fleetATotalDamage / sideA.length;
            int currentDamage = 0;
            for(FleetRules part : sideA){
                currentDamage += damagePerFleet;
                currentDamage = part.takeDamage(currentDamage); //pass untaken damage back to the next fleet
            }
        }

        if(fleetBTotalDamage > 0){
            int damagePerFleet = fleetBTotalDamage / sideA.length;
            int currentDamage = 0;
            for(FleetRules part : sideB){
                currentDamage += damagePerFleet;
                currentDamage = part.takeDamage(currentDamage); //pass untaken damage back to the next fleet
            }
        }

        //save fleets
    }
}
