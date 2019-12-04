package a8;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class LifeView extends JPanel
{
	
	private JSpotBoard game;
	private JPanel cmdButtons;
	
	public LifeView()
	{
		setLayout(new BorderLayout());
		
		game = new JSpotBoard(10,10);
		cmdButtons = new JPanel();
		
		//setting up what'll be in the button panel
		
		JButton clear = new JButton("Clear");
		JButton randomize = new JButton("Randomize");
		JButton next = new JButton("Next");
		JButton set = new JButton("Set Board");
		JButton setT = new JButton("Set Thresholds");
		JButton torus = new JButton("Torus Mode: ON");
		JButton time = new JButton("Click to Start");
		JButton config = new JButton("Configure Time");
		JButton rainbow = new JButton("Rainbow Mode: OFF");
		
		cmdButtons.add(clear);
		cmdButtons.add(randomize);
		cmdButtons.add(next);
		cmdButtons.add(set);
		cmdButtons.add(setT);
		cmdButtons.add(torus);
		cmdButtons.add(time);
		cmdButtons.add(config);
		cmdButtons.add(rainbow);
		
		add(game, BorderLayout.CENTER);
		add(cmdButtons, BorderLayout.SOUTH);
	}
	
	public JSpotBoard getBoard()
	{
		return game;
	}
	public void setBoard(boolean[][] j)
	{
		game.setSpots(j);
	}
	public void reset()
	{
		game.reset();
	}
	
	public void addActionListener(ActionListener l)
	{
		for(Component c: cmdButtons.getComponents())
		{
			JButton b = (JButton) c;
			b.addActionListener(l);
		}
	}


}
