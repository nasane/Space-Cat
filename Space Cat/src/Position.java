/**
 * A class for managing position on the game screen.
 * @author Nathan Bossart
 */
public class Position {

    private int x; // the x coordiante
    private int y; // the y coordinate

    /**
     * A constructor for a Position object.
     * @param xCoord the x-coordinate for the position
     * @param yCoord the y-coordinate for the position
     */
    public Position(int xCoord, int yCoord) {
	this.x = xCoord;
	this.y = yCoord;
    }

    /**
     * A method to check if this Position is within a rectangle defined by the coordinates of another Position.
     * @param otherPos the other Position
     * @return true if this Position fits in the other Position, false otherwise
     */
    public boolean fitsIn(Position otherPos) {
	if (x>=0 && y>=0 && x<=otherPos.getX() && y<=otherPos.getY()) return true;
	return false;
    }

    /**
     * A method for adding Position objects together.
     * @param otherPos the Position to add
     * @return the new Position
     */
    public Position add(Position otherPos) {
	Position ret = new Position(this.getX()+otherPos.getX(), this.getY()+otherPos.getY());
	return ret;
    }

    /**
     * A method for getting the x-coordinate for a Position.
     * @return the x-coordinate
     */
    public int getX() {
	return this.x;
    }

    /**
     * A method for getting the y-coordinate for a Position.
     * @return the y-coordinate
     */
    public int getY() {
	return this.y;
    }
    
    /**
     * Method for determining if a position is close to a door.
     * @param d the door to check
     * @return whether or not the position is considered "in" the door
     */
    public boolean in(Door d) {
    	double ans = ((double) this.getX() - d.getCoords().getX())*((double) this.getX() - d.getCoords().getX());
		ans += ((double) this.getY() - d.getCoords().getY())*((double) this.getY() - d.getCoords().getY());
		ans = Math.sqrt(ans);
    	if (ans<60){
    		return true;
    	}
    	return false;
    }

}
