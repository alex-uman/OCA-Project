package textured;

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
		this.field.setItemList(this.bullet);

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

//		String sym = bullet.getUp() + " " + bullet.getDown();
//		if (bullet.getUp()&&!bullet.getDown())
//			System.out.println("Down");
//		else if (!bullet.getUp()&&bullet.getDown())
//			System.out.println("Up");
//		else
//			System.out.println(sym);
		
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

		try {
			Thread.sleep(Constants.BULLET_DELAY);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
