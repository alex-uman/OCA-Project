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

	public Item getPixel(int x, int y) {

		if (x < 0 || y < 0 || x >= this.getDimX() || y >= this.getDimY())
			return null;

		return this.pixel[x][y];
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

//		System.out.println(item);
//		System.out.println(check);

		if (this.putItem(item.getX(), item.getY(), item) == 1) {
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

	public byte moveUP(Item item) {
		return this.moveItem(0, -1, item);
	}

	public byte moveDOWN(Item item) {
		return this.moveItem(0, 1, item);
	}

	public byte moveLEFT(Item item) {
		return this.moveItem(-1, 0, item);
	}

	public byte moveRIGHT(Item item) {
		return this.moveItem(1, 0, item);
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

		int width = item.getWidth() + x;
		int heigth = item.getHeigth() + y;

		for (int i = x; i < width; i++)
			for (int j = y; j < heigth; j++)
				this.pixel[i][j] = item;

		item.setXY(x, y);

	}

	private void eraseItem(Item item) {
		fillNull(item.getX(), item.getY(), item.getWidth(), item.getHeigth());
	}

	private void fillNull(int x, int y, int width, int heigth) {

		width += x;
		heigth += y;

		for (int i = x; i < width; i++)
			for (int j = y; j < heigth; j++)
				this.pixel[i][j] = null;
	}

	public byte checkSpace(int x, int y, Item item) {

		if (item == null)
			return -2;

		int width = item.getWidth() + x;
		int heigth = item.getHeigth() + y;

		if (x < 0 || y < 0 || width > this.getDimX() || heigth > this.getDimY())
			return -1;

		for (int i = x; i < width; i++)
			for (int j = y; j < heigth; j++) {
//				System.out.println(i + " " + j + " " + this.pixel[i][j]);
				if (this.pixel[i][j] != null) {
//					System.out.println("!!!" + i + " " + j);
					return 0;
				}
			}

		return 1;

	}

	public byte checkPixel(int x, int y) {

		if (x < 0 || y < 0 || x > this.getDimX() || y > this.getDimY())
			return -1;

		if (this.pixel[x][y] != null)
			return 0;

		return 1;
	}

//	public byte checkLEFT(Item item) {
//
//		if (item == null)
//			return -2;
//
//		int x = item.getX() - 1;
//		int y = item.getY();
//		int size = y + item.getHeigth();
//		byte check;
//
//		for (int i = y; i < size; i++) {
//
//			check = checkPixel(x, i);
//
//			if (check != 1)
//				return check;
//		}
//
//		return 1;
//	}
//
//	public byte checkRIGHT(Item item) {
//
//		if (item == null)
//			return -2;
//
//		int x = item.getX() + item.getWidth() + 1;
//		int y = item.getY();
//		int size = y + item.getHeigth();
//		byte check;
//
//		for (int i = y; i < size; i++) {
//
//			check = checkPixel(x, i);
//
//			if (check != 1)
//				return check;
//		}
//
//		return 1;
//	}
//
//	public byte checkUP(Item item) {
//
//		if (item == null)
//			return -2;
//
//		int x = item.getX();
//		int y = item.getY() - 1;
//		int size = x + item.getWidth();
//		byte check;
//
//		for (int i = x; i < size; i++) {
//
//			check = checkPixel(i, y);
//
//			if (check != 1)
//				return check;
//		}
//
//		return 1;
//	}
//
//	public byte checkDOWN(Item item) {
//
//		if (item == null)
//			return -2;
//
//		int x = item.getX();
//		int y = item.getY() + item.getHeigth() + 1;
//		int size = x + item.getWidth();
//		byte check;
//
//		for (int i = x; i < size; i++) {
//
//			check = checkPixel(i, y);
//
//			if (check != 1)
//				return check;
//		}
//
//		return 1;
//	}

	public byte hitLEFT(Item item) {

		if (item == null)
			return -2;

		Item nextItem = getPixel(item.getX() - 1, item.getY() + (item.getHeigth() / 2));

		if (nextItem != null) {
			if (moveLEFT(Constants.MOVE_DISTANCE, nextItem) != 1)
				eliminateItem(nextItem);
			return 0;
		}

		return 1;

	}

	public byte hitRIGHT(Item item) {

		if (item == null)
			return -2;

		Item nextItem = getPixel(item.getX() + item.getWidth() + 1, item.getY() + (item.getHeigth() / 2));

		if (nextItem != null) {
			if (moveRIGHT(Constants.MOVE_DISTANCE, nextItem) != 1)
				eliminateItem(nextItem);
			return 0;
		}

		return 1;
	}

	public byte hitUP(Item item) {

		if (item == null)
			return -2;

		Item nextItem = getPixel(item.getX() + (item.getWidth() / 2), item.getY() - 1);

		if (nextItem != null) {
			if (moveUP(Constants.MOVE_DISTANCE, nextItem) != 1)
				eliminateItem(nextItem);
			return 0;
		}

		return 1;
	}

	public byte hitDOWN(Item item) {

		if (item == null)
			return -2;

		Item nextItem = getPixel(item.getX() + (item.getWidth() / 2), item.getY() + item.getHeigth() + 1);

		if (nextItem != null) {
			if (moveDOWN(Constants.MOVE_DISTANCE, nextItem) != 1)
				eliminateItem(nextItem);
			return 0;
		}

		return 1;
	}

	public byte eliminateItem(Item item) {

		if (item == null)
			return -2;

		if (!item.Moveable())
			return -3;

//		int x = item.getX();
//		int y = item.getY();
//
//		System.out.println(x + " " + y + " " + (item.getWidth() + x) + " " + (item.getHeigth() + y));

		removeItem(item);

		return 1;
	}

	private void removeItem(Item item) {

		ArrayList<Item> list = getItemList();

//		System.out.println(getItemList().size());

		eraseItem(item);

		list.remove(item);

		ArrayList<Item> tempList = new ArrayList<>();

		for (Item i : list)
			if (i != null)
				tempList.add(i);

		list = tempList;

		if (list.size() == 1)
			System.exit(0);

//		System.out.println(getItemList().size());

	}

}
