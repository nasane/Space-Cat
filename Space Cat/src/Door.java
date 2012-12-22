/**
 * A Door class.
 * @author Joe Mayer
 */
public class Door {
	
	private boolean active;   // whether or not the door can be passed through
	private int index;        // door index (indicates which room it leads to)
	private Position coords;  // position of the door
	private boolean end;      // whether or not the door is the end of the level
	
	/**
	 * Constructor for a door object.
	 * @param p the position of the door
	 */
	public Door(Position p){
		coords = p;
		}
	
	/**
	 * Constructor for a door object.
	 * @param a whether or not the door is active
	 * @param i the door index (indicates which room it leads to)
	 * @param c position of the door
	 */
	public Door(boolean a, int i, Position c){
		this.active=a;
		this.index=i;
		this.coords = c;
	}
	
	/**
	 * Method for determining if a door can be passed through.
	 * @return whether or not the door can be passed through
	 */
	public boolean getActive(){
		return this.active;
	}
	
	/**
	 * Method for retrieving the door index (indicates which room it leads to).
	 * @return the room to which the door leads to
	 */
	public int getIndex(){
		return this.index;
	}
	
	/**
	 * Method for obtaining the coordinates of the door on the screen.
	 * @return the current position of the door
	 */
	public Position getCoords(){
		return this.coords;
	}
	
	/**
	 * Method for setting a door as active or inactive (able to be passed through).
	 * @param a whether or not the door should be able to be passed through
	 */
	public void setActive(boolean a){
		this.active = a;
	}
	
	/**
	 * Method for setting the door index (which room it leads to).
	 * @param i the index to assign to the door
	 */
	public void setIndex(int i){
		this.index = i;
	}
	
	/**
	 * Method for assigning the door a position.
	 * @param c the position to assign to the door
	 */
	public void setCoords(Position c){
		this.coords = c;
	}
	
	/**
	 * Method for setting the door as an end-of-level door.
	 */
	public void setAsEnd(){
		this.end = true;
	}
	
	/**
	 * Method for determining if the door is the end-of-level door.
	 * @return whether or not the door is the end-of-level door
	 */
	public boolean isEnd(){
		return this.end;
	}

}