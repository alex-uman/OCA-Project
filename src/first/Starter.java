package first;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

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

//			Item item;

			try {

				for (int i = 0; i < size; i++) {

					Item item = this.field.getItemList(i);

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

						if (brick.getThickness() == 2) {
							g.setColor(new Color(255, 255, 255, 200));
							g.fillRect(item.getX() + 3, item.getY() + 3, item.getWidth() - 6, item.getHeigth() - 6);
						}
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

		Field field = new Field(Constants.DIM_X, Constants.DIM_Y);

		BulletDown bulletDown = new BulletDown();
		BulletThread bulletDownThread = new BulletThread(bulletDown, field);
		BulletUp bulletUp = new BulletUp();
		BulletThread bulletUpThread = new BulletThread(bulletUp, field);

		PitcherDown pitcherDown = setPitcherDown(field);
		PitcherUp pitcherUp = setPitcherUp(field);
		PitcherAIThread pitcherAI = new PitcherAIThread(bulletUp, bulletDown, pitcherUp, field);

		fillField(field);
		setField(field, pitcherDown);

		bulletDownThread.start();
		bulletUpThread.start();
		pitcherAI.start();

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

		brickRow(Constants.BORDER_THICK, 300, field);

//		field.setItemList(new BrickHalf(4, 330));

		brickRow(Constants.BORDER_THICK + Constants.BRICK_WIDTH / 2, 330, field, true);

//		field.setItemList(new BrickHalf(479, 330));

		brickRow(Constants.BORDER_THICK, 360, field);

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
		field.setItemList(new Border(field.getDimX() - Constants.BORDER_THICK - 16, 0, Constants.BORDER_THICK,
				field.getDimY() - Constants.MARGIN_Y));

		// field.setItemList(new Border(Constants.BORDER_THICK, 0, field.getDimX() -
		// (Constants.BORDER_THICK * 2) - 16,
//				Constants.BORDER_THICK));
//		field.setItemList(new Border(Constants.BORDER_THICK, field.getDimY() - 43,
//				field.getDimX() - (Constants.BORDER_THICK * 2) - 16, Constants.BORDER_THICK));
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

	static void setField(Field field, Item pitcherDown) {
		frame = new JFrame("Suppressor");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		drawPanel = new DrawPanel(field);
		frame.getContentPane().add(BorderLayout.CENTER, drawPanel);

		frame.setVisible(true);
		frame.setResizable(false);
		frame.setSize(field.getDimX(), field.getDimY());
		frame.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - field.getDimX()) / 2,
				(Toolkit.getDefaultToolkit().getScreenSize().height - field.getDimY()) / 2 - Constants.MARGIN_Y / 2);

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
