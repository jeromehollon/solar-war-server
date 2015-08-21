package com.hollonconsulting.solarwars.server.model.response.star;

import com.hollonconsulting.solarwars.server.entity.Star;

import java.util.List;

public class StarResponse {

    private Integer id;
    private String name;
    private int x;
    private int y;
    private List<String> planets;
    private Star.StarType type;

    public StarResponse() {
    }

    public StarResponse(Integer id, String name, int x, int y, List<String> planets, Star.StarType type) {
        this.id = id;
        this.name = name;
        this.x = x;
        this.y = y;
        this.planets = planets;
        this.type = type;
    }

    public StarResponse(Star star, List<String> planets) {
        this.id = star.getId();
        this.x = star.getX();
        this.y = star.getY();
        this.type = star.getStarType();
        this.name = star.getName();
        this.planets = planets;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<String> getPlanets() {
        return planets;
    }

    public void setPlanets(List<String> planets) {
        this.planets = planets;
    }

    public Star.StarType getType() {
        return type;
    }

    public void setType(Star.StarType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StarResponse)) return false;

        StarResponse response = (StarResponse) o;

        if (getX() != response.getX()) return false;
        if (getY() != response.getY()) return false;
        if (getId() != null ? !getId().equals(response.getId()) : response.getId() != null) return false;
        if (getName() != null ? !getName().equals(response.getName()) : response.getName() != null) return false;
        if (getPlanets() != null ? !getPlanets().equals(response.getPlanets()) : response.getPlanets() != null)
            return false;
        return getType() == response.getType();

    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + getX();
        result = 31 * result + getY();
        result = 31 * result + (getPlanets() != null ? getPlanets().hashCode() : 0);
        result = 31 * result + (getType() != null ? getType().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "StarResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", planets=" + planets +
                ", type=" + type +
                '}';
    }
}
