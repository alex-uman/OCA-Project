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
//		for (;;) {
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

		if (bullet.getLeft())
			field.moveLEFT(bullet);

		if (bullet.getRight())
			field.moveRIGHT(bullet);

		if (bullet.getUp())
			field.moveUP(bullet);

		if (bullet.getDown() == true)
			field.moveDOWN(bullet);

		try {
			Thread.sleep(Constants.BULLET_DELAY);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Starter.frame.repaint();
//		}
	}
}
