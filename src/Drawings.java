import java.awt.*;

import java.awt.Graphics;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.RoundRectangle2D;

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
		
		//add URL
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
        
        JPanel circle = new MyCircleJPanel();
		frame.add(circle);
	}	
	
}

class MyRectangleJPanel extends JPanel {
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        g.fillRect(50, 20, 100, 100);
        
        Graphics g2 =  g;
        //super.paintComponent(g);
        g2.setColor(Color.red);
        g2.drawRect(10, 10, 100, 100);   
    }
}

class MyCircleJPanel extends JPanel{
    
    public void paintComponent(Graphics g) {
    	Graphics2D g2 = (Graphics2D) g;
        Shape line = new Line2D.Double(3, 3, 303, 303);
        Shape rect = new Rectangle(3, 3, 303, 303);
        Shape circle = new Ellipse2D.Double(100, 100, 100, 100);
        Shape roundRect = new RoundRectangle2D.Double(20, 20, 250, 250, 5, 25);
        g2.draw(line);
        g2.draw(rect);
        g2.draw(circle);
        g2.draw(roundRect);
    	
    }
}
