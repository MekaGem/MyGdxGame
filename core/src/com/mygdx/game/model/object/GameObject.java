package com.mygdx.game.model.object;

import com.mygdx.game.model.manager.Manager;
import com.mygdx.game.model.manager.Message;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public abstract class GameObject {
    private final Queue<Message> messages = new LinkedList<>();
    private final List<Manager> managers = new ArrayList<>();

    public void receive(Message message) {}

    public void act(float delta) {}

    public final void update(float delta) {
        for (Manager manager : managers) {
            manager.update(delta);
        }
        while (!messages.isEmpty()) {
            receive(messages.poll());
        }
        act(delta);
    }

    public final void sendMessage(Message message) {
        for (Manager manager : managers) {
            manager.messages.add(message);
        }
        messages.add(message);
    }

    public final void subscribe(Manager manager) {
        managers.add(manager);
        manager.onStart();
    }

    public final void unsubscribe(Manager manager) {
        managers.remove(manager);
    }
}
