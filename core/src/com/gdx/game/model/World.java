package com.gdx.game.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.gdx.game.controller.utilities.Util;

import java.util.ArrayList;
import java.util.Iterator;

public class World implements Iterable<GameElement> {
    private Maze _maze;
    private Pacman _pacman;
    private Ghost _red;
    private Ghost _pink;
    private Ghost _blue;
    private Ghost _yellow;
    public ArrayList<Entity> listEntity;
    public ArrayList<Entity> listMovingEntities;

    private long startTime;

    public World() {

        listEntity = new ArrayList<Entity>();
        listMovingEntities = new ArrayList<Entity>();
        this._pacman = new Pacman(new Vector2(17, 14), this);
        this._red = new Ghost(new Vector2(14, 15), this, Util.RED, 1);
        this._pink = new Ghost(new Vector2(14, 14), this, Util.PINK, 2);
        this._blue = new Ghost(new Vector2(14, 13), this, Util.BLUE, 3);
        this._yellow = new Ghost(new Vector2(14, 12), this, Util.YELLOW, 2);

        this.listEntity.add(this._pacman);
        this.listEntity.add(this._red);
        this.listEntity.add(this._pink);
        this.listEntity.add(this._blue);
        this.listEntity.add(this._yellow);
        this._maze = new Maze(this);

        startTime = TimeUtils.millis();
    }

    void setMaze(Maze maze) { this._maze = maze;}

    public int getHeight() {
        return this._maze.getHeight();
    }

    public int getWidth() {
        return this._maze.getWidth();
    }

    public void moveEntities() {
        for (Entity E : this.listEntity) {
            if (E.move()) {
                if (!this.listMovingEntities.contains(E)) {
                    E.alpha = 0;
                    this.listMovingEntities.add(E);
                }
            }
        }
    }

    public void resetDirections()
    {
        Util.currentDir = Util.NOWHERE;
        Util.previousDir = Util.NOWHERE;
        this._red.setDir(Util.NOWHERE);
        this._pink.setDir(Util.NOWHERE);
        this._blue.setDir(Util.NOWHERE);
        this._yellow.setDir(Util.NOWHERE);
    }

    public void set(Vector2 pos, GameElement tile) {
        this._maze.set(pos, tile);
    }

    public Maze getMaze() {
        return _maze;
    }

    public Pacman getPacman() {
        return _pacman;
    }
    public Ghost get_red() { return _red; }
    public Ghost get_pink() { return _pink; }
    public Ghost get_blue() { return _blue; }
    public Ghost get_yellow() { return _yellow; }

    @Override
    public Iterator<GameElement> iterator() {
        return new WorldIterator(this);
    }
}

class WorldIterator implements Iterator<GameElement>
{
    private World _world;
    private Iterator<GameElement> _mazeIterator;
    private int _i;

    WorldIterator(World world) {
        this._world = world;
        this._mazeIterator = this._world.getMaze().iterator();
        this._i = 0; /* 0 = maze, 1 = pacman */
    }

    @Override
    public boolean hasNext() {
        return (this._i < 1);
    }

    @Override
    public GameElement next() {
        if(_i == 0) {
            if (!this._mazeIterator.hasNext())
                _i = 1; // on passe à Pacman
        }
        else _i++;

        GameElement res;
        switch(this._i) {
            case 0 : return this._mazeIterator.next();
            case 1 : return this._world.getPacman();
            default : return null; /* erreur */
        }
    }

    @Override
    public void remove() {
    }
}
