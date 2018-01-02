import java.util.Random;
import java.util.ArrayList;

public class GameLogic
{
	public final int COLUMN; 
	public final int ROW;
	
	private int[][] grid;
	
	public void printGame()
	{
		for (int row = 1; row <= ROW; row++)
		{
			for (int col = 1; col <= COLUMN; col++)
			{
				System.out.printf("%d\t", grid[col][row]);
			}
			System.out.println();
		}
	}
	
	public GameLogic(final int column, final int row) throws IllegalArgumentException
	{
		if (column < 2 || row < 2)
		{
			throw new IllegalArgumentException("Rows and columns must be >= 2");
		}
		this.COLUMN = column;
		this.ROW = row;
		grid = new int[column + 2][row + 2];
		for (int col = 0; col < COLUMN + 2; col++)
		{
			for (int ro = 0; ro < ROW; ro++)
			{
				grid[col][ro] = 0;
			}
		}
	}
	
	public int getCellValue(int col, int row) throws IndexOutOfBoundsException
	{
		if (col < 0 || col >= COLUMN || row < 0 || row >= ROW)
		{
			throw new IndexOutOfBoundsException();
		}
		return grid[col + 1][row + 1];
	}
	
	public void setCellValue(int col, int row, int value) throws IndexOutOfBoundsException
	{
		if (col < 0 || col >= COLUMN || row < 0 || row >= ROW)
		{
			throw new IndexOutOfBoundsException();
		}
		grid[col + 1][row + 1] = value;
	}
	
	public void slideLeft()
	{
		int destinationColumn;
		
		for (int row = 0; row <= ROW; row++)
		{
			destinationColumn = 1;
			
			for (int column = 2; column <= COLUMN; column++)
			{
				if (destinationColumn == column || grid[column][row] == 0)
				{
					continue;
				}
				else if (grid[column][row] == grid[destinationColumn][row])
				{
					grid[destinationColumn][row] = grid[destinationColumn][row] * 2;
					grid[column][row] = 0;
					destinationColumn++;
				}
				else
				{
					if (grid[destinationColumn][row] != 0)
					{
						destinationColumn++;
					}
					else if (destinationColumn != column)
					{
						grid[destinationColumn][row] = grid[column][row];
						grid[column][row] =0;
					}
				}
			}
		}
	}
	
	public void slideRight()
	{
		int destinationColumn;
		
		for (int row = 1; row <= ROW; row++)
		{
			destinationColumn = COLUMN;
			
			for (int column = COLUMN - 1; column <= 1; column--)
			{
				if (destinationColumn == column || grid[column][row] == 0)
				{
					continue;
				}
				else if (grid[column][row] == grid[destinationColumn][row])
				{
					grid[destinationColumn][row] = grid[destinationColumn][row] * 2;
					grid[column][row] = 0;
					destinationColumn--;
				}
				else
				{
					if (grid[destinationColumn][row] != 0)
					{
						destinationColumn--;
					}
					else if (destinationColumn != column)
					{
						grid[destinationColumn][row] = grid[column][row];
						grid[column][row] =0;
					}
				}
			}
		}
	}
	
	public void slideUp()
	{
		int destinationRow;
		
		for (int column = 1; column <= COLUMN; column++)
		{
			destinationRow = 1;
			
			for (int row = 2; row <= ROW; row++)
			{
				if (destinationRow == row || grid[column][destinationRow] == 0)
				{
					continue;
				}
				else if (grid[column][row] == grid[column][destinationRow])
				{
					grid[column][destinationRow] = grid[column][destinationRow] * 2;
					grid[column][row] = 0;
					destinationRow++;
				}
				else
				{
					if (grid[column][destinationRow] != 0)
					{
						destinationRow++;
					}
					else if (destinationRow != row)
					{
						grid[column][destinationRow] = grid[column][row];
						grid[column][row] =0;
					}
				}
			}
		}
	}
	
	public void slideDown()
	{
		int destinationRow;
		
		for (int column = 1; column <= COLUMN; column++)
		{
			destinationRow = ROW;
			
			for (int row = ROW - 1; row <= 1; row--)
			{
				if (destinationRow == row || grid[column][destinationRow] == 0)
				{
					continue;
				}
				else if (grid[column][row] == grid[column][destinationRow])
				{
					grid[column][destinationRow] = grid[column][destinationRow] * 2;
					grid[column][row] = 0;
					destinationRow--;
				}
				else
				{
					if (grid[column][destinationRow] != 0)
					{
						destinationRow--;
					}
					else if (destinationRow != row)
					{
						grid[column][destinationRow] = grid[column][row];
						grid[column][row] =0;
					}
				}
			}
		}
	}
	
	public boolean addNew2or4()
	{
		int col, row;
		
		Random random = new Random();
		
		if (isFull())
		{
			return false;
		}
		
		do
		{
			col = random.nextInt(COLUMN) + 1;
			row = random.nextInt(ROW) + 1;
		} while (grid[col][row] != 0);
		/*
		ArrayList<Integer> twoOrFour = new ArrayList<Integer>();
		twoOrFour.add(2);
		twoOrFour.add(4);
		twoOrFour.trimToSize();
		
		int randomized = twoOrFour.get(random.nextInt(twoOrFour.size()));
			
		if (randomized == 2)
		{
			grid[col][row] = 2;
		}
		else if (randomized == 4)
		{
			grid[col][row] = 4;
		}*/
		grid[col][row] = 2;
		return true;
	}
	
	public boolean canPlay()
	{
		if (!isFull())
		{
			return true;
		}
		
		for (int col = 1; col < COLUMN; col++)
		{
			for (int row = 1; row <= ROW; row++)
			{
				if (grid[row][col] == grid[row + 1][col] || 
					grid[row][col] == grid[row][col + 1] || 
					grid[row][col] == grid[row - 1][col] || 
					grid[row][col] == grid[row][col - 1])
				{
					return true;
				}
			}
		}
		return false;
	}
	
	public int getScore()
	{
		int score = 0;
		
		for (int col = 1; col <= COLUMN; col++)
		{
			for (int row = 1; row <= ROW; row++)
			{
				score += grid[col][row];
			}
		}
		
		return score;
	}
	
	public boolean isFull()
	{
		for (int c = 1; c <= COLUMN; c++)
		{
			for (int r = 1; r < ROW; r++)
			{
				if (grid[c][r] == 0)
				{
					return false;
				}
			}
		}
		return true;
	}
		
}