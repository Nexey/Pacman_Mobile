package com.gdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.gdx.game.model.*;
import com.gdx.game.utilities.Util;

import java.util.HashMap;

public class TextureFactory {
    private HashMap<Class<?>, Texture> _textures;
    private HashMap<String, Texture> _sprites;

    private TextureFactory() {
        _textures = new HashMap<Class<?>, Texture>();
        _textures.put(Pacman.class, new Texture(Gdx.files.internal("pacmanRight.png")));
        _textures.put(Block.class, new Texture(Gdx.files.internal("bloc.png")));
        _textures.put(Gom.class, new Texture(Gdx.files.internal("pellet.png")));
        _textures.put(SuperGom.class, new Texture(Gdx.files.internal("superpellet.png")));
        _textures.put(Dark.class, new Texture(Gdx.files.internal("dark.png")));

        _sprites = new HashMap<String, Texture>();
        _sprites.put("pacman", new Texture(Gdx.files.internal("pacmanRight.png")));
        _sprites.put("ghost1", new Texture(Gdx.files.internal("ghost1.png")));
        _sprites.put("ghost2", new Texture(Gdx.files.internal("ghost2.png")));
        _sprites.put("ghost3", new Texture(Gdx.files.internal("ghost3.png")));
        _sprites.put("ghost4", new Texture(Gdx.files.internal("ghost4.png")));
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

