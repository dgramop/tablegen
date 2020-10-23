import java.awt.*;
import java.awt.Graphics;
import javax.swing.*;

public class Drawings extends JPanel{
	
	public Graphics draw() {
		Graphics g = getGraphics();
		return g;
	}
	
	public static void main(String[]args) {
		/*Frame frame = new Frame();
		Rectangle r = new Rectangle(0,0,30,40);
		JPanel panel = new JPanel();
		
		frame.add(panel);
		
		int frameWidth = 500;		 
		int frameHeight = 500;
		 
		frame.setSize(frameWidth, frameHeight);
		 
		frame.setVisible(true);
		Graphics g = panel.getGraphics();
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, 100, 100);
        frame.validate(); // because you added panel after setVisible was called
        frame.repaint();*/
		
		JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(600, 400);

        JPanel panel = new MyRectangleJPanel(); // changed this line
        frame.add(panel);

        // Graphics g = panel.getGraphics();
        // g.setColor(Color.BLUE);
        // g.fillRect(0, 0, 100, 100);

        frame.validate(); // because you added panel after setVisible was called
        frame.repaint(); // because you added panel after setVisible was called
		
	}	
}

class MyRectangleJPanel extends JPanel {
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, 100, 100);
    }
}
