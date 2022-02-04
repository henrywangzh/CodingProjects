package gamePackage;

import java.awt.Rectangle;
import java.util.Random;

public class PowerUps extends GameController{

	public PowerUps(int x, int y, int w, int h, String imgpath) {
		super(x, y, w, h, imgpath);
		Random rand = new Random();
		setxCoord(rand.nextInt(1400));
		setyCoord(rand.nextInt(900));
		
	}

}
