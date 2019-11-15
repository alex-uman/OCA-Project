package testing;

import javax.swing.*;

import testing.Tester.DrawPanel;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Dog {

	static KeyEvent key;

	static JFrame field;
	static DrawPanel drawPanel;

	static private int oneX = 0;
	static private int oneY = 0;

	boolean up = false;
	boolean down = true;
	boolean left = false;
	boolean right = true;

	static final String IMG_PATH = "C:\\Locate\\Workspace\\Images\\";

	static final BufferedImage BG = setImage("grass.png");

	static final BufferedImage DOG_WAITING_FRAME1 = setImage("dog1-64.png");
	static final BufferedImage DOG_WAITING_FRAME2 = setImage("dog2-64.png");

	static final int ICON_SIZE = 64;
	static final int DIM_X = 1000;
	static final int DIM_Y = 700;

	static BufferedImage dog = DOG_WAITING_FRAME1;

	static class DrawPanel extends JPanel {
		public void paintComponent(Graphics g) {

			fillBG(BG, g);

			g.drawImage(dog, oneX, oneY, this);
		}

		void fillBG(BufferedImage bg, Graphics g) {

			for (int i = 0; i < DIM_X; i += ICON_SIZE)
				for (int j = 0; j < DIM_Y; j += ICON_SIZE)
					g.drawImage(BG, i, j, this);
		}
	}

	public static void main(String[] args) {

		setField();

		move(key);

	}

	static void setField() {
		field = new JFrame("Dogwalk");
		field.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		drawPanel = new DrawPanel();
		field.getContentPane().add(BorderLayout.CENTER, drawPanel);

		field.setVisible(true);
		field.setResizable(false);
		field.setSize(DIM_X, DIM_Y);
		field.setLocation(0, 0);

//		field.addKeyListener(key);

	}

	static BufferedImage setImage(String file) {

		BufferedImage image = null;

		try {
			image = ImageIO.read(new File(IMG_PATH + file));
		} catch (Exception e) {
			System.out.println("\"" + IMG_PATH + file + "\" not found!");
		}

		return image;
	}

//	static void move() {

//		handle(key);

//    new EventHandler<KeyEvent>()
//    {
	public static void move(KeyEvent ev) {

//		System.out.println(ev.VK_RIGHT);
//		System.out.println("Es wurde folgende Taste gedrückt:\t" + ev.getKeyCode());
		switch (ev.getID()) {
		case 38:
			if (oneY > 0)
				oneY--;
			break;
		case 40:
			if (oneY < DIM_Y - ICON_SIZE - 32)
				oneY++;
			break;
		case 37:
			if (oneX > 0)
				oneX--;
			break;
		case 39:
			if (oneX < DIM_X - ICON_SIZE - 16)
				oneX++;
			break;
		}

		field.repaint();
//	}
//    };
	}
}
