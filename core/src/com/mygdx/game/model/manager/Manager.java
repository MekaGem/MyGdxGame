package com.mygdx.game.model.manager;

import com.mygdx.game.model.object.GameObject;

import java.util.LinkedList;
import java.util.Queue;

public abstract class Manager {
    public final Queue<Message> messages = new LinkedList<>();
    private final GameObject reference;
    private boolean stopped = false;

    protected Manager(GameObject reference) {
        this.reference = reference;
    }

    public void onStart () {}

    public void onStop () {}

    public void receive(Message message) {}

    public void act(float delta) {}

    public final GameObject getReference() {
        return reference;
    }

    public final void update(float delta) {
        while (!messages.isEmpty() && !stopped) {
            receive(messages.poll());
        }
        if(!stopped)
            act(delta);
    }

    protected final void stop() {
        if(!stopped) {
            reference.unsubscribe(this);
            onStop();
            stopped = true;
        }
    }

    protected final void stop(Manager manager) {
        if(!stopped) {
            stop();
            if (manager.reference != reference) {
                throw new IllegalArgumentException("Different reference to GameObject");
            }
            manager.messages.addAll(messages);
            reference.subscribe(manager);
        }
    }
}
