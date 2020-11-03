import java.awt.Color;

/**
 * This class represents a pointed-top Hexagon chamber used to make up a
 * <code>Dungeon</code> object.
 * <p>
 * Each chamber has a type. It can be a Wall, Start, End, Empty, Popped, Dragon,
 * Lava, Cactus, Start_Processed, Start_Popped, End_Processed, or Pushed
 * chamber. Each chamber type will be a different colour:.
 * <p>
 * Hexagon chambers know about their neighbors (if set using setNeighbor
 * method).
 * <p>
 * The neighbors of a chamber are accessed by an index 0-5 inclusive.
 * <ul>
 * <li>The hexagons are pointed-top in orientation, the 0 index is the
 * upper-right side</li>
 * <li>Indexes for the sides progress incrementally clockwise from the 0 index,
 * to 5 on the upper-left side</li>
 * </ul>
 * Eg.
 * <p>
 * <code>    5 /  \ 0</code>
 * <p>
 * <code>    4 |  | 1</code>
 * <p>
 * <code>    3 \  / 2</code>
 * <p>
 * 
 * @author CS1027
 *
 */
public class Hexagon extends HexComponent {
	private static final long serialVersionUID = 4865976127980106774L;

	// enum to represent available hexagon types
	public static enum HexType {
		WALL, START, END, EMPTY, START_PROCESSED, END_PROCESSED, START_POPPED, PUSHED, POPPED, DRAGON, LAVA, CACTI
	};

	// Attributes
	private HexType type; // Stores the current type of this Hexagon. This value
							// changes as
							// the Hexagons in the board are marked
	private HexType originalType; // Type initially assigned to this Hexagon
	private boolean isStart; // Is this the start?
	private boolean isEnd; // Is this the end?
	private Hexagon[] neighbors; // Stores the hexagons which surround this one
									// on each of 6 sides
	private int timeDelay;

	/**
	 * Create a Hexagon chamber of the specified type
	 * 
	 * @param t
	 *            the HexType to create
	 */
	public Hexagon(HexType t, int delay) {
		timeDelay = delay;
		this.type = t;
		this.originalType = t;
		this.isStart = (t == HexType.START);
		this.isEnd = (t == HexType.END);

		// set the initial color based on the initial type
		this.setColor(this.type);
		// allocate space for the neighbor array
		this.neighbors = new Hexagon[6];
	}

	/**
	 * Set the neighbor for this hexagon using the neighbor index.
	 * 
	 * The index for the neighbor indicates which side of the hexagon this new
	 * neighbor is on. 0-5 inclusive.
	 * 
	 * @param neighbor
	 *            The new Hexagon neighbor
	 * @param i
	 *            The index specifying which side this neighbor is on (0-5
	 *            inclusive)
	 * @throws InvalidNeighbourIndexException
	 *             When an index is specified that is not 0-5 inclusive.
	 */
	public void setNeighbour(Hexagon neighbor, int i) throws InvalidNeighbourIndexException {
		if (0 <= i && i <= 5)
			this.neighbors[i] = neighbor;
		else
			throw new InvalidNeighbourIndexException(i);
	}

	/**
	 * Returns the neighbor for this hexagon using the neighbor index
	 * 
	 * The index for the neighbor indicates which side of the hexagon the
	 * neighbor to get is on. 0-5 inclusive.
	 * 
	 * @param i
	 *            The index indicating the side of the hexagon this neighbor is
	 *            on
	 * @return The hexagon that is on the i-th side of the current hexagon, or
	 *         null if no neighbor
	 * @throws InvalidNeighbourIndexException
	 *             When an index is specified that is not 0-5 inclusive.
	 */
	public Hexagon getNeighbour(int i) throws InvalidNeighbourIndexException {
		if (0 <= i && i <= 5)
			return this.neighbors[i];
		else
			throw new InvalidNeighbourIndexException(i);
	}

	/**
	 * This method checks if the current hexagon is a wall chamber.
	 * 
	 * @return true if this is a wall chamber, false otherwise.
	 */
	public boolean isWall() {
		return type == HexType.WALL;
	}

	/**
	 * This method checks if the current hexagon is an empty chamber.
	 * 
	 * @return true if this chamber is empty, false otherwise.
	 */
	public boolean isEmpty() {
		return type == HexType.EMPTY;
	}

	/**
	 * This method checks if the current hexagon has been marked as pushed or
	 * popped.
	 * 
	 * @return true if this chamber has been marked as pushed or popped, false
	 *         otherwise.
	 */

	public boolean isMarked() {
		return (type == HexType.PUSHED) || (type == HexType.POPPED);
	}

	/**
	 * This method checks if the current Hexagon object hosts a dragon. This
	 * means that no adjacent Hexagon object can be in the path to the end
	 * 
	 * @return true if chamber has a dragon, false otherwise.
	 */
	public boolean isDragon() {
		return type == HexType.DRAGON;
	}

	/**
	 * This method checks if the current hexagon is a lava chamber.
	 * 
	 * @return true if this is a lava chamber, false otherwise.
	 */
	public boolean isLava() {
		return type == HexType.LAVA;
	}

	/**
	 * This method checks if the current hexagon is a cacti chamber.
	 * 
	 * @return true if this is a cacti chamber, false otherwise.
	 */
	public boolean isCacti() {
		return type == HexType.CACTI;
	}

	/**
	 * This method checks if the current hexagon is the starting chamber.
	 * 
	 * @return true if this is the starting chamber, false otherwise.
	 */
	public boolean isStart() {
		return this.isStart;
	}

	/**
	 * This method checks if the current hexagon is the exit.
	 * 
	 * @return true if this is the exit, false otherwise.
	 */
	public boolean isExit() {
		return this.isEnd;
	}

	/**
	 * This re-draws the current hexagonal chamber
	 */
	private void reDraw() {
		try {
			Thread.sleep(timeDelay);
		} catch (Exception e) {
			System.err.println("Error while issuing time delay\n" + e.getMessage());
		}
		super.repaint();
	}

	/**
	 * This method marks the chamber as Pushed and updates the chamber's colour
	 */
	public void markPushed() {
		type = HexType.PUSHED;
		setColor(type);
		reDraw();
	}

	/**
	 * This method marks the chamber as popped and updates the chamber's colour
	 */
	public void markPopped() {
		type = HexType.POPPED;
		setColor(this.type);
		reDraw();
	}

	/**
	 * This method marks the exit chamber and updates the chamber's colour
	 */
	public void markExit() {
		this.type = HexType.END_PROCESSED;
		this.setColor(this.type);
		reDraw();
	}

	/**
	 * This method marks the chamber as the starting chamber and updates the
	 * chamber's colour
	 */
	public void markStart() {
		this.type = HexType.START_PROCESSED;
		this.setColor(this.type);
		reDraw();
	}

	/**
	 * Helper method to set the current chamber color based on the type of
	 * chamber.
	 * 
	 * @param t
	 *            The type of the chamber; used to set the color
	 */
	private void setColor(HexType t) {
		switch (t) {
		case WALL:
			this.setBackground(HexColors.WALL);
			break;
		case START:
			this.setBackground(HexColors.START);
			break;
		case END:
			this.setBackground(HexColors.END);
			break;
		case EMPTY:
			this.setBackground(HexColors.UNVISITED);
			break;
		case END_PROCESSED:
			this.setBackground(HexColors.END_PROCESSED);
			break;
		case START_PROCESSED:
			this.setBackground(HexColors.START_PROCESSED);
			break;
		case START_POPPED:
			this.setBackground(HexColors.START_POPPED);
			break;
		case PUSHED:
			if (originalType == HexType.LAVA)
				setBackground(HexColors.LAVA_PUSHED);
			else if (originalType == HexType.CACTI)
				setBackground(HexColors.CACTUS_PUSHED);
			else if (originalType == HexType.START)
				this.setBackground(HexColors.START_PROCESSED);
			else if (originalType == HexType.END)
				this.setBackground(HexColors.END_PROCESSED);
			else
				setBackground(HexColors.PUSHED);
			break;
		case POPPED:
			if (originalType == HexType.LAVA)
				setBackground(HexColors.LAVA_POPPED);
			else if (originalType == HexType.CACTI)
				setBackground(HexColors.CACTUS_POPPED);
			else if (originalType == HexType.START)
				setBackground(HexColors.START_POPPED);
			else
				setBackground(HexColors.POPPED);
			break;
		case DRAGON:
			this.setBackground(HexColors.DRAGON);
			break;
		case LAVA:
			this.setBackground(HexColors.LAVA);
			break;
		case CACTI:
			this.setBackground(HexColors.CACTUS);
			break;
		default:
			this.setBackground(HexColors.WALL);
			break;
		}
		this.setForeground(Color.BLACK);
	}

	public String toString() {
		if (type == HexType.WALL)
			return "Wall chamber, ";
		else if (type == HexType.START || type == HexType.START_PROCESSED || type == HexType.START_POPPED
				|| originalType == HexType.START)
			return "Initial chamber, ";
		else if (type == HexType.END || type == HexType.END_PROCESSED || originalType == HexType.END)
			return "Exit, ";
		else if (type == HexType.EMPTY || originalType == HexType.EMPTY)
			return "Empty chamber, ";
		else if (type == HexType.LAVA || originalType == HexType.LAVA)
			return "Lava chamber, ";
		else if (type == HexType.CACTI || originalType == HexType.CACTI)
			return "Cacti chamber, ";
		else if (type == HexType.DRAGON)
			return "Dragon chamber, ";
		else
			return "";
	}

}