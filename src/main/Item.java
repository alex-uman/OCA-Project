package main;

public abstract class Item {

	private int posX, posY, width, heigth;
	private boolean moveable = false;
	private String image = "";

	Item(int posX, int posY, int width, int heigth, boolean moveable) {
		this.setX(posX);
		this.setY(posY);
		this.setWidth(width);
		this.setHeigth(heigth);
		this.moveable = moveable;

	}

	public void setImage(String image) {
		this.image = image;
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

	public String getImage() {
		return image;
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

	public boolean getMoveable() {
		return moveable;
	}

	public String getTyp() {
		return "Item";
	}

	public String toString() {
		return this.getTyp() + " " + this.getY() + " " + this.getX(); // + " " + this.getWidth() + " " +
																		// this.getHeigth();
	}
}

abstract class Moveable extends Item {

	Moveable(int posX, int posY, int width, int height) {
		super(posX, posY, width, height, true);
	}

	public String getTyp() {
		return "Moveable";
	}
}

abstract class Immobile extends Item {

	Immobile(int posX, int posY, int width, int height) {
		super(posX, posY, width, height, false);
	}

	public String getTyp() {
		return "Immobile";
	}
}

class Border extends Immobile {

	Border(int posX, int posY, int width, int height) {
		super(posX, posY, width, height);
		this.setImage(Constants.BORDER);
	}

	public String getTyp() {
		return "Border";
	}
}

class Brick extends Moveable {

	private byte thickness = 1;

	Brick(int posX, int posY) {
		super(posX, posY, Constants.BRICK_WIDTH, Constants.BRICK_HEIGTH);
		this.setImage(RandomColor());
	}

	public String getTyp() {
		return "Brick";
	}

	public byte getThickness() {
		return this.thickness;
	}

	public void setThickness(byte thickness) {
		this.thickness = thickness;
	}

	private String RandomColor() {

		int random = (int) (Math.random() * 5);
//		System.out.println(random);

		switch (random) {
		case 0:
			return Constants.BRICK_BLUE;
		case 1:
			return Constants.BRICK_GREEN;
		case 2:
			return Constants.BRICK_MAGENTA;
		case 3:
			return Constants.BRICK_ORANGE;
		default:
			return Constants.BRICK_PINK;
		}
	}
}

class Pitcher extends Moveable {

	private boolean isUpper;

	Pitcher(boolean isUpper, Field field) {
		super((field.getDimX() - Constants.PITCHER_WIDTH) / 2,
				isUpper ? 2 : field.getDimY() - Constants.MARGIN_Y - Constants.PITCHER_HEIGTH - 2,
				Constants.PITCHER_WIDTH, Constants.PITCHER_HEIGTH);
		this.isUpper = isUpper;
		this.setImage(isUpper ? Constants.PITCHER_UP : Constants.PITCHER_DOWN);
		field.setItemList(this);
	}

	Pitcher(Field field) {
		this(true, field);
	}

	public String getTyp() {
		return "Pitcher";
	}

	public boolean getIsUpper() {
		return this.isUpper;
	}
}
