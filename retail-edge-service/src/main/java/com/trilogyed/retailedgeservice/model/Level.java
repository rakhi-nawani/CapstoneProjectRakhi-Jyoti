package com.trilogyed.retailedgeservice.model;

import java.time.LocalDate;
import java.util.Objects;

public class Level {

    private int levelUpId;
    private int customerId;
    private int points;
    private LocalDate memberDate;

    public Level() {
    }

    public Level(int id, int customerId, int points, LocalDate memberDate) {
        this.levelUpId = id;
        this.customerId = customerId;
        this.points = points;
        this.memberDate = memberDate;
    }

    public Level(int customerId, int points, LocalDate memberDate) {
        this.customerId = customerId;
        this.points = points;
        this.memberDate = memberDate;
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

    public LocalDate getMemberDate() {
        return memberDate;
    }

    public void setMemberDate(LocalDate memberDate) {
        this.memberDate = memberDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Level levelUp = (Level) o;
        return levelUpId == levelUp.levelUpId &&
                customerId == levelUp.customerId &&
                points == levelUp.points &&
                Objects.equals(memberDate, levelUp.memberDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(levelUpId, customerId, points, memberDate);
    }

    @Override
    public String toString() {
        return "LevelUp{" +
                "id=" + levelUpId +
                ", customerId=" + customerId +
                ", points=" + points +
                ", memberDate=" + memberDate +
                '}';
    }
}
