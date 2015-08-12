package com.hollonconsulting.solarwars.server.model.response.player;

public class PlayerResponse {
    private Integer id;
    private String username;
    private Integer money;
    private Integer score;

    public PlayerResponse(Integer id, String username, Integer money, Integer score) {
        this.id = id;
        this.username = username;
        this.money = money;
        this.score = score;
    }

    public PlayerResponse() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "PlayerResponse{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", money=" + money +
                ", score=" + score +
                '}';
    }
}
