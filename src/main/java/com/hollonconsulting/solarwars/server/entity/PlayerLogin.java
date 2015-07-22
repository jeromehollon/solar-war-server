package com.hollonconsulting.solarwars.server.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table
public class PlayerLogin implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    private Integer playerId;
    private Date timestamp;
    private String deviceModel;
    private String deviceManufacturer;
    private String deviceBuild;
    private String deviceVersion;

    
}
