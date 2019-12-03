package textured;

public class PitcherAIThread implements Runnable {

	private Thread pitcherThread;

	private Field field;
	private Bullet bulletUp, bulletDown;
	private Pitcher pitcherUp;

	PitcherAIThread(Bullet bulletUp, Bullet bulletDown, Pitcher pitcherUp, Field field) {
//		super();
		this.field = field;
		this.bulletUp = bulletUp;
		this.bulletDown = bulletDown;
		this.pitcherUp = pitcherUp;
	}

	public void run() {
		Thread thread = Thread.currentThread();

		while (thread == pitcherThread)
			move(this.bulletUp, this.bulletDown, this.pitcherUp, this.field);
	}

	public void stop() {
		pitcherThread = null;
	}

	public void start() {
		if (pitcherThread == null) {
			pitcherThread = new Thread(this);
			pitcherThread.start();
		}
	}

	static void move(Bullet bulletUp, Bullet bulletDown, Pitcher pitcherUp, Field field) {

		Bullet bullet = bulletUp.getY() < bulletDown.getY() ? bulletUp : bulletDown;

		if (bullet.getY() < field.getDimY() / 3) {
			int bX = bullet.getX() + bullet.getWidth() / 2;
			int pX = pitcherUp.getX() + pitcherUp.getWidth() / 2;

			if (pX < bX)
				field.moveRIGHT(pitcherUp);
			else if (pX > bX)
				field.moveLEFT(pitcherUp);

			try {
				Thread.sleep(1);
			} catch (Exception e) {
				System.out.println("ppp");
//				e.printStackTrace();
			}
		}
		Starter.frame.repaint();
	}
}