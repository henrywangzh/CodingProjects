package gamePackage;

import java.awt.Rectangle;

public class Block extends DrawImage{

	public Block(int x, int y, int w, int h, String imgpath) {
		super(x, y, w, h, imgpath);
		
	}
	Rectangle entireHitbox = new Rectangle(this.getxCoord(),this.getyCoord(),this.getWidth(),this.getHeight());
	Rectangle topHitbox = new Rectangle(this.getxCoord(), this.getyCoord()-5, this.getWidth(), 5);
	Rectangle bottomHitbox = new Rectangle(this.getxCoord(), this.getyCoord()+this.getHeight(), this.getWidth(), 1);
	Rectangle leftHitbox = new Rectangle(this.getxCoord()-5, this.getyCoord(), 1, this.getHeight());
	Rectangle rightHitbox = new Rectangle(this.getxCoord()+this.getWidth(), this.getyCoord(), 1, this.getHeight());
}
