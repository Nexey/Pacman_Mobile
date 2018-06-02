package com.gdx.game.controller.experts;

import com.badlogic.gdx.math.Vector2;
import com.gdx.game.model.GameElement;
import com.gdx.game.model.World;
import com.gdx.game.model.Block;

public class BlockCOR extends AbstractCOR {

    public BlockCOR(AbstractCOR next) {
        super(next);
    }

    @Override
    public boolean canBuild(int elementType) {

        return (elementType == this._BLOCK);
    }

    @Override
    public GameElement construct(World world, int x, int y) {
        return new Block(new Vector2(x, y), world);
    }
}