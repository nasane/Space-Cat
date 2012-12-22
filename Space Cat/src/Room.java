import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;

/**
 * Creates a Room with four Doors.
 * @author Joe Mayer
 */

public class Room extends JPanel{

	private ArrayList<Door> Doors = new ArrayList<Door>();
	private ArrayList<Character> BadGuys = new ArrayList<Character>();
    private ArrayList<Gem> gems = new ArrayList<Gem>();
    private Random rand = new Random();
    private int numBadGuys;
	
	/**
	 * Constructs a generic room. 
	 * With random enemies and gems.
	 */
	public Room(){
		final BadGuy bg1 = new BadGuy(30,30, randomPos(), 40, 40, 1);
        final BadGuy bg2 = new BadGuy(30, 30, randomPos(), 40, 40, 2);
        final BadGuy bg3 = new BadGuy(30, 30, randomPos(), 40, 40, 3);
        final BadGuy bg4 = new BadGuy(60, 30, randomPos(), 40, 40, 4);
        Gem gem1 = new Gem(rand.nextInt(4) + 1, randomPos(), 5);
        Gem gem2 = new Gem(rand.nextInt(4) + 1, randomPos(), 6);
	    numBadGuys = rand.nextInt(3);
        System.out.println(numBadGuys + " = numBadGuys");
        if (numBadGuys >= 0) {
            BadGuys.add(bg1);
        }
        if (numBadGuys >= 1) {
            BadGuys.add(bg2);
        }
        if (numBadGuys >= 2) {
            BadGuys.add(bg3);
        }
        if (numBadGuys == 3) {
            BadGuys.add(bg4);
        }
        gems.add(gem1);
        gems.add(gem2);
	}
	
	/**
	 * Checks whether or not a character is in a door.
	 * @param c The Character to check.
	 * @return The index that the door points to if its active
	 * else -1;
	 */
	public int isInActiveDoor(Character c) {
		Position p = c.getPos();
		for (Door d : Doors) {
			if (p.in(d) && d.getActive()) {
				return d.getIndex();
			}
		}
		return -1;
	}
	/**
	 * Method for obtaining the bad guys.
	 * @return The arraylist of BadGuys.
	 */ 
	public ArrayList<Character> getBadGuys() {
		return BadGuys;
	}

	/**
	 * Method for obtaining the gems.
	 * @return The arraylist of Gems.
	 */
    public ArrayList<Gem> getGems() {
        return gems;
    }

    /**
     * Method for removing a bad guy
     * @param bg character to remove
     */
    public void removeBadGuy(Character bg) {
        if (BadGuys.indexOf(bg) != -1)
            BadGuys.get(BadGuys.indexOf(bg)).setDead();
    }

    /**
     * Method for removing a gem.
     * @param g the gem to remove.
     */
    public void removeGem(Gem g) {
        gems.get(gems.indexOf(g)).setUsed();
    }

	/**
	 * 	Constructs a door and adds it to the ArrayList of doors.
	 * @param p  Coordinates of the door.
	 */
	public void makeDoor(Position p){
		Door D = new Door(p);
		Doors.add(D);
	}
	/**
	 *  
	 * @param index The door number.
	 * @return True or false depending if the door is 
	 * active or not.
	 */
	public boolean getDoorActive(int index){
		return Doors.get(index).getActive();
	}
	/**
	 * 
	 * @param index The door number.
	 * @return The room number that the door points to.
	 */
	public int getDoorIndex(int index){
		return Doors.get(index).getIndex();
	}
	/**
	 * 
	 * @param index The door number.
	 * @return The coordinates of the door.
	 */
	public Position getCoords(int index){
		return Doors.get(index).getCoords();
	}
	/**
	 * 
	 * @param index The door number.
	 * @param a Boolean for whether the door is active.
	 */
	public void setDoorActive(int index, boolean a){
		Doors.get(index).setActive(a);
	}
	/**
	 * 
	 * @param index The door number.
	 * @param i The room for the door to lead to.
	 */
	public void setDoorIndex(int index, int i){
		Doors.get(index).setIndex(i);
	}
	/**
	 * 
	 * @param index The door number.
	 * @param c The coordinates to set the door at.
	 */
	public void setDoorCoords(int index, Position c){
		Doors.get(index).setCoords(c);
	}
	/**
	 * 
	 * @param index The door number to set as the end door.
	 */
	public void setEndDoor(int index){
		Doors.get(index).setAsEnd();
	}
	/**
	 * 
	 * @param index The index to compare to.
	 * @return Returns the number of the door that
	 * leads to the room indicated by the index.
	 */
	public int getDoorNumber(int index){
		int temp;
		for(int n =0;n<4;n++){
			temp = Doors.get(n).getIndex();
			if(temp == index){
				return n;
			}
		}
		return -1;
	}

	/**
	 * Method for generating a random position.
	 * @return randomly generated position.
	 */
    private Position randomPos() {
        int x = rand.nextInt(800);
        int y = rand.nextInt(600);
        return new Position(x, y);
    }
}
