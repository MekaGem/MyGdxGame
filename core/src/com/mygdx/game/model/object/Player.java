package com.mygdx.game.model.object;

import com.mygdx.game.model.manager.Manager;
import com.mygdx.game.model.manager.Message;
import com.mygdx.game.model.util.ControlBox;

public class Player extends Unit {
    public boolean canMove = true;
    public float x;
    public float y;
    public float z;
    public int hp = 100;
    public float slow;
    public float speed = 1;
    public ControlBox controlBox;

    public boolean blink = false;
    public int LookDirection = 1; //1 or -1
    public PlayerAnimation playerAnimation = PlayerAnimation.idle;

    public enum PlayerAnimation {idle, walk, jump}
    public Player (ControlBox controlBox){
        this.controlBox = controlBox;
        controlBox.Listeners.add(this);
    }

    @Override
    public void receive(Message message) {
        if (message.getText().equals("hurt")) {
            //problem: how to make Player invincible in PlayerJump?

            hp-=(int)message.getObject();
            if(hp<0) {
                //SendMessage(new Message("dies"); ??
                //subscribe(new PlayerDies(this));
            }
            else {
                subscribe(new PlayerHurt(this));
            }
        }
    }

    @Override
    public void act(float delta) {
        if(canMove) {
            if(controlBox.directionX==0&&controlBox.directionY==0) {
                playerAnimation = PlayerAnimation.idle;
            }
            else
            {
                playerAnimation = PlayerAnimation.walk;
                if(controlBox.directionX!=0) {
                    LookDirection = controlBox.directionX;
                    x += controlBox.directionX * speed * delta * slow;
                }
                y+=controlBox.directionY*speed*delta*slow;
            }
        }
    }

    public class PlayerDefault extends Manager {
        public PlayerDefault(GameObject reference) {
            super(reference);
        }

        @Override
        public void receive(Message message) {
            if (message.getText().equals("jump")) {
                stop(new PlayerJump(getReference(),(int)message.getObject()));
            }
        }

        @Override
        public void act(float delta) {
        }
    }

    public class PlayerHurt extends Manager{
        private float timer = 1;
        private float blinkPeriod = timer/5;
        public PlayerHurt(GameObject reference) {
            super(reference);
        }

        @Override
        public void receive(Message message) {
            if (message.getText().equals("hurt")) {
                stop();
            }
        }

        @Override
        public void act(float delta) {
            timer-=delta;
            if(timer<=0) {
                ((Player) getReference()).blink = false;
                stop();
            }
            else {
                ((Player) getReference()).blink = Math.floor(timer / blinkPeriod) % 2 != 0;
            }
        }
    }
    public class PlayerJump extends Manager {
        public int direction;
        private final float speed = 2;
        private float timer = 2;
        public PlayerJump(GameObject reference, int direction) {
            super(reference);
            this.direction = direction;
        }
        @Override
        public void onStart () {
            ((Player)getReference()).playerAnimation = PlayerAnimation.jump;
            ((Player)getReference()).canMove = false;
        }

        @Override
        public void onStop () {
            ((Player)getReference()).canMove = true;
        }

        @Override
        public void receive(Message message) {
            if (message.getText().equals("hurt")) {
            }
        }
        @Override
        public void act(float delta) {
            timer-=delta;
            if(timer>0) {
                ((Player) getReference()).x += delta * speed * direction;
                //check collisions
                //perform collisions if any occurred
            }
            else
                stop(new PlayerDefault((getReference())));
        }
    }
    /*public class PlayerStartAttack extends Manager {
        public PlayerStartAttack(GameObject reference) {
            super(reference);
        }

        @Override
        public void receive(Message message) {
            if (message.getText().equals("jump")) {
            }
            if (message.getText().equals("hurt")) {
            }
        }

        @Override
        public void act(float delta) {

        }
    }
    public class PlayerFinishAttack extends Manager {
        public int direction;
        public PlayerFinishAttack(GameObject reference, int direction) {
            super(reference);
            this.direction = direction;
        }

        @Override
        public void receive(Message message) {
            if (message.getText().equals("hurt")) {
            }
        }

        @Override
        public void act(float delta) {
        }
    }*/
}
