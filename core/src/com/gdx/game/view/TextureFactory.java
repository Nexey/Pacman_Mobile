package com.gdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.gdx.game.model.*;
import com.gdx.game.controller.utilities.Util;

import java.util.HashMap;

public class TextureFactory {
    private HashMap<Class<?>, Texture> _textures;
    private HashMap<String, Texture> _sprites;
    private HashMap<Integer, String> _directions;
    private HashMap<Integer, String> _directionStep;
    private String pacman;

    private TextureFactory() {
        _textures = new HashMap<Class<?>, Texture>();
        _textures.put(Block.class, new Texture(Gdx.files.internal("bloc.png")));
        _textures.put(Gom.class, new Texture(Gdx.files.internal("pellet.png")));
        _textures.put(SuperGom.class, new Texture(Gdx.files.internal("superpellet.png")));
        _textures.put(Dark.class, new Texture(Gdx.files.internal("dark.png")));

        _sprites = new HashMap<String, Texture>();
        _sprites.put("ghost1", new Texture(Gdx.files.internal("ghost1.png")));
        _sprites.put("ghost2", new Texture(Gdx.files.internal("ghost2.png")));
        _sprites.put("ghost3", new Texture(Gdx.files.internal("ghost3.png")));
        _sprites.put("ghost4", new Texture(Gdx.files.internal("ghost4.png")));

        _directions = new HashMap<Integer, String>();
        _directions.put(Util.LEFTG, "Left");
        _directions.put(Util.DOWNG, "Down");
        _directions.put(Util.RIGHTG, "Right");
        _directions.put(Util.UPG, "Up");

        _directionStep = new HashMap<Integer, String>();
        _directionStep.put(0, "");
        _directionStep.put(1, "-2");

        pacman = "pacman";
        String path;
        for (int i = 0; i < _directionStep.size(); i++)
            for (int j = 0; j < _directions.size(); j++) {
                path = pacman + _directions.get(j) + _directionStep.get(i);
                _sprites.put(path, new Texture(Gdx.files.internal(path + ".png")));
            }

    }

    private static TextureFactory instance = null;

    static public void reset() {
        instance = null;
    }

    public static TextureFactory getInstance() {
        if (instance == null)
            instance = new TextureFactory();
        return instance;
    }

    public Texture getTexture(Class<? extends GameElement> aClass) {
        return _textures.get(aClass);
    }

    public Texture getTexture(String spriteName) {
        return _sprites.get(spriteName);
    }
}

