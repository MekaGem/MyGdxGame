package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.model.manager.Manager;
import com.mygdx.game.model.manager.Message;
import com.mygdx.game.model.object.Man;

public class GameScreen extends BasicScreen {
    private Actor actor = new Actor() {
        private final ShapeRenderer renderer = new ShapeRenderer();
        private final Texture logo = new Texture(Gdx.files.internal("badlogic.jpg"));

        @Override
        public void draw(Batch batch, float parentAlpha) {
            batch.end();

            renderer.setProjectionMatrix(batch.getProjectionMatrix());
            renderer.setTransformMatrix(batch.getTransformMatrix());
            renderer.translate(getX(), getY(), 0);

            renderer.begin(ShapeRenderer.ShapeType.Line);
            renderer.rect(0, 0, 50, 100);
            renderer.end();

            batch.begin();
            //batch.draw(logo, 0, 0);
        }
    };

    private Man man = new Man();

    public GameScreen(MyGdxGame game) {
        super(game);

    }

    @Override
    public void show() {
        super.show();
        stage.addActor(actor);
        actor.setPosition(0, 0);
        Manager manIdle = man.new ManIdle(man);
        man.subscribe(manIdle);

    }

    @Override
    public void updateScreen(float delta) {
        stage.act(delta);
        man.update(delta);
        actor.setPosition(man.getPosition().x, man.getPosition().y);
    }

    @Override
    public void renderScreen() {
//        System.err.println(actor.getX() + ":" + actor.getY());
//        stage.getViewport().getCamera().translate(actor.getX(), actor.getY(), 0);
//        stage.getViewport().getCamera().update();
        stage.draw();
//        stage.getViewport().getCamera().translate(-actor.getX(), -actor.getY(), 0);
//        stage.getViewport().getCamera().update();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.SPACE) {
            man.sendMessage(new Message("move"));
        }
        return true;
    }
}
