package tablegen;

import java.awt.*;

import java.awt.event.*;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.regex.*;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Portal {

	static int height, width, length, overhang, lamps, receptacles;
	static Table table;
	static int scaleFactor;

	/**
	 * Returns the scale factor you should use when rendering an object so it
	 * fits/fills the viewport and is still to-scale
	 * 
	 * @param itemHeight  The height of your tallest dimension
	 * @param itemWidth   The width of your widest dimension
	 * @param pixelHeight Target height in pixels
	 * @param pixelWidth  Target width in pixels
	 * @return A scale factor to use to scale all the dimensions
	 */
	public static double getScaleFactor(double itemHeight, double itemWidth, int pixelHeight, int pixelWidth) {
		return Math.min(pixelHeight / itemHeight, pixelWidth / itemWidth);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
        frame.setSize(1200, 700);    //fixed size for portal
        
        JPanel panel = new JPanel();
        
        TableJPanel tablePanel = new TableJPanel(null, 0,0,0,0);
       
        JLabel errorMessage = new JLabel("");
        errorMessage.setBounds(200, 300, 180, 20);
       
        JLabel heightLabel = new JLabel("Enter Table Height(inches):");  //creation of label and text field for table height
        heightLabel.setBounds(100,50,180,20);
        JTextField heightField = new JTextField();
        heightField.setBounds(300, 50, 150, 20);
        
        JLabel widthLabel = new JLabel("Enter Table Width(inches):");  //creation of label and text field for table width
        widthLabel.setBounds(100,100,180,20);
        JTextField widthField = new JTextField();
        widthField.setBounds(300, 100, 150, 20);
        
        JLabel lengthLabel = new JLabel("Enter Table Length(inches):"); //creation of label and text field for table length
        lengthLabel.setBounds(100,150,180,20);
        JTextField lengthField = new JTextField();
        lengthField.setBounds(300, 150, 150, 20);
        
        JLabel overhangLabel = new JLabel("Enter Overhang Value(inches):"); //creation of label and text field for table overhang value
        overhangLabel.setBounds(100,200,180,20);
        JTextField overhangField = new JTextField();
        overhangField.setBounds(300, 200, 150, 20);
        
        JLabel lampLabel = new JLabel("Enter Number of Lamps(0-5):");  //creation of label and text field for number of table lamps
        lampLabel.setBounds(500,50,180,20);
        JTextField lampField = new JTextField();
        lampField.setBounds(720, 50, 150, 20);
        
        JLabel receptacleLabel = new JLabel("Enter Number of Receptacles(0-3):");  //creation of label and text field for number of receptacles
        receptacleLabel.setBounds(500,100,200,20);
        JTextField receptacleField = new JTextField();
        receptacleField.setBounds(720, 100, 150, 20);
        
        JLabel store1Label = new JLabel("Enter Store for Wood/Electric:");  //creation of label and text field for store for wood and electronics
        store1Label.setBounds(500,150,200,20);
        JLabel store1options = new JLabel("(Lowes, HomeDepot)"); //displays options
        store1options.setBounds(500,170,200,20);
        JTextField store1Field = new JTextField();
        store1Field.setBounds(720, 150, 150, 20);
        
        JLabel store2Label = new JLabel("Enter Store for Lamps:");  //creation of label and text field for store for lamps
        store2Label.setBounds(500,200,200,20);
        JLabel store2options = new JLabel("(Ikea, Lowes, HomeDepot)"); //displays options
        store2options.setBounds(500,220,200,20);
        JTextField store2Field = new JTextField();
        store2Field.setBounds(720, 200, 150, 20);
        
        JButton b = new JButton("Submit");  //submit button
        b.setBounds(450, 300, 100, 20);
        
        
        b.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
            	//check if boxes are all filled
            	if(heightField.getText() == null && widthField.getText() == null &&
            	lengthField.getText() == null && overhangField.getText() == null &&
            	lampField.getText() == null && receptacleField.getText() == null &&
            	store1Field.getText() == null && store2Field.getText() == null) {
            		if(heightField.getText().matches("[0-9]+") &&   //makes sure height, length, width, and overhang values are all numbers
                        	widthField.getText().matches("[0-9]+") &&
                        	lengthField.getText().matches("[0-9]+") &&
                        	overhangField.getText().matches("[0-9]+") &&
                        	lampField.getText().matches("[0-9]+") &&
                        	receptacleField.getText().matches("[0-9]+")&&
                        	(store1Field.getText().equals("HomeDepot")||store1Field.getText().equals("Lowes"))&&  //makes sure store values are correct options
                        		(store2Field.getText().equals("HomeDepot")||store2Field.getText().equals("Lowes")||store2Field.getText().equals("Ikea"))){
                        	    int h = Integer.parseInt(heightField.getText());
                                int w = Integer.parseInt(widthField.getText());   //converts height, width, length, overhang, number of lamps, and number of receptacle values to int
                                int l = Integer.parseInt(lengthField.getText());  //stores all these variables
                                int o = Integer.parseInt(overhangField.getText());
                                int la = Integer.parseInt(lampField.getText());
                                int r = Integer.parseInt(receptacleField.getText());
                                
                            	ApplianceStore woodstore;
                                ApplianceStore lampstore;
                                
                                if(store1Field.getText().equals("HomeDepot"))  //sets woodstore variable using ApplianceStore enum
                                {
                                	woodstore = ApplianceStore.HomeDepot;
                                }
                                else {
                                 woodstore = ApplianceStore.Lowes;
                                		}
                                
                                if(store2Field.getText().equals("HomeDepot"))  //sets lampstore variable using ApplianceStore enum
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
                                
                                
                                               
                                if(h>0 && w>0 && l>0 && o>0 && la>=0 && la<=5 && r>=0 && r<=3) { //proceeds if all values are positive and logical
                                	height = h;     //sets height, width, length, overhang, lamps, and receptacle values to collected h,w,l,o,la,r values respectively
                                	width = w;
                                	length = l;
                                	overhang = o;
                                	lamps = la;
                                	receptacles = r;
                                	
                                	table = TableMaker.makeTable(height, width, length, overhang, lamps, receptacles);  //creates table object using these values

                                double price = 	table.calculatePrice(woodstore, lampstore);  //uses calculatePrice method from table to calculate the estimated price using the woodstore and lampstore
                                	Instructions i = new Instructions(table);
                                	String instructions = (i.writeInstructions());  //creates instructions                                	

                                	tablePanel.setTable(table);  //sets table, length, width, height, and overhang values of tablePanel
                                	tablePanel.setLength(length);
                                	tablePanel.setWidth(width);
                                	tablePanel.setHeight(height);
                                	tablePanel.setOverhang(overhang);
                                    
                                	JLabel topLabel = new JLabel("Top View");  //label for Top View
                                	topLabel.setBounds(130,40,100,20);
                                	tablePanel.add(topLabel);
                                	
                                	JLabel bottomLabel = new JLabel("Bottom View"); //label for Bottom View
                                	bottomLabel.setBounds(410, 40, 100, 20);
                                	tablePanel.add(bottomLabel);
                                	
                                	JLabel sideLabel = new JLabel("Side View");  //label for Side View
                                	sideLabel.setBounds(670, 40, 100, 20);
                                	tablePanel.add(sideLabel);
                                	
                                	JLabel legLabel = new JLabel("Legs(4)");  //label for Leg View
                                	legLabel.setBounds(140, 270, 100, 20);
                                	tablePanel.add(legLabel);
                                	
                                	JLabel slatLabel = new JLabel("Slats");  //label for Slat View
                                	slatLabel.setBounds(360, 270, 100, 20);
                                	tablePanel.add(slatLabel);
                                	
                                	JLabel slat1Number = new JLabel("(2)");  //label for number of slat type 1
                                	slat1Number.setBounds(310, 300, 20, 20);
                                	tablePanel.add(slat1Number);
                                	
                                	JLabel slat2Number = new JLabel("(1)");  //label for number of slat type 2
                                	slat2Number.setBounds(310, 330, 20, 20);
                                	tablePanel.add(slat2Number);
                                
                                	tablePanel.setLayout(null);
                                	
                                	frame.setContentPane(tablePanel);
                                	frame.validate();
                                	
                                }
                                else {
                                	errorMessage.setText("Please enter valid dimensions");
                                }
                            }
                        	else {
                        		errorMessage.setText("Please fill in all the fields");
                        	}
                        }
            			else {
            					errorMessage.setText("Please fill in all the fields");
            	}
            
            }
        });
        
        //adds everything to the panel
        panel.add(heightLabel);
        panel.add(heightField);
        panel.add(widthLabel);
        panel.add(widthField);
        panel.add(lengthLabel);
        panel.add(lengthField);
        panel.add(overhangLabel);
        panel.add(overhangField);
        panel.add(lampLabel);
        panel.add(lampField);
        panel.add(receptacleLabel);
        panel.add(receptacleField);
        panel.add(store1Label);
        panel.add(store1Field);
        panel.add(store1options);
        panel.add(store2Label);
        panel.add(store2Field);
        panel.add(store2options);
        panel.add(b);
        panel.add(errorMessage);
        panel.setLayout(null);
        
        
        frame.setVisible(true);
        frame.setContentPane(panel);
        
        b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				if (heightField.getText().matches("[0-9]+") && widthField.getText().matches("[0-9]+")  //checks if input is valid before proceeding
						&& lengthField.getText().matches("[0-9]+") && overhangField.getText().matches("[0-9]+")
						&& lampField.getText().matches("[0-9]+") && receptacleField.getText().matches("[0-9]+")
						&& (store1Field.getText().equals("HomeDepot") || store1Field.getText().equals("Lowes"))
						&& (store2Field.getText().equals("HomeDepot") || store2Field.getText().equals("Lowes")
								|| store2Field.getText().equals("Ikea"))) {
					int h = Integer.parseInt(heightField.getText());  //converts height, width, length, overhang, number of lamps, and number of receptacle values to int
					int w = Integer.parseInt(widthField.getText());   //stores all these variables
					int l = Integer.parseInt(lengthField.getText());
					int o = Integer.parseInt(overhangField.getText());
					int la = Integer.parseInt(lampField.getText());
					int r = Integer.parseInt(receptacleField.getText());

					ApplianceStore woodstore;
					ApplianceStore lampstore;

					if (store1Field.getText().equals("HomeDepot")) {  //sets woodstore variable using ApplianceStore enum
						woodstore = ApplianceStore.HomeDepot;
					} else {
						woodstore = ApplianceStore.Lowes;
					}

					if (store2Field.getText().equals("HomeDepot")) {  //sets lampstore variable using ApplianceStore enum
						lampstore = ApplianceStore.HomeDepot;
					} else if (store2Field.getText().equals("Ikea")) {
						lampstore = ApplianceStore.Ikea;
					} else {
						lampstore = ApplianceStore.Lowes;
					}

					if (h > 0 && w > 0 && l > 0 && o > 0) {  //proceeds if all values are positive and logical
						height = h;       //sets height, width, length, overhang, lamps, and receptacle values to collected h,w,l,o,la,r values respectively
						width = w;
						length = l;
						overhang = o;
						lamps = la;
						receptacles = r;
						errorMessage.setText("success");

						scaleFactor = (int) getScaleFactor(height, width, 200, 200);
						table = TableMaker.makeTable(height, width, length, overhang, lamps, receptacles);  //creates table object using these values

						double price = table.calculatePrice(woodstore, lampstore);
						Instructions i = new Instructions(table);
						String instructions = (i.writeInstructions());

						tablePanel.setTable(table);
						tablePanel.setLength(length * scaleFactor);
						tablePanel.setWidth(width * scaleFactor);
						tablePanel.setHeight(height * scaleFactor);
						tablePanel.setOverhang(overhang * scaleFactor);

						JLabel topLabel = new JLabel("Top View");
						topLabel.setBounds(130, 40, 100, 20);
						tablePanel.add(topLabel);

						JLabel bottomLabel = new JLabel("Bottom View");
						bottomLabel.setBounds(410, 40, 100, 20);
						tablePanel.add(bottomLabel);

						JLabel sideLabel = new JLabel("Side View");
						sideLabel.setBounds(670, 40, 100, 20);
						tablePanel.add(sideLabel);

						JLabel priceLabel = new JLabel("Estimated Price:$" + price);
						priceLabel.setBounds(950, 40, 200, 20);
						tablePanel.add(priceLabel);

						JLabel legLabel = new JLabel("Legs(4)");
						legLabel.setBounds(140, 270, 100, 20);
						tablePanel.add(legLabel);

						JLabel slatLabel = new JLabel("Slats");
						slatLabel.setBounds(360, 270, 100, 20);
						tablePanel.add(slatLabel);

						JLabel slat1Number = new JLabel("(2)");
						slat1Number.setBounds(310, 300, 20, 20);
						tablePanel.add(slat1Number);

						JLabel slat2Number = new JLabel("(1)");
						slat2Number.setBounds(310, 330, 20, 20);
						tablePanel.add(slat2Number);

						JTextArea instructLabel = new JTextArea(instructions);
						instructLabel.setBounds(600, 270, 500, 250);
						tablePanel.add(instructLabel);

						tablePanel.setLayout(null);

						frame.setContentPane(tablePanel);
						frame.validate();

					} else {
						errorMessage.setText("Please enter valid dimensions.");
					}
				} else {
					if (heightField.getText().charAt(0) == '-' || widthField.getText().charAt(0) == '-'
							|| lengthField.getText().charAt(0) == '-' || overhangField.getText().charAt(0) == '-') {
						errorMessage.setText("Please enter valid dimensions");
					} else
						errorMessage.setText("Please enter correct store options");
				}
			}
		});

		panel.add(heightLabel);
		panel.add(heightField);
		panel.add(widthLabel);
		panel.add(widthField);
		panel.add(lengthLabel);
		panel.add(lengthField);
		panel.add(overhangLabel);
		panel.add(overhangField);
		panel.add(lampLabel);
		panel.add(lampField);
		panel.add(receptacleLabel);
		panel.add(receptacleField);
		panel.add(store1Label);
		panel.add(store1Field);
		panel.add(store1options);
		panel.add(store2Label);
		panel.add(store2Field);
		panel.add(store2options);
		panel.add(b);
		panel.add(errorMessage);
		panel.setLayout(null);

		frame.setVisible(true);
		frame.setContentPane(panel);
	}

}

class TableJPanel extends JPanel {
	int length;
	int width;
	int height;
	int overhang;
	Table table;

	public TableJPanel(Table table, int length, int width, int height, int overhang) {
		this.table = table;
		this.length = length;
		this.width = width;
		this.height = height;
		this.overhang = overhang;
	}

	
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        int topScaleFactor = (int)Portal.getScaleFactor(width, length, 200, 200);  //creates scale factor for top view
        Shape topView = new Rectangle(70, 70, length * topScaleFactor, width * topScaleFactor); //creates rectangle that shows top view
        
        
        int bottomScaleFactor = (int)Portal.getScaleFactor(width, length, 200, 200);  //creates scale factor for bottom view
        Shape rect = new Rectangle(350, 70, length * bottomScaleFactor, width * bottomScaleFactor);//table top
        
        //legs
        Shape leg1 = new Rectangle(350 + (overhang * bottomScaleFactor), 70 + (overhang * bottomScaleFactor), (int)(3.5 * bottomScaleFactor),(int)(1.5 * bottomScaleFactor));
        Shape leg2 = new Rectangle((int)(350 + (length * bottomScaleFactor) - (overhang * bottomScaleFactor) - (3.5 * bottomScaleFactor)), 70 + (overhang * bottomScaleFactor), (int)(3.5 * bottomScaleFactor),(int)(1.5 * bottomScaleFactor));
        Shape leg3 = new Rectangle((int)(350 + (overhang * bottomScaleFactor)), (int)(70 + (width * bottomScaleFactor) - (overhang * bottomScaleFactor) - (1.5 * bottomScaleFactor)), (int)(3.5 * bottomScaleFactor),(int)(1.5 * bottomScaleFactor));
        Shape leg4 = new Rectangle((int)(350 + (length * bottomScaleFactor) - (overhang * bottomScaleFactor) - (3.5 * bottomScaleFactor)), (int)(70 + (width * bottomScaleFactor) - (overhang * bottomScaleFactor) - (1.5 * bottomScaleFactor)), (int)(3.5 * bottomScaleFactor),(int)(1.5 * bottomScaleFactor));
        
        //slats
        Shape slat1 = new Rectangle(350 +(overhang * bottomScaleFactor), 70 + (overhang * bottomScaleFactor), (length * bottomScaleFactor) - (overhang * bottomScaleFactor*2), (int)(1.5 * bottomScaleFactor));
        Shape slat2 = new Rectangle(350 +(overhang * bottomScaleFactor), 70 + (overhang * bottomScaleFactor), (int)(3.5 * bottomScaleFactor), (width * bottomScaleFactor) - (overhang * bottomScaleFactor*2));
        Shape slat3 = new Rectangle((int)(350 + (length * bottomScaleFactor) - (overhang * bottomScaleFactor) - (3.5 * bottomScaleFactor)), 70 + (overhang * bottomScaleFactor), (int)(3.5 * bottomScaleFactor), (width * bottomScaleFactor) - (overhang * bottomScaleFactor*2));
        
        //scale factor for side view
        int sideScaleFactor = (int)Portal.getScaleFactor(height, length, 200, 200);
        //side view table top
        Shape tableTop = new Rectangle(600, 70, length * sideScaleFactor, (int)(0.8* sideScaleFactor));
        //side view legs
        Shape sideLeg1 = new Rectangle(600+(overhang * sideScaleFactor), (int)(70 + (0.8* sideScaleFactor)), (int)(3.5*sideScaleFactor), (int)((height * sideScaleFactor) - (0.8* sideScaleFactor)));
        Shape sideLeg2 = new Rectangle((int)(600+(length * sideScaleFactor)-(overhang * sideScaleFactor) -(3.5* sideScaleFactor)), (int)(70 + (0.8* sideScaleFactor)), (int)(3.5*sideScaleFactor), (int)((height * sideScaleFactor) - (0.8* sideScaleFactor)));
        //side view slats
        Shape sideSlat = new Rectangle(600 + (overhang * sideScaleFactor), (int)(70 + (0.8* sideScaleFactor)), (length * sideScaleFactor) - ((overhang * sideScaleFactor)*2), (int)(3.5*sideScaleFactor));
        
        //scale factor for leg view
        int legScaleFactor = (int)Portal.getScaleFactor(height - 0.8, 3.5, 200, 200);
        //draws leg using leg scale factor
        Shape legView = new Rectangle(150, 300, (int)(3.5 * legScaleFactor), (int)((height -0.8) * legScaleFactor));
        
        //scale factor for slat type 1
        int slatScaleFactor1 = (int)Portal.getScaleFactor(3.5, width-(overhang*2), 20, 150);
        //draws slat type 1
        Shape slatView1 = new Rectangle(350, 300, (width * slatScaleFactor1)-(overhang * slatScaleFactor1*2), (int)(3.5 * slatScaleFactor1));
        //scale factor for slat type 2
        int slatScaleFactor2 = (int)Portal.getScaleFactor(3.5, length - (overhang*2), 20, 50);
        //draws slat type 2
        Shape slatView2 = new Rectangle(350, 300, (length * slatScaleFactor2)-(overhang * slatScaleFactor2*2), (int)(3.5 * slatScaleFactor2));
        
      
       
        BufferedImage lampimg = null;  //image for lamp
        try {
           lampimg = ImageIO.read(new File(table.getLampImage(table.getLampStoreEnum())));
        } catch (IOException e) {
        }
        BufferedImage legimg = null;  //image for leg
        try {
           legimg = ImageIO.read(new File(table.getLegImage(table.getWoodStoreEnum())));
        } catch (IOException e) {
        }
        BufferedImage plugimg = null;  //image for plug
        try {
           plugimg = ImageIO.read(new File(table.getReceptImage(table.getWoodStoreEnum())));
        } catch (IOException e) {
        }
        
        BufferedImage switchimg = null;  //image for switch
		try {
			switchimg = ImageIO.read(new File(table.getSwitchImage(table.getWoodStoreEnum())));
		} catch (IOException e) {
		}
		BufferedImage topimg = null;  //image for table top
		try {
			topimg = ImageIO.read(new File(table.getTopImage(table.getWoodStoreEnum())));
		} catch (IOException e) {
		}

        //adds all elements to table panel
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
        g2.draw(sideSlat);
        g2.draw(legView);
        g2.draw(slatView1);
        g2.draw(slatView2);
        g2.drawImage(lampimg, 250, 550, 100, 100, null);
        g2.drawImage(legimg, 400, 550, 100, 100, null);
        g2.drawImage(plugimg, 550, 550, 100, 100, null);
        g2.drawImage(switchimg, 700, 550, 100, 100, null);
		g2.drawImage(topimg, 850, 550, 100, 100, null);
        
    }
    
    //setter methods for table so it can be set outside class
    public void setTable(Table table) {
    	this.table = table;
    }
    
   //setter methods for length so it can be set outside class
    public void setLength(int length) {
    	this.length = length;
    }
    
    //setter methods for width so it can be set outside class
    public void setWidth(int width) {
    	this.width = width;
    }
    
    //setter methods for height so it can be set outside class
    public void setHeight(int height) {
    	this.height = height;
    }
    
    //setter methods for overhang so it can be set outside class
	public void setOverhang(int overhang) {
		this.overhang = overhang;
	}

}
