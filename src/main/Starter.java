package main;

import javax.swing.*;
import java.awt.*;

//import java.awt.image.BufferedImage;
//import java.io.File;
//import javax.imageio.ImageIO;

public class Starter {

	static JFrame frame;
	static DrawPanel drawPanel;

//	static final int ICON_SIZE = 64;

	static class DrawPanel extends JPanel {

		Field field;

		public DrawPanel(Field field) {
			this.field = field;
		}

		public void paintComponent(Graphics g) {

			g.setColor(Color.BLACK);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());

			int size = this.field.getItemList().size();

			Item item;

//			System.out.println("\nsize: " + size);

			for (int i = 0; i < size; i++) {

				item = this.field.getItemList(i);

				switch (item.getTyp()) {

				case "Border":
					g.setColor(Color.WHITE);
					g.fillRect(item.getX(), item.getY(), item.getWidth(), item.getHeigth());
//					System.out.println(item);
					break;

				case "Brick":
					g.setColor(Color.BLACK);
					g.fillRect(item.getX(), item.getY(), item.getWidth(), item.getHeigth());
					g.setColor(Color.WHITE);
					g.fillRect(item.getX() + 1, item.getY() + 1, item.getWidth() - 2, item.getHeigth() - 2);
					g.setColor(Color.RED);
					g.fillRect(item.getX() + 3, item.getY() + 3, item.getWidth() - 6, item.getHeigth() - 6);
//					System.out.println(item);
					break;

				case "Bullet":
					g.setColor(Color.YELLOW);
					g.fillOval(item.getX(), item.getY(), item.getWidth(), item.getHeigth());
//					System.out.println(item);
					break;

				}
			}
		}
	}

	public static void main(String[] args) {

		Field aField = new Field(Constants.DIM_X, Constants.DIM_Y);

		fillField(aField);

		Bullet bulletOne = new Bullet(300, 650);

		setBullet(bulletOne, aField);

		setField(aField);

		move(bulletOne, aField);

	}

	static void fillField(Field aField) {

		aField.setItemList(new Border(0, 0, Constants.BORDER_THICK, aField.getDimY() - 39));
		aField.setItemList(new Border(aField.getDimX() - Constants.BORDER_THICK - 16, 0, Constants.BORDER_THICK,
				aField.getDimY() - 39));
		aField.setItemList(new Border(Constants.BORDER_THICK, 0, aField.getDimX() - (Constants.BORDER_THICK * 2) - 16,
				Constants.BORDER_THICK));

		aField.setItemList(new Border(Constants.BORDER_THICK, aField.getDimY() - 43,
				aField.getDimX() - (Constants.BORDER_THICK * 2) - 16, Constants.BORDER_THICK));

		aField.setItemList(new Brick(4, 300));
		aField.setItemList(new Brick(54, 300));
		aField.setItemList(new Brick(104, 300));
		aField.setItemList(new Brick(154, 300));
		aField.setItemList(new Brick(204, 300));
		aField.setItemList(new Brick(254, 300));
		aField.setItemList(new Brick(304, 300));
		aField.setItemList(new Brick(354, 300));
		aField.setItemList(new Brick(404, 300));
		aField.setItemList(new Brick(454, 300));

		aField.setItemList(new BrickHalf(4, 330));

		aField.setItemList(new Brick(29, 330));
		aField.setItemList(new Brick(79, 330));
		aField.setItemList(new Brick(129, 330));
		aField.setItemList(new Brick(179, 330));
		aField.setItemList(new Brick(229, 330));
		aField.setItemList(new Brick(279, 330));
		aField.setItemList(new Brick(329, 330));
		aField.setItemList(new Brick(379, 330));
		aField.setItemList(new Brick(429, 330));

		aField.setItemList(new BrickHalf(479, 330));

		aField.setItemList(new Brick(4, 360));
		aField.setItemList(new Brick(54, 360));
		aField.setItemList(new Brick(104, 360));
		aField.setItemList(new Brick(154, 360));
		aField.setItemList(new Brick(204, 360));
		aField.setItemList(new Brick(254, 360));
		aField.setItemList(new Brick(304, 360));
		aField.setItemList(new Brick(354, 360));
		aField.setItemList(new Brick(404, 360));
		aField.setItemList(new Brick(454, 360));

//		System.out.println(aField.getItemList().size());

// 		for (int i = 0; i < myField.getDimX(); i++)
//		for (int j = 0; j < myField.getDimY(); j++)
//			if (myField.getPixel(i, j) != null)
//				System.out.println(i + " " + j + " " + myField.getPixel(i, j));

	}

	static void setBullet(Bullet aBullet, Field aField) {
		aField.setItemList(aBullet);
	}

	static void setField(Field aField) {
		frame = new JFrame("Suppressor");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		drawPanel = new DrawPanel(aField);
		frame.getContentPane().add(BorderLayout.CENTER, drawPanel);

		frame.setVisible(true);
		frame.setResizable(false);
		frame.setSize(aField.getDimX(), aField.getDimY());
		frame.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - aField.getDimX()) / 2,
				(Toolkit.getDefaultToolkit().getScreenSize().height - aField.getDimY()) / 2 - 20);

	}

	static void move(Bullet aBullet, Field aField) {
		for (;;) {
			if (aField.hitRIGHT(aBullet) != 1) {
				aBullet.setRight(false);
				aBullet.setLeft(true);
			}
			if (aField.hitLEFT(aBullet) != 1) {
				aBullet.setRight(true);
				aBullet.setLeft(false);
			}
			if (aField.hitUP(aBullet) != 1) {
				aBullet.setUp(false);
				aBullet.setDown(true);
			}
			if (aField.hitDOWN(aBullet) != 1) {
				aBullet.setUp(true);
				aBullet.setDown(false);
			}

//			System.out.println("Left:  " + aBullet.getLeft());
//			System.out.println("Right: " + aBullet.getRight());
//			System.out.println("Up:    " + aBullet.getUp());
//			System.out.println("Down:  " + aBullet.getDown());
//			System.out.println();

			if (aBullet.getLeft())
				aField.moveLEFT(aBullet);

			if (aBullet.getRight())
				aField.moveRIGHT(aBullet);

			if (aBullet.getUp())
				aField.moveUP(aBullet);

			if (aBullet.getDown() == true)
				aField.moveDOWN(aBullet);

			try {
				Thread.sleep(1);
			} catch (Exception e) {
				e.printStackTrace();
			}

			frame.repaint();
		}
	}
}
