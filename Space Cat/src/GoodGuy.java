/**
 * A class for creating Good Guys.
 * @author Nathan Bossart
 */
public class GoodGuy extends Character {
	/**
	 * Basic constructor for a GoodGuy character.
	 */
	public GoodGuy(int startingHP, int startingStamina,
			Position startingCoords, int maxHP, int maxStamina, int spriteID) {
		super(startingHP, startingStamina, startingCoords, maxHP, maxStamina, spriteID);
	}

	/**
	 * Class that allows GoodGuy to pickup Gem and be affected by it.
	 * @param gem the gem to pick up
	 */
	public void pickUp(Gem gem) {
		gem.applyEffects(this);
	}
	
	/**
     * Returns whether or not the character is a non-playable character.
     * @return whether or not the entity is a non-playable character
     */
	public boolean isNPC() {
		return false;
	}

}
