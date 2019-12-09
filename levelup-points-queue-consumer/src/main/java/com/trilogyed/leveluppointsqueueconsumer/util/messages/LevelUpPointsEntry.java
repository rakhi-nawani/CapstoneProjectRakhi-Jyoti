package com.trilogyed.leveluppointsqueueconsumer.util.messages;

public class LevelUpPointsEntry {

    private int levelUpId;
    private int customerId;
    private int points;

    public LevelUpPointsEntry() {
    }

    public LevelUpPointsEntry(int levelUpId, int customerId, int points) {
        this.levelUpId = levelUpId;
        this.customerId = customerId;
        this.points = points;
    }

    public int getLevelUpId() {
        return levelUpId;
    }

    public void setLevelUpId(int levelUpId) {
        this.levelUpId = levelUpId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "LevelUpPointsEntry{" +
                "levelUpId=" + levelUpId +
                ", customerId=" + customerId +
                ", points=" + points +
                '}';
    }
}
