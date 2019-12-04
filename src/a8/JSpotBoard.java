package a8;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;

import javax.swing.JPanel;

/*
 * JSpotBoard is a user interface component that implements SpotBoard.
 * 
 * Spot width and spot height are specified to the constructor. 
 * 
 * By default, the spots on the spot board are set up with a checker board pattern
 * for background colors and yellow highlighting.
 * 
 * Uses SpotBoardIterator to implement Iterable<Spot>
 * 
 */

public class JSpotBoard extends JPanel
{
	int incrementH, incrementW;
	private boolean[][] spots;
	boolean isRainbow;
	
	public JSpotBoard(int width, int height)
	{
		if (width < 1 || height < 1)
			throw new IllegalArgumentException("Illegal spot board geometry");
		
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(900, 900));
		
		spots = new boolean[width][height];
		for(int i = 0; i < spots.length; i++)
		{
			for(int j = 0; j < spots[0].length; j++)
			{
				spots[i][j] = false;
			}
		}
		
		isRainbow = false;
		incrementH = 900/height;
		incrementW = 900/width;
		//the size for every spot on the grid
		
		setBackground(Color.BLACK);
	}
	@Override
	public void paintComponent(Graphics g)
	{
		// Super class paintComponent will take care of 
		// painting the background.
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g.create();
		
		for(int i = 0; i < spots.length; i++)
		{
			for(int j = 0; j < spots[0].length; j++)
			{
				if(spots[i][j] && isRainbow)
					g2d.setColor(randomColor());
				else if(spots[i][j])
					g2d.setColor(Color.WHITE);
				else
					g2d.setColor(Color.BLACK);
				
				g2d.fillRect(i * incrementW, j * incrementH, incrementW, incrementH);
			}
		}
	}
	
	public int getSpotWidth()
	{
		return spots.length;
	}
	
	public int getSpotHeight() 
	{
		return spots[0].length;
	}

	// Lookup method for Spot at position (x,y)
	
	public boolean getSpotAt(int x, int y)
	{
		if (x < 0 || x >= getSpotWidth() || y < 0 || y >= getSpotHeight())
			throw new IllegalArgumentException("Illegal spot coordinates");
		
		return spots[x][y];
	}
	public void setSpotAt(int x, int y, boolean b)
	{
		if (x < 0 || x >= getSpotWidth() || y < 0 || y >= getSpotHeight())
			throw new IllegalArgumentException("Illegal spot coordinates");
		
		spots[x][y] = b;
	}
	public void toggleSpotAt(int x, int y)
	{
		if (x < 0 || x >= getSpotWidth() || y < 0 || y >= getSpotHeight())
			throw new IllegalArgumentException("Illegal spot coordinates");
		
		spots[x][y] = !spots[x][y];
		trigger_update();
	}
	public void setSpots(boolean[][] b)
	{
		spots = new boolean[b.length][b[0].length];
		incrementW = 1000/b.length;
		incrementH = 1000/b[0].length;
		for(int x = 0; x < spots.length; x++)
		{
			for(int y = 0; y < spots[0].length; y++)
			{
				spots[x][y] = b[x][y];
			}
				
		}
		trigger_update();
	}
	public void reset()
	{
		for(int x = 0; x < spots.length; x++)
		{
			for(int y = 0; y < spots[0].length; y++)
			{
				spots[x][y] = false;
			}
		}
		trigger_update();
	}
	
	// Convenience methods for (de)registering spot listeners.
	
	public String toString()
	{
		String result = "";
		for(int x = 0; x < spots.length; x++)
		{
			for(int y = 0; y < spots[0].length; y++)
			{
				if(spots[x][y])
					result = result.concat("W");
				else
					result = result.concat("B");
			}
		}
		return result;
	}

	private Color randomColor()
	{
		int r = (int)(Math.random() * 255);
		int g = (int)(Math.random() * 255);
		int b = (int)(Math.random() * 255);
		
		return new Color(r + 1, g + 1, b + 1);
	}
	public void setIsRainbow(boolean b)
	{
		isRainbow = b;
	}
	public boolean getIsRainbow()
	{
		return isRainbow;
	}

	
	public void trigger_update()
	{		
		repaint();

		// Not sure why, but need to schedule a call
		// to repaint for a little bit into the future
		// as well as the one we just did above
		// in order to make sure that we don't end up
		// with visual artifacts due to race conditions.
		
		new Thread(new Runnable()
		{
			public void run()
			{
				try
				{
					Thread.sleep(50);
				}
				catch (InterruptedException e)
				{
				}
				repaint();
			}
		}).start();
	}
}
