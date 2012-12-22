import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

/**
 * Class for managing the levels.
 * @author Nathan Bossart
 */
public class Level {

	private Renderer roomrenderer;  // renderer to use

	/**
	 * Constructor for a Level.
	 * @param frame the game screen
	 * @param levelNum the level complexity
	 */
	public Level(JFrame frame, double levelNum) {

		frame.getContentPane().removeAll();
		frame.setTitle("Level 1");
		final GoodGuy  playableCharacter = new GoodGuy(100, 100, new Position(50,275), 120, 120, 0);

		BufferedImage protagonist = null, eyeGuy = null, wormDude = null, gem1 = null, gem2 = null; 

		try {
			protagonist = ImageIO.read(new File("./images/SpaceCatRIGHT1.png"));
            eyeGuy = ImageIO.read(new File("./images/EnemyEYE.png"));
            wormDude = ImageIO.read(new File("./images/EnemyLEFT.png"));
            gem1 = ImageIO.read(new File("./images/Diamond.png"));
            gem2 = ImageIO.read(new File("./images/GemGREEN.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Image[] sprites = new Image[7];
		sprites[0] = protagonist;
        sprites[1] = eyeGuy;
        sprites[2] = eyeGuy;
        sprites[3] = wormDude;
        sprites[4] = wormDude;
        sprites[5] = gem1;
        sprites[6] = gem2;
		
		roomrenderer = new Renderer(sprites, playableCharacter, levelNum, frame);
        roomrenderer.updateRoom();

		frame.add(roomrenderer);
		frame.pack();
		frame.validate();
		frame.repaint();

	}
}
