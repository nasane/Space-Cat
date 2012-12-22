import java.lang.Math;

/** 
 * A class for a character in the game.
 * @author Nathan Bossart
 */
public class Character {

	private int          hp;          // current health points
	private boolean      alive;       // whether or not it is alive
	private int          stamina;     // stamina points
	private Position     coords;      // current coordinates
	private int          maxHP;       // maximum possible health points
	private int          maxStamina;  // maximum possible stamina points
	private int          spriteID;    // Array index of sprite image
	private int          dx, dy;      // Velocity values

	/**
	 * Constructor for a Character.
	 * @param startingHP starting health level
	 * @param startingStamina starting stamina level
	 * @param startingCoords starting coordinates
	 * @param maxHP maximum health points
	 * @param maxStamina maximum stamina points
	 */
	public Character(int startingHP, int startingStamina, Position startingCoords,
			int maxHP, int maxStamina, int spriteID)   {
		this.hp         = startingHP;
		this.stamina    = startingStamina;
		this.coords     = startingCoords;
		this.alive      = true;
		this.maxHP      = maxHP;
		this.maxStamina = maxStamina;
		this.spriteID   = spriteID;
	}

	/**
	 * Method for deducting health points from a character.
	 * @param hitPoints number of points to deduct
	 */
	public void deductHP(int hitPoints) {
		hp -= hitPoints;
		if (hp <= 0) {
			alive = false;
		}
	}

	/** 
	 * Method for adding health points to a character.
	 * @param healthPoints number of points to add
	 */
	public void addHP(int healthPoints) {
		int temp = hp + healthPoints;
		if (temp > maxHP) {
			hp = maxHP;
		} else {
			hp = temp;
		}
	}

	/** 
	 * Method for retrieving the current health status of a character.
	 * @return the current number of health points for the character
	 */
	public int getHP() {
		return hp;
	}

	/** 
	 * Method for checking the life status of a character.
	 * @return the current living status of the character
	 */
	public boolean isAlive() {
		return alive;
	}

	/**
	 * Method for killing a character.
	 */
    public void setDead() {
        alive = false;
    }

	/** 
	 * Method for moving a character.
	 */
	public void move() {
		Position newPos = new Position(dx, dy);
		coords = coords.add(newPos);
	}

	/**
	 * Method for determining if a character is visible on the screen.
	 * @param screenRes a position representing the maximum resolution of the game screen
	 * @return the current visibility status of the character
	 */
	public boolean isVisible(Position screenRes) {
		if (coords.fitsIn(screenRes)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Method for retrieving the current coordinates of the character.
	 * @return the character's current coordinates
	 */
	public Position getPos() {
		return coords;
	}

	/** Method for retreving the current stamina level for the character.
	 * @return the character's current stamina level
	 */
	public int getStamina() {
		return stamina;
	}

	/** Method for reducing the stamina level of the character.
	 * @param hitPoints the number of stamina points to deduct from the character
	 */
	public void deductStamina(int hitPoints) {
		int temp = stamina - hitPoints;
		if (temp < 0) {
			stamina = 0;
		} else {
			stamina = temp;
		}
	}

	/** Method for adding points to the stamina level of the character.
	 * @param staminaPoints the number of points to add to the character's stamina
	 */
	public void addStamina(int staminaPoints) {
		int temp = stamina + staminaPoints;
		if (temp > maxStamina) {
			stamina = maxStamina;
		} else {
			stamina = temp;
		}
	}

	/** Method for determing whether or not the character can attack.
	 * @return boolean value of whether or not the character can attack
	 */
	public boolean canAttack() {
		if (stamina > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Returns the array index of this characters sprite in the renderer sprite table
	 * MAKE SURE SPRITE ID IS SET CORRECTLY
	 * @return The global spriteID for this character
	 */
	public int getSpriteID() {
		return spriteID;
	}

	/**
	 * Returns the max health points the character can hold.
	 * @return the max health points the character can hold
	 */
	public int getMaxHP() {
		return maxHP;
	}

	/**
	 * Returns the max stamina points the character can hold.
	 * @return the max stamina points the character can hold
	 */
	public int getMaxStamina() {
		return maxStamina;
	}

	/**
	 * Returns whether or not the character is a non-playable character.
	 * @return whether or not the entity is a non-playable character
	 */
	public boolean isNPC() {
		return true;         // Note: This function is overloaded for the GoodGuy class.
	}

	/**
	 * Sets the rate at which the character moves horizontally.
	 * @param val the rate at which the character moves horizontally
	 */
	public void setdx(int val) {
		dx = val;
	}

	/**
	 * Sets the rate at which the character moves vertically.
	 * @param val the rate at which the character moves vertically
	 */
	public void setdy(int val) {
		dy = val;
	}

	/**
	 * Sets the movement rates of the character towards another character.
	 * @param player the character to move towards
	 */
	public void moveTowards(Character player) {
		if (Math.abs(player.getPos().getX()-this.getPos().getX()) > Math.abs(player.getPos().getY()-this.getPos().getY())) {
			if (player.getPos().getX()>getPos().getX()) {
				setdx(1);
			} else if (player.getPos().getX()<getPos().getX()) {
				setdx(-1);
			} else {
				setdx(0);
			}
		} else {
			if (player.getPos().getY()>getPos().getY()) {
				setdy(1);
			} else if (player.getPos().getY()<getPos().getY()) {
				setdy(-1);
			} else {
				setdy(0);
			}
		}
	}

	/**
	 * Sets the movment rates of the character to 0.
	 */
	public void stop() {
		setdx(0);
		setdy(0);
	}

	/**
	 * Method for finding the linear distance to another character.
	 * @param other the other character to compute distance from
	 * @return the distance between the characters
	 */
	public double lengthTo(Character other) {
		double ans = ((double) this.getPos().getX() - other.getPos().getX())*((double) this.getPos().getX() - other.getPos().getX());
		ans += ((double) this.getPos().getY() - other.getPos().getY())*((double) this.getPos().getY() - other.getPos().getY());
		ans = Math.sqrt(ans);
		return ans;
	}

	/**
	 * Method for finding the linear distance to a Gem.
	 * @param gem The gem to compute the distance to
	 * @return the distance between the character and the gem
	 */
	public double lengthTo(Gem gem) {
		double ans = ((double) this.getPos().getX() - gem.getPos().getX())*((double) this.getPos().getX() - gem.getPos().getX());
		ans += ((double) this.getPos().getY() - gem.getPos().getY())*((double) this.getPos().getY() - gem.getPos().getY());
		ans = Math.sqrt(ans);
		return ans;
	}

	/**
	 * Method for reversing the current movement of the character.
	 */
	public void reverse() {
		dx = -dx;
		dy = -dy;
	}

	/**
	 * Method for explicitly changing the horizontal positioning of the character.
	 * @param x the horizontal position to change to
	 */
	public void setX(int x) {
		coords = new Position(x, coords.getY());
	}

	/**
	 * Method for explicitly changing the vertical positioning of the character.
	 * @param y the vertical position to change to
	 */
	public void setY(int y) {
		coords = new Position(coords.getX(), y);
	}
}
