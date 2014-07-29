package com.mygdx.game.model.manager;

import com.mygdx.game.model.object.GameObject;

import java.util.LinkedList;
import java.util.Queue;

public abstract class Manager {
    private final Queue<Message> messages = new LinkedList<>();
    private final GameObject reference;

    protected Manager(GameObject reference) {
        this.reference = reference;
    }

    public void receive(Message message) {}

    public void act(float delta) {}

    public final GameObject getReference() {
        return reference;
    }

    public final void update(float delta) {
        while (!messages.isEmpty()) {
            receive(messages.poll());
        }
        act(delta);
    }

    protected final void stop() {
        reference.unsubscribe(this);
    }

    protected final void stop(Manager manager) {
        stop();
        if (manager.reference != reference) {
            throw new IllegalArgumentException("Different reference to GameObject");
        }
        reference.subscribe(manager);
    }
}
