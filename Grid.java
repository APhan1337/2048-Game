import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Grid extends JPanel
{
	int COLUMNS;
	int ROWS;
	
	private Tile[][] numbers;
	
	public Grid(GameLogic game)
	{
		init(game.COLUMN, game.ROW, game);
	}
	
	public void init(int xSize, int ySize, GameLogic game)
	{
		removeAll();
		COLUMNS = xSize;
		ROWS = ySize;
		setLayout(new GridLayout(ROWS, COLUMNS));
		numbers = new Tile[COLUMNS][ROWS];
		for (int row = 0; row < ROWS; row++)
		{
			for (int col = 0; col < COLUMNS; col++)
			{
				numbers[col][row] = new Tile(game.getCellValue(col, row));
				add(numbers[col][row]);
			}
		}
	}
	
	public void setValue(int column, int row, GameLogic game)
	{
		this.numbers[column][row].setValue(game.getCellValue(column, row));
	}
	
	public Tile[][] getValue()
	{
		return numbers;
	}
}