package com.hollonconsulting.solarwars.server.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Roles extends BaseEntity {

    @Id
    @GeneratedValue
    private Integer id;

    private Integer playerId;
    private Role role;

    public Roles() {
    }

    public Roles(Role role, Integer playerId) {
        this.role = role;
        this.playerId = playerId;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public enum Role {
        USER,
        BANNED,
        ADMIN
    }

    @Override
    public String toString() {
        return "Roles{" +
                "id=" + id +
                ", playerId=" + playerId +
                ", role=" + role +
                '}';
    }
}
