package com.mygdx.game.model.manager;

public class Message {
    private final String text;
    private final Object object;

    public Message(String text) {
        this(text, null);
    }

    public Message(String text, Object object) {
        this.text = text;
        this.object = object;
    }

    public String getText() {
        return text;
    }

    public Object getObject() {
        return object;
    }
}
