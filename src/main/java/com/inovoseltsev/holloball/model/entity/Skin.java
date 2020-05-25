package com.inovoseltsev.holloball.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "skin")
public class Skin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "sking_id")
    private Long skinId;

    @Column(name = "skin_game_number", nullable = false, unique = true)
    private Integer skinGameNumber;

    public Skin() {
    }


    public Long getSkinId() {
        return skinId;
    }

    public void setSkinId(Long skinId) {
        this.skinId = skinId;
    }

    public Integer getSkinGameNumber() {
        return skinGameNumber;
    }

    public void setSkinGameNumber(Integer gameSkinNumber) {
        this.skinGameNumber = gameSkinNumber;
    }

    @ManyToMany(mappedBy = "skins")
    private List<AppUser> users;

    public List<AppUser> getUsers() {
        return users;
    }

    public void setUsers(List<AppUser> users) {
        this.users = users;
    }
}
