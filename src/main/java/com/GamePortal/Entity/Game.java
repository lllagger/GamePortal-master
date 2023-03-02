package com.GamePortal.Entity;

import com.GamePortal.Utils.UserSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "gameinformation")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gameId;
    @Column
    private String gameName;
    @Column
    private int gameCost;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(
            name = "user_game",
            joinColumns = {@JoinColumn(name = "game_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )

    @JsonSerialize(using = UserSerializer.class)
    private List<User> gameUsers = new ArrayList<>();

    public Game() {
    }

    public Game(int gameId, String gameName, int gameCost) {
        super();
        this.gameId = (long) gameId;
        this.gameName = gameName;
        this.gameCost = gameCost;
    }

    public void addUser(User user) {
        gameUsers.add(user);
    }


    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public int getGameCost() {
        return gameCost;
    }

    public void setGameCost(int gameCost) {
        this.gameCost = gameCost;
    }

    public void usersGame(User user) {
        gameUsers.add(user);
    }

    public List<User> getUsersGame() {
        return gameUsers;
    }

    public String toString() {
        return "Game[gameId=" + gameId + ",gameName=" + gameName + ",gameCost=" + gameCost + "]";
    }
}
