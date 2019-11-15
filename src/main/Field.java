package main;

import java.util.ArrayList;

public class Field {

	private Item[][] pixel;

	private ArrayList<Item> itemList = new ArrayList<>();

	Field(int dimX, int dimY) {

		if (dimX < 1 && dimY < 1)
			throw new IllegalStateException("False Field declaration!");

		this.pixel = new Item[dimX][dimY];

		this.clearField();
	}

	public void clearField() {
		this.fillNull(0, 0, this.getDimX(), this.getDimY());
	}

	public int getDimX() {
		return this.pixel.length;
	}

	public int getDimY() {
		return this.pixel[0].length;
	}

	public ArrayList<Item> getItemList() {
		return this.itemList;
	}

	public Item getItemList(int index) {

		if (index < 0 || index > this.itemList.size() - 1)
			return null;

		return this.itemList.get(index);
	}

	public boolean setItemList(Item item) {

		if (item == null)
			return false;

		byte check = this.putItem(item.getX(), item.getY(), item);

		System.out.println(check);
		System.out.println(item);

		if (check == 1) {
			this.itemList.add(item);
			return true;
		}

		return false;
	}

	public byte putItem(int x, int y, Item item) {

		byte check = this.checkSpace(x, y, item);

		if (check == 1)
			setItem(x, y, item);

		return check;

	}

	public byte moveUP(int value, Item item) {
		return this.moveItem(0, -value, item);
	}

	public byte moveDOWN(int value, Item item) {
		return this.moveItem(0, value, item);
	}

	public byte moveLEFT(int value, Item item) {
		return this.moveItem(-value, 0, item);
	}

	public byte moveRIGHT(int value, Item item) {
		return this.moveItem(value, 0, item);
	}

	public byte moveItem(int x, int y, Item item) {

		if (!item.Moveable())
			return -3;

		int posX = item.getX();
		int posY = item.getY();
		int newX = x + posX;
		int newY = y + posY;

		this.fillNull(posX, posY, item.getWidth(), item.getHeigth());

		byte check = this.putItem(newX, newY, item);

		if (check != 1)

			setItem(posX, posY, item);

		return check;

	}

	public boolean nullArea(int x, int y, int width, int heigth) {

		if (x < 0 || y < 0 || x + width >= this.getDimX() || y + heigth >= this.getDimY())
			return false;

		fillNull(x, y, width, heigth);

		return true;

	}

	private void setItem(int x, int y, Item item) {

		int width = item.getWidth();
		int heigth = item.getHeigth();

		for (int i = 0; i < width; i++)
			for (int j = 0; j < heigth; j++)
				this.pixel[i][j] = item;

		item.setXY(x, y);

	}

	private void fillNull(int x, int y, int width, int heigth) {

		for (int i = 0; i < width; i++)
			for (int j = 0; j < heigth; j++)
				this.pixel[i + x][j + y] = null;
	}

	public Item getPixel(int x, int y) {

		if (x < 0 || y < 0 || x >= this.getDimX() || y >= this.getDimY())
			return null;

		return this.pixel[x][y];

	}

	public byte checkSpace(int x, int y, Item item) {

		if (item == null)
			return -2;

		int width = item.getWidth();
		int heigth = item.getHeigth();

		if (x < 0 || y < 0 || x + width > this.getDimX() || y + heigth > this.getDimY())
			return -1;

		for (int i = 0; i < width; i++)
			for (int j = 0; j < heigth; j++)
				if (this.pixel[x + i][y + j] != null) {
//					System.out.println(i + " " + j);
					return 0;
				}

		return 1;

	}

//	public byte fillField() {
//
//		ArrayList<Item> list = this.getItemList();
//
//		if (list.size() == 0)
//			return -2;
//
//		byte check;
//		byte min = 1;
//
//		for (Item i : list) {
//			check = this.putItem(i.getX(), i.getY(), i);
//			min = check < min ? check : min;
//		}
//
//		return min;
//	}

}
