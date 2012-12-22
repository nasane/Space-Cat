import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Class for rendering game sprites to a JPanel
 * Requires a JFrame to call home
 * @author Bob Urberger
 */
public class Renderer extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	private final       GoodGuy  player;
	private final       JFrame   frame;
	private int                  gemint;
    private final       JFrame   indicator = new JFrame();
	private ArrayList<Character> onScreen; 
    private ArrayList<Gem>       roomGems;
	private Image[]              sprites;
	private Position             pos;
	private Timer                timer;
	private LevelCreator         level;
	private Room                 r;
	private int                  currentroom;
	private double               levelNum;
	private int                  monint;
	private int                  roomNum;
    private BufferedImage        dead, ggLeft, ggRight, ggAttLeft, ggAttRight, eyeGuy, wormDude;
	private GoodGuy              p;
	private boolean              leftLooking = false;
    private boolean              attackOnce  = false;
    final   JLabel               health      = new JLabel("  HP: ");
	final   JLabel               gemCount    = new JLabel("  Gems Picked Up: ");
	final   JLabel               monCount    = new JLabel("  Monsters Killed: ");
	JLabel                       roomNumL    = new JLabel("  Currently in room ");
	int                          roomListenerValue;
	
	/**
	 * Creates a Renderer object which keeps track of all
	 * visible sprites and draws them as a JPanel.
	 * @param sprites array of sprite images to be drawn by the renderer
	 * @param p the playable character
	 * @param l the level complexity
	 * @param frame the game screen
	 */
	public Renderer(final Image[] sprites, final GoodGuy p, double l, final JFrame frame) {
		this.frame = frame;
		this.createLevel(l);
		levelNum = l;
		r = level.getRoom(0);
		//this.addCharList(r.getBadGuys());
		roomListenerValue = -1;
		currentroom = 0;
		this.p = p;
		player = p;
		this.gemint =0; //= gems;
		this.monint =0; //=numMons;
		this.roomNum = 0;
		// Set the background
		BufferedImage bg = null;
		
		indicator.setSize(150,100);
		JPanel healthmeter = new JPanel();
		health.setText("  HP: "+p.getHP());
		gemCount.setText("  Gems Picked Up: "+gemint);
		monCount.setText("  Monsters Killed: "+monint);
		roomNumL.setText("  Currently in room "+roomNum);
		healthmeter.setLayout(new GridLayout(4,1));
		healthmeter.setSize(150,100);
		healthmeter.add(health);
		healthmeter.add(gemCount);
		healthmeter.add(monCount);
		healthmeter.add(roomNumL);
		indicator.add(healthmeter);
		indicator.setVisible(true);
		indicator.setResizable(false);
		frame.setAlwaysOnTop(true);


        // Commonly used images
        try {
			bg = ImageIO.read(new File("images/BackGround.png"));
            dead = ImageIO.read(new File("./images/EnemySplat.png"));
            ggLeft = ImageIO.read(new File("./images/SpaceCatLEFT1.png"));
            ggRight = ImageIO.read(new File("./images/SpaceCatRIGHT1.png"));
            ggAttLeft = ImageIO.read(new File("./images/SpaceCatKickLEFT.png"));
            ggAttRight = ImageIO.read(new File("./images/SpaceCatKickRIGHT.png"));
            eyeGuy = ImageIO.read(new File("./images/EnemyEYE.png"));
            wormDude = ImageIO.read(new File("./images/EnemyLEFT.png"));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
		
		JLabel bglabel = new JLabel(new ImageIcon(bg));
		add(bglabel);

		this.sprites  = sprites;
		onScreen = new ArrayList<Character>();
        roomGems = new ArrayList<Gem>();

		add(player);

		setDoubleBuffered(true);

		timer = new Timer(5, this);
		timer.start();

		ActionMap actionMap = getActionMap();
		InputMap inputMap = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);

		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "left_press");
		actionMap.put("left_press", new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
                sprites[0] = ggLeft;
                leftLooking = true;
                player.setdx(-2);
			}
		});

		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "right_press");
		actionMap.put("right_press", new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
                sprites[0] = ggRight;
                player.setdx(2);
                leftLooking = false;
			}
		});

		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "up_press");
		actionMap.put("up_press", new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
                player.setdy(-2);
			}
		});

		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "down_press");
		actionMap.put("down_press", new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
                player.setdy(2);
			}
		});

		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, true), "left_release");
		actionMap.put("left_release", new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				player.setdx(0);
			}
		});

		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, true), "right_release");
		actionMap.put("right_release", new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				player.setdx(0);
			}
		});

		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, true), "up_release");
		actionMap.put("up_release", new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				player.setdy(0);
			}
		});

		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, true), "down_release");
		actionMap.put("down_release", new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				player.setdy(0);
			}
		});
		
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "space_press");
		actionMap.put("space_press", new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				if (leftLooking) {
                    sprites[0] = ggAttLeft;
				} else {
                    sprites[0] = ggAttRight;
				}
				if (!attackOnce) {
                    for (Character c : onScreen) {
                        if (c.isNPC() && player.lengthTo(c)<25) {
                        	boolean wasAlive = false;
                        	if (c.isAlive()) {
                        		wasAlive = true;
                        	}
                            c.deductHP(15);
                            if (!c.isAlive() && wasAlive) {
                            	++monint;
                            	monCount.setText("  Monsters Killed: "+monint);
                            	indicator.repaint();
                            }
                        }
                    }
                    attackOnce = true;
                }
				
                for (Gem g : roomGems) {
                    if (player.lengthTo(g) < 75) {
                        if (!g.isUsed()) {
                            g.applyEffects(player);
                            ++gemint;
                            gemCount.setText(" Gems picked up: " + gemint);
                            indicator.repaint();
                        }
                    }
                }
			}
		});
		
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true), "space_release");
		actionMap.put("space_release", new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				if (leftLooking) {
                    sprites[0] = ggLeft;
				} else {
                    sprites[0] = ggRight;
				}
                attackOnce = false;
				
			}
		});
		
	}

	/**
	 * Adds a single character to the draw list
	 * @param ch Character to add
	 */
	public void add(Character ch) {
		onScreen.add(ch);
	}

	/**
	 * Adds a single gem to the draw list
	 * @param g Gem to add
	 */
    public void addGem(Gem g) {
        roomGems.add(g);
    }

    /**
     * Adds mutiple gems to the draw list
     * @param gemList the list of gems to add
     */
    public void addGemList(Collection<Gem> gemList) {
        roomGems.addAll(gemList);
    }

	/**
	 * Adds a Collection of characters to the draw list
	 * @param charList The collection of characters
	 */
	public void addCharList(Collection<Character> charList) {
		onScreen.addAll(charList);
	}

	/**
	 * Clears all active NPCs from the draw list
	 */
	public void clearNPC() {
		for (int i = 0; i < onScreen.size(); i++) {
			if (onScreen.get(i).isNPC()) 
				onScreen.remove(i);
		}
	}

	/**
	 * A method for updating to the current room.
	 */
    public void updateRoom() {
        clearNPC();
        roomGems.clear();
        addCharList(r.getBadGuys());
        addGemList(r.getGems());
    }
    
    /**
     * Primary timer trigger, acts as time stepped game loop
     * Controls character position, attacks, life and death, damage dealing, etc.
     */
	public void actionPerformed(ActionEvent e) {
		
        roomListenerValue = r.isInActiveDoor(player);
		if (roomListenerValue>-1) {
			int temp;
			temp = r.getDoorNumber(roomListenerValue);
			r = level.getRoom(roomListenerValue);
			if(temp == 0){
                System.out.println("Door 0");
				player.setX(300);
				player.setY(90);
                updateRoom();
			}
			else if(temp ==1){
                System.out.println("Door 1");
				player.setX(400);
				player.setY(275);
                updateRoom();
			}
			else if(temp ==2){
                System.out.println("Door 2");
				player.setX(65);
				player.setY(275);
                updateRoom();
			}
			else{
                System.out.println("Door 3");
				player.setX(400);
				player.setY(510);
                updateRoom();
			}
			roomNum = roomListenerValue;
			roomNumL.setText("  Currently in room "+roomNum);
			indicator.repaint();
		} 
		else if (roomListenerValue == -2 && gemint >= (levelNum)) {
			levelNum = levelNum + 1.00;
			gemint = 0;
			frame.setTitle("Level "+(int)levelNum);
			createLevel(levelNum);
			r=level.getRoom(0);
			sprites[0] = ggRight;
			player.setX(65);
			player.setY(275);
            updateRoom();
			roomNum = 0;
			roomNumL.setText("  Currently in room "+roomNum);
            indicator.repaint();
		}
		
		for (Character c : onScreen) {
            if (c.isAlive()) {
                int sID = c.getSpriteID();
                if (sprites[sID].equals(dead)) {
                    if (sID <= 2) {
                        sprites[sID] = eyeGuy;
                    } else if (sID <= 4) {
                        sprites[sID] = wormDude;
                    }
                }
                Position cur = c.getPos();
                if (cur.getX() > 690) {
                    c.setdx(0);
                    c.setX(690);
                }
                if (cur.getY() > 545) {
                    c.setdy(0);
                    c.setY(545);
                }
                if (cur.getX() < 0) {
                    c.setdx(0);
                    c.setX(0);
                }
                if (cur.getY() < 0) {
                    c.setdy(0);
                    c.setY(0);
                }
                if (c.isNPC() && c.lengthTo(player)<150) {
                    c.moveTowards(player);
                    if (c.lengthTo(player)<50) {
                    	if (Math.random()<.01) {
                    		player.deductHP(10);
                    		health.setText("  HP: "+p.getHP());
                    		indicator.repaint();
                    	}
                    }
                }
                else if (c.isNPC()) {
                    c.stop();
                }
                c.move();
            } else {
                sprites[c.getSpriteID()] = dead;
                r.removeBadGuy(c);
            }

		}

		repaint();
	}

	/**
	 * Draws all character sprites currently member of onScreen ArrayList.
	 * Does not check for overlap or boundaries, should be handled elsewhere
	 * @param g Graphics context
	 */
	public void paint(Graphics g) {
		super.paint(g);

		Graphics2D g2d = (Graphics2D) g;
        // Enable anti aliasing 
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
          RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
          RenderingHints.VALUE_RENDER_QUALITY);

        // Draw all characters and gems on the screen
		for (Character c: onScreen) {
			pos = c.getPos();   
			g2d.drawImage(sprites[c.getSpriteID()], pos.getX(), pos.getY(), null);
		}
        for (Gem gem : roomGems) {
            if (gem.isUsed()) {
                r.removeGem(gem);
            } else {
                pos = gem.getPos();
                g2d.drawImage(sprites[gem.getSpriteID()], pos.getX(), pos.getY(), null);
            }
        }
        //Linux support, forces X,gtk,qt,whatever to sync swing updates for smooth animation
		Toolkit.getDefaultToolkit().sync();
		g.dispose();
		
	}
	
	/**
	 * Method for creating a new level.
	 * @param levelNum complexity of the level
	 */
	public void createLevel(double levelNum) {
		level = new LevelCreator(levelNum);
	}

}
