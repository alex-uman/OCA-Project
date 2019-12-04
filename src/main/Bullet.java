package main;

class Bullet extends Moveable {

	private boolean left = false;
	private boolean right = true;
	private boolean up = true;
	private boolean down = false;
	private byte rateX = 0;
	private byte rateY = 0;
	private Pitcher pitcher;
	private int count = 0;
	private int fps = 1;

	Bullet(Pitcher pitcher) {
		super(pitcher.getX() + (pitcher.getWidth() - Constants.BULLET_WIDTH) / 2,
				pitcher.getIsUpper() == true ? pitcher.getY() + pitcher.getHeigth()
						: pitcher.getY() - Constants.BULLET_HEIGTH,
				Constants.BULLET_WIDTH, Constants.BULLET_HEIGTH);

		this.pitcher = pitcher;
		this.setImage(Constants.BALL[count]);
	}

	public void setLeft(boolean val) {
		this.left = val;
	}

	public void setRight(boolean val) {
		this.right = val;
	}

	public void setUp(boolean val) {
		this.up = val;
	}

	public void setDown(boolean val) {
		this.down = val;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void riseCount() {
		this.count++;
	}

	public void resetCount() {
		this.count = 0;
	}

	public void setFPS(int fps) {
		this.fps = fps;
	}

	public void riseFPS() {
		this.fps++;
	}

	public void resetFPS() {
		this.fps = 1;
	}

	public int getFPS() {
		return this.fps;
	}

	public int getCount() {
		return this.count;
	}

	public boolean getLeft() {
		return this.left;
	}

	public boolean getRight() {
		return this.right;
	}

	public boolean getUp() {
		return this.up;
	}

	public boolean getDown() {
		return this.down;
	}

	public byte getRateX() {
		return this.rateX;
	}

	public byte getRateY() {
		return this.rateY;
	}

	public void halfRateX() {
		this.rateX = 2;
	}

	public void normalRateX() {
		this.rateX = 0;
	}

	public void halfRateY() {
		this.rateY = 2;
	}

	public void normalRateY() {
		this.rateY = 0;
	}

	public void switchRateX() {
		if (this.rateX > 0)
			this.rateX = this.rateX == 2 ? (byte) 1 : (byte) 2;
	}

	public void switchRateY() {
		if (this.rateY > 0)
			this.rateY = this.rateY == 2 ? (byte) 1 : (byte) 2;
	}

	public void setPitcher(Pitcher pitcher) {
		this.pitcher = pitcher;
	}

	public Pitcher getPitcher() {
		return this.pitcher;
	}

	public String getTyp() {
		return "Bullet";
	}
}