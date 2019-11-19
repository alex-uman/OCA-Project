package main;

public class BulletThread implements Runnable {

	private Thread newThread;

	private Field field;
	private Bullet bullet;

	BulletThread(Bullet bullet, Field field) {
		super();
		this.field = field;
		this.bullet = bullet;
	}

	public void run() {
		this.field.setItemList(this.bullet);

		Thread thread = Thread.currentThread();

		while (thread == newThread)
			move(this.bullet, this.field);
	}

	public void stop() {
		newThread = null;
	}

	public void start() {
		if (newThread == null) {
			newThread = new Thread(this);
			newThread.start();
		}
	}

	static void move(Bullet bullet, Field field) {

		if (field.hitRIGHT(bullet) != 1) {
			bullet.setRight(false);
			bullet.setLeft(true);
		}
		if (field.hitLEFT(bullet) != 1) {
			bullet.setRight(true);
			bullet.setLeft(false);
		}
		if (field.hitUP(bullet) != 1) {
			bullet.setUp(false);
			bullet.setDown(true);
		}
		if (field.hitDOWN(bullet) != 1) {
			bullet.setUp(true);
			bullet.setDown(false);
		}

		bullet.switchRateX();
		bullet.switchRateY();

		if (bullet.getLeft())
			if (bullet.getRateX() < 2)
				field.moveLEFT(2,bullet);
		if (bullet.getRight())
			if (bullet.getRateX() < 2)
				field.moveRIGHT(2,bullet);
		if (bullet.getUp())
			if (bullet.getRateY() < 2)
				field.moveUP(2,bullet);
		if (bullet.getDown() == true)
			if (bullet.getRateY() < 2)
				field.moveDOWN(2,bullet);

		try {
			Thread.sleep(Constants.BULLET_DELAY);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
