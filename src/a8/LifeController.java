package a8;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class LifeController implements ActionListener, MouseListener
{
		LifeModel model;
		LifeView view;
		
	public LifeController(LifeModel model, LifeView view)
	{
		this.model = model;
		this.view = view;
				
		view.addActionListener(this);
		view.getBoard().addMouseListener(this);
	}

	public void actionPerformed(ActionEvent e)
	{
		JButton button = (JButton) e.getSource();
		String buttonStr = button.getText();
		
		if(buttonStr.equals("Clear"))
		{
			model.reset();
			view.reset();
		}
		else if(buttonStr.equals("Randomize"))
		{
			boolean[][] rand = new boolean[model.getWidth()][model.getHeight()];
				for(int x = 0; x < rand.length; x++)
				{
					for(int y = 0; y < rand[0].length; y++)
					{
						if(Math.random() > 0.5)
							rand[x][y] = true;
						else
							rand[x][y] = false;
					}
				}
				
				model.setBoard(rand);
				view.setBoard(rand);
		}
		else if(buttonStr.equals("Next"))
		{
			model.nextStep();
			view.setBoard(model.getBoard());
		}
		else if(buttonStr.equals("Set Board"))
		{
			String inp = JOptionPane.showInputDialog("What width do you want?");
			int width = Integer.parseInt(inp);
			String inpu = JOptionPane.showInputDialog("What height do you want?");
			int height = Integer.parseInt(inpu);
			
			if(width < 1 || height < 1)
			{	
				JOptionPane.showMessageDialog(button, "Cannot have dimensions less than 1", "Error", height);
				return;
			}

			model.changeBoardDimensions(width, height);
			view.setBoard(model.getBoard());
		}
		else if(buttonStr.equals("Set Thresholds"))
		{
			String inp = JOptionPane.showInputDialog("What should the low threshold be?");
			int low = Integer.parseInt(inp);
			String inpu = JOptionPane.showInputDialog("What should the high threshold be?");
			int high = Integer.parseInt(inpu);
			
			if(low < 0 || low > 8 || high < 0 || high > 8)
			{	
				JOptionPane.showMessageDialog(button, "Cannot have values less than 0 or greater than 8", "Error", low);
				return;
			}
			
			model.setLow(low);
			model.setHigh(high);
		}
		else if(buttonStr.equals("Torus Mode: ON"))
		{
			button.setText("Torus Mode: OFF");
			model.setTorus(false);
		}
		else if(buttonStr.equals("Torus Mode: OFF"))
		{
			button.setText("Torus Mode: ON");
			model.setTorus(true);
		}
		else if(buttonStr.equals("Click to Start"))
		{
			model.setIsRunning(true);
			button.setText("Click to Stop");
			new Thread(new Runnable()
			{
				public void run()
				{
					while(model.isRunning())
					{
						try
						{
							Thread.sleep(model.getDelay());
							if(!model.isRunning())
								break;
							model.nextStep();
							view.setBoard(model.getBoard());
						} 
						catch (InterruptedException e)
						{
						}
					}
				}
			}).start();
			
		}
		else if(buttonStr.equals("Click to Stop"))
		{
			button.setText("Click to Start");
			model.setIsRunning(false);
		}
		else if(buttonStr.equals("Configure Time"))
		{			
			String inp = JOptionPane.showInputDialog("In milliseconds, what delay do you want between updates?");
			int delay = Integer.parseInt(inp);
			
			if(delay < 10 || delay > 1000 )
			{	
				JOptionPane.showMessageDialog(button, "Cannot have a delay less than 10 or greater than 1000 milliseconds", "Error", delay);
				return;
			}
			
			model.setDelay(delay);
		}
		else if(buttonStr.contentEquals("Rainbow Mode: OFF"))
		{
			view.getBoard().setIsRainbow(true);
			button.setText("Rainbow Mode: ON");
			view.getBoard().trigger_update();
		}
		else if(buttonStr.contentEquals("Rainbow Mode: ON"))
		{
			view.getBoard().setIsRainbow(false);
			button.setText("Rainbow Mode: OFF");
			view.getBoard().trigger_update(); 
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		int x = e.getX();
		int y = e.getY();
		
		x /= view.getBoard().incrementW;
		y /= view.getBoard().incrementH;
				
		model.changeSpot(x, y);
		view.getBoard().toggleSpotAt(x, y);	
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}