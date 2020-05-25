package com.inovoseltsev.holloball.model.entity;


import com.inovoseltsev.holloball.model.role.Role;
import com.inovoseltsev.holloball.model.state.State;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "app_user")
public class AppUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "login", nullable = false, unique = true)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "role", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Column(name = "state", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private State state;

    @Column(name = "level")
    private Integer level;

    @Column(name = "coins")
    private Integer coins;

    public AppUser() {
    }

    public AppUser(String firstName, String lastName, String login,
                   String password, String email, Role role, State state,
                   Integer level, Integer coins) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = role;
        this.state = state;
        this.level = level;
        this.coins = coins;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }


    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getCoins() {
        return coins;
    }

    public void setCoins(Integer coins) {
        this.coins = coins;
    }

    @ManyToMany
    @JoinTable(name = "user_skin", joinColumns =
    @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name =
            "skin_id"))
    private List<Skin> skins;

    public List<Skin> getSkins() {
        return skins;
    }

    public void setSkins(List<Skin> skins) {
        this.skins = skins;
    }
}
