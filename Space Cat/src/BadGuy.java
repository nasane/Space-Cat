/**
 * A class for creating Bad Guys.
 * @author Nathan Bossart
 */
public class BadGuy extends Character {

	/**
	 * Basic constructor for bad guys.
	 * @param startingHP initial health of bad guy
	 * @param startingStamina initial stamina of bad guy
	 * @param startingCoords initial coordinates of bad guy
	 * @param maxHP maximum health of bad guy
	 * @param maxStamina maximum stamina of bad guy
	 * @param spriteID index of representative image in sprite array
	 */
	public BadGuy(int startingHP, int startingStamina, Position startingCoords,
			int maxHP, int maxStamina, int spriteID) {
		super(startingHP, startingStamina, startingCoords, maxHP, maxStamina, spriteID);
	}

}