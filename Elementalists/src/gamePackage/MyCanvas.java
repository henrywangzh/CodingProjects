package gamePackage;

import java.awt.Canvas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.LinkedList;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Image;
import java.awt.MouseInfo;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import java.util.TimerTask;
import java.awt.event.*;
import java.awt.geom.AffineTransform;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.Timer;

public class MyCanvas extends Canvas implements KeyListener {

	// global variables
	Goodguy mario = new Goodguy(50, 600, 100, 100, "Sprites/Wizard1.png");
	public Rectangle playButton = new Rectangle(525, 325, 330, 150);
	public Rectangle helpButton = new Rectangle(200, 375, 150, 75);
	public Rectangle quitButton = new Rectangle(1000, 375, 150, 75);
	public Rectangle returnButton = new Rectangle(400, 475, 575, 100);
	public Rectangle shopButton = new Rectangle(250, 190, 275, 120);
	public Rectangle nextlevelButton = new Rectangle(850, 200, 325, 100);
	public Rectangle exitshopButton = new Rectangle(1005, 100, 240, 100);
	public Rectangle[] shopButtons = { new Rectangle(150, 415, 360, 80), new Rectangle(620, 300, 280, 75),
			new Rectangle(1000, 300, 280, 75), new Rectangle(620, 430, 280, 75), new Rectangle(1000, 430, 280, 75)};
	public Rectangle[] starButtons = {new Rectangle(190, 610, 280, 75),new Rectangle(580, 610, 280, 75),new Rectangle(980, 610, 280, 75)};
	public int[] costs = { 1500, 1000, 2500, 4500, 6500 };
	public boolean[] itemBought = { false, false, false, false, false};
	public String[] weapons = {"chemical", "spike", "water", "fire", "tornado"};
	DrawImage TitleScreen = new DrawImage(125, 50, 1200, 680, "Sprites/ElementalistsBackground.jpg");
	DrawImage playSpace = new DrawImage(0, 0, 1440, 800, "Sprites/Chamber.jpg");
	DrawImage GameOver = new DrawImage(0, 0, 1440, 900, "Sprites/GameOver.png");
	DrawImage coin = new DrawImage(0, 0, 100, 100, "Sprites/goldcoin.png");
	DrawImage fire = new DrawImage(1150, -100, 400, 400, "Sprites/fire.gif");
	DrawImage Icespike = new DrawImage(0, 600, 200, 200, "Sprites/Icespike.gif");
	DrawImage lightning = new DrawImage(1150, 0, 400, 855, "Sprites/lightning.gif");
	DrawImage fireCircle = new DrawImage(mario.getxCoord(), mario.getyCoord(), 200, 200, "Sprites/firecircle.gif");
	DrawImage fireCircleshop = new DrawImage(200, 400, 200, 200, "Sprites/firecircle.gif");
	DrawImage fireball = new DrawImage(600, 400, 200, 50, "Sprites/fireball.png");
	DrawImage shieldIcon = new DrawImage(260, 450, 75, 75, "Sprites/woodshield.png");
	DrawImage shopIcon = new DrawImage(320, 120, 100, 100, "Sprites/shop.png");
	DrawImage shopBackground = new DrawImage(0, 0, 1440, 780, "Sprites/shopbackground.jpg");
	DrawImage nextLevelArrow = new DrawImage(nextlevelButton.x, nextlevelButton.y - 50, 300, 75, "Sprites/arrow.png");
	LinkedList badguys = new LinkedList();
	LinkedList attacks = new LinkedList();
	LinkedList powerups = new LinkedList();
	LinkedList bossattacks = new LinkedList();
	Image image = Toolkit.getDefaultToolkit().getImage("Wizard.gif");
	Random rand = new Random();
	boolean wPressed = false;
	boolean sPressed = false;
	boolean dPressed = false;
	boolean aPressed = false;
	boolean stageClear = false;
	int currentWeapon = 0;
	int numbadguys = 0;
	boolean stopSpawning = false;
	boolean stopRespawn = false;
	int level = 0;
	int starting4 = 0;
	int gold = 6000;
	int stars = 5;
	int lastHit = -40;
	int time;
	int bonusDmg = 0;
	boolean firsttime = false;
	String chemicalName[] = { "Hydrogen Peroxide", "Zinc Phosphide", "Sodium Cyanide", "Strychinne", "carbonate",
			"hydrogen carbonate", "nitrate", "phosphate", "sulfate", "chlorate", "manganate", "chromate", "ammonium",
			"hydroxide", "ethanoate", "oxalate", "thiosulfate", "cyanide", "thicyanate", "dichromate",
			"Hydrosulfuric acid", "Barium oxide", "Hydrochloric acid", "Sulfur trioxide" };
	String chemicalFormula[] = { "H2O2", "Zn3P2", "NaCN", "C21H22N2O2", "(CO3)2-", "(HCO3)-", "(NO3)-", "(PO4)3-",
			"(SO4)2-", "(ClO3)-", "(MnO3)-", "(CrO4)2-", "(NH4)+", "(OH)-", "(CH3COO)-", "(C2O4)2-", "(S2O3)2-",
			"(CN)-", "(SCN)-", "(Cr2O7)2-", "H2S", "BaO", "HCl", "SO3" };
	String response;
	String[] music = {"Audio/Dungeon.wav", "Audio/GerudoValley.wav", "Audio/athleticTheme.wav"};
	int chosenChemical;

	private Clip clip;
	private Clip clip2;
	private Clip clip3;

	private enum STATE {
		MENU, GAME, SELECT, LOSE, SHOP, HELP
	};

	private STATE state = STATE.SHOP;

	public MyCanvas() {
		this.setSize(1440, 900);
		this.addKeyListener(this);
		this.setBackground(Color.black);
		this.requestFocus();
		playIt("Audio/Challengers.wav");
		firsttime = true;
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				double mouseX = MouseInfo.getPointerInfo().getLocation().x;
				double mouseY = MouseInfo.getPointerInfo().getLocation().y - 50;
				if (state == STATE.SHOP) {
					if (exitshopButton.contains(mouseX, mouseY)) {
						state = STATE.GAME;
						clip3.stop();
						clip2.start();
					}
					for (int w = 0; w < shopButtons.length; w++) {
						if (shopButtons[w].contains(mouseX, mouseY) && gold >= costs[w] && itemBought[w] == false) {
							gold -= costs[w];
							itemBought[w] = true;
							playIt("Audio/money.wav");
						}
					}
					
						if(starButtons[0].contains(mouseX, mouseY) && stars >= 1) {
							stars -= 1;
							bonusDmg++;
							playIt("Audio/money.wav");
							
						}
						if(starButtons[1].contains(mouseX, mouseY) && stars >= 1) {
							stars -= 1;
							mario.speed++;
							playIt("Audio/money.wav");
						}
						if(starButtons[2].contains(mouseX, mouseY) && stars >= 1) {
							stars -= 1;
							mario.hp++;
							mario.totalhp++;
							playIt("Audio/money.wav");
						}
					
				}
				if (state == STATE.LOSE || state == STATE.HELP) {
					if (returnButton.contains(mouseX, mouseY)) {
						state = STATE.MENU;
					}
				} else if (state == STATE.MENU) {
					if (playButton.contains(mouseX, mouseY)) {
						
						state = STATE.GAME;
						mario.setxCoord(600);
						mario.setyCoord(300);
						level = 1;
						clip.stop();
						clip.flush();
						playIt2(music[0]);
						stopRespawn = true;
						numbadguys = 0;
						if (firsttime) {
							powerups();
							spawnEnemies();
						}
						firsttime = false;
					} else if (quitButton.contains(mouseX, mouseY)) {
						System.exit(0);
					} else if (helpButton.contains(mouseX, mouseY)) {
						state = STATE.HELP;
						
					}

				} else if (state == STATE.GAME && stageClear) {
					if (shopButton.contains(mouseX, mouseY)) {
						clip2.stop();
						state = STATE.SHOP;
						playIt3("Audio/shop.wav");
					} else if (nextlevelButton.contains(mouseX, mouseY)) {
						clip2.stop();
						clip2.flush();
						level++;
						numbadguys = 0;
						stopRespawn = true;
						stopSpawning = false;
						stageClear = false;
						mario.checkLevel(level);
						if(level % 3 == 2 ) {
							playSpace.setImg("Sprites/Chamber.jpg");
							playIt2(music[0]);
							mario.setxCoord(600);
							mario.setyCoord(300);
						}
						else if(level % 3 == 0 && level >= 2) {
							playSpace.setImg("Sprites/grassbackground.jpg");
							mario.setxCoord(300);
							mario.setyCoord(400);
							playIt2(music[1]);
						} else if (level % 3 == 1 && level >= 2) {
							playSpace.setImg("Sprites/beach.jpg");
							mario.setxCoord(200);
							mario.setyCoord(300);
							playIt2(music[2]);
						}
						
					}

				} else if (state == STATE.GAME) {
					Projectile chemical = new Projectile(mario.getxCoord() + 80, mario.getyCoord() + 80, 40, 40,
							currentWeapon, mouseX, mouseY, bonusDmg);
					attacks.add(chemical);
					playIt("Audio/shoot2.wav");
				}
			}
		});

	}

	public void spawnEnemies() {

		final Timer spawn = new Timer(4000 - (100 * level), new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (numbadguys < 4 + (level * 4) && level >= 2) {
					Badguy bg = new Badguy(level, false);
					badguys.add(bg);
					bg.checkLevel(level);
					bg.hp = bg.hp + (level * 5);

					numbadguys++;
					if (numbadguys >= 4 + (level * 4)) {
						stopSpawning = true;
					}
				}
			}
		});
		spawn.start();
	}

	public void playIt(String filepath) {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filepath));
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void playIt2(String filepath) {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filepath));
			clip2 = AudioSystem.getClip();
			clip2.open(audioInputStream);
			clip2.start();
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void playIt3(String filepath) {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filepath));
			clip3 = AudioSystem.getClip();
			clip3.open(audioInputStream);
			clip3.start();
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void paint(Graphics g) {

		time++;
		enemies();
		Font font = new Font("Halo", Font.BOLD, 85);
		Font font2 = new Font("Halo", Font.PLAIN, 50);
		Font font3 = new Font("Halo", Font.PLAIN, 40);
		g.setColor(Color.white);
		Graphics2D g2d = (Graphics2D) g;
		if (state == STATE.MENU) {
			g.drawImage(TitleScreen.getImg(), TitleScreen.getxCoord(), TitleScreen.getyCoord(), TitleScreen.getWidth(),
					TitleScreen.getHeight(), this);
			g.drawImage(fire.getImg(), fire.getxCoord(), fire.getyCoord(), fire.getWidth(), fire.getHeight(), this);
			g.drawImage(Icespike.getImg(), Icespike.getxCoord(), Icespike.getyCoord(), Icespike.getWidth(),
					Icespike.getHeight(), this);
			g.drawImage(lightning.getImg(), lightning.getxCoord(), lightning.getyCoord(), lightning.getWidth(),
					lightning.getHeight(), this);
			g.setFont(font);
			g.drawString("Elementalists", (this.getWidth() / 2) - 340, 250);
			g.drawString("Play", playButton.x + 40, playButton.y + 90);
			g.setFont(font2);
			g.drawString("Help", helpButton.x + 15, helpButton.y + 45);
			g.drawString("Quit", quitButton.x + 15, quitButton.y + 45);
			g2d.draw(playButton);
			g2d.draw(helpButton);
			g2d.draw(quitButton);
		} else if (state == STATE.LOSE) {
			g.drawImage(TitleScreen.getImg(), TitleScreen.getxCoord(), TitleScreen.getyCoord(), TitleScreen.getWidth(),
					TitleScreen.getHeight(), this);
			g.setFont(font);
			g.drawString("Game Over", 400, 400);
			g.setFont(font2);
			g.drawString("Click here to Retry", returnButton.x + 20, returnButton.y + 50);
			g2d.draw(returnButton);
		} else if (state == STATE.SHOP) {
			g.drawImage(shopBackground.getImg(), shopBackground.getxCoord(), shopBackground.getyCoord(),
					shopBackground.getWidth(), shopBackground.getHeight(), this);
			badguys.clear();
			powerups.clear();
			attacks.clear();

		} else if (state == STATE.HELP) {
			g.drawImage(TitleScreen.getImg(), TitleScreen.getxCoord(), TitleScreen.getyCoord(), TitleScreen.getWidth(),
					TitleScreen.getHeight(), this);
			g.setFont(font);
			g.drawString("Help", 550, 200);
			g.setFont(font3);
			g.drawString("WASD to move, mouse to shoot", 350, 300);
			g.drawString("There are infinite levels. After each level is a shop!", 200, 350);
			g.drawString("Kill enemies and answer chemistry questions for gold", 200, 400);
			g.drawString("You have 20 hp, if you die, you lose half your gold", 200, 450);
			g.setFont(font2);
			g.drawString("Return to Menu", returnButton.x + 50, returnButton.y + 50);
			g2d.draw(returnButton);
		}
		if (time % 50 == 0 && mario.shieldHp < mario.shieldTotalHp && !stageClear && state == STATE.GAME
				&& time - lastHit > 300) {
			mario.shieldHp += 1;
		}
		if (level != 0 && (state == STATE.GAME) || state == STATE.SELECT) {
			
			g.drawImage(playSpace.getImg(), playSpace.getxCoord(),
					playSpace.getyCoord(), playSpace.getWidth(), playSpace.getHeight(),
					this);
			if (state == STATE.GAME) {
				AffineTransform transform = g2d.getTransform();
				double mouseX = MouseInfo.getPointerInfo().getLocation().x;
				double mouseY = MouseInfo.getPointerInfo().getLocation().y;
				int marioCenterx = mario.getxCoord() + (mario.getWidth() / 2);
				int marioCentery = mario.getyCoord() + (mario.getHeight() / 2);
				double angle = Math.atan2(marioCentery - mouseY, marioCenterx - mouseX) - Math.PI / 2;
				((Graphics2D) g).rotate(angle, marioCenterx, marioCentery);
				g.drawImage(mario.getImg(), mario.getxCoord(), mario.getyCoord(), mario.getWidth(), mario.getHeight(),
						this);
				g2d.setTransform(transform);
				g.setColor(Color.black);
				g.fillRect(495, 20, 200, 40);
				g.setColor(Color.red);
				g.fillRect(499, 24, (mario.hp * 200) / mario.totalhp - 8, 32);
				g.setColor(Color.white);
				g.setFont(font3);
				g.drawString(Integer.toString(mario.hp) + "/" + Integer.toString(mario.totalhp), 500, 50);
			} else {
				g.drawImage(mario.getImg(), mario.getxCoord(), mario.getyCoord(), mario.getWidth(), mario.getHeight(),
						this);
			}
			if (itemBought[0]) {
				fireCircle.setxCoord(mario.getxCoord() - 50);
				fireCircle.setyCoord(mario.getyCoord() - 50); // add firecircle if mario bought the weapon. absorbs 3
																// hits by enemies.
				g.setColor(Color.black);
				g.fillRect(495, 70, 200, 40);
				g.setColor(Color.gray);
				g.fillRect(499, 74, (mario.shieldHp * 200) / mario.shieldTotalHp - 8, 32);
				g.setColor(Color.white);
				g.setFont(font3);
				g.drawString(Integer.toString(mario.shieldHp) + "/" + Integer.toString(mario.shieldTotalHp), 500, 100);
				g.drawImage(fireCircle.getImg(), fireCircle.getxCoord(), fireCircle.getyCoord(), fireCircle.getWidth(),
						fireCircle.getHeight(), this);
			}
			g.setFont(font3);

			g.drawString("Level:  " + (level - 1), 1150, 50);
			g.drawString("Weapon: " + weapons[currentWeapon], 730, 50);
		} else if (state == STATE.SELECT) {
			g.setFont(font);
			g.drawString("Chemistry Question!", (this.getWidth() / 2) - 240, 200);
		}

		if (level == 1) {
			g.setFont(font);
			g.setColor(Color.darkGray);
			g.drawString("Practice Mode", 350, 100);
			g.setFont(font2);
			g.drawString("Press ENTER to begin level one", 320, 150);
		}

		if (stageClear && level != 0 && state == STATE.GAME) {
			
			g.setFont(font);
			g.drawString("Stage Clear!", (this.getWidth() / 2) - 140, 100);
			g.setFont(font2);
			g.drawString("Shop", shopButton.x + 50, shopButton.y + 50);
			g.drawImage(shopIcon.getImg(), shopIcon.getxCoord(), shopIcon.getyCoord(), shopIcon.getWidth(),
					shopIcon.getHeight(), this);
			g.drawString("Next Level", nextlevelButton.x + 50, nextlevelButton.y + 50);
			g.drawImage(nextLevelArrow.getImg(), nextLevelArrow.getxCoord(), nextLevelArrow.getyCoord(),
					nextLevelArrow.getWidth(), nextLevelArrow.getHeight(), this);
			powerups.clear();
		}

		for (int i = 0; i < badguys.size(); i++) {
			Badguy bg = (Badguy) badguys.get(i);
			bg.checkLevel(level);
			g.drawImage(bg.getImg(), bg.getxCoord(), bg.getyCoord(), bg.getWidth(), bg.getHeight(), this);
			g.setColor(Color.black);
			g.fillRect(bg.getxCoord(), bg.getyCoord(), bg.getWidth(), 5);
			g.setColor(Color.red);
			g.fillRect(bg.getxCoord(), bg.getyCoord() + 1, (bg.hp * bg.getWidth()) / bg.starthp, 3);
			if (state == STATE.GAME) {
				bg.moveIt(mario.getxCoord() + 80, mario.getyCoord() + 50);
				bg.collide();
			}

			// bg.shootIt(mario.getxCoord(), mario.getyCoord());
		}

		for (int j = 0; j < attacks.size(); j++) {
			Projectile k = (Projectile) attacks.get(j);
			g.drawImage(k.getImg(), (int) Math.round(k.getxCoord()), (int) Math.round(k.getyCoord()), k.getWidth(),
					k.getHeight(), this);
			if (state == STATE.GAME) {
				k.moveIt();
			}

			if (k.getxCoord() > this.getWidth() || k.getxCoord() < 0 || k.getyCoord() < 0
					|| k.getyCoord() > this.getHeight()) {
				attacks.remove(k);
			}
		}

		for (int h = 0; h < powerups.size(); h++) {
			PowerUps a = (PowerUps) powerups.get(h);
			
			g.drawImage(a.getImg(), a.getxCoord(), a.getyCoord(), a.getWidth(), a.getHeight(), this);
		}
		for (int i = 0; i < badguys.size(); i++) {
			Badguy bg = (Badguy) badguys.get(i);
			bg.checkLevel(level);
			for (int j = 0; j < attacks.size(); j++) {
				Projectile k = (Projectile) attacks.get(j);
				Rectangle br = new Rectangle(bg.getxCoord(), bg.getyCoord(), bg.getWidth(), bg.getHeight());
				Rectangle kr = new Rectangle((int) Math.round(k.getxCoord()), (int) Math.round(k.getyCoord()),
						k.getWidth(), k.getHeight());
				for(int d = 0; d < mario.obstacles.size(); d++) {
					Block wall = (Block) mario.obstacles.get(d);
					if (kr.intersects(wall.entireHitbox)) {
						attacks.remove(k);
					}
				}
				
				if (kr.intersects(br)) {
					bg.hp -= k.dmg;
					attacks.remove(j);
				}
				if (bg.hp <= 0) {
					badguys.remove(bg);
					gold += bg.starthp / 2;
				}

			}

		}
		g.setFont(font3);
		g.setColor(Color.white);
		g.drawString("Gold: " + gold, 100, 50);
		g.drawString("Stars:" + stars, 100, 100);
		g.drawImage(coin.getImg(), coin.getxCoord(), coin.getyCoord(), coin.getWidth(), coin.getHeight(), this);
		Rectangle marioRect = new Rectangle(mario.getxCoord() + 30, mario.getyCoord() + 30, mario.getWidth() - 40,
				mario.getHeight() - 40);
		for (int i = 0; i < badguys.size(); i++) {
			Badguy bg = (Badguy) badguys.get(i);
			bg.checkLevel(level);
			Rectangle r = new Rectangle(bg.getxCoord(), bg.getyCoord(), bg.getWidth(), bg.getHeight());
			if (marioRect.intersects(r)) {
				if (time - lastHit >= 40) {
					if (itemBought[0] && mario.shieldHp > 0) {
						mario.shieldHp -= bg.dmg;
						lastHit = time;
						playIt("Audio/shieldhit.wav");
						if(mario.shieldHp <= 0) {
							mario.shieldHp = 0;
						}
					} else {
						mario.hp -= bg.dmg;
						lastHit = time;
						playIt("Audio/hurt.wav");
					}
				}

			}

			for (int t = 0; t < bossattacks.size(); t++) {
				BadguyProjectile b = (BadguyProjectile) attacks.get(t);
				g.drawImage(b.getImg(), (int) Math.round(b.getxCoord()), (int) Math.round(b.getyCoord()), b.getWidth(),
						b.getHeight(), this);
				Rectangle br = new Rectangle((int) Math.round(b.getxCoord()), (int) Math.round(b.getyCoord()),
						b.getWidth(), b.getHeight());
				if (marioRect.intersects(br)) {
					lost();
				}

			}
		}
		if (mario.hp <= 0) {
			lost();
		}
		for (int h = 0; h < powerups.size(); h++) {
			PowerUps a = (PowerUps) powerups.get(h);
			Rectangle powerupRect = new Rectangle(a.getxCoord(), a.getyCoord(), a.getWidth(), a.getHeight());
			if (marioRect.intersects(powerupRect)) {
				powerups.remove(a);
				state = STATE.SELECT;
				chosenChemical = rand.nextInt(chemicalName.length);
				response = JOptionPane
						.showInputDialog("Please enter the chemical formula of: " + chemicalName[chosenChemical]);
				state = STATE.GAME;
				if (response.equals(chemicalFormula[chosenChemical])) {
					stars += 1;
					playIt("Audio/money.wav");
				} else {
					playIt("Audio/incorrect.wav");
				}
			}
		}
		if (badguys.size() == 0 && stopSpawning) {
			stageClear = true;
		} else if (badguys.size() > 0) {
			stageClear = false;
		}

	}

	public void lost() {
		state = STATE.LOSE;
		stopRespawn = false;
		stopSpawning = false;
		stageClear = false;
		level = 0;
		badguys.clear();
		powerups.clear();
		attacks.clear();
		gold = gold / 2;
		mario.hp = mario.totalhp;
		itemBought[0] = false;
		itemBought[1] = false;
		itemBought[2] = false;
		itemBought[3] = false;
		itemBought[4] = false;
		clip2.stop();
		playIt("Audio/Challengers.wav");
	}

	public void powerups() {
		System.out.println("HELLO");

		final Timer generatePowerup = new Timer(10000 + rand.nextInt(20000), new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (state == STATE.GAME && level >= 2 && !stageClear) {
					PowerUps powerup = new PowerUps(0, 0, 50, 50, "Sprites/powerup.png");
					Rectangle marioRect = new Rectangle(mario.getxCoord(), mario.getyCoord(), mario.getWidth(),
							mario.getHeight());
					Rectangle r = new Rectangle(powerup.getxCoord()-150, powerup.getyCoord()-150, powerup.getWidth()+150, powerup.getHeight()+150);
					for(int i = 0; i < mario.obstacles.size(); i++) {
						Block wall = (Block) mario.obstacles.get(i);
						if(r.intersects(wall.entireHitbox) || r.intersects(marioRect)) {
							System.out.println("Don't spawn here");
							continue;
							
						}
					}
					powerups.add(powerup);
				}
			}
		});
		generatePowerup.start();

	}

	public void enemies() {
		if(state == STATE.GAME && level % 5 == 0 && stopRespawn) {
			Badguy boss = new Badguy(level, true);
			badguys.add(boss);
			boss.checkLevel(level);
			boss.hp += (level * 50);
			stopRespawn = false;
			numbadguys++;
		}
		else if (state == STATE.GAME && level >= 2 && stopRespawn) {

			for (int i = 0; i < 4; i++) {

				Badguy bg = new Badguy(level, false);
				badguys.add(bg);
				bg.checkLevel(level);
				bg.hp += (level * 5);
				numbadguys++;
			}
			stopRespawn = false;
		}

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			aPressed = true;
			break;
		case KeyEvent.VK_W:
			wPressed = true;
			break;
		case KeyEvent.VK_S:
			sPressed = true;
			break;
		case KeyEvent.VK_D:
			dPressed = true;
			break;
		case KeyEvent.VK_ENTER:
			if (level == 1) {
				level++;
				}
			break;
		case KeyEvent.VK_1:
			currentWeapon = 0;
			break;
		case KeyEvent.VK_2:
			if (itemBought[1]) {
				currentWeapon = 1;
			}
			break;
		case KeyEvent.VK_3:
			if (itemBought[2]) {
				currentWeapon = 2;
			}
			break;
		case KeyEvent.VK_4:
			if (itemBought[3]) {
				currentWeapon = 3;
			}
			break;
		case KeyEvent.VK_5:
			if (itemBought[4]) {
				currentWeapon = 4;
			}
			break;
		}
		if (state == STATE.GAME) {
			mario.moveIt(wPressed, sPressed, aPressed, dPressed);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		mario.setImg("Sprites/Wizard1.png");
		aPressed = false;
		wPressed = false;
		sPressed = false;
		dPressed = false;
	}
}
