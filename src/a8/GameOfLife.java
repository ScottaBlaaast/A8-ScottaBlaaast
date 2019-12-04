package a8;
import javax.swing.JFrame;

public class GameOfLife
{
	public static void main(String[] args)
	{
		LifeModel model = new LifeModel();
		LifeView view = new LifeView();
		LifeController controller = new LifeController(model, view);
		
		JFrame main_frame = new JFrame();
		main_frame.setTitle("Conway's Game of Life");
		main_frame.setSize(900,900);
		main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main_frame.setResizable(false);
		
		main_frame.setContentPane(view);
		
		main_frame.pack();
		main_frame.setVisible(true);		

	}
}
