package com.mygdx.game.model.util;

public final class Cooldown {
    private final float period;
    private float current;

    public Cooldown(float period) {
        if (period < 0) {
            throw new IllegalArgumentException("Cooldown period is less than zero");
        }
        this.period = period;
    }

    public void tick(float delta) {
        if (delta < 0) {
            throw new IllegalArgumentException("Delta time is less than zero");
        }
        current = Math.min(period, current + delta);
    }

    public boolean isStopped() {
        return current == period;
    }
}
