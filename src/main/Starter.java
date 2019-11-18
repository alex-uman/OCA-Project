package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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

			for (int i = 0; i < size; i++) {

				item = this.field.getItemList(i);

				switch (item.getTyp()) {

				case "Border":
					g.setColor(Color.WHITE);
					g.fillRect(item.getX(), item.getY(), item.getWidth(), item.getHeigth());
					break;

				case "Brick":

					Brick brick = (Brick) item;
					g.setColor(Color.BLACK);
					g.fillRect(item.getX(), item.getY(), item.getWidth(), item.getHeigth());
					g.setColor(Color.WHITE);
					g.fillRect(item.getX() + 1, item.getY() + 1, item.getWidth() - 2, item.getHeigth() - 2);
					g.setColor(brick.getColor());
					g.fillRect(item.getX() + 3, item.getY() + 3, item.getWidth() - 6, item.getHeigth() - 6);
					break;

				case "Bullet":
					g.setColor(Color.YELLOW);
					g.fillOval(item.getX(), item.getY(), item.getWidth(), item.getHeigth());
					break;

				case "Pitcher":
					g.setColor(Color.WHITE);
					g.fillRect(item.getX(), item.getY(), item.getWidth(), item.getHeigth());
					g.setColor(Color.BLACK);
					g.fillRect(item.getX() + 2, item.getY() + 2, item.getWidth() - 5, item.getHeigth() - 5);
					break;

				}
			}
		}
	}

	static class KeyCatcher implements KeyListener {
		private Field field;
		private Item item;

		KeyCatcher(Field field, Item item) {
			this.field = field;
			this.item = item;
		}

		public void keyPressed(KeyEvent key) {
			switch (key.getKeyCode()) {
			case 37:
				moveCatcherLeft(field, item);
				break;
			case 39:
				moveCatcherRight(field, item);
				break;
			}

		}

		public void keyReleased(KeyEvent key) {
		}

		public void keyTyped(KeyEvent key) {
		}
	}

	public static void main(String[] args) {

		Field aField = new Field(Constants.DIM_X, Constants.DIM_Y);

		PitcherDown pitcherDown = setPitcherDown(aField);
		PitcherUp pitcherUp = setPitcherUp(aField);

		fillField(aField);

		setField(aField, pitcherDown);

		BulletDown bulletDown = new BulletDown();
		BulletThread bulletDownThread = new BulletThread(bulletDown, aField);
		bulletDownThread.start();

		BulletUp bulletUp = new BulletUp();
		BulletThread bulletUpThread = new BulletThread(bulletUp, aField);
		bulletUpThread.start();

		PitcherThread pitcherAI = new PitcherThread(bulletUp, bulletDown, pitcherUp, aField);
		pitcherAI.start();

		for (;;) {
//			try {
//				Thread.sleep(100);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}

			if (aField.getBrickCount() < 1) {

				bulletUpThread.stop();
				bulletDownThread.stop();
				pitcherAI.stop();

				System.exit(0);
			}
		}
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

//		aField.setItemList(new BrickHalf(4, 330));

		aField.setItemList(new Brick(29, 330));
		aField.setItemList(new Brick(79, 330));
		aField.setItemList(new Brick(129, 330));
		aField.setItemList(new Brick(179, 330));
		aField.setItemList(new Brick(229, 330));
		aField.setItemList(new Brick(279, 330));
		aField.setItemList(new Brick(329, 330));
		aField.setItemList(new Brick(379, 330));
		aField.setItemList(new Brick(429, 330));

//		aField.setItemList(new BrickHalf(479, 330));

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
	}

	static PitcherUp setPitcherUp(Field field) {
		PitcherUp pitcherUp = new PitcherUp();
		field.setItemList(pitcherUp);
		return pitcherUp;
	}

	static PitcherDown setPitcherDown(Field field) {
		PitcherDown pitcherDown = new PitcherDown();
		field.setItemList(pitcherDown);
		return pitcherDown;
	}

	static void setField(Field aField, Item pitcherDown) {
		frame = new JFrame("Suppressor");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		drawPanel = new DrawPanel(aField);
		frame.getContentPane().add(BorderLayout.CENTER, drawPanel);

		frame.setVisible(true);
		frame.setResizable(false);
		frame.setSize(aField.getDimX(), aField.getDimY());
		frame.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - aField.getDimX()) / 2,
				(Toolkit.getDefaultToolkit().getScreenSize().height - aField.getDimY()) / 2 - 20);

		JButton button = new JButton();

		button.addKeyListener(new KeyCatcher(aField, pitcherDown));
		frame.add(button);
	}

	static void moveCatcherRight(Field field, Item item) {
		field.moveRIGHT(Constants.MOVE_DISTANCE, item);
	}

	static void moveCatcherLeft(Field field, Item item) {
		field.moveLEFT(Constants.MOVE_DISTANCE, item);
	}

}
