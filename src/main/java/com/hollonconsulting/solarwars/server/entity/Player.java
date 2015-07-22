package com.hollonconsulting.solarwars.server.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table
public class Player implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    private String username;
    private Integer allianceId; //alliances TBD
    private Long lastLogin;
    private Integer score;
    private Integer money;
}
