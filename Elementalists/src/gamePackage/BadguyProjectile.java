package gamePackage;

import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Toolkit;

public class BadguyProjectile{
	public String facing;
	

	private double xCoord = 0;
	private double yCoord = 0;
	private int width = 10;
	private int height = 10;
	private Image img;
	private double marioX;
	private double marioY;
	private final double angle;
	private final double xSpeed;
	private final double ySpeed;
	int speed = 3;

	public BadguyProjectile(int x, int y, int w, int h, String imgpath, double marioX, double marioY)
	{
		setxCoord(x - 30);
		setyCoord(y - 30);
		setWidth(w);
		setHeight(h);
		setImg(imgpath);
		this.marioX = marioX;
		this.marioY = marioY;
		angle = Math.atan2(marioY - y, marioX - x);
		   ySpeed = (speed) * Math.sin(angle);
	       xSpeed = (speed) * Math.cos(angle);
		
	}
	public void moveIt(){
		double x = getxCoord();
		double y = getyCoord(); 
       x = x + xSpeed;
       setxCoord(x);
       y = y + ySpeed;
       setyCoord(y);
	}    

	public double getxCoord() {
		return this.xCoord;
	}

	public void setxCoord(double xCoord) {
		this.xCoord = xCoord;
	}

	public double getyCoord() {
		return this.yCoord;
	}

	public void setyCoord(double yCoord) {
		this.yCoord = yCoord;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	public void setImg(String imgpath) {
		this.img = Toolkit.getDefaultToolkit().getImage(imgpath);
	}


}
