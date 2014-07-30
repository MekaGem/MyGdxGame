package com.mygdx.game.model.object;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.mygdx.game.model.manager.Manager;
import com.mygdx.game.model.manager.Message;
import com.mygdx.game.model.util.Direction;

public class Unit extends GameObject {
    private Vector3 position = new Vector3(0, 0, 0);
    //private final float speed = 10.0f;

    public Vector3 getPosition() {
        return position;
    }
}
