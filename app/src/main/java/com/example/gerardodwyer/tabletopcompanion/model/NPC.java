package com.example.gerardodwyer.tabletopcompanion.model;

/**
 * Created by Gerard o dwyer on 12/03/2019.
 */

public class NPC {

    private String name;
    private String weapon;
    private String power;
    private String allegiance;
    private String appearance;
    private String backstory;

    public NPC(String name, String weapon, String power, String allegiance, String appearance, String backstory) {
        this.name = name;
        this.weapon = weapon;
        this.power = power;
        this.allegiance = allegiance;
        this.appearance = appearance;
        this.backstory = backstory;
    }

    public NPC() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeapon() {
        return weapon;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getAllegiance() {
        return allegiance;
    }

    public void setAllegiance(String allegiance) {
        this.allegiance = allegiance;
    }

    public String getAppearance() {
        return appearance;
    }

    public void setAppearance(String appearance) {
        this.appearance = appearance;
    }

    public String getBackstory() {
        return backstory;
    }

    public void setBackstory(String backstory) {
        this.backstory = backstory;
    }
}
