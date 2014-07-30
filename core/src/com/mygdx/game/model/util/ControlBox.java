package com.mygdx.game.model.util;

import com.mygdx.game.model.object.GameObject;
import com.mygdx.game.model.manager.Message;

import java.util.ArrayList;
import java.util.List;


public class ControlBox {
    public final List<GameObject> Listeners = new ArrayList<>();
    public int directionX = 0;
    public int directionY = 0;
    public boolean aPressed = false;
    public boolean bPressed = false;
    public boolean leftPressed = false;
    public boolean rightPressed = false;
    public boolean upPressed = false;
    public boolean downPressed = false;
    private float timer = 0;
    private final float timerLimit = 1;
    private int lastX = 0;
    public final void update(float delta) {
        if(timer>0)
            timer-=delta;
    }
    public void sendMessage (Message message) {
        for (GameObject gameObject : Listeners) {
            gameObject.sendMessage(message);
        }
    }
    public void pressRight() {
        if(lastX == 1 && timer>0) {
            sendMessage(new Message("jump",1));
        }
        else {
            if(directionX == 0 && directionY == 0) {
                lastX = 1;
                timer = timerLimit;
            }
            else
                timer = 0;
        }
        rightPressed = true;
        directionX = 1;
    }
    public void pressLeft() {
        if(lastX == -1 && timer>0) {
            sendMessage(new Message("jump",-1));
        }
        else {
            if(directionX == 0 && directionY == 0) {
                lastX = -1;
                timer = timerLimit;
            }
            else
                timer = 0;
        }
        leftPressed = true;
        directionX = -1;
    }
    public void pressDown() {
        downPressed = true;
        directionY = 1;
        timer = 0;
    }
    public void pressUp() {
        upPressed = true;
        directionY = -1;
        timer = 0;
    }
    public void pressA() {
        aPressed = true;
    }
    public void pressB() {
        bPressed = true;
    }
    public void releaseRight() {
        rightPressed = false;
        if(leftPressed)
            directionX = -1;
        else
            directionX = 0;

    }
    public void releaseLeft() {
        leftPressed = false;
        if(rightPressed)
            directionX = 1;
        else
            directionX = 0;
    }
    public void releaseDown() {
        downPressed=false;
        if(upPressed)
            directionY = -1;
        else
            directionY = 0;
    }
    public void releaseUp() {
        upPressed = false;
        if(downPressed)
            directionY = 1;
        else
            directionY = 0;
    }
    public void releaseA() {
        aPressed =false;
    }
    public void releaseB() {
        bPressed = false;
    }
}
