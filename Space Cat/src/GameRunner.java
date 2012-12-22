import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

/**
 * Main class for game, glues everything together, holds main game loop
 * @author Bob Urberger, Nathan Bossart
 */
public class GameRunner {

	public static JFrame frame;  // JFrame to use for the game screen

	/**
	 * Main class for game, glues everything together.
	 * @param args unused
	 */
	public static void main(String[] args) {

		frame = new JFrame();
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);

		StartMenu start = new StartMenu(frame);

	}

	/**
	 * Holds the main game loop (triggered by pressing Play Game on StartMenu)
	 */
	static ActionListener PlayGame = new ActionListener()
	{
		/**
		 * Class to hold the main game loop (triggered by pressing Play Game button on StartMenu)
		 */
		public void actionPerformed(ActionEvent ae)
		{
			Level lvl = new Level(frame, 1.0);
			//TODO: return to start menu
		}
	};

}
