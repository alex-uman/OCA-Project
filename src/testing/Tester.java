package testing;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

final public class Tester {

	BufferedImage dog = DOG_WAITING_FRAME1;

	JFrame frame;
	DrawPanel drawPanel;

	private int oneX = 0;
	private int oneY = 0;

	boolean up = false;
	boolean down = true;
	boolean left = false;
	boolean right = true;

	static final String IMG_PATH = "e:\\Workspace\\Images\\";

	static final BufferedImage BG = setImage("grass.png");

	static final BufferedImage DOG_WAITING_FRAME1 = setImage("dog1-64.png");
	static final BufferedImage DOG_WAITING_FRAME2 = setImage("dog2-64.png");

	static final int ICON_SIZE = 64;
	static final int DIM_X = 1000;
	static final int DIM_Y = 700;

	public static void main(String[] args) {
		new Tester().go();
	}

	private void go() {
		frame = new JFrame("Dogwalk");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		drawPanel = new DrawPanel();

		frame.getContentPane().add(BorderLayout.CENTER, drawPanel);

		frame.setVisible(true);
		frame.setResizable(false);
		frame.setSize(DIM_X, DIM_Y);
		frame.setLocation(0, 0);

		moveIt();
	}

	class DrawPanel extends JPanel {
		public void paintComponent(Graphics g) {

//			g.setColor(Color.WHITE);
//			g.fillRect(0, 0, this.getWidth(), this.getHeight());

			for (int i = 0; i < DIM_X; i += ICON_SIZE)
				for (int j = 0; j < DIM_Y; j += ICON_SIZE)
					g.drawImage(BG, i, j, this);

			g.drawImage(dog, oneX, oneY, this);

		}
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

	private void moveIt() {
		for (;;) {
			if (oneX >= DIM_X - ICON_SIZE - 16) {
				right = false;
				left = true;
			}
			if (oneX <= 0) {
				right = true;
				left = false;
			}
			if (oneY >= DIM_Y - ICON_SIZE - 32) {
				up = true;
				down = false;
			}
			if (oneY <= 0) {
				up = false;
				down = true;
			}
			if (up) {
				oneY--;
			}
			if (down) {
				oneY++;
			}
			if (left) {
				oneX--;
			}
			if (right) {
				oneX++;
			}
			try {
				Thread.sleep(10);
			} catch (Exception e) {
				e.printStackTrace();
			}
			frame.repaint();
		}
	}
}