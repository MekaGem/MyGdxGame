package com.mygdx.game.model.object;

import com.mygdx.game.model.manager.Manager;
import com.mygdx.game.model.manager.Message;

import java.util.ArrayList;
import java.util.List;

public abstract class GameObject {
    private final List<Manager> managers = new ArrayList<>();

    public void receive(Message message) {}

    public void act(float delta) {}

    public final void update(float delta) {
        for (Manager manager : managers) {
            manager.update(delta);
        }
        act(delta);
    }

    public final void sendMessage(Message message) {
        for (Manager manager : managers) {
            manager.receive(message);
        }
        receive(message);
    }

    public final void subscribe(Manager manager) {
        managers.add(manager);
    }

    public final void unsubscribe(Manager manager) {
        managers.remove(manager);
    }
}
