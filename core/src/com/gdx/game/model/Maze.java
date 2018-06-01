package com.gdx.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.gdx.game.experts.MazeCOR;
import com.gdx.game.utilities.Util;
import com.gdx.game.view.TextureFactory;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Maze implements Iterable<GameElement> {
	private World _world;
	private /*final*/ int _width;
	private /*final*/ int _height;

	private Texture listText[] = {
			TextureFactory.getInstance().getTexture(Block.class),
			TextureFactory.getInstance().getTexture(Gom.class),
			TextureFactory.getInstance().getTexture(SuperGom.class),
			TextureFactory.getInstance().getTexture(Dark.class),
			TextureFactory.getInstance().getTexture("pacman"),
			TextureFactory.getInstance().getTexture("ghost1"),
			TextureFactory.getInstance().getTexture("ghost2"),
			TextureFactory.getInstance().getTexture("ghost3"),
			TextureFactory.getInstance().getTexture("ghost4")
	};

	private final int _textWidth = 16;
	private final int _textHeight = 16;

	/* 0 : mur, 1 : vide, 2 : intersection, 3 : barriere fantomes */
	private int[][] _laby1 = new int[][] {
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 0},
			{0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0},
			{0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0},
			{0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0},
			{0, 2, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 2, 0},
			{0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0},
			{0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0},
			{0, 1, 1, 1, 1, 1, 2, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 2, 1, 1, 1, 1, 1, 0},
			{0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 3, 3, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0},
			{1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 0, 1, 1, 1, 1, 1, 1, 0, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1},
			{0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 1, 0, 0, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 0, 0, 1, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0},
			{0, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 0, 0, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 0},
			{0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0},
			{0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0},
			{0, 1, 1, 1, 0, 0, 2, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1, 1, 2, 0, 0, 1, 1, 1, 0},
			{0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0},
			{0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0},
			{0, 1, 1, 2, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 2, 1, 1, 0},
			{0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
			{0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
			{0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
	};
	private GameElement[][] _laby2;

	public Maze() {
		this._world = new World();
		this.init();
	}

	Maze(World w) {
		_world = w;
		this.init();
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
		this._laby2[(int)this._world.getPacman().getPosition().x][(int)this._world.getPacman().getPosition().y] = this._world.getPacman();
		this._laby2[(int)this._world.get_red().getPosition().x][(int)this._world.get_red().getPosition().y] = this._world.get_red();
		this._laby2[(int)this._world.get_pink().getPosition().x][(int)this._world.get_pink().getPosition().y] = this._world.get_pink();
		this._laby2[(int)this._world.get_blue().getPosition().x][(int)this._world.get_blue().getPosition().y] = this._world.get_blue();
		this._laby2[(int)this._world.get_yellow().getPosition().x][(int)this._world.get_yellow().getPosition().y] = this._world.get_yellow();
	}

	public GameElement get(int x, int y) {
		return this._laby2[x][y];
	}

	public GameElement get(Vector2 pos) {
		return this._laby2[(int)pos.x][(int)pos.y];
	}

	public int getHeight() { return _height; }

	public int getWidth()  { return _width; }

	private boolean checkCoords(Vector2 pos) {
		boolean checkLeftBound = (pos.x >= 0) && (pos.y >= 0);
		boolean checkRightBound = (pos.x < this._height) && (pos.y < this._width);
		return checkLeftBound && checkRightBound;
	}

	protected boolean validTile(Vector2 pos) {
		Dark dark = new Dark(new Vector2(0, 0), this._world);
		Gom gom = new Gom(new Vector2(0, 0), this._world);
		SuperGom superGom = new SuperGom(new Vector2(0, 0), this._world);

		GameElement ge;

		if (checkCoords(pos)) {
			ge = this.get(pos);
			return (ge.equals(dark)) || (ge.equals(gom)) || (ge.equals(superGom));
		}
		else return false;
	}

	public void updateMaze(SpriteBatch batch) {
		if (Util.currentDir != Util.NOWHERE) {

			Vector2 oldPos[] = {
					new Vector2(this._world.getPacman().getPosition()),
					new Vector2(this._world.get_red().getPosition()),
					new Vector2(this._world.get_pink().getPosition()),
					new Vector2(this._world.get_blue().getPosition()),
					new Vector2(this._world.get_yellow().getPosition())
			};
			GameElement previousTiles[] = new GameElement[5];
			for (int i = 1; i < oldPos.length; i++) {
				previousTiles[i] = this.get(oldPos[i]);
			}

			this._world.moveEntities();

			Vector2 newPos[] = {
					new Vector2(this._world.getPacman().getPosition()),
					new Vector2(this._world.get_red().getPosition()),
					new Vector2(this._world.get_pink().getPosition()),
					new Vector2(this._world.get_blue().getPosition()),
					new Vector2(this._world.get_yellow().getPosition())
			};

			this._laby2[(int) oldPos[Util.PACMAN].x] [(int) oldPos[Util.PACMAN].y] =new Dark(new Vector2((int) oldPos[Util.PACMAN].x, oldPos[Util.PACMAN].y), this._world);

			for (int i = 1; i < oldPos.length; i++) {
				if (previousTiles[i].equals(this._laby2[(int) oldPos[i].x] [(int) oldPos[i].y]))
					previousTiles[i] = new Dark(new Vector2((int) oldPos[i].x, oldPos[i].y), this._world);
				this._laby2[(int) oldPos[i].x] [(int) oldPos[i].y] = previousTiles[i];
			}

			this._laby2[(int) newPos[Util.PACMAN].x] [(int) newPos[Util.PACMAN].y] = this._world.getPacman();
			this._laby2[(int) newPos[Util.RED].x] [(int) newPos[Util.RED].y] = this._world.get_red();
			this._laby2[(int) newPos[Util.PINK].x] [(int) newPos[Util.PINK].y] = this._world.get_pink();
			this._laby2[(int) newPos[Util.BLUE].x] [(int) newPos[Util.BLUE].y] = this._world.get_blue();
			this._laby2[(int) newPos[Util.YELLOW].x] [(int) newPos[Util.YELLOW].y] = this._world.get_yellow();
		}
		this.drawMaze(batch);
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
	}

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