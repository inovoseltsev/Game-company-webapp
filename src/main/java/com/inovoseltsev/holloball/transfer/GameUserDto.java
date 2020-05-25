package com.inovoseltsev.holloball.transfer;

import com.inovoseltsev.holloball.model.entity.Skin;

import java.util.Arrays;
import java.util.List;

public class GameUserDto {
    private String login;
    private Integer level;
    private Integer[] skinNumbers;
    private Integer coins;

    public GameUserDto() {
    }

    public GameUserDto(Integer level, List<Skin> skinNumbers, Integer coins) {
        this.level = level;
        this.skinNumbers = getSkinsNumbers(skinNumbers);
        this.coins = coins;
    }

    private Integer[] getSkinsNumbers(List<Skin> skins) {
        Integer[] skinsNumbers = new Integer[skins.size()];
        for (int i = 0; i < skins.size(); i++) {
            skinsNumbers[i] = skins.get(i).getSkinGameNumber();
        }
        return skinsNumbers;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer[] getSkinNumbers() {
        return skinNumbers;
    }

    public void setSkinNumbers(Integer[] skinNumbers) {
        this.skinNumbers = skinNumbers;
    }

    public Integer getCoins() {
        return coins;
    }

    public void setCoins(Integer coins) {
        this.coins = coins;
    }

    @Override
    public String toString() {
        return "GameUserDto{" +
                "level=" + level +
                ", skinNumbers=" + Arrays.toString(skinNumbers) +
                ", coins=" + coins +
                '}';
    }
}
