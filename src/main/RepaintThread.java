package main;

public class RepaintThread implements Runnable {

	private Thread repaintThread;

	public void run() {

		Thread thread = Thread.currentThread();

		while (thread == repaintThread) {

			try {
				Thread.sleep(20);
			} catch (Exception e) {
				System.out.println("rrr");
//				e.printStackTrace();
			} finally {
				Starter.frame.repaint();
			}

		}

	}

	public void stop() {
		repaintThread = null;
	}

	public void start() {
		if (repaintThread == null) {
			repaintThread = new Thread(this);
			repaintThread.start();
		}
	}
}