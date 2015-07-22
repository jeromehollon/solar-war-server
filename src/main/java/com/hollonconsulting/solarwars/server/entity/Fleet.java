package com.hollonconsulting.solarwars.server.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table
public class Fleet implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    //placement
    private int x;
    private int y;
    private Integer playerId;   //owner

    //stocking
    private int fighters;
    private int frigates;
    private int battleships;
    private int orbitalStations;
    private int ageisCruisers;
    private int troopTransports;

    private int troops;

    //activities
    private Integer destinationX;   //use destination x/y if they're going somewhere
    private Integer destinationY;
    private double heading;         //use heading if they're moving in a direction
    private long lastUpdate;    //timestamp for movement

    public Fleet(int x, int y, Integer playerId, int fighters, int frigates, int battleships, int orbitalStations, int ageisCruisers, int troopTransports, int troops, Integer destinationX, Integer destinationY, double heading, long lastUpdate) {
        this.x = x;
        this.y = y;
        this.playerId = playerId;
        this.fighters = fighters;
        this.frigates = frigates;
        this.battleships = battleships;
        this.orbitalStations = orbitalStations;
        this.ageisCruisers = ageisCruisers;
        this.troopTransports = troopTransports;
        this.troops = troops;
        this.destinationX = destinationX;
        this.destinationY = destinationY;
        this.heading = heading;
        this.lastUpdate = lastUpdate;
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

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public int getFighters() {
        return fighters;
    }

    public void setFighters(int fighters) {
        this.fighters = fighters;
    }

    public int getFrigates() {
        return frigates;
    }

    public void setFrigates(int frigates) {
        this.frigates = frigates;
    }

    public int getBattleships() {
        return battleships;
    }

    public void setBattleships(int battleships) {
        this.battleships = battleships;
    }

    public int getOrbitalStations() {
        return orbitalStations;
    }

    public void setOrbitalStations(int orbitalStations) {
        this.orbitalStations = orbitalStations;
    }

    public int getAgeisCruisers() {
        return ageisCruisers;
    }

    public void setAgeisCruisers(int ageisCruisers) {
        this.ageisCruisers = ageisCruisers;
    }

    public int getTroopTransports() {
        return troopTransports;
    }

    public void setTroopTransports(int troopTransports) {
        this.troopTransports = troopTransports;
    }

    public int getTroops() {
        return troops;
    }

    public void setTroops(int troops) {
        this.troops = troops;
    }

    public Integer getDestinationX() {
        return destinationX;
    }

    public void setDestinationX(Integer destinationX) {
        this.destinationX = destinationX;
    }

    public Integer getDestinationY() {
        return destinationY;
    }

    public void setDestinationY(Integer destinationY) {
        this.destinationY = destinationY;
    }

    public double getHeading() {
        return heading;
    }

    public void setHeading(double heading) {
        this.heading = heading;
    }

    public long getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Fleet)) return false;

        Fleet fleet = (Fleet) o;

        if (getX() != fleet.getX()) return false;
        if (getY() != fleet.getY()) return false;
        if (getFighters() != fleet.getFighters()) return false;
        if (getFrigates() != fleet.getFrigates()) return false;
        if (getBattleships() != fleet.getBattleships()) return false;
        if (getOrbitalStations() != fleet.getOrbitalStations()) return false;
        if (getAgeisCruisers() != fleet.getAgeisCruisers()) return false;
        if (getTroopTransports() != fleet.getTroopTransports()) return false;
        if (getTroops() != fleet.getTroops()) return false;
        if (Double.compare(fleet.getHeading(), getHeading()) != 0) return false;
        if (getLastUpdate() != fleet.getLastUpdate()) return false;
        if (getId() != null ? !getId().equals(fleet.getId()) : fleet.getId() != null) return false;
        if (getPlayerId() != null ? !getPlayerId().equals(fleet.getPlayerId()) : fleet.getPlayerId() != null)
            return false;
        if (getDestinationX() != null ? !getDestinationX().equals(fleet.getDestinationX()) : fleet.getDestinationX() != null)
            return false;
        return !(getDestinationY() != null ? !getDestinationY().equals(fleet.getDestinationY()) : fleet.getDestinationY() != null);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + getX();
        result = 31 * result + getY();
        result = 31 * result + (getPlayerId() != null ? getPlayerId().hashCode() : 0);
        result = 31 * result + getFighters();
        result = 31 * result + getFrigates();
        result = 31 * result + getBattleships();
        result = 31 * result + getOrbitalStations();
        result = 31 * result + getAgeisCruisers();
        result = 31 * result + getTroopTransports();
        result = 31 * result + getTroops();
        result = 31 * result + (getDestinationX() != null ? getDestinationX().hashCode() : 0);
        result = 31 * result + (getDestinationY() != null ? getDestinationY().hashCode() : 0);
        temp = Double.doubleToLongBits(getHeading());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (int) (getLastUpdate() ^ (getLastUpdate() >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Fleet{" +
                "id=" + id +
                ", x=" + x +
                ", y=" + y +
                ", playerId=" + playerId +
                ", fighters=" + fighters +
                ", frigates=" + frigates +
                ", battleships=" + battleships +
                ", orbitalStations=" + orbitalStations +
                ", ageisCruisers=" + ageisCruisers +
                ", troopTransports=" + troopTransports +
                ", troops=" + troops +
                ", destinationX=" + destinationX +
                ", destinationY=" + destinationY +
                ", heading=" + heading +
                ", lastUpdate=" + lastUpdate +
                '}';
    }
}
