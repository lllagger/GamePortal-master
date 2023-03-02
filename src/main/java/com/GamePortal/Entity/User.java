package com.GamePortal.Entity;

import com.GamePortal.Utils.GameSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "userinformation")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column
    private String userName;
    @Column
    private int userCredit;
    @Column
    private String userPassword;
    @Column
    private String userRole;
    @ManyToMany(mappedBy = "gameUsers", fetch = FetchType.LAZY)
    @JsonSerialize(using = GameSerializer.class)
    private List<Game> userGames = new ArrayList<>();

    public User() {
    }

    public User(Long userId, String userName, int userCredit, String userPassword, String userRole, List<Game> userGames) {
        this.userId = userId;
        this.userName = userName;
        this.userCredit = userCredit;
        this.userPassword = userPassword;
        this.userRole = userRole;
        this.userGames = userGames;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserCredit() {
        return userCredit;
    }

    public void setUserCredit(int userCredit) {
        this.userCredit = userCredit;
    }

    public List<Game> getUserGames() {
        return userGames;
    }

    public void setUserGames(List<Game> userGames) {
        this.userGames = userGames;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}
