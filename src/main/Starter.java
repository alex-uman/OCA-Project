package main;

import javax.swing.*;
import java.awt.*;

//import java.awt.image.BufferedImage;
//import java.io.File;
//import javax.imageio.ImageIO;

public class Starter {

	static JFrame field;
	static DrawPanel drawPanel;

	static Field myField = new Field(Constants.DIM_X, Constants.DIM_Y);

	static private int oneX = 0;
	static private int oneY = 0;

	static final int ICON_SIZE = 64;

	static class DrawPanel extends JPanel {

		Field field;

		public DrawPanel(Field field) {
			this.field = field;
		}

		public void paintComponent(Graphics g) {

			g.setColor(Color.BLACK);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());

			int size = field.getItemList().size();

			Item item;

//			System.out.println("\nsize: " + size);

			for (int i = 0; i < size; i++) {

				item = field.getItemList(i);

				switch (item.getTyp()) {

				case "Brick":
					g.setColor(Color.BLACK);
					g.fillRect(item.getX(), item.getY(), item.getWidth(), item.getHeigth());
					g.setColor(Color.WHITE);
					g.fillRect(item.getX() + 1, item.getY() + 1, item.getWidth() - 1, item.getHeigth() - 1);
					g.setColor(Color.RED);
					g.fillRect(item.getX() + 3, item.getY() + 3, item.getWidth() - 5, item.getHeigth() - 5);
					break;

				case "Border":
					g.setColor(Color.WHITE);
					g.fillRect(item.getX(), item.getY(), item.getWidth(), item.getHeigth());
					break;
				}
			}
		}
	}

	public static void main(String[] args) {

		fillField(myField);

		setField(myField);

	}

	static void fillField(Field aField) {

		myField.setItemList(new Border(0, 0, Constants.BORDER_THICK, myField.getDimY()));
		myField.setItemList(new Border(myField.getDimX() - Constants.BORDER_THICK - 16, 0, Constants.BORDER_THICK,
				myField.getDimY()));

		myField.setItemList(new Brick(4, 300));
		myField.setItemList(new Brick(54, 300));
		myField.setItemList(new Brick(104, 300));
		myField.setItemList(new Brick(154, 300));
		myField.setItemList(new Brick(204, 300));
		myField.setItemList(new Brick(254, 300));
		myField.setItemList(new Brick(304, 300));
		myField.setItemList(new Brick(354, 300));
		myField.setItemList(new Brick(404, 300));
		myField.setItemList(new Brick(454, 300));

		myField.setItemList(new BrickHalf(4, 330));
		
		myField.setItemList(new Brick(29, 330));
		myField.setItemList(new Brick(79, 330));
		myField.setItemList(new Brick(129, 330));
		myField.setItemList(new Brick(179, 330));
		myField.setItemList(new Brick(229, 330));
		myField.setItemList(new Brick(279, 330));
		myField.setItemList(new Brick(329, 330));
		myField.setItemList(new Brick(379, 330));
		myField.setItemList(new Brick(429, 330));
		
		myField.setItemList(new BrickHalf(479, 330));

		myField.setItemList(new Brick(4, 360));
		myField.setItemList(new Brick(54, 360));
		myField.setItemList(new Brick(104, 360));
		myField.setItemList(new Brick(154, 360));
		myField.setItemList(new Brick(204, 360));
		myField.setItemList(new Brick(254, 360));
		myField.setItemList(new Brick(304, 360));
		myField.setItemList(new Brick(354, 360));
		myField.setItemList(new Brick(404, 360));
		myField.setItemList(new Brick(454, 360));
		
		myField.setItemList(new Border(252, 0, Constants.BORDER_THICK, myField.getDimY()));

	}

	static void setField(Field aField) {
		field = new JFrame("Supressor");
		field.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		drawPanel = new DrawPanel(aField);
		field.getContentPane().add(BorderLayout.CENTER, drawPanel);

		field.setVisible(true);
		field.setResizable(false);
		field.setSize(aField.getDimX(), aField.getDimY());
		field.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - aField.getDimX()) / 2,
				(Toolkit.getDefaultToolkit().getScreenSize().height - aField.getDimY()) / 2 - 20);

	}
}
