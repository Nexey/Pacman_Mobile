package com.gdx.game.controller.experts;

import com.badlogic.gdx.math.Vector2;
import com.gdx.game.model.GameElement;
import com.gdx.game.model.World;
import com.gdx.game.model.Gom;

public class PelletCOR extends AbstractCOR {

    public PelletCOR(AbstractCOR next) {
        super(next);
    }

    @Override
    public boolean canBuild(int elementType) {
        return (elementType == this._VIDE) || (elementType == this._INTERSECTION);
    }

    @Override
    public GameElement construct(World world, int x, int y) {
        return new Gom(new Vector2(x, y), world);
    }
}