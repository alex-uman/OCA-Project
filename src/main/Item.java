package main;

import java.awt.Color;

public class Item {

	private int posX, posY, width, heigth;
	private boolean moveable = false;

	Item(int posX, int posY, int width, int heigth) {
		this.setX(posX);
		this.setY(posY);
		this.setWidth(width);
		this.setHeigth(heigth);

	}

	public void setX(int posX) {
		this.posX = posX;
	}

	public void setY(int posY) {
		this.posY = posY;
	}

	public void setXY(int posX, int posY) {
		this.setX(posX);
		this.setY(posY);
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeigth(int heigth) {
		this.heigth = heigth;
	}

	public void setMobile() {
		moveable = true;
	}

	public void setImmobile() {
		moveable = false;
	}

	public int getX() {
		return posX;
	}

	public int getY() {
		return posY;
	}

	public int getWidth() {
		return width;
	}

	public int getHeigth() {
		return heigth;
	}

	public boolean Moveable() {
		return moveable;
	}

	public String getTyp() {
		return "Item";
	}

	public String toString() {
		return this.getTyp() + " " + this.getX() + " " + this.getY() + " " + this.getWidth() + " " + this.getHeigth();
	}
}

class Moveable extends Item {

	Moveable(int posX, int posY, int width, int height) {
		super(posX, posY, width, height);
		this.setMobile();
	}

	public String getTyp() {
		return "Moveable";
	}
}

class Immobile extends Item {

	Immobile(int posX, int posY, int width, int height) {
		super(posX, posY, width, height);
	}

	public String getTyp() {
		return "Immobile";
	}
}

class Border extends Immobile {

	Border(int posX, int posY, int width, int height) {
		super(posX, posY, width, height);
	}

	public String getTyp() {
		return "Border";
	}
}

class Brick extends Moveable {

	private Color color = RandomColor();
	private byte thickness = 1;

	Brick(int posX, int posY) {
		super(posX, posY, Constants.BRICK_WIDTH, Constants.BRICK_HEIGTH);
	}

	public String getTyp() {
		return "Brick";
	}

	public Color getColor() {
		return this.color;
	}

	public byte getThickness() {
		return this.thickness;
	}

	public void setThickness(byte thickness) {
		this.thickness = thickness;
	}

	private Color RandomColor() {

		int random = (int) (Math.random() * 7);

		switch (random) {
		case 0:
			return Color.RED;
		case 1:
			return Color.GREEN;
		case 2:
			return Color.BLUE;
		case 3:
			return Color.MAGENTA;
		case 4:
			return Color.CYAN;
		default:
			return Color.ORANGE;
		}
	}
}

//class ThickBrick extends Brick {
//
//	ThickBrick(int posX, int posY) {
//		super(posX, posY);
//		this.setThickness((byte) 2);
//	}
//}

//class BrickHalf extends Brick {
//
//	BrickHalf(int posX, int posY) {
//		super(posX, posY);
//		this.setWidth(this.getWidth() / 2);
//	}
//}

class Bullet extends Moveable {

	private boolean left = false;
	private boolean right = true;
	private boolean up = true;
	private boolean down = false;
	private byte rateX = 0;
	private byte rateY = 0;

	Bullet(int posX, int posY) {
		super(posX, posY, Constants.BULLET_WIDTH, Constants.BULLET_HEIGTH);
	}

	public void setLeft(boolean val) {
		this.left = val;
	}

	public void setRight(boolean val) {
		this.right = val;
	}

	public void setUp(boolean val) {
		this.up = val;
	}

	public void setDown(boolean val) {
		this.down = val;
	}

	public boolean getLeft() {
		return this.left;
	}

	public boolean getRight() {
		return this.right;
	}

	public boolean getUp() {
		return this.up;
	}

	public boolean getDown() {
		return this.down;
	}

	public byte getRateX() {
		return this.rateX;
	}

	public byte getRateY() {
		return this.rateY;
	}

	public void halfRateX() {
		this.rateX = 2;
	}

	public void normalRateX() {
		this.rateX = 0;
	}

	public void halfRateY() {
		this.rateY = 2;
	}

	public void normalRateY() {
		this.rateY = 0;
	}

	public void switchRateX() {
		if (this.rateX > 0)
			this.rateX = this.rateX == 2 ? (byte) 1 : (byte) 2;
	}

	public void switchRateY() {
		if (this.rateY > 0)
			this.rateY = this.rateY == 2 ? (byte) 1 : (byte) 2;
	}

	public String getTyp() {
		return "Bullet";
	}
}

class BulletUp extends Bullet {

	BulletUp() {
		super((Constants.DIM_X - Constants.BULLET_WIDTH) / 2, Constants.BORDER_THICK + Constants.BULLET_HEIGTH + 2);
	}
}

class BulletDown extends Bullet {

	BulletDown() {
		super((Constants.DIM_X - Constants.BULLET_WIDTH) / 2,
				Constants.DIM_Y - 39 - Constants.BORDER_THICK - Constants.PITCHER_HEIGTH - Constants.BULLET_HEIGTH - 2);
	}
}

class Pitcher extends Moveable {

	Pitcher(int posX, int posY) {
		super(posX, posY, Constants.PITCHER_WIDTH, Constants.PITCHER_HEIGTH);
	}

	public String getTyp() {
		return "Pitcher";
	}
}

class PitcherUp extends Pitcher {

	PitcherUp() {
		super((Constants.DIM_X - Constants.PITCHER_WIDTH) / 2, 2);// Constants.BORDER_THICK );
	}
}

class PitcherDown extends Pitcher {

	PitcherDown() {
		super((Constants.DIM_X - Constants.PITCHER_WIDTH) / 2,
				Constants.DIM_Y - Constants.MARGIN_Y - Constants.PITCHER_HEIGTH - 2);// -
		// Constants.BORDER_THICK);
	}
}