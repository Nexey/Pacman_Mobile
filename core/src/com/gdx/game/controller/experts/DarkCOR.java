package com.gdx.game.controller.experts;

import com.badlogic.gdx.math.Vector2;
import com.gdx.game.model.Dark;
import com.gdx.game.model.GameElement;
import com.gdx.game.model.World;

public class DarkCOR extends AbstractCOR  {

    public DarkCOR(AbstractCOR next) {
        super(next);
    }

    @Override
    public boolean canBuild(int elementType) {
        return (elementType == this._VIDE);
    }

    @Override
    public GameElement construct(World world, int x, int y) {
        return new Dark(new Vector2(x, y), world);
    }
}
