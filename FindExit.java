
/**
 * @author Fatima Hasan 
 * 
 * CS1027 - Assignmment 2
 * This class will take a file name as a parameter and finds the exit to the dungeon provided.
 */
import java.io.FileNotFoundException;
import java.io.IOException;

public class FindExit {

	private Dungeon dungeon;

	/**
	 * This is the constructor for the FindExit class, it receives as input the
	 * name of the file containing the description of the dungeon.
	 * 
	 * @param filename
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws InvalidDungeonCharacterException
	 */
	public FindExit(String filename) throws InvalidDungeonCharacterException, FileNotFoundException, IOException {

		dungeon = new Dungeon(filename);
	}

	/**
	 * This main method will create an object of the class FindExit using the
	 * constructor. This method will also print a message saying whether the
	 * exit was found or not, and if the exit was found, the number of chambers
	 * in the path will be printed,too
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		FindExit chambers = null;

		try {
			if (args.length < 1) {
				throw new IllegalArgumentException("Please provide a file as a command line argument");
			} else {
				String dungeonFileName = args[0];
				chambers = new FindExit(dungeonFileName);
			}
			ArrayStack<Hexagon> dungeonStack = new ArrayStack<Hexagon>();
			Dungeon startingChamber = chambers.dungeon;
			startingChamber.start.markStart();
			dungeonStack.push(startingChamber.start);// so that the stack is not
														// empty once it goes
														// into
														// the while loop.
			int count = 0;// to count the number of chambers in the path.
			while (!dungeonStack.isEmpty() && true) {
				Hexagon currentChamber;
				currentChamber = dungeonStack.peek();
				if (currentChamber.isExit()) {
					currentChamber.markExit();
					System.out.println("The exit is found!");
					System.out.println("The dungeon stack size is " + dungeonStack.size()
							+ " and the number of chambers in the path is  " + (count + 1));
					break; // to exit the loop

				}
				if (adjacentToDragon(currentChamber)) {
					dungeonStack.pop();
					currentChamber.markPopped();

				} else {
					Hexagon nextChamber = bestChamber(currentChamber);
					if (nextChamber == null) {
						currentChamber.markPopped();
						dungeonStack.pop();

					} else {
						nextChamber.markPushed();
						dungeonStack.push(nextChamber);

					}
					count++;
				}

			}
			if (dungeonStack.isEmpty()) {
				System.out.println(" The exit is not found!");
			}
			// catching exceptions
		} catch (InvalidDungeonCharacterException e) {
			System.out.println("There is an invalid dungeon character in the file, please try again!");
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			System.out.println("The file was not found, please try again!");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Invalid Input/Output file, please try again!");
			e.printStackTrace();
		}

	}

	/**
	 * The adjacentToDragon(Hexagon chamber) method returns a boolean saying if
	 * the current chamber is adjacent to a dragon's lair.
	 * 
	 * @param chamber
	 *            - where the warrior currently is.
	 * @return a boolean
	 */
	private static boolean adjacentToDragon(Hexagon chamber) {
		for (int i = 0; i < 6; i++) {
			Hexagon neighbourChamber = chamber.getNeighbour(i);
			if (neighbourChamber != null && neighbourChamber.isDragon()) {
				return true;
			}
		}
		return false;

	}

	/**
	 * The method bestChamber returns the best chamber for the warrior to move
	 * to from the current one & if nothing was found returns null. The warrior
	 * will prefer the empty chamber to the cacti and lava, and the cacti to the
	 * lava, and if any were adjacent to the dragon chamber, he can't go to that
	 * chamber.
	 * 
	 * @param chamber
	 *            - where the warrior currently is.
	 * 
	 * @return the best chamber for the warrior to move to.
	 */
	private static Hexagon bestChamber(Hexagon chamber) {
		Hexagon bestChamber = null; // starting with null because if nothing is
									// found this is what will be returned.
		for (int i = 0; i < 6; i++) { // neighboring chambers are numbered from
										// 0 to 5.
			Hexagon neighbourChamber = chamber.getNeighbour(i);
			if (neighbourChamber != null && !neighbourChamber.isMarked() && !neighbourChamber.isDragon()) {
				if (neighbourChamber.isExit()) {
					bestChamber = neighbourChamber;
					return bestChamber;
				}
				if (neighbourChamber.isEmpty()) {
					bestChamber = neighbourChamber;

				} else if (neighbourChamber.isCacti() && !neighbourChamber.isEmpty()
						&& (bestChamber == null || bestChamber.isLava())) {
					bestChamber = neighbourChamber;

				} else if (neighbourChamber.isLava() && !neighbourChamber.isEmpty() && !neighbourChamber.isCacti()
						&& bestChamber == null) {
					bestChamber = neighbourChamber;

				}
			}
		}
		return bestChamber;
	}
}
