package gamePackage;

import java.awt.Image;
import java.awt.Toolkit;

public class Projectile{
	public String facing;
	

	private double xCoord = 0;
	private double yCoord = 0;
	private int width = 10;
	private int height = 10;
	private Image img;
	private double mouseX;
	private double mouseY;
	private final double angle;
	private final double xSpeed;
	private final double ySpeed;
	int speed;
	int setspeed[] = {10, 16, 8, 12, 20};
	int time = 0;
	int imagenum = 0;
	int images[] = {1, 4, 4, 4};
	String weapons[] = {"chemical", "spike", "water", "fire", "tornado"};
	int weapon;
	int setdmg[] = {10, 15, 25, 35, 45};
	int dmg;
	public Projectile(int x, int y, int w, int h, int weapon, double mouseX, double mouseY, int bonusDmg)
	{
		setxCoord(x - 30);
		setyCoord(y - 30);
		setWidth(w);
		setHeight(h);
		setImg("Sprites/" + weapons[weapon] + "1.png");
		this.mouseX = mouseX;
		this.mouseY = mouseY;
		this.weapon = weapon;
		angle = Math.atan2(mouseY - y, mouseX - x);
		dmg = setdmg[weapon] + bonusDmg;
		speed = setspeed[weapon];
		   ySpeed = (speed) * Math.sin(angle);
	       xSpeed = (speed) * Math.cos(angle);
		
	}
	
	public void moveIt(){
		time++;
		if(time % 2 == 0) {
			imagenum++;
		}
		setImg("Sprites/"+ weapons[weapon] + Integer.toString(imagenum % images[weapon] + 1)+".png");
		double x = getxCoord();
		double y = getyCoord(); 
       x += xSpeed;
       setxCoord(x);
       y += ySpeed;
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
