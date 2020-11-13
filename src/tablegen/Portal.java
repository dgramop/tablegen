package tablegen;
import java.awt.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Portal {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.setSize(600, 400);
       
        
        JLabel heightLabel = new JLabel("Enter Table Height:");
        heightLabel.setBounds(150,50,150,20);
        JTextField heightField = new JTextField();
        heightField.setBounds(300, 50, 150, 20);
        
        JLabel widthLabel = new JLabel("Enter Table Width:");
        widthLabel.setBounds(150,100,150,20);
        JTextField widthField = new JTextField();
        widthField.setBounds(300, 100, 150, 20);
        
        JLabel lengthLabel = new JLabel("Enter Table Length:");
        lengthLabel.setBounds(150,150,150,20);
        JTextField lengthField = new JTextField();
        lengthField.setBounds(300, 150, 150, 20);
        
        JLabel overhangLabel = new JLabel("Enter Overhang Value:");
        overhangLabel.setBounds(150,200,150,20);
        JTextField overhangField = new JTextField();
        overhangField.setBounds(300, 200, 150, 20);
        
        
        
        frame.add(heightLabel);
        frame.add(heightField);
        frame.add(widthLabel);
        frame.add(widthField);
        frame.add(lengthLabel);
        frame.add(lengthField);
        frame.add(overhangLabel);
        frame.add(overhangField);
        
        frame.setLayout(null);
        frame.setVisible(true);
        
        
	}
	
}
