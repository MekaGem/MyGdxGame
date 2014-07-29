package com.mygdx.game.model.util;

public enum Direction {
    UP(0, 1),
    RIGHT(1, 0),
    DOWN(0, -1),
    LEFT(-1, 0),
    NONE(0, 0);

    public final float x;
    public final float y;

    private Direction(float x, float y) {
        this.x = x;
        this.y = y;
    }
}
