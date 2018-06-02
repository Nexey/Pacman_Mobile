package com.gdx.game.controller.experts;

import com.badlogic.gdx.math.Vector2;
import com.gdx.game.model.GameElement;
import com.gdx.game.model.Gom;
import com.gdx.game.model.World;

public class MazeCOR extends AbstractCOR {
    private static MazeCOR instance = new MazeCOR();

    private AbstractCOR cor;

    private MazeCOR(){
        cor = new BlockCOR(
                new PelletCOR(
                        new BarrierCOR(null)
                )
        );
    }

    public static AbstractCOR getCOR(){
        return instance.cor;
    }

    @Override
    public boolean canBuild(int elementType) {
        return (elementType == this._VIDE);
    }

    @Override
    public GameElement construct(World world, int x, int y) {
        return new Gom(new Vector2(x, y), world);
    }
}