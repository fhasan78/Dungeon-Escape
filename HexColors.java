import java.awt.Color;

public class HexColors {
	public static final Color WALL = Color.BLACK;
	public static final Color START = new Color(250, 250, 0);
	public static final Color END = Color.BLUE;
	public static final Color UNVISITED = new Color(100, 255, 100);
	public static final Color PUSHED = Color.CYAN;
	public static final Color END_PROCESSED = new Color(0, 190, 190);
	public static final Color START_PROCESSED = new Color(200, 255, 255);
	public static final Color START_POPPED = new Color(50, 255, 255);
	public static final Color POPPED = Color.LIGHT_GRAY;
	public static final Color DRAGON = Color.YELLOW;
	public static final Color LAVA = Color.RED;
	public static final Color CACTUS = Color.PINK;
	public static final Color LAVA_PUSHED = Color.RED.darker();
	public static final Color CACTUS_PUSHED = Color.PINK.darker();
	public static final Color LAVA_POPPED = Color.RED.brighter();
	public static final Color CACTUS_POPPED = Color.PINK.brighter();

	public Color gradientColor(Color c) {
		if (c == WALL)
			return Color.DARK_GRAY.brighter();
		else if (c == PUSHED)
			return c.darker().darker();
		else if (c == END_PROCESSED)
			return new Color(0, 0, 180);
		else if (c == START_PROCESSED)
			return Color.CYAN.darker();
		else if (c == START_POPPED)
			return Color.GRAY;
		return c.darker();
	}

	public Color initialGradient(Color c) {
		if (c == END_PROCESSED)
			return new Color(150, 250, 250);
		else if (c == START_PROCESSED)
			return new Color(250, 250, 80);
		else if (c == START_POPPED)
			return new Color(250, 250, 80);
		else if (c == END)
			return new Color(110, 110, 250);
		else
			return c;
	}
}
