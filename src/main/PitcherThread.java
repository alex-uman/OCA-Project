package main;

public class PitcherThread implements Runnable {

	private Thread newThread;

	private Field field;
	private Bullet bulletUp, bulletDown;
	private Pitcher pitcherUp;

	PitcherThread(Bullet bulletUp, Bullet bulletDown, Pitcher pitcherUp, Field field) {
		super();
		this.field = field;
		this.bulletUp = bulletUp;
		this.bulletDown = bulletDown;
		this.pitcherUp = pitcherUp;
	}

	public void run() {
		Thread thread = Thread.currentThread();

		while (thread == newThread)
			move(this.bulletUp, this.bulletDown, this.pitcherUp, this.field);
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

	static void move(Bullet bulletUp, Bullet bulletDown, Pitcher pitcherUp, Field field) {

		int x = bulletUp.getY() < bulletDown.getY() ? bulletUp.getX() : bulletDown.getX();

		if (pitcherUp.getX() < x)
			field.moveRIGHT(Constants.MOVE_DISTANCE, pitcherUp);
		else if (pitcherUp.getX() > x)
			field.moveLEFT(Constants.MOVE_DISTANCE, pitcherUp);

		try {
			Thread.sleep(Constants.BULLET_DELAY);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Starter.frame.repaint();
	}
}