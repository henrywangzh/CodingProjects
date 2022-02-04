package gamePackage;

import java.awt.Rectangle;
import java.util.LinkedList;

public class Goodguy extends GameController {
	boolean jumping = true;
	int time = 0;
	int hp;
	int totalhp;
	int imagenum = 0;
	int shieldHp;
	int shieldTotalHp;
	int level;
	int speed = 10;

	/**
	 * Goodguy overloaded constructor
	 * 
	 * @param x initial x location
	 * @param y initial y location
	 * @param w initial width
	 * @param h initial height
	 */
	public Goodguy(int x, int y, int w, int h, String imgpath) {
		super(x, y, w, h, imgpath);
		setImg("Sprites/Wizard1.png");
		hp = 20;
		totalhp = 20;
		shieldHp = 10;
		shieldTotalHp = 10;
	}

	public void moveIt(boolean up, boolean down, boolean left, boolean right) {
		this.level = level;
		if (up) {
			this.moveIt("up", this.getWidth(), this.getHeight());
		}
		if (down) {
			this.moveIt("down", this.getWidth(), this.getHeight());
		}
		if (left) {
			this.moveIt("left", this.getWidth(), this.getHeight());
		}
		if (right) {
			this.moveIt("right", this.getWidth(), this.getHeight());
		}

	}

	public void checkLevel(int level) {
		if (level % 3 == 2) {
			obstacles.clear();
			obstacles.add(leftWall);
			obstacles.add(rightWall);
		}
		if (level % 3 == 0 && level >= 2) {
			obstacles.clear();
			obstacles.add(topTreeWall);
			obstacles.add(leftTreeWall);
			obstacles.add(rightTreeWall);
			obstacles.add(bigTreeWall);
			obstacles.add(rightStoneWall);
			obstacles.add(leftStoneWall);
		}
		if (level % 3 == 1 && level >= 2) {
			obstacles.clear();
		}
	}

	public void moveIt(String direction, int w, int h) {
		time++;
		if (time % 3 == 0) {
			imagenum++;
		}
		Rectangle marioRect = new Rectangle(this.getxCoord(), this.getyCoord(), this.getWidth(), this.getHeight());
		
		int x = getxCoord();
		int y = getyCoord();
		setImg("Sprites/Wizard" + Integer.toString(imagenum % 3 + 1) + ".png");

		for (int i = 0; i < obstacles.size(); i++) {
			Block wall = (Block) obstacles.get(i);
			if (marioRect.intersects(wall.leftHitbox)) {
				x -= speed;
			}
			if (marioRect.intersects(wall.rightHitbox)) {
				x += speed;
			}
			if (marioRect.intersects(wall.bottomHitbox)) {
				y += speed;
			}
			if (marioRect.intersects(wall.topHitbox)) {
				y -= speed;
			}
		}
		if (direction == "right") {

			x += speed;
			if (x > 1360) {
				x -= speed;
			}
			setxCoord(x);

		}

		if (direction == "left") {

			if (x < 0) {
				x += speed;
			}
			x -= speed;
			setxCoord(x);

		}
		if (direction == "up") {

			if (y < 0) {
				y += speed;
			}
			y -= speed;
			setyCoord(y);
		}
		if (direction == "down") {

			if (y > 675) {
				y -= speed;
			}
			y += speed;
			setyCoord(y);
		}
	}

}
