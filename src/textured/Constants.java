package textured;

import java.awt.Toolkit;

public class Constants {

//	static int hitCount = 0;

	static final int MARGIN_X = 24;
	static final int MARGIN_Y = 39;

	static final int DIM_Y = Toolkit.getDefaultToolkit().getScreenSize().height - 50;

	static final int PITCHER_MOVE_DISTANCE = DIM_Y / 50;

	static final int BORDER_THICK = DIM_Y / 250;

	static final int BRICK_WIDTH = (int) (((DIM_Y / 1.6) - BORDER_THICK * 2) / 10);
	static final int BRICK_HEIGTH = (int) (BRICK_WIDTH * 0.6);

	static final int MOVE_DISTANCE = BRICK_HEIGTH / 2; // DIM_Y / 100;

	static final int DIM_X = BRICK_WIDTH * 10 + BORDER_THICK * 2;

	static final int BULLET_WIDTH = DIM_X / 25;
	static final int BULLET_HEIGTH = BULLET_WIDTH;

	static final int PITCHER_WIDTH = DIM_X / 5;
	static final int PITCHER_HEIGTH = PITCHER_WIDTH / 5;

	static final int BULLET_DELAY = DIM_Y / 100;

	static final String PATH = "src\\";

	static final String FIELD_BG = PATH + "Field.png";

	static final String BRICK_BLUE = PATH + "Gem_Blue.png";
	static final String BRICK_GREEN = PATH + "Gem_Green.png";
	static final String BRICK_MAGENTA = PATH + "Gem_Magenta.png";
	static final String BRICK_ORANGE = PATH + "Gem_Orange.png";
	static final String BRICK_PINK = PATH + "Gem_Pink.png";
	static final String BRICK_CAGE = PATH + "Gem_Cage.png";

	static final String BORDER = PATH + "Field.png";

	static final String PITCHER_UP = PATH + "Gem_Cage.png";
	static final String PITCHER_DOWN = PATH + "Gem_Cage.png";

	static final String BALL_1 = PATH + "Ball-1.png";
	static final String BALL_2 = PATH + "Ball-2.png";
	static final String BALL_3 = PATH + "Ball-3.png";
	static final String BALL_4 = PATH + "Ball-4.png";
	static final String BALL_5 = PATH + "Ball-5.png";
	static final String[] BALL = { BALL_1, BALL_2, BALL_3, BALL_4, BALL_5 };

	static final int FPS = 25;
}
