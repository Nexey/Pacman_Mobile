package com.gdx.game.controller.experts;
import com.gdx.game.model.GameElement;
import com.gdx.game.model.World;

public abstract class AbstractCOR {
    protected final int _BLOCK = 0;
    protected final int _GOMME = 1;
    protected final int _INTERSECTION = 2;
    protected final int _VIDE = 3;
    protected final int _SUPERPELLET = 4;

    public AbstractCOR next;

    public AbstractCOR(AbstractCOR next){
        this.next = next;
    }

    public AbstractCOR() {
        this.next = null;
    }

    public abstract boolean canBuild(int elementType);
    public abstract GameElement construct(World world, int x, int y);


    public GameElement build(World world, int elementType, int x, int y){
        if(canBuild(elementType))
            return construct(world, x, y);

        if(next == null)
            return null;

        return next.build(world, elementType, x, y);
    }
}