package com.example.gerardodwyer.tabletopcompanion.model;

/**
 * Created by Gerard o dwyer on 29/04/2019.
 */

public class Quest {

    private String title;
    private String rewards;
    private String details;

    public Quest(String title, String rewards, String details) {
        this.title = title;
        this.rewards = rewards;
        this.details = details;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRewards() {
        return rewards;
    }

    public void setRewards(String rewards) {
        this.rewards = rewards;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
