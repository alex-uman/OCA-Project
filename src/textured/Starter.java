package textured;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Starter {

	static JFrame frame;
	static DrawPanel drawPanel;

	static class DrawPanel extends JPanel {

		Field field;

		public DrawPanel(Field field) {
			this.field = field;
		}

		public void paintComponent(Graphics g) {

			g.setColor(new Color(0, 0, 150));
			g.fillRect(0, 0, this.getWidth(), this.getHeight());

			int size = this.field.getItemList().size();

			try {

				for (int i = 0; i < size; i++) {

					Item item = this.field.getItemList(i);

//					System.out.println(item);

					switch (item.getTyp()) {

					case "Border":
						g.setColor(Color.WHITE);
						g.fillRect(item.getX(), item.getY(), item.getWidth(), item.getHeigth());
						break;

					case "Brick":

						Brick brick = (Brick) item;

						g.drawImage(setImage(brick.getColor()), brick.getX(), brick.getY(), brick.getWidth(),
								brick.getHeigth(), this);

						if (brick.getThickness() == 2)
							g.drawImage(setImage(Constants.BRICK_CAGE), brick.getX(), brick.getY(), brick.getWidth(),
									brick.getHeigth(), this);

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
			} catch (NullPointerException e) {
				System.out.println("!!!");
			}
		}
	}

	static BufferedImage setImage(String file) {

		BufferedImage image = null;

		try {
			image = ImageIO.read(new File(file));
		} catch (Exception e) {
			throw new IllegalArgumentException("\"" + file + "\" not found!");
		}

//		System.out.println(Starter.class.getResource(file));

		return image;
	}

	static class KeyCatcher implements KeyListener {
		private Field field;
		private Item item;

		KeyCatcher(Field field, Item item) {
			this.field = field;
			this.item = item;
		}

		public void keyPressed(KeyEvent key) {
			catchKey(key);
		}

		public void keyReleased(KeyEvent key) {
		}

		public void keyTyped(KeyEvent key) {
//			catchKey(key);
		}

		private void catchKey(KeyEvent key) {
			switch (key.getKeyCode()) {
			case 37:
				moveCatcherLeft(field, item);
				System.out.println("Left");
				break;
			case 39:
				moveCatcherRight(field, item);
				System.out.println("Right");
				break;
			}
		}

	}

	public static void main(String[] args) {

		Field field = new Field(Constants.DIM_X, Constants.DIM_Y);

		Pitcher pitcherDown = new Pitcher(false, field);
		Pitcher pitcherUp = new Pitcher(true, field);

		Bullet bulletDown = new Bullet(pitcherDown);
		Bullet bulletUp = new Bullet(pitcherUp);

		BulletThread bulletDownThread = new BulletThread(bulletDown, field);
		BulletThread bulletUpThread = new BulletThread(bulletUp, field);
		PitcherAIThread pitcherAI = new PitcherAIThread(bulletUp, bulletDown, pitcherUp, field);

		setField(field, pitcherDown);

		bulletDownThread.start();
		bulletUpThread.start();
		pitcherAI.start();

		fillField(field);

		for (;;) {
//			try {
//				Thread.sleep(100);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}

//			if (field.getBrickCount() < 1) {
//
//				bulletUpThread.stop();
//				bulletDownThread.stop();
//				pitcherAI.stop();
//
//				closing();
//			}
		}
	}

	static void fillField(Field field) {

		standardBorder(field);

		int middle = (field.getDimY() - Constants.BRICK_HEIGTH) / 2;

		brickRow(Constants.BORDER_THICK, middle - Constants.BRICK_HEIGTH, field);
		brickRow(Constants.BORDER_THICK + Constants.BRICK_WIDTH / 2, middle, field, true);
		brickRow(Constants.BORDER_THICK, middle + Constants.BRICK_HEIGTH, field);

	}

	static boolean brickRow(int x, int y, Field field, boolean isThick) {

		if (field == null)
			return false;

		int width = field.getDimX();

		for (;;) {
			Brick brick = new Brick(x, y);
			if (isThick)
				brick.setThickness((byte) 2);
			field.setItemList(brick);
			if (x > width)
				return true;
			x += brick.getWidth();
		}
	}

	static boolean brickRow(int x, int y, Field field) {
		return brickRow(x, y, field, false);
	}

	static void standardBorder(Field field) {
		field.setItemList(new Border(0, 0, Constants.BORDER_THICK, field.getDimY() - Constants.MARGIN_Y));
		field.setItemList(new Border(field.getDimX() - Constants.BORDER_THICK, 0, Constants.BORDER_THICK,
				field.getDimY() - Constants.MARGIN_Y));

//		 field.setItemList(new Border(Constants.BORDER_THICK, 0, field.getDimX() -
// 		(Constants.BORDER_THICK * 2) - 16,
//				Constants.BORDER_THICK));
//		field.setItemList(new Border(Constants.BORDER_THICK, field.getDimY() - 43,
//				field.getDimX() - (Constants.BORDER_THICK * 2) - 16, Constants.BORDER_THICK));
	}

	static void setField(Field field, Item pitcherDown) {
		frame = new JFrame("Brickdance");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		drawPanel = new DrawPanel(field);
		frame.getContentPane().add(BorderLayout.CENTER, drawPanel);

		frame.setVisible(true);
		frame.setAlwaysOnTop(true);
		frame.setResizable(false);
		frame.setSize(field.getDimX(), field.getDimY());
		frame.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - field.getDimX()) / 2, 0);

		JButton button = new JButton();

		button.addKeyListener(new KeyCatcher(field, pitcherDown));
		frame.add(button);

//		frame.setExtendedState(JFrame.ICONIFIED);
//		frame.setExtendedState(JFrame.NORMAL);
//		frame.toFront();
//		frame.requestFocus();
	}

	static void moveCatcherRight(Field field, Item item) {
		for (int i = Constants.PITCHER_MOVE_DISTANCE; i > 0; i /= 2)
			field.moveRIGHT(i, item);
//			if (field.moveRIGHT(i, item) == 1)
//				break;
	}

	static void moveCatcherLeft(Field field, Item item) {
		for (int i = Constants.PITCHER_MOVE_DISTANCE; i > 0; i /= 2)
			field.moveLEFT(i, item);
//			if (field.moveLEFT(i, item) == 1)
//				break;
	}

	static void finalAction(byte action, Field field) {
		if (field == null)
			return;

		switch (action) {
		case 1:
			finalSentence(action, field);
			closing();
		case 2:
			finalSentence(action, field);
			closing();
		default:
			return;
		}
	}

	private static void finalSentence(byte action, Field field) {
		System.out.println("Player " + action + " wins!");
	}

	private static void closing() {
		System.exit(0);
	}
}
