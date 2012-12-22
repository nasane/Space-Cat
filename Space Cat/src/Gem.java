/**
 * Class for special objects that the user can pick up.
 * @author Nathan Bossart
 */
public class Gem {
	
	private Position coords;   // current gem coordinates
	private int      gemType;  // number 1-5, defines the effects of using the gem
    private int      spriteID;
    private boolean  used;
	
	/**
	 * Constructor for a gem.
	 * Gem 1 - Adds a low amount of health.
	 * Gem 2 - Adds a high amount of health.
	 * Gem 3 - Adds a low amount of stamina.
	 * Gem 4 - Adds a high amount of stamina.
	 * Gem 5 - Boosts character to max health and stamina.
	 * @param gemType number 1-5 for the type of gem
	 * @param coord location of the gem on screen
     * @param spriteID Index of gem image in sprite lookup table
	 */
	public Gem(int gemType, Position coords, int spriteID) {
		this.coords  = coords;
		this.gemType = gemType;
        this.spriteID = spriteID;
        used = false;
	}
	
	/**
	 * Applies gem effects to playable character and eliminates gem from screen.
	 * @param gg playable character to apply gem effects
	 */
	public void applyEffects(GoodGuy gg) {
		if (gemType == 1) {
			gg.addHP(20);
		} else if (gemType == 2) {
			gg.addHP(50);
		} else if (gemType == 3) {
			gg.addStamina(20);
		} else if (gemType == 4) {
			gg.addStamina(50);
		} else {
			int mhp = gg.getMaxHP();
			int mst = gg.getMaxHP();
			gg.addHP(mhp);
			gg.addStamina(mst);
		}
        used = true;
	}
	
	/**
	 * Method for obtaining the position of the gem.
	 * @return the position of the gem
	 */
	public Position getPos() {
		return coords;
	}
	
	/**
     * Method for determining if a gem is visible on the screen.
     * @param screenRes a position representing the maximum resolution of the game screen
     * @return the current visibility status of the gem
     */
    public boolean isVisible(Position screenRes) {
        if (coords.fitsIn(screenRes)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * A method for getting the sprite location for this gem 
     * @return the sprite location
     */
    public int getSpriteID() {
        return spriteID;
    }

    /**
     * Method for determining if a gem has been used.
     * @return whether or not the gem has been used
     */
    public boolean isUsed() {
        return used;
    }

    /**
     * Method for setting a gem as used.
     */
    public void setUsed() {
        used = true;
    }
	
}
