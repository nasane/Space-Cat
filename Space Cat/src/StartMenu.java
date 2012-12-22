import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Class for creating a startup menu.
 * @author Nathan Bossart
 */
public class StartMenu extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for a start menu.
	 * @param frame the game screen
	 */
	public StartMenu(JFrame frame) {
								
		JPanel startMenu = new JPanel();
        setFocusable(false);
		
		startMenu.setLayout(new BoxLayout(startMenu, BoxLayout.PAGE_AXIS));

		JButton button1 = new JButton("Play Game");
		JButton button2 = new JButton("How To Play");
		JButton button4 = new JButton("Exit");
		
		JLabel  title   = new JLabel("SPACE CAT");
		title.setFont(new Font("Helvetica", Font.BOLD, 60));
		
		JLabel  footer  = new JLabel("Created by Nathan Bossart, Bob Urberger, and Joseph Mayer     Artwork by Beth Osia");
		footer.setFont(new Font("Helvetica", Font.PLAIN, 14));
		
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		button1.setAlignmentX(Component.CENTER_ALIGNMENT);
		button2.setAlignmentX(Component.CENTER_ALIGNMENT);
		button4.setAlignmentX(Component.CENTER_ALIGNMENT);
		footer.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		// Start Game
		button1.addActionListener(GameRunner.PlayGame);
        button1.setFocusable(false);
		
		// How To Play
		button2.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
            	JFrame popup = new JFrame();
        		popup.setSize(550, 300);
        		popup.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        		popup.setResizable(true);
            	popup.setTitle("How To Play");
            	JLabel text1 = new JLabel("  Navigate your way through the portals!");
            	JLabel text2 = new JLabel("  Collect gems and kill monsters to be able to move to other sectors and levels.");
            	JLabel text3 = new JLabel("  Controls:");
            	JLabel text4 = new JLabel("      move   --- arrow keys");
            	JLabel text5 = new JLabel("      action --- space bar");
            	popup.setLayout(new GridLayout(5,1));
            	popup.add(text1);
            	text1.setAlignmentX(Component.CENTER_ALIGNMENT);
            	popup.add(text2);
            	text2.setAlignmentX(Component.CENTER_ALIGNMENT);
            	popup.add(text3);
            	text3.setAlignmentX(Component.CENTER_ALIGNMENT);
            	popup.add(text4);
            	popup.add(text5);
            	popup.setBackground(Color.white);
            	popup.setVisible(true);
            }
        });
		button2.setFocusable(false);
		
		// Exit
		button4.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                System.exit(0);
            }
        });
		button4.setFocusable(false);
		
		startMenu.add(Box.createVerticalGlue());
		startMenu.add(title);
		startMenu.add(Box.createVerticalGlue());
		startMenu.add(button1);
		startMenu.add(Box.createVerticalGlue());
		startMenu.add(button2);
		startMenu.add(Box.createVerticalGlue());
		startMenu.add(button4);
		startMenu.add(Box.createVerticalGlue());
		startMenu.add(footer);

		frame.setBackground(Color.white);
		
		frame.add(startMenu);
		frame.setTitle("Main Menu");
        frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
	}

}
