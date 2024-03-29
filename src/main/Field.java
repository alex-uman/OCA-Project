package main;

import java.util.ArrayList;

public class Field {

	private Item[][] pixel;
	private ArrayList<Item> itemList = new ArrayList<>();
//	private int brickCount = 0;

	Field(int dimX, int dimY) {

		if (dimX < 1 && dimY < 1)
			throw new IllegalArgumentException("False Field declaration!");

		this.pixel = new Item[dimX][dimY];

		this.clearField();
	}

	public void clearField() {
		this.fillNull(0, 0, this.getDimX(), this.getDimY());
	}

//	public void brickCountUp() {
//		this.brickCount++;
//	}
//
//	public void brickCountDown() {
//		this.brickCount--;
//	}
//
//	public int getBrickCount() {
//		return this.brickCount;
//	}

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

		if (this.itemList.contains(item))
			return true;

		if (this.putItem(item.getX(), item.getY(), item) == 1) {
			this.itemList.add(item);

//			if (item.getTyp() == "Brick")
//				this.brickCountUp();

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

	public synchronized byte moveUP(int value, Item item) {
		return this.moveItem(0, -value, item);
	}

	public synchronized byte moveDOWN(int value, Item item) {
		return this.moveItem(0, value, item);
	}

	public synchronized byte moveLEFT(int value, Item item) {
		return this.moveItem(-value, 0, item);
	}

	public synchronized byte moveRIGHT(int value, Item item) {
		return this.moveItem(value, 0, item);
	}

	public synchronized byte moveUP(Item item) {
		return this.moveItem(0, -1, item);
	}

	public synchronized byte moveDOWN(Item item) {
		return this.moveItem(0, 1, item);
	}

	public synchronized byte moveLEFT(Item item) {
		return this.moveItem(-1, 0, item);
	}

	public synchronized byte moveRIGHT(Item item) {
		return this.moveItem(1, 0, item);
	}

	public synchronized byte moveItem(int x, int y, Item item) {

		if (item == null)
			return -2;

		if (item.getTyp() == "Brick") {
			Brick brick = (Brick) item;
			if (brick.getThickness() > 1) {
				brick.setThickness((byte) (brick.getThickness() - 1));
//				System.out.println("t " + ++Constants.hitCount);
				return 1;
			}
		}

		if (!item.getMoveable())
			return -3;

		int posX = item.getX();
		int posY = item.getY();
		int newX = x + posX;
		int newY = y + posY;

		this.fillNull(posX, posY, item.getWidth(), item.getHeigth());

		byte check = this.putItem(newX, newY, item);

		if (check != 1) {
			this.fillNull(posX, posY, item.getWidth(), item.getHeigth());
			setItem(posX, posY, item);
		}

		if (check == -10)
			Starter.finalAction((byte) 1, this);
		if (check == -20)
			Starter.finalAction((byte) 2, this);

		return check;
	}

	// public byte checkArea(int x, int y, Item item) {
//
//		int width = x + item.getWidth();
//		int heigth = y + item.getHeigth();
//
//		if (x < 0 || y < 0 || width >= this.getDimX() || heigth >= this.getDimY())
//			return -2;
//
//		for (int i = x - 1; i < width; i++)
//			for (int j = y - 1; j < heigth; j++) {
//				Item pixel = this.getPixel(i, j);
//				if (pixel != null & pixel != item)
//					return 0;
//			}
//
//		return 1;
//
//	}

//	public void cleanAfter(int x, int y, Item item) {
//
//		int itemX = item.getX();
//		int itemY = item.getY();
//
//		int width = item.getWidth();
//		int heigth = item.getHeigth();
//
//		for (int i = x - 1; i < width; i++)
//			for (int j = y - 1; j < heigth; j++) {
//				Item pixel = this.getPixel(i, j);
//				if (pixel != null & pixel != item) {
//				}
//			}
//	}

	public synchronized boolean nullArea(int x, int y, int width, int heigth) {

		if (x < 0 || y < 0 || x + width >= this.getDimX() || y + heigth >= this.getDimY())
			return false;

		fillNull(x, y, width, heigth);

		return true;

	}

	private synchronized void setItem(int x, int y, Item item) {

		if (item == null)
			return;

		int width = item.getWidth() + x;
		int heigth = item.getHeigth() + y;

		for (int i = x; i < width; i++)
			for (int j = y; j < heigth; j++)
				this.pixel[i][j] = item;

		item.setXY(x, y);

	}

	private synchronized void eraseItem(Item item) {
		if (item == null)
			return;

		fillNull(item.getX(), item.getY(), item.getWidth(), item.getHeigth());
	}

	private synchronized void fillNull(int x, int y, int width, int heigth) {

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

		if (y < 0)
			return -10;

		if (heigth > this.getDimY())
			return -20;

		if (x < 0 || width > this.getDimX())
			return -30;

		for (int i = x; i < width; i++)
			for (int j = y; j < heigth; j++) {
				Item thisPixel = this.pixel[i][j];
				if (thisPixel != null && thisPixel != item) {
					return 0;
				}
			}
		return 1;
	}

	public synchronized byte hitLEFT(Item item) {

		if (item == null)
			return -2;

		Item nextItem = getPixel(item.getX() - 1, item.getY() + (item.getHeigth() / 2));

		if (nextItem != null) {
			if (nextItem.getTyp() == "Pitcher" && item.getTyp() == "Bullet")
				return fixRate(item, nextItem);
			if (nextItem.getTyp() != "Brick")
				return 0;

			if (moveLEFT(Constants.MOVE_DISTANCE, nextItem) != 1)
				eliminateItem(nextItem);

			return 0;
		}

		return 1;

	}

	public synchronized byte hitRIGHT(Item item) {

		if (item == null)
			return -2;

		Item nextItem = getPixel(item.getX() + item.getWidth() + 1, item.getY() + (item.getHeigth() / 2));

		if (nextItem != null) {
			if (nextItem.getTyp() == "Pitcher" && item.getTyp() == "Bullet")
				return fixRate(item, nextItem);
			if (nextItem.getTyp() != "Brick")
				return 0;

			if (moveRIGHT(Constants.MOVE_DISTANCE, nextItem) != 1)
				eliminateItem(nextItem);

			return 0;
		}

		return 1;
	}

	public synchronized byte hitUP(Item item) {

		if (item == null)
			return -2;

		Item nextItem = getPixel(item.getX() + (item.getWidth() / 2), item.getY() - 1);

		if (nextItem != null) {
			if (nextItem.getTyp() == "Pitcher" && item.getTyp() == "Bullet")
				return fixRate(item, nextItem);
			if (nextItem.getTyp() != "Brick")
				return 0;

			if (moveUP(Constants.MOVE_DISTANCE, nextItem) != 1)
				eliminateItem(nextItem);

			return 0;
		}

		return 1;
	}

	public synchronized byte hitDOWN(Item item) {

		if (item == null)
			return -2;

		Item nextItem = getPixel(item.getX() + (item.getWidth() / 2), item.getY() + item.getHeigth() + 1);

		if (nextItem != null) {
			if (nextItem.getTyp() == "Pitcher" && item.getTyp() == "Bullet")
				return fixRate(item, nextItem);
			if (nextItem.getTyp() != "Brick")
				return 0;

			if (moveDOWN(Constants.MOVE_DISTANCE, nextItem) != 1)
				eliminateItem(nextItem);

			return 0;
		}

		return 1;
	}

	public byte fixRate(Item bullet, Item pitcher) {

		if (bullet == null || bullet.getTyp() != "Bullet" || pitcher == null || pitcher.getTyp() != "Pitcher")
			return -2;

		Bullet thisBullet = (Bullet) bullet;
		Pitcher thisPitcher = (Pitcher) pitcher;

		thisBullet.setPitcher(thisPitcher);

		int bulletX = thisBullet.getX() + thisBullet.getWidth() / 2;

		int pitcherX = thisPitcher.getX() + thisPitcher.getWidth() / 2;
		int pitcherWidth = thisPitcher.getWidth() / 2;
		int pitcherMain = (int) ((double) pitcherWidth * 0.7);
		int pitcherMid = (int) ((double) pitcherWidth * 0.2);

//		System.out.println(pitcherWidth + " " + pitcherMain + " " + pitcherMid);

		if (bulletX > pitcherX - pitcherMid && bulletX < pitcherX + pitcherMid) {
			thisBullet.halfRateX();
			thisBullet.normalRateY();
			return 0;
		}

		if (bulletX > pitcherX - pitcherMain && bulletX < pitcherX + pitcherMain) {
			thisBullet.normalRateY();
			thisBullet.normalRateX();
			return 0;
		}

		thisBullet.halfRateY();
		thisBullet.normalRateX();
		return 0;
	}

	public synchronized byte eliminateItem(Item item) {

		if (item == null)
			return -2;

		if (!item.getMoveable())
			return -3;

		removeItem(item);

		return 1;
	}

	private synchronized void removeItem(Item item) {

		if (item.getTyp() == "Bullet")
			System.out.println("a Bullet is about to be removed!");

		ArrayList<Item> list = getItemList();

		eraseItem(item);

//		if (item.getTyp() == "Brick")
//			this.brickCountDown();
//
//		try {
//			Thread.sleep(1);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

//		if (item.getTyp() != "Bullet") {

		list.remove(item);

		ArrayList<Item> tempList = new ArrayList<>();

		for (Item i : list)
			if (i != null)
				tempList.add(i);

		list = tempList;
//		} else {
//			Bullet bullet = (Bullet) item;
//			BulletThread bulletThread = new BulletThread(bullet, this);
//			bulletThread.start();
//
//			System.out.println((bullet.getPitcher().getIsUpper() ? "Up" : "Down"));
//		}

//			if (list.size() == 1)
//			System.exit(0);
	}
}
