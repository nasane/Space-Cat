import java.util.ArrayList;
import java.lang.Math;
import java.util.Random;

/**
 * Generates a random labyrinth.
 * @author Joe Mayer
 * 
 * Creates a Level defined by a ArrayList of doors.
 */
public class LevelCreator {

	private ArrayList<Room> Dungeon = new ArrayList<Room>();
	private double num_rooms;

	/**
	 * Constructor for a random game board.
	 * @param gameScreen the JPanel to modify/enhance
	 * @param screenSize size of the game window
	 * @param complexity number from 1 to 10 (1 is simplest, 10 is most complex)
	 */
	public LevelCreator(double complexity) {
		//2^n rooms in the dungeon
		num_rooms =Math.pow(2.00,complexity);
		this.createDungeon();
		this.chooseEnd();
	}
	/**
	 * @param index The index of the Room in the ArrayList.
	 * @return The desired room.
	 */
	public Room getRoom(int index) {
		return Dungeon.get(index);
	}
	/**
	 * @param r_i The Room with the door.
	 * @param d_i The Door you want. 
	 * @return The index the door in the room is pointing to.
	 */
	public int getDoorIndex(int r_i, int d_i) {
		return Dungeon.get(r_i).getDoorIndex(d_i);
	}
	/**
	 * Creates the ArrayList of Rooms.
	 * Creates the Rooms and then creates 4 doors per room.
	 * Sets the coordinates for the doors.
	 * Connects the doors between each of the rooms.
	 */
	private void createDungeon(){
		int half = (int) (num_rooms/2);
		//Add rooms
		for(int x=0;x<num_rooms;x++){
			Dungeon.add(new Room());
			//Make four doors per room.
			for(int n=0;n<4;n++){
				if (n==0){
					Dungeon.get(x).makeDoor(new Position(0,300));
				}
				if (n==1){
					Dungeon.get(x).makeDoor(new Position(400,575));
				}
				if (n==2){
					Dungeon.get(x).makeDoor(new Position(630,300));
				}
				if (n==3){
					Dungeon.get(x).makeDoor(new Position(400,25));
				}
			}
		}
		//Connect the bottom rooms
		for(int x=0;x<(num_rooms/2);x++){
			if(x != (num_rooms/2)-1 && x != 0){
				Dungeon.get(x).setDoorActive(0,true);
				Dungeon.get(x).setDoorIndex(0, (x-1));
				Dungeon.get(x).setDoorActive(2,true);
				Dungeon.get(x).setDoorIndex(2, (x+1));
				Dungeon.get(x).setDoorActive(3,true);
				Dungeon.get(x).setDoorIndex(3, (x+half));
			}
			else if(x==0 && x == (num_rooms/2)-1){
				Dungeon.get(x).setDoorActive(3,true);
				Dungeon.get(x).setDoorIndex(3, (x+1));
			}
			else if(x == (num_rooms/2)-1){
				Dungeon.get(x).setDoorActive(0,true);
				Dungeon.get(x).setDoorIndex(0, (x-1));
				Dungeon.get(x).setDoorActive(3,true);
				Dungeon.get(x).setDoorIndex(3, (x+half));
			}
			else if(x==0){
				Dungeon.get(x).setDoorActive(2,true);
				Dungeon.get(x).setDoorIndex(2, (x+1));
				Dungeon.get(x).setDoorActive(3,true);
				Dungeon.get(x).setDoorIndex(3, (x+half));
			}

		}
		//Connect the top rooms
		for(int x= (int) (num_rooms/2) ;x< num_rooms;x++){
			if(x != (num_rooms)-1 && x != (num_rooms/2)){
				Dungeon.get(x).setDoorActive(0,true);
				Dungeon.get(x).setDoorIndex(0, (x-1));
				Dungeon.get(x).setDoorActive(1,true);
				Dungeon.get(x).setDoorIndex(1, (x-half));
				Dungeon.get(x).setDoorActive(2,true);
				Dungeon.get(x).setDoorIndex(2, (x+1));
			}
			else if(x==(num_rooms/2) && x == (num_rooms)-1){
				Dungeon.get(x).setDoorActive(1,true);
				Dungeon.get(x).setDoorIndex(1, (x-1));
			}
			else if(x == (num_rooms)-1){
				Dungeon.get(x).setDoorActive(0,true);
				Dungeon.get(x).setDoorIndex(0, (x-1));
				Dungeon.get(x).setDoorActive(1,true);
				Dungeon.get(x).setDoorIndex(1, (x-half));
			}
			else if(x==(num_rooms/2)){
				Dungeon.get(x).setDoorActive(1,true);
				Dungeon.get(x).setDoorIndex(1, (x-half));
				Dungeon.get(x).setDoorActive(2,true);
				Dungeon.get(x).setDoorIndex(2, (x+1));
			}

		}

	}
	/**
	 * Randomly make one of the outer doors
	 * the end of level door.
	 */
	private void chooseEnd(){
		Random generator1 = new Random();
		int rand_door=0;
		int n_rooms = (int) (num_rooms);
		int rand_room = generator1.nextInt(n_rooms);
		Random generator2 = new Random();
		if(rand_room < (num_rooms/2)){
			if(rand_room != (num_rooms/2)-1 && rand_room != 0){
				rand_door = 1;
			}
			else if(rand_room==0 && rand_room== (num_rooms/2)-1){
				rand_door = generator2.nextInt(2);
			}
			else if(rand_room == (num_rooms/2)-1){
				rand_door = generator2.nextInt(2) + 1;
			}
			else if(rand_room==0){
				rand_door = generator2.nextInt(2);
			}
		}
		else{
			if(rand_room != (num_rooms)-1 && rand_room != (num_rooms/2)){
				rand_door = 3;
			}
			else if(rand_room==(num_rooms/2) && rand_room== (num_rooms)-1){
				rand_door = 3;
			}
			else if(rand_room == (num_rooms)-1){
				rand_door = generator2.nextInt(1)+2;
			}
			else if(rand_room ==(num_rooms/2)){
				rand_door = 0;
			}		
		}
		Dungeon.get(rand_room).setDoorActive(rand_door,true);
		Dungeon.get(rand_room).setEndDoor(rand_door);
		Dungeon.get(rand_room).setDoorIndex(rand_door, -2);
		System.out.println(rand_room + " " + rand_door);
	}
	/**
	 * Clear the ArrayList of Rooms.
	 */
	public void clear(){
		Dungeon.clear();
	}

}
