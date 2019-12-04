package a8;
import java.util.ArrayList;
import java.util.List;

public class LifeModel
{
	private boolean[][] board;
	private boolean torus, isRunning;
	private int low, high, delay;
	
	public LifeModel()
	{
		torus = true;
		isRunning = false;
		board = new boolean[10][10];
		low = 2;
		high = 3;
		delay = 1000;
		for(int i = 0; i < 10; i++)
		{
			for(int j = 0; j < 10; j++)
			{
				board[i][j] = false;
			}
		}
	}
	
	public boolean isRunning()
	{
		return isRunning;
	}
	public void setIsRunning(boolean b)
	{
		isRunning = b;
	}
	public int getDelay()
	{
		return delay;
	}
	public void setDelay(int x)
	{
		delay = x;
	}
	public int getWidth()
	{
		return board.length;
	}
	public int getHeight()
	{
		return board[0].length;
	}
	public boolean[][] getBoard()
	{
		return board;
	}
	public void setBoard(boolean[][] x)
	{
		board = x;
	}
	public int getLow()
	{
		return low;
	}
	public void setLow(int i)
	{
		low = i;
	}
	public int getHigh()
	{
		return high;
	}
	public boolean getTorus()
	{
		return torus;
	}
	public void setTorus(boolean b)
	{
		torus = b;
	}
	public void setHigh(int i)
	{
		high = i;
	}
	public void changeSpot(int x, int y)
	{
		board[x][y] = !board[x][y];
	}
	public void changeBoardDimensions(int x, int y)
	{
		boolean[][] changed = new boolean[x][y];
		
		for(int i = 0; i < x; i++)
		{
			for(int j = 0; j < y; j++)
			{
				if(i < board.length && j < board[0].length && board[i][j])
					changed[i][j] = true;
				else
					changed[i][j] = false;
			}
		}
		
		board = changed;
	}
	public void nextStep()
	{
		boolean[][] b = new boolean[board.length][board[0].length];
		
		for(int x = 0; x < board.length; x++)
		{
			for(int y = 0; y < board[0].length; y++)
			{
				b[x][y] = board[x][y];
			}
		}
		
		for(int x = 0; x < board.length; x++)
		{
			for(int y = 0; y < board[0].length; y++)
			{
				int compare = getNeighbors(x, y);
				if(board[x][y] && compare < low || compare > high)
					b[x][y] = false;
				else if(!board[x][y] && compare == high)
					b[x][y] = true;
			}
		}
		
		for(int x = 0; x < board.length; x++)
		{
			for(int y = 0; y < board[0].length; y++)
			{
				board[x][y] = b[x][y];
			}
		}
	}
	public int getNeighbors(int x, int y)
	{
		int result = 0;
		
		if(torus)
		{
			if(x - 1 <= -1 && y - 1 <= -1 && board[board.length - 1][board[0].length - 1])
				result++;
			if(x - 1 <= -1 && board[board.length - 1][y])
				result++;
			if(x - 1 <= -1 && y + 1 >= board[0].length && board[board.length - 1][0])
				result++;
			if(y - 1 <= -1 && board[x][board[0].length - 1])
				result++;
			if(y + 1 >= board[0].length && board[x][0])
				result++;
			if(x + 1 >= board.length && y - 1 <= -1 && board[0][board[0].length - 1])
				result++;
			if(x + 1 >= board.length && board[0][y])
				result++;
			if(x + 1 >= board.length && y + 1 >= board[0].length && board[0][0])
			return result;
		}
		
		if(x - 1 > -1 && y - 1 > -1 && board[x-1][y-1])
			result++;
		if(x - 1 > -1 && board[x-1][y])
			result++;
		if(x-1 > -1 && y + 1 < board[0].length && board[x-1][y+1])
			result++;
		if(y - 1 > -1 && board[x][y-1])
			result++;
		if(y + 1 < board[0].length && board[x][y+1])
			result++;
		if(x + 1 < board.length && y - 1 > -1 && board[x+1][y-1])
			result++;
		if(x + 1 < board.length && board[x+1][y])
			result++;
		if(x + 1 < board.length && y + 1 < board[0].length && board[x+1][y+1])
			result++;
		
		return result;
	}
	public void reset()
	{
		board = new boolean[board.length][board[0].length];
		
		for(int i = 0; i < board.length; i++)
		{
			for(int j = 0; j < board[0].length; j++)
			{
				board[i][j] = false;
			}
		}
	}
}
