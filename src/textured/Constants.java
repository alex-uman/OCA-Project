package textured;

import java.awt.Toolkit;

public class Constants {

	static final int MARGIN_X = 24;
	static final int MARGIN_Y = 39;

	static final int DIM_Y = Toolkit.getDefaultToolkit().getScreenSize().height - 50;

	static final int PITCHER_MOVE_DISTANCE = (int) (DIM_Y / 50);

	static final int MOVE_DISTANCE = (int) (DIM_Y / 100);

	static final int BORDER_THICK = (int) (DIM_Y / 250);

	static final int BRICK_WIDTH = (int) (((DIM_Y / 1.6) - BORDER_THICK * 2) / 10);
	static final int BRICK_HEIGTH = (int) (BRICK_WIDTH * 0.6);

	static final int DIM_X = BRICK_WIDTH * 10 + BORDER_THICK * 2;

	static final int BULLET_WIDTH = (int) (DIM_X / 25);
	static final int BULLET_HEIGTH = BULLET_WIDTH;

	static final int PITCHER_WIDTH = (int) (DIM_X / 5);
	static final int PITCHER_HEIGTH = (int) (PITCHER_WIDTH / 5);

	static final int BULLET_DELAY = (int) (DIM_Y / 200);

	static final String PATH = "src\\";

	static final String BRICK_BLUE = PATH + "Gem_Blue.png";
	static final String BRICK_GREEN = PATH + "Gem_Green.png";
	static final String BRICK_MAGENTA = PATH + "Gem_Magenta.png";
	static final String BRICK_ORANGE = PATH + "Gem_Orange.png";
	static final String BRICK_PINK = PATH + "Gem_Pink.png";
	static final String BRICK_CAGE = PATH + "Gem_Cage.png";

}
