package com.gdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.gdx.game.controller.experts.MazeCOR;
import com.gdx.game.controller.utilities.Util;
import com.gdx.game.view.TextureFactory;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Maze implements Iterable<GameElement> {
	private World _world;
    private /*final*/ int _width;
    private /*final*/ int _height;
    public GlyphLayout layout;
    public boolean placeBarrier;

	public Texture listText[] = {
			TextureFactory.getInstance().getTexture(Block.class),
			TextureFactory.getInstance().getTexture(Gom.class),
			TextureFactory.getInstance().getTexture(SuperGom.class),
			TextureFactory.getInstance().getTexture(Dark.class),
            TextureFactory.getInstance().getTexture(Fence.class),
			TextureFactory.getInstance().getTexture("ghostEscaping"),
			TextureFactory.getInstance().getTexture("ghostDead"),
			TextureFactory.getInstance().getTexture("ghost1"),
			TextureFactory.getInstance().getTexture("ghost2"),
			TextureFactory.getInstance().getTexture("ghost3"),
			TextureFactory.getInstance().getTexture("ghost4")
	};

	public final int _textWidth = 16;
	public final int _textHeight = 16;

	/* 0 : mur, 1 : gomme, 2 : intersection, 3 : barrières fantomes, 4 : super gomme, 5 : vide */
    private int[][] _laby1 = new int[][] {
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 0},
			{0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0},
			{0, 4, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 4, 0},
			{0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0},
			{0, 2, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 2, 0},
			{0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0},
			{0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0},
			{0, 1, 1, 1, 1, 1, 2, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 2, 1, 1, 1, 1, 1, 0},
			{0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 5, 5, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 3, 3, 3, 3, 3, 3, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0},
			{1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 0, 3, 3, 3, 3, 3, 3, 0, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1},
			{0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 1, 0, 0, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 0, 0, 1, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0},
			{0, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 0, 0, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 0},
			{0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0},
			{0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0},
			{0, 4, 1, 1, 0, 0, 2, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1, 1, 2, 0, 0, 1, 1, 4, 0},
			{0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0},
			{0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0},
			{0, 1, 1, 2, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 2, 1, 1, 0},
			{0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
			{0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
			{0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
	};

	public GameElement[][] _laby2;
	public SpriteBatch batch;
	public BitmapFont score;

    Maze(World w) {
        layout = new GlyphLayout();
		_world = w;
		this.init();
		w.setMaze(this);
	}

	private void init () {
		this._height = _laby1.length;
		this._width  = _laby1[0].length;
		this._laby2 = new GameElement[this._height][this._width];

		int x = 0,y = 0;
		for(int[] t : _laby1) {
			for(int elementType : t) {
				GameElement element = MazeCOR.getCOR().build(
						this._world,
						elementType,
						x,
						y);
				this._laby2[x][y] = element;
				y = (++y % this._width);
			}
			x++;
		}

		// On place le PacMan et les fantomes
		for (Entity E : this._world.listEntity)
			this._laby2[(int)E.getPosition().x][(int)E.getPosition().y] = E;
	}

	GameElement get(int x, int y) {
		return this._laby2[x][y];
	}

	GameElement get(Vector2 pos) {
		return this._laby2[(int)pos.x][(int)pos.y];
	}

	public int getCase(Vector2 pos) {
		return this._laby1[(int)pos.x][(int)pos.y];
	}

	public int getHeight() { return _height; }

	public int getWidth()  { return _width; }

	public World getWorld() {
		return _world;
	}

	/*public void updateMaze(SpriteBatch batch, BitmapFont score) {
	    if (!this.placeBarrier) {
            this.placeBarrier = this._world.get_yellow().isSorti();// && this._world.get_blue().isSorti() && this._world.get_pink().isSorti() && this._world.get_red().isSorti();
            if (this.placeBarrier) {
                this._laby2[12][14] = new Fence(new Vector2(12, 14), this._world);
                this._laby2[13][14] = new Fence(new Vector2(13, 14), this._world);
            }
        }
	    if (Util.currentDir != Util.NOWHERE) {
			this._world.getPacman().updateAnimation();
			// Après cet appel, les tiles des Entity seront mises à jour
			this._world.moveEntities();

			// Il faut redessiner les entitiés en les replaçant sur le labyrinthe
			for (Entity E: this._world.listEntity)
				this._laby2[(int)E.getPosition().x][(int)E.getPosition().y] = E;
		}
        this.drawMaze(batch);
		this.drawPacman(batch, score);
	}*/

	void set(Vector2 pos, GameElement tile) {
		this._laby2[(int)pos.x][(int)pos.y] = tile;
	}

	/*private void drawPacman(SpriteBatch batch, BitmapFont score) {
        batch.begin();
		batch.draw(this._world.getPacman().getTexture(), this._world.getPacman().getPosition().y * 16, (30 - this._world.getPacman().getPosition().x) * 16);
		String scoreStr = "SCORE : " + Util.SCORE;
		layout.setText(score.newFontCache().getFont(), scoreStr);
		score.draw(batch, scoreStr, Gdx.graphics.getWidth() - layout.width, Gdx.graphics.getHeight() - layout.height);
		batch.end();
	}

	private void drawMaze(SpriteBatch batch) {
		for (Texture text : listText) {
			if ((text.getWidth() != _textWidth) || (text.getHeight() != _textHeight))
				drawResize(batch, text);
			else draw(batch, text);
		}
	}

	private void draw(SpriteBatch batch, Texture text) {
		batch.begin();
		for (int i = 0; i < 28; i++)
			for (int j = 0; j < 31; j++)
					if (this._laby2[j][i].getTexture() == text)
					batch.draw(
							text,
							i * 16,
							(30 - j) * 16
					);
		batch.end();
	}

	private void drawResize(SpriteBatch batch, Texture text) {
		batch.begin();
		for (int i = 0; i < 28; i++)
			for (int j = 0; j < 31; j++)
				if (this._laby2[j][i].getTexture() == text)
				batch.draw(
						text,
						i * 16,
						(30 - j) * 16,
						_textWidth,
						_textHeight,
						0,
						0,
						text.getWidth(),
						text.getHeight(),
						false,
						false
				);
		batch.end();
	}*/

	@Override
	public Iterator<GameElement> iterator() {
		return new MazeIterator(this);
	}
}

class MazeIterator implements Iterator<GameElement> {

	private Maze _maze;
	private int _i, _j;

	MazeIterator(Maze maze) {
		this._maze = maze;
		_i = _j = 0;
		this.init();
	}

	private void init() {
		while (_maze.get(_i, _j) == null && this.hasNext()) {
			_i = (_i + 1) % this._maze.getWidth();
			if (_i == 0) _j++;
		}
	}

	@Override
	public boolean hasNext() {
		return (_j < this._maze.getHeight()) && (_i < this._maze.getWidth());
	}

	@Override
	public GameElement next() {
		if (!this.hasNext()) throw new NoSuchElementException("No more game elements");
		GameElement gameElement = this._maze.get(_i, _j);
		do {
			_j = (_j + 1) % this._maze.getWidth();
			if (_j == 0)
				_i++;
		} while (this._maze.get(_i, _j) != null
				&& this.hasNext());
		return gameElement;
	}

	@Override
	public void remove() {}
}