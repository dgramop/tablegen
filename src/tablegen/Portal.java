package tablegen;
import java.awt.*;

import java.awt.event.*;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.regex.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Portal {
	
	static int height, width, length, overhang, lamps, receptacles;
	static Table table;
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
        frame.setSize(1200, 700);
        
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        
        TableJPanel panel = new TableJPanel(0,0,0,0);
       
        JLabel errorMessage = new JLabel("");
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
        
        JLabel lampLabel = new JLabel("Enter Number of Lamps(0-5):");
        lampLabel.setBounds(500,50,180,20);
        JTextField lampField = new JTextField();
        lampField.setBounds(720, 50, 150, 20);
        
        JLabel receptacleLabel = new JLabel("Enter Number of Receptacles(0-3):");
        receptacleLabel.setBounds(500,100,200,20);
        JTextField receptacleField = new JTextField();
        receptacleField.setBounds(720, 100, 150, 20);
        
        JLabel store1Label = new JLabel("Enter Store for Wood/Electronics:");
        store1Label.setBounds(500,150,200,20);
        JTextField store1Field = new JTextField();
        store1Field.setBounds(720, 150, 150, 20);
        
        JLabel store2Label = new JLabel("Enter Store for Lamps:");
        store2Label.setBounds(500,200,200,20);
        JTextField store2Field = new JTextField();
        store2Field.setBounds(720, 200, 150, 20);
        
        JButton b = new JButton("Submit");
        b.setBounds(450, 300, 100, 20);
        
        
        b.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
            	//check if boxes are all filled
            	
            	if(heightField.getText().matches("[0-9]+") &&
            	widthField.getText().matches("[0-9]+") &&
            	lengthField.getText().matches("[0-9]+") &&
            	overhangField.getText().matches("[0-9]+") &&
            	lampField.getText().matches("[0-9]+") &&
            	receptacleField.getText().matches("[0-9]+")&&
            	(store1Field.getText().equals("HomeDepot")||store1Field.getText().equals("Lowes"))&&
            		(store2Field.getText().equals("HomeDepot")||store2Field.getText().equals("Lowes")||store2Field.getText().equals("Ikea"))){
            	    int h = Integer.parseInt(heightField.getText());
                    int w = Integer.parseInt(widthField.getText());
                    int l = Integer.parseInt(lengthField.getText());
                    int o = Integer.parseInt(overhangField.getText());
                    int la = Integer.parseInt(lampField.getText());
                    int r = Integer.parseInt(receptacleField.getText());
                    
                    ApplianceStore woodstore;
                    ApplianceStore lampstore;
                    if(store1Field.getText().equals("HomeDepot"))
                    {
                    	woodstore = ApplianceStore.HomeDepot;
                    }
                    else {
                     woodstore = ApplianceStore.Lowes;
                    		}
                    
                    if(store2Field.getText().equals("HomeDepot"))
                    {
                    	 lampstore = ApplianceStore.HomeDepot;
                    }
                    else if(store2Field.getText().equals("Ikea"))
                    {
                    	lampstore = ApplianceStore.Ikea;
                    }
                    else {
                    	 lampstore = ApplianceStore.Lowes;
                    		}
                    
                    
                    
                   
                    
                    if(h>0 && w>0 && l>0 && o>0) {
                    	height = h;
                    	width = w;
                    	length = l;
                    	overhang = o;
                    	lamps = la;
                    	receptacles = r;
                    	errorMessage.setText("success");
                    	
                    	table = TableMaker.makeTable(height, width, length, overhang, lamps, receptacles);

                    	System.out.println(table.calculatePrice(woodstore, lampstore));
                    	Instructions i = new Instructions(table);
                    	System.out.println(i.writeInstructions());
                    	

                    	
                    	panel.setLength(length);
                    	panel.setWidth(width);
                    	panel.setHeight(height);
                    	panel.setOverhang(overhang);
                        
                    	JLabel topLabel = new JLabel("Top View");
                    	topLabel.setBounds(130,40,100,20);
                    	panel.add(topLabel);
                    	
                    	JLabel bottomLabel = new JLabel("Bottom View");
                    	bottomLabel.setBounds(410, 40, 100, 20);
                    	panel.add(bottomLabel);
                    	
                    	JLabel sideLabel = new JLabel("Side View");
                    	sideLabel.setBounds(670, 40, 100, 20);
                    	panel.add(sideLabel);
                    	
                    	panel.setLayout(null);
                    	
                    	frame.setContentPane(panel);
                    	frame.validate();
                    	
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
        
        
        panel1.add(heightLabel);
        panel1.add(heightField);
        panel1.add(widthLabel);
        panel1.add(widthField);
        panel1.add(lengthLabel);
        panel1.add(lengthField);
        panel1.add(overhangLabel);
        panel1.add(overhangField);
        panel1.add(lampLabel);
        panel1.add(lampField);
        panel1.add(receptacleLabel);
        panel1.add(receptacleField);
        panel1.add(store1Label);
        panel1.add(store1Field);
        panel1.add(store2Label);
        panel1.add(store2Field);
        panel1.add(b);
        panel1.add(errorMessage);
        panel1.setLayout(null);
        
        
        frame.setVisible(true);
        frame.setContentPane(panel1);
        
        
	}
	
}

class TableJPanel extends JPanel {
	int length;
	int width;
	int height;
	int overhang;
	
	public TableJPanel(int length, int width, int height, int overhang) {
		this.length = length;
		this.width = width;
		this.overhang = overhang;
	}
	
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        Shape topView = new Rectangle(70, 70, length, width);
        
        Shape rect = new Rectangle(350, 70, length, width);
        Shape leg1 = new Rectangle(350 + overhang, 70 + overhang, 20,10);
        Shape leg2 = new Rectangle(350 + length - overhang - 20, 70 + overhang, 20,10);
        Shape leg3 = new Rectangle(350 + overhang, 70 + width - overhang - 10, 20,10);
        Shape leg4 = new Rectangle(350 + length - overhang - 20, 70 + width - overhang - 10, 20,10);
        
        Shape slat1 = new Rectangle(350 +overhang, 70 + overhang, length - (overhang*2), 10);
        Shape slat2 = new Rectangle(350 +overhang, 70 + overhang, 20, width - (overhang*2));
        Shape slat3 = new Rectangle(350 + length - overhang - 20, 70 + overhang, 20, width - (overhang*2));
        
        Shape tableTop = new Rectangle(600, 70, length, 4);
        Shape sideLeg1 = new Rectangle(600+overhang, 74, 20, height - 4);
        Shape sideLeg2 = new Rectangle(600+length-overhang -20, 74, 20, height - 4);
        
        
        JLabel label = new JLabel("label");
        label.setBounds(20,20, 100, 10);
        
        g2.draw(rect);
        g2.draw(leg1);
        g2.draw(leg2);
        g2.draw(leg3);
        g2.draw(leg4);
        g2.draw(slat1);
        g2.draw(slat2);
        g2.draw(slat3);
        g2.draw(topView);
        g2.draw(tableTop);
        g2.draw(sideLeg1);
        g2.draw(sideLeg2);
        
        
    }
    
    public void setLength(int length) {
    	this.length = length;
    }
    
    public void setWidth(int width) {
    	this.width = width;
    }
    
    public void setHeight(int height) {
    	this.height = height;
    }
    
    public void setOverhang(int overhang) {
    	this.overhang = overhang;
    }
    
    


}
