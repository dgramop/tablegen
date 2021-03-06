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
        frame.setSize(1200, 700);
        
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        
        TableJPanel panel = new TableJPanel(null, 0,0,0,0);
       
        JLabel errorMessage = new JLabel("");
        errorMessage.setBounds(200, 300, 180, 20);
       
        JLabel heightLabel = new JLabel("Enter Table Height(inches):");
        heightLabel.setBounds(100,50,180,20);
        JTextField heightField = new JTextField();
        heightField.setBounds(300, 50, 150, 20);
      //creation of label and text field for table height
        
        JLabel widthLabel = new JLabel("Enter Table Width(inches):");
        widthLabel.setBounds(100,100,180,20);
        JTextField widthField = new JTextField();
        widthField.setBounds(300, 100, 150, 20);
      //creation of label and text field for table width
        
        JLabel lengthLabel = new JLabel("Enter Table Length(inches):");
        lengthLabel.setBounds(100,150,180,20);
        JTextField lengthField = new JTextField();
        lengthField.setBounds(300, 150, 150, 20);
      //creation of label and text field for table length
        
        JLabel overhangLabel = new JLabel("Enter Overhang Value(inches):");
        overhangLabel.setBounds(100,200,180,20);
        JTextField overhangField = new JTextField();
        overhangField.setBounds(300, 200, 150, 20);
      //creation of label and text field for table overhang value
        
        JLabel lampLabel = new JLabel("Enter Number of Lamps(0-5):");
        lampLabel.setBounds(500,50,180,20);
        JTextField lampField = new JTextField();
        lampField.setBounds(720, 50, 150, 20);
      //creation of label and text field for number of table lamps
        
        JLabel receptacleLabel = new JLabel("Enter Number of Receptacles(0-3):");
        receptacleLabel.setBounds(500,100,200,20);
        JTextField receptacleField = new JTextField();
        receptacleField.setBounds(720, 100, 150, 20);
      //creation of label and text field for number of receptacles
        
        JLabel store1Label = new JLabel("Enter Store for Wood/Electric:");
        store1Label.setBounds(500,150,200,20);
        JLabel store1options = new JLabel("(Lowes, HomeDepot)");
        store1options.setBounds(500,170,200,20);
        JTextField store1Field = new JTextField();
        store1Field.setBounds(720, 150, 150, 20);
      //creation of label and text field for store for wood and electronic
        
        JLabel store2Label = new JLabel("Enter Store for Lamps:");
        store2Label.setBounds(500,200,200,20);
        JLabel store2options = new JLabel("(Ikea, Lowes, HomeDepot)");
        store2options.setBounds(500,220,200,20);
        JTextField store2Field = new JTextField();
        store2Field.setBounds(720, 200, 150, 20);
      //creation of label and text field for store for lamps
        
        JButton b = new JButton("Submit");
        b.setBounds(450, 300, 100, 20);
        
        
        b.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
            	//check if boxes are all filled
            	//makes sure height, length, width, and overhang values are all numbers
            	if(heightField.getText().matches("[0-9]+") &&
            	widthField.getText().matches("[0-9]+") &&
            	lengthField.getText().matches("[0-9]+") &&
            	overhangField.getText().matches("[0-9]+") &&
            	lampField.getText().matches("[0-9]+") &&
            	receptacleField.getText().matches("[0-9]+")&&
            	(store1Field.getText().equals("HomeDepot")||store1Field.getText().equals("Lowes"))&&
            		(store2Field.getText().equals("HomeDepot")||store2Field.getText().equals("Lowes")||store2Field.getText().equals("Ikea"))){
            		//makes sure store values are correct options
            		int h = Integer.parseInt(heightField.getText());
                    int w = Integer.parseInt(widthField.getText());
                    int l = Integer.parseInt(lengthField.getText());
                    int o = Integer.parseInt(overhangField.getText());
                    int la = Integer.parseInt(lampField.getText());
                    int r = Integer.parseInt(receptacleField.getText());
                  //converts height, width, length, overhang, number of lamps, and number of receptacle values to int
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
                    	//proceeds if all values are positive and logical
                    	//sets height, width, length, overhang, lamps, and receptacle values to collected h,w,l,o,la,r values respectively
                    	height = h;
                    	width = w;
                    	length = l;
                    	overhang = o;
                    	lamps = la;
                    	receptacles = r;
                    	errorMessage.setText("success");
                    	
                    	
                    	//scaleFactor = (int)getScaleFactor(height, width, 200, 200);
                    	table = TableMaker.makeTable(height, width, length, overhang, lamps, receptacles);
                    	//creates table object using these values
                    double price = 	table.calculatePrice(woodstore, lampstore);
                  //uses calculatePrice method from table to calculate the estimated price using the woodstore and lampstore	
                    Instructions i = new Instructions(table);
                    	String instructions = (i.writeInstructions());
                    	

                    	panel.setTable(table);
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
                    /*	
                    	JLabel priceLabel = new JLabel("Estimated Price:$"+ price);
                    	priceLabel.setBounds(850, 40, 200, 20);
                    	panel.add(priceLabel);
                   */ 	
                    	JLabel legLabel = new JLabel("Legs(4)");
                    	legLabel.setBounds(140, 270, 100, 20);
                    	panel.add(legLabel);
                    	
                    	JLabel slatLabel = new JLabel("Slats");
                    	slatLabel.setBounds(360, 270, 100, 20);
                    	panel.add(slatLabel);
                    	
                    	JLabel slat1Number = new JLabel("(2)");
                    	slat1Number.setBounds(310, 300, 20, 20);
                    	panel.add(slat1Number);
                    	
                    	JLabel slat2Number = new JLabel("(1)");
                    	slat2Number.setBounds(310, 330, 20, 20);
                    	panel.add(slat2Number);
                    	
                    /*	JLabel instructLabel = new JLabel(instructions);
                    	instructLabel.setBounds(450, 330, 500, 100);
                    	panel.add(instructLabel);
                    */
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
        panel1.add(store1options);
        panel1.add(store2Label);
        panel1.add(store2Field);
        panel1.add(store2options);
        panel1.add(b);
        panel1.add(errorMessage);
        panel1.setLayout(null);
        
        
        frame.setVisible(true);
        frame.setContentPane(panel1);
        
        

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1200, 700);

		/*JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();

		TableJPanel panel = new TableJPanel(null, 0, 0, 0, 0);

		JLabel errorMessage = new JLabel("");
		errorMessage.setBounds(250, 200, 300, 100);

		JLabel heightLabel = new JLabel("Enter Table Height(inches):");
		heightLabel.setBounds(100, 50, 180, 20);
		JTextField heightField = new JTextField();
		heightField.setBounds(300, 50, 150, 20);

		JLabel widthLabel = new JLabel("Enter Table Width(inches):");
		widthLabel.setBounds(100, 100, 180, 20);
		JTextField widthField = new JTextField();
		widthField.setBounds(300, 100, 150, 20);

		JLabel lengthLabel = new JLabel("Enter Table Length(inches):");
		lengthLabel.setBounds(100, 150, 180, 20);
		JTextField lengthField = new JTextField();
		lengthField.setBounds(300, 150, 150, 20);

		JLabel overhangLabel = new JLabel("Enter Overhang Value(inches):");
		overhangLabel.setBounds(100, 200, 180, 20);
		JTextField overhangField = new JTextField();
		overhangField.setBounds(300, 200, 150, 20);

		JLabel lampLabel = new JLabel("Enter Number of Lamps(0-5):");
		lampLabel.setBounds(500, 50, 180, 20);
		JTextField lampField = new JTextField();
		lampField.setBounds(720, 50, 150, 20);

		JLabel receptacleLabel = new JLabel("Enter Number of Receptacles(0-3):");
		receptacleLabel.setBounds(500, 100, 200, 20);
		JTextField receptacleField = new JTextField();
		receptacleField.setBounds(720, 100, 150, 20);

		JLabel store1Label = new JLabel("Enter Store for Wood/Electric:");
		store1Label.setBounds(500, 150, 200, 20);
		JLabel store1options = new JLabel("(Lowes, HomeDepot)");
		store1options.setBounds(500, 170, 200, 20);
		JTextField store1Field = new JTextField();
		store1Field.setBounds(720, 150, 150, 20);

		JLabel store2Label = new JLabel("Enter Store for Lamps:");
		store2Label.setBounds(500, 200, 200, 20);
		JLabel store2options = new JLabel("(Ikea, Lowes, HomeDepot)");
		store2options.setBounds(500, 220, 200, 20);
		JTextField store2Field = new JTextField();
		store2Field.setBounds(720, 200, 150, 20);

		JButton b = new JButton("Submit");
		b.setBounds(450, 300, 100, 20);*/

		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// check if boxes are all filled

				if (heightField.getText().matches("[0-9]+") && widthField.getText().matches("[0-9]+")
						&& lengthField.getText().matches("[0-9]+") && overhangField.getText().matches("[0-9]+")
						&& lampField.getText().matches("[0-9]+") && receptacleField.getText().matches("[0-9]+")
						&& (store1Field.getText().equals("HomeDepot") || store1Field.getText().equals("Lowes"))
						&& (store2Field.getText().equals("HomeDepot") || store2Field.getText().equals("Lowes")
								|| store2Field.getText().equals("Ikea"))) {
					int h = Integer.parseInt(heightField.getText());
					int w = Integer.parseInt(widthField.getText());
					int l = Integer.parseInt(lengthField.getText());
					int o = Integer.parseInt(overhangField.getText());
					int la = Integer.parseInt(lampField.getText());
					int r = Integer.parseInt(receptacleField.getText());

					ApplianceStore woodstore;
					ApplianceStore lampstore;

					if (store1Field.getText().equals("HomeDepot")) {
						woodstore = ApplianceStore.HomeDepot;
					} else {
						woodstore = ApplianceStore.Lowes;
					}

					if (store2Field.getText().equals("HomeDepot")) {
						lampstore = ApplianceStore.HomeDepot;
					} else if (store2Field.getText().equals("Ikea")) {
						lampstore = ApplianceStore.Ikea;
					} else {
						lampstore = ApplianceStore.Lowes;
					}

					if (h > 0 && w > 0 && l > 0 && o > 0) {
						height = h;
						width = w;
						length = l;
						overhang = o;
						lamps = la;
						receptacles = r;
						errorMessage.setText("success");

						scaleFactor = (int) getScaleFactor(height, width, 200, 200);
						table = TableMaker.makeTable(height, width, length, overhang, lamps, receptacles);

						double price = table.calculatePrice(woodstore, lampstore);
						Instructions i = new Instructions(table);
						String instructions = (i.writeInstructions());

						panel.setTable(table);
						panel.setLength(length * scaleFactor);
						panel.setWidth(width * scaleFactor);
						panel.setHeight(height * scaleFactor);
						panel.setOverhang(overhang * scaleFactor);

						JLabel topLabel = new JLabel("Top View");
						topLabel.setBounds(130, 40, 100, 20);
						panel.add(topLabel);

						JLabel bottomLabel = new JLabel("Bottom View");
						bottomLabel.setBounds(410, 40, 100, 20);
						panel.add(bottomLabel);

						JLabel sideLabel = new JLabel("Side View");
						sideLabel.setBounds(670, 40, 100, 20);
						panel.add(sideLabel);

						JLabel priceLabel = new JLabel("Estimated Price:$" + price);
						priceLabel.setBounds(950, 40, 200, 20);
						panel.add(priceLabel);

						JLabel legLabel = new JLabel("Legs(4)");
						legLabel.setBounds(140, 270, 100, 20);
						panel.add(legLabel);

						JLabel slatLabel = new JLabel("Slats");
						slatLabel.setBounds(360, 270, 100, 20);
						panel.add(slatLabel);

						JLabel slat1Number = new JLabel("(2)");
						slat1Number.setBounds(310, 300, 20, 20);
						panel.add(slat1Number);

						JLabel slat2Number = new JLabel("(1)");
						slat2Number.setBounds(310, 330, 20, 20);
						panel.add(slat2Number);

						JTextArea instructLabel = new JTextArea(instructions);
						instructLabel.setBounds(600, 270, 500, 250);
						panel.add(instructLabel);

						panel.setLayout(null);

						frame.setContentPane(panel);
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
		panel1.add(store1options);
		panel1.add(store2Label);
		panel1.add(store2Field);
		panel1.add(store2options);
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
	Table table;

	public TableJPanel(Table table, int length, int width, int height, int overhang) {
		this.table = table;
		this.length = length;
		this.width = width;
		this.height = height;
		this.overhang = overhang;
	}

	/*@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		Shape topView = new Rectangle(70, 70, (int) (length), (int) (width));

		Shape rect = new Rectangle(350, 70, length, width);
		Shape leg1 = new Rectangle(350 + overhang, 70 + overhang, 20, 10);
		Shape leg2 = new Rectangle(350 + length - overhang - 20, 70 + overhang, 20, 10);
		Shape leg3 = new Rectangle(350 + overhang, 70 + width - overhang - 10, 20, 10);
		Shape leg4 = new Rectangle(350 + length - overhang - 20, 70 + width - overhang - 10, 20, 10);

		Shape slat1 = new Rectangle(350 + overhang, 70 + overhang, length - (overhang * 2), 10);
		Shape slat2 = new Rectangle(350 + overhang, 70 + overhang, 20, width - (overhang * 2));
		Shape slat3 = new Rectangle(350 + length - overhang - 20, 70 + overhang, 20, width - (overhang * 2));

		Shape tableTop = new Rectangle(600, 70, length, 4);
		Shape sideLeg1 = new Rectangle(600 + overhang, 74, 20, height - 4);
		Shape sideLeg2 = new Rectangle(600 + length - overhang - 20, 74, 20, height - 4);

		Shape legView = new Rectangle(150, 300, 20, height - 4);

		Shape slatView1 = new Rectangle(350, 300, width - (overhang * 2), 20);
		Shape slatView2 = new Rectangle(350, 330, length - (overhang * 2), 20);

		BufferedImage lampimg = null;
		try {
			lampimg = ImageIO.read(new File(table.getLampImage(table.getLampStoreEnum())));
		} catch (IOException e) {
		}
		BufferedImage legimg = null;
		try {
			legimg = ImageIO.read(new File(table.getLegImage(table.getWoodStoreEnum())));
		} catch (IOException e) {
		}
		BufferedImage plugimg = null;
		try {
			plugimg = ImageIO.read(new File(table.getReceptImage(table.getWoodStoreEnum())));
		} catch (IOException e) {
		}
		BufferedImage switchimg = null;
		try {
			switchimg = ImageIO.read(new File(table.getSwitchImage(table.getWoodStoreEnum())));
		} catch (IOException e) {
		}
		BufferedImage topimg = null;
		try {
			topimg = ImageIO.read(new File(table.getTopImage(table.getWoodStoreEnum())));
		} catch (IOException e) {
		}

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
		g2.draw(legView);
		g2.draw(slatView1);
		g2.draw(slatView2);
		g2.drawImage(lampimg, 250, 550, 100, 100, null);
		g2.drawImage(legimg, 400, 550, 100, 100, null);
		g2.drawImage(plugimg, 550, 550, 100, 100, null);
		g2.drawImage(switchimg, 700, 550, 100, 100, null);
		g2.drawImage(topimg, 850, 550, 100, 100, null);
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}*/

	
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        //creates scale factor for top view
        int topScaleFactor = (int)Portal.getScaleFactor(width, length, 200, 200);
        Shape topView = new Rectangle(70, 70, length * topScaleFactor, width * topScaleFactor);
      //creates rectangle that shows top view
      //creates scale factor for bottom view
        int bottomScaleFactor = (int)Portal.getScaleFactor(width, length, 200, 200);
        Shape rect = new Rectangle(350, 70, length * bottomScaleFactor, width * bottomScaleFactor);
        Shape leg1 = new Rectangle(350 + (overhang * bottomScaleFactor), 70 + (overhang * bottomScaleFactor), (int)(3.5 * bottomScaleFactor),(int)(1.5 * bottomScaleFactor));
        Shape leg2 = new Rectangle((int)(350 + (length * bottomScaleFactor) - (overhang * bottomScaleFactor) - (3.5 * bottomScaleFactor)), 70 + (overhang * bottomScaleFactor), (int)(3.5 * bottomScaleFactor),(int)(1.5 * bottomScaleFactor));
        Shape leg3 = new Rectangle((int)(350 + (overhang * bottomScaleFactor)), (int)(70 + (width * bottomScaleFactor) - (overhang * bottomScaleFactor) - (1.5 * bottomScaleFactor)), (int)(3.5 * bottomScaleFactor),(int)(1.5 * bottomScaleFactor));
        Shape leg4 = new Rectangle((int)(350 + (length * bottomScaleFactor) - (overhang * bottomScaleFactor) - (3.5 * bottomScaleFactor)), (int)(70 + (width * bottomScaleFactor) - (overhang * bottomScaleFactor) - (1.5 * bottomScaleFactor)), (int)(3.5 * bottomScaleFactor),(int)(1.5 * bottomScaleFactor));
        
        Shape slat1 = new Rectangle(350 +(overhang * bottomScaleFactor), 70 + (overhang * bottomScaleFactor), (length * bottomScaleFactor) - (overhang * bottomScaleFactor*2), (int)(1.5 * bottomScaleFactor));
        Shape slat2 = new Rectangle(350 +(overhang * bottomScaleFactor), 70 + (overhang * bottomScaleFactor), (int)(3.5 * bottomScaleFactor), (width * bottomScaleFactor) - (overhang * bottomScaleFactor*2));
        Shape slat3 = new Rectangle((int)(350 + (length * bottomScaleFactor) - (overhang * bottomScaleFactor) - (3.5 * bottomScaleFactor)), 70 + (overhang * bottomScaleFactor), (int)(3.5 * bottomScaleFactor), (width * bottomScaleFactor) - (overhang * bottomScaleFactor*2));
        
        //scale factor for side view
        int sideScaleFactor = (int)Portal.getScaleFactor(height, length, 200, 200);
        Shape tableTop = new Rectangle(600, 70, length * sideScaleFactor, (int)(0.8* sideScaleFactor));
        Shape sideLeg1 = new Rectangle(600+(overhang * sideScaleFactor), (int)(70 + (0.8* sideScaleFactor)), (int)(3.5*sideScaleFactor), (int)((height * sideScaleFactor) - (0.8* sideScaleFactor)));
        Shape sideLeg2 = new Rectangle((int)(600+(length * sideScaleFactor)-(overhang * sideScaleFactor) -(3.5* sideScaleFactor)), (int)(70 + (0.8* sideScaleFactor)), (int)(3.5*sideScaleFactor), (int)((height * sideScaleFactor) - (0.8* sideScaleFactor)));
        Shape sideSlat = new Rectangle(600 + (overhang * sideScaleFactor), (int)(70 + (0.8* sideScaleFactor)), (length * sideScaleFactor) - ((overhang * sideScaleFactor)*2), (int)(3.5*sideScaleFactor));
        
        int legScaleFactor = (int)Portal.getScaleFactor(height - 0.8, 3.5, 200, 200);
        Shape legView = new Rectangle(150, 300, (int)(3.5 * legScaleFactor), (int)((height -0.8) * legScaleFactor));
        
        int slatScaleFactor1 = (int)Portal.getScaleFactor(3.5, width-(overhang*2), 200, 200);
        Shape slatView1 = new Rectangle(350, 300, (width * slatScaleFactor1)-(overhang * slatScaleFactor1*2), (int)(3.5 * slatScaleFactor1));
        int slatScaleFactor2 = (int)Portal.getScaleFactor(3.5, length - (overhang*2), 200, 200);
        Shape slatView2 = new Rectangle(350, 330, (length * slatScaleFactor2) - (overhang*2), (int)(3.5 * slatScaleFactor2));
        
      
      //image for lamp
        BufferedImage lampimg = null;
        try {
           lampimg = ImageIO.read(new File(table.getLampImage(table.getLampStoreEnum())));
        } catch (IOException e) {
        }
      //image for leg
        BufferedImage legimg = null;
        try {
           legimg = ImageIO.read(new File(table.getLegImage(table.getWoodStoreEnum())));
        } catch (IOException e) {
        }
      //image for receptacle
        BufferedImage plugimg = null;
        try {
           plugimg = ImageIO.read(new File(table.getReceptImage(table.getWoodStoreEnum())));
        } catch (IOException e) {
        }
      //image for switch
        BufferedImage switchimg = null;
		try {
			switchimg = ImageIO.read(new File(table.getSwitchImage(table.getWoodStoreEnum())));
		} catch (IOException e) {
		}
		//image for table top
		BufferedImage topimg = null;
		try {
			topimg = ImageIO.read(new File(table.getTopImage(table.getWoodStoreEnum())));
		} catch (IOException e) {
		}

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
    
    public void setTable(Table table) {
    	this.table = table;
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
    
    /*public void setOverhang(int overhang) {
    	this.overhang = overhang;
    }*/
    
    


	public void setOverhang(int overhang) {
		this.overhang = overhang;
	}

}
