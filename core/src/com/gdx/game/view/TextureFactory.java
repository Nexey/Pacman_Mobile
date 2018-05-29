package com.gdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.gdx.game.model.*;

import java.util.HashMap;

public class TextureFactory {
    private HashMap<Class<?>, Texture> _textures;

    private TextureFactory() {
        _textures = new HashMap<Class<?>, Texture>();
        _textures.put(Pacman.class, new Texture(Gdx.files.internal("pacmanRight.png")));
        _textures.put(Block.class, new Texture(Gdx.files.internal("bloc.png")));
        _textures.put(Gom.class, new Texture(Gdx.files.internal("pellet.png")));
        _textures.put(SuperGom.class, new Texture(Gdx.files.internal("superpellet.png")));
        _textures.put(Dark.class, new Texture(Gdx.files.internal("dark.png")));
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
}

