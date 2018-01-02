import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Tile extends JComponent
{
	public static final int SCALE = 100;
	public static final int BORDER = SCALE /20;
	public static final int FONT_SIZE = (int)(SCALE * 0.4);
	public static final Font FONT = new Font("Consolas", Font.PLAIN, FONT_SIZE);
	
	private int value;
	
	public Tile(int value)
	{
		this.value = value;
		setFont(FONT);
		setPreferredSize(new Dimension(SCALE, SCALE));
	}
	
	public void setValue(int value)
	{
		this.value = value;
	}
	
	public int getValue()
	{
		return this.value;
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		int width = getWidth();
		int height = getHeight();
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, SCALE, SCALE);
		
		Color color;
		if (value == 0)
		{
			color = Color.CYAN;
		}
		else
		{
			int len = Integer.numberOfTrailingZeros(value);
			color = Color.getHSBColor(len / 11.0f, 0.8f, 0.5f);
		}
		g.setColor(color);
		g.fillRoundRect(BORDER, BORDER, width - (2 * BORDER), height - (2 * BORDER), SCALE / 3, SCALE / 3);
		g.setColor(Color.LIGHT_GRAY);
		
		FontMetrics metrics = g.getFontMetrics(FONT);
		String text = Integer.toString(value);
		g.drawString(text, (width - metrics.stringWidth(text)) / 2, height / 2 + metrics.getAscent() / 3);
		
		
	}
	
	public static void main(String[] args)
	{
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		
		panel.add(new Tile(2));
		
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}