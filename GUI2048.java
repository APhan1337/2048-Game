import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI2048 extends JFrame implements ActionListener
{
	private Grid panelGame;
	private GameLogic game;
	private JButton buttonLeft, buttonRight, buttonUp, buttonDown;
	
	public GUI2048()
	{
		super("2048");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setSize(400,550);
		
		JPanel panelMain = new JPanel();
		panelMain.setBackground(Color.GRAY);
		add(panelMain);
		
		panelMain.setLayout(new BoxLayout(panelMain, BoxLayout.Y_AXIS));
		
		game = new GameLogic(4, 4);
		game.addNew2or4();
		game.addNew2or4();
		
		game.printGame();
		panelGame = new Grid(game);
		//panelGame.setLayout(new GridLayout(4, 4));
		//for (int i = 0; i < 16; i++)
		//{
		//	panelGame.add(new JLabel("test"));
		//}
		
		panelMain.add(panelGame);
		
		//panelGame.setPreferredSize(new Dimension (400, 400));
		//panelMain.add(buttonLeft);
		//panelMain.add(buttonRight);
		//panelMain.add(buttonUp);
		//panelMain.add(buttonDown);
		
		JPanel panelButtons = new JPanel();
		panelButtons.setBackground(Color.PINK);
		panelButtons.setLayout(new GridBagLayout());
		
		GridBagConstraints constraints = new GridBagConstraints();
		
		buttonLeft = new JButton("Slide Left");
		constraints.gridx = 1;
		constraints.gridy = 2;
		buttonLeft.addActionListener(this);
		panelButtons.add(buttonLeft, constraints);
		
		buttonRight = new JButton("Slide Right");
		constraints.gridx = 3;
		constraints.gridy = 2;
		buttonRight.addActionListener(this);
		panelButtons.add(buttonRight, constraints);
		
		buttonUp = new JButton("Slide Up");
		constraints.gridx = 2;
		constraints.gridy = 1;
		buttonUp.addActionListener(this);
		panelButtons.add(buttonUp, constraints);
		
		buttonDown = new JButton("Slide Down");
		constraints.gridx = 2;
		constraints.gridy = 3;
		buttonDown.addActionListener(this);
		panelButtons.add(buttonDown, constraints);
		
		panelMain.add(panelButtons);
		
		updateTile();
	}
	
	public void updateTile()
	{	
		for (int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				panelGame.setValue(i, j, game);	
			}
		}
		panelGame.repaint();
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == buttonLeft)
		{
			game.slideLeft();
			game.addNew2or4();
			updateTile();
			panelGame.repaint();
		}
		else if (e.getSource() == buttonRight)
		{
			game.slideRight();
			game.addNew2or4();
			updateTile();
			panelGame.repaint();
		}
		else if (e.getSource() == buttonUp)
		{
			game.slideUp();
			game.addNew2or4();
			updateTile();
			panelGame.repaint();
		}
		else if (e.getSource() == buttonDown)
		{
			game.slideDown();
			game.addNew2or4();
			updateTile();
			panelGame.repaint();
		}
		game.printGame();
	}
	
	public static void main(String[] args)
	{
		GUI2048 play = new GUI2048();
		
		play.setVisible(true);
	}
}