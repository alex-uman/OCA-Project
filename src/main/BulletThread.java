package main;

import java.util.ArrayList;

public class BulletThread implements Runnable {

	private Thread bulletThread;

	private Field field;
	private Bullet bullet;

	BulletThread(Bullet bullet, Field field) {
//		super();
		this.field = field;
		this.bullet = bullet;
	}

	public void run() {
		Thread thread = Thread.currentThread();

		while (thread == bulletThread)
			move(this.bullet, this.field);
	}

	public void stop() {
		bulletThread = null;
	}

	public void start() {
		if (bulletThread == null) {
			bulletThread = new Thread(this);
			bulletThread.start();
		}
	}

	static void move(Bullet bullet, Field field) {

//		System.out.println((bullet.getPitcher().getIsUpper() ? "Up" : "Down"));

//		if (!field.getItemList().contains(bullet))

		field.setItemList(bullet);

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
				field.moveLEFT(bullet);
		if (bullet.getRight())
			if (bullet.getRateX() < 2)
				field.moveRIGHT(bullet);
		if (bullet.getUp())
			if (bullet.getRateY() < 2)
				field.moveUP(bullet);
		if (bullet.getDown() == true)
			if (bullet.getRateY() < 2)
				field.moveDOWN(bullet);

		int fps = bullet.getFPS();
		if (fps < Constants.FPS)
			bullet.riseFPS();
		else {
			bullet.resetFPS();

			int count = bullet.getCount();
			if (count < Constants.BALL.length - 1)
				bullet.riseCount();
			else
				bullet.resetCount();

			bullet.setImage(Constants.BALL[count]);

//			System.out.println(bullet.getPitcher() + " " + bullet.getImage() + " " + count);
		}

		try {
			Thread.sleep(Constants.BULLET_DELAY);

//			Starter.frame.repaint();

		} catch (Exception e) {
			System.out.println("bbb");
//			e.printStackTrace();
		}
	}
}
