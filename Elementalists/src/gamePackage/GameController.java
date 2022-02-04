package gamePackage;

import java.util.ArrayList;
import java.util.LinkedList;

public class GameController extends DrawImage {
	
	Block leftWall = new Block(240, 260, 290, 230, "");
	Block rightWall = new Block(860, 260, 290, 230, "");
	Block topTreeWall = new Block(260, 120, 30, 30, "");
	Block rightTreeWall = new Block(1190, 470, 25, 25, "");
	Block leftTreeWall = new Block(330, 600, 30, 30, "");
	Block bigTreeWall = new Block(650, 320, 150, 150, "");
	Block rightStoneWall = new Block(860, 100, 110, 20, "");
	Block leftStoneWall = new Block(210, 410, 110, 20, "");
	ArrayList obstacles = new ArrayList();
	public GameController(int x, int y, int w, int h, String imgpath) {
		super(x, y, w, h, imgpath);
		// TODO Auto-generated constructor stub
		
		obstacles.add(rightWall);
		obstacles.add(leftWall);
	}
	

}
