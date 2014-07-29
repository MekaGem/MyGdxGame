package com.mygdx.game.model.object;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.mygdx.game.model.manager.Manager;
import com.mygdx.game.model.manager.Message;
import com.mygdx.game.model.util.Direction;

public class Man extends GameObject {
    private Vector2 position = new Vector2(0, 0);
    private Direction moveDirection = Direction.NONE;
    private final float speed = 10.0f;

    private void move(float delta) {
        final float distance = speed * delta;
        position.add(moveDirection.x * distance, moveDirection.y * distance);
    }

    public Vector2 getPosition() {
        return position;
    }

    @Override
    public void receive(Message message) {

    }

    @Override
    public void act(float delta) {

    }

    public class ManIdle extends Manager {
        public ManIdle(GameObject reference) {
            super(reference);
        }

        @Override
        public void receive(Message message) {
            if (message.getText().equals("move")) {
                moveDirection = Direction.RIGHT;
            }
        }

        @Override
        public void act(float delta) {
            if (moveDirection != Direction.NONE) {
                stop(new ManMove(getReference()));
            }
        }
    }

    public class ManMove extends Manager {
        public ManMove(GameObject reference) {
            super(reference);
        }

        @Override
        public void receive(Message message) {
            if (message.getText().equals("move")) {
                moveDirection = Direction.NONE;
            }
        }

        @Override
        public void act(float delta) {
            if (moveDirection != Direction.NONE) {
                move(delta);
            } else {
                stop(new ManIdle(getReference()));
            }
        }
    }
}
