package gamePackage;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.Random;

import javax.swing.Timer;

public class Badguy extends GameController {

	int setspeed[] = { 5, 4,3, 2 };
	int speed;
	Random rand = new Random();
	int hp;
	int starthp;
	int level;
	int dmg;
	int size[] = { 25, 50, 75, 100 };
	int xlocation[] = { 1540, rand.nextInt(1300) - 400, -200, rand.nextInt(1300) - 400 };
	int ylocation[] = { rand.nextInt(1700) - 400, -200, rand.nextInt(1300) - 400, 1100 };
	String sprite[] = { "Sprites/galoomba.png", "Sprites/goomba.png", "Sprites/enemy.png", "Sprites/Bowser.png",};
	int arrhp[] = { 20, 40, 60, 80 };
	int setdmg[] = { 1, 2, 3, 4 };
	boolean boss;
	boolean intersectleft = false;
	boolean intersectright = false;
	boolean intersecttop = false;
	boolean intersectbottom = false;

	public Badguy(int level, boolean boss) {

		super(500, 500, 0, 0, "");
		this.boss = boss;
		int type = rand.nextInt(4);
		int xytype = rand.nextInt(4);
		this.level = level;
		if (!boss) {
			setxCoord(xlocation[xytype]);
			setyCoord(ylocation[xytype]);
			setWidth(size[type]);
			setHeight(size[type]);
			setImg(sprite[type]);
			hp = arrhp[type] + (level * 5);
			starthp = hp + (level * 5);
			speed = (setspeed[type]);
			dmg = setdmg[type];
		} else {
			 setxCoord(xlocation[xytype]);
			 setyCoord(ylocation[xytype]);
			setWidth(200);
			setHeight(200);
			setImg("Sprites/boss.png");
			hp = 1000 + (level * 50);
			starthp = hp + (level * 50);
			dmg = 30;
			speed = 2;
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
	public void collide() {
		int x = getxCoord();
		int y = getyCoord();
		Rectangle r = new Rectangle(this.getxCoord(), this.getyCoord(), this.getWidth(), this.getHeight());
		for (int i = 0; i < obstacles.size(); i++) {
			Block wall = (Block) obstacles.get(i);
			if (r.intersects(wall.rightHitbox)) {
				intersectright = true;
				break;
			}
			else if (r.intersects(wall.leftHitbox)) {
				intersectleft = true;
				break;
			}
			else if (r.intersects(wall.topHitbox)) {
				intersecttop = true;
				break;
			}
			else if (r.intersects(wall.bottomHitbox)) {
				intersectbottom = true;
				break;
			}
			else {
				intersectleft = false;
				intersectright = false;
				intersecttop = false;
				intersectbottom = false;
			}
		}
	}

	public void moveIt(int marioX, int marioY) {

		int x = getxCoord();
		int y = getyCoord();

		if (x > marioX + rand.nextInt(100) - 50) {
			x -= speed;
			if (intersectright) {
				x += speed;
				
				if (y > 450 && marioY + 50 > 450) {
					y += (speed);
				} else if (y < 450 && marioY + 50 < 450) {
					y -=(speed);
				}
			}
			setxCoord(x);
			setyCoord(y);
		}
		if (x < marioX - rand.nextInt(100) - 50) {
			x += speed;
			if (intersectleft) {
				if (y > 450 && marioY + 50 > 450) {
					y += (speed);
				} else if (y < 450 && marioY + 50 < 450) {
					y -= (speed);
				}
				x -= speed;
			}
			setxCoord(x);
			setyCoord(y);
		}
		if (y > marioY + rand.nextInt(100) - 50) {

			// int speed = speed * (Math.round(rand.nextInt(5)/4));
			y -= speed;
			if (intersectbottom) {
				y += speed;
			}
			setxCoord(x);
			setyCoord(y);
		}
		if (y < marioY - rand.nextInt(100) - 5) {
			// int speed = speed * (Math.round(rand.nextInt(5)/4));
			y += speed;
			if (intersecttop) {
				y -= speed;
			}
			setxCoord(x);
			setyCoord(y);
		}

	}

}
