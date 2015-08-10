package com.hollonconsulting.solarwars.server.entity;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
public class Player extends BaseEntity {

    @Id
    @GeneratedValue
    private Integer id;

    private String username;
    private String password;
    private String email;
    private Integer allianceId; //alliances TBD
    private Long lastLogin;
    private Integer score;
    private Integer money;
    private boolean locked;
    private boolean enabled;
    private Integer failedAttempts;

    public Player() {
    }

    public Player(Integer allianceId, Long lastLogin, Integer money, String password, Integer score, String username, boolean isLocked, boolean isEnabled, Integer failedAttempts, String email) {
        this.allianceId = allianceId;
        this.lastLogin = lastLogin;
        this.money = money;
        this.password = password;
        this.score = score;
        this.username = username;
        this.locked = isLocked;
        this.enabled = isEnabled;
        this.failedAttempts = failedAttempts;
        this.email = email;
    }

    public static Player CreateNewPlayer(String username, String password, String email){
        return new Player(null, null, 1000, password, 0, username, false, true, 0, email);
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAllianceId() {
        return allianceId;
    }

    public void setAllianceId(Integer allianceId) {
        this.allianceId = allianceId;
    }

    public Long getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Long lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public Integer getFailedAttempts() {
        return failedAttempts;
    }

    public void setFailedAttempts(Integer failedAttempts) {
        this.failedAttempts = failedAttempts;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", allianceId=" + allianceId +
                ", lastLogin=" + lastLogin +
                ", score=" + score +
                ", money=" + money +
                ", locked=" + locked +
                ", enabled=" + enabled +
                ", failedAttempts=" + failedAttempts +
                '}';
    }
}
