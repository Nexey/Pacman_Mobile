package com.gdx.game.controller.experts;

import com.badlogic.gdx.math.Vector2;
import com.gdx.game.model.Fence;
import com.gdx.game.model.GameElement;
import com.gdx.game.model.World;

public class FenceCOR extends AbstractCOR {

    public FenceCOR(AbstractCOR next) {
        super(next);
    }

    @Override
    public boolean canBuild(int elementType) {
        return (elementType == this._FENCE);
    }

    @Override
    public GameElement construct(World world, int x, int y) {
        return new Fence(new Vector2(x, y), world);
    }
}
