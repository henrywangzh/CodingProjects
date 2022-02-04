package gamePackage;

import java.awt.Frame;

import javax.swing.JFrame;


public class MyScreen extends JFrame

{
	public MyScreen() {
		this.setSize(1440, 900);
		this.setExtendedState(Frame.MAXIMIZED_BOTH);

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.requestFocus();
		this.setTitle("Elementalists");
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			MyScreen screen = new MyScreen();
		    MyCanvas canvas = new MyCanvas();
			screen.getContentPane().add(canvas);
	}
	
}
