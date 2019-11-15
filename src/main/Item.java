package main;

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

	Brick(int posX, int posY) {
		super(posX, posY, Constants.BRICK_WIDTH, Constants.BRICK_HEIGTH);
	}

	public String getTyp() {
		return "Brick";
	}
}

class BrickHalf extends Moveable {

	BrickHalf(int posX, int posY) {
		super(posX, posY, Constants.BRICK_WIDTH / 2, Constants.BRICK_HEIGTH);
	}

	public String getTyp() {
		return "Brick";
	}

}
