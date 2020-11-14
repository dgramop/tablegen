package tablegen;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Portal {
	
	static int height, width, length, overhang;
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.setSize(600, 400);
       
        JLabel errorMessage = new JLabel("error");
        errorMessage.setBounds(200, 300, 180, 20);
       
        JLabel heightLabel = new JLabel("Enter Table Height(inches):");
        heightLabel.setBounds(100,50,180,20);
        JTextField heightField = new JTextField();
        heightField.setBounds(300, 50, 150, 20);
        
        JLabel widthLabel = new JLabel("Enter Table Width(inches):");
        widthLabel.setBounds(100,100,180,20);
        JTextField widthField = new JTextField();
        widthField.setBounds(300, 100, 150, 20);
        
        JLabel lengthLabel = new JLabel("Enter Table Length(inches):");
        lengthLabel.setBounds(100,150,180,20);
        JTextField lengthField = new JTextField();
        lengthField.setBounds(300, 150, 150, 20);
        
        JLabel overhangLabel = new JLabel("Enter Overhang Value(inches):");
        overhangLabel.setBounds(100,200,180,20);
        JTextField overhangField = new JTextField();
        overhangField.setBounds(300, 200, 150, 20);
        
        JButton b = new JButton("Submit");
        b.setBounds(250, 250, 100, 20);
        
        
        
        b.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
            	if(heightField.getText().matches("[0-9]+") &&
            	widthField.getText().matches("[0-9]+") &&
            	lengthField.getText().matches("[0-9]+") &&
            	overhangField.getText().matches("[0-9]+")){
            	    int h = Integer.parseInt(heightField.getText());
                    int w = Integer.parseInt(widthField.getText());
                    int l = Integer.parseInt(lengthField.getText());
                    int o = Integer.parseInt(overhangField.getText());
                    
                    if(h>0 && w>0 && l>0 && o>0) {
                    	height = h;
                    	width = w;
                    	length = l;
                    	overhang = o;
                    }
                    else {
                    	errorMessage.setText("Please enter valid dimensions");
                    }
                }
            	else {
            		if(heightField.getText().charAt(0) == '-' ||
            		   widthField.getText().charAt(0) == '-' ||
            		   lengthField.getText().charAt(0) == '-' ||
            		   overhangField.getText().charAt(0) == '-'){
            			errorMessage.setText("Please enter valid dimensions");
            		}
            		else
            		   errorMessage.setText("Please fill in all the fields");
            	}
            }
        });
        
        
        
        frame.add(heightLabel);
        frame.add(heightField);
        frame.add(widthLabel);
        frame.add(widthField);
        frame.add(lengthLabel);
        frame.add(lengthField);
        frame.add(overhangLabel);
        frame.add(overhangField);
        frame.add(b);
        frame.add(errorMessage);
        
        frame.setLayout(null);
        frame.setVisible(true);
        
        
	}
	
}
