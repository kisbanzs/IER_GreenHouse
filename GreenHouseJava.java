// Environment code for project GreenHouse.mas2j

import jason.asSyntax.*;
import jason.environment.*;
import java.util.logging.*;
import java.util.*;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;
import java.awt.Dimension;
import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.lang.*;



public class GreenHouseJava extends Environment {
	private GreenHouseGraphics kinezet;
	private int novekedes = 0;
	private boolean water = false;
	private boolean ontoz = false;
	private boolean riaszt = false;
	private boolean hut = false;
	private boolean fut = false;
	private boolean resetBoolean = false;
	private boolean manual = false;
	private int homerseklet =(new Random()).nextInt(50);
	
	public boolean getManual() {
		return manual;
	}
	
	public void setManual(boolean tmp){
		manual = tmp;
	}
	
	public boolean getRiaszt() {
		return riaszt;
	}
	
	public void setRiaszt(boolean tmp){
		riaszt = tmp;
	}
	
	public boolean getHut() {
		return hut;
	}
	
	public void setHut(boolean tmp){
		hut = tmp;
	}
	
	public boolean getFut() {
		return fut;
	}

	
	public void setFut(boolean tmp){
		fut = tmp;
	}
	
	public void setOntoz(boolean tmp){
		ontoz = tmp;
	}
	
	public boolean getOntoz() {
		return ontoz;
	}
	
	public boolean getWater() {
		return water;
	}
	
	public void setWater(boolean tmp){
		water = tmp;
	}
	
	public int getNovekedes(){
		return novekedes;
	}
	
	public void setNovekedes(int tmp) {
		novekedes=tmp;
	}
	
	public int getHomerseklet() {
		return homerseklet;
	}
	
	public boolean getResetBoolean() {
		return resetBoolean;
	}
	
	public void setResetBoolean(boolean tmp) {
		resetBoolean = tmp;
	}
	
	public void setHomerseklet(int a){
		homerseklet = a;
	}

    private Logger logger = Logger.getLogger("GreenHouse.mas2j."+GreenHouseJava.class.getName());
	public GreenHouseGUI gui = new GreenHouseGUI();
	
	private Object modelLock = new Object(); 

    /** Called before the MAS execution with the args informed in .mas2j */

	public GreenHouseJava(){		
		new Thread() {
            public void run() {
                try {
                    while (isRunning()) {
						 while (getManual() == true) {
							 if(getOntoz()==true) {
								if(getNovekedes()<3){
									setWater(true);
									kinezet.repaint();
									Thread.sleep(700);
									setWater(false);
									setNovekedes(getNovekedes()+1);
									kinezet.repaint();
								}
								else {
									setNovekedes(0);
									kinezet.repaint();
								}
								setOntoz(false);
							 }
							 gui.control();
							 Thread.sleep(4000);
						 }
						 while(getHomerseklet() > 30 && getHomerseklet() < 41 && getManual() == false) {
							 setHut(true);
							 setFut(false);
							 setRiaszt(false);
							 gui.control();
							 Thread.sleep(4000);
						 }
						 while(getHomerseklet() > 40 && getManual() == false) {
							 setHut(false);
							 setFut(false);
							 setRiaszt(true);
							 gui.control();
							 Thread.sleep(4000);
						 }
						 while(getHomerseklet() < 5 && getManual() == false) {
							 setHut(false);
							 setFut(true);
							 setRiaszt(false);
							 gui.control();
							 Thread.sleep(4000);
						 }
						 while(getHomerseklet() > 4 && getHomerseklet() < 31 && getManual() == false) {
							 gui.label.setText("");
							 setResetBoolean(false);
							 gui.resetSet();
							 if(getOntoz()==true) {
								if(getNovekedes()<3){
									setWater(true);
									kinezet.repaint();
									Thread.sleep(700);
									setWater(false);
									setNovekedes(getNovekedes()+1);
									kinezet.repaint();
								}
								else {
									setNovekedes(0);
									kinezet.repaint();
								}
								setOntoz(false);
							 }
							 Thread.sleep(1000);						 
						 }
						 Thread.sleep(1000);
					}
                } catch (Exception e) {} 
            }
        }.start();  
	}
	
    @Override
    public void init(String[] args) {
      super.init(args);
        /*addPercept(ASSyntax.parseLiteral("percept(demo)"));*/
    }


	
	
	 class GreenHouseGUI extends JFrame {
		 JPanel panel = new JPanel();
		 JPanel grafikuspanel = new JPanel();
		 public JLabel label = new JLabel();
		 JButton riaszt = new JButton("Riasztas");
		 JButton ontoz = new JButton("Ontozes");
		 JButton hut = new JButton("Hutes"); 
		 JButton fut = new JButton("Futes");
		 JButton uj_hom_btn = new JButton("Uj homerseklet");
		 JTextArea uj_hom_txt = new JTextArea();
		 JLabel akt_hom_txt = new JLabel("Aktualis homerseklet: " + getHomerseklet());
		 JButton reset = new JButton("Reset");
		 
		 public void control(){
			 if(getFut()==true){
				 label.setText(gui.fut.getText());
				 setResetBoolean(true);
				 resetSet();
				 setHomerseklet(getHomerseklet()+1);
				 homersekletSet();
			 }
			 if(getHut()==true){
				 label.setText(gui.hut.getText());
				 setResetBoolean(true);
				 resetSet();
				 setHomerseklet(getHomerseklet()-1);
				 homersekletSet();
			 }
			 if(getRiaszt()==true){
				/* label.setText(gui.riaszt.getText());
				 setResetBoolean(true);
				 resetSet();
				 setHomerseklet(getHomerseklet()-1);
				 homersekletSet();*/
			 }
		 }
		 
		 public void resetSet(){
			 if (getResetBoolean()==false){
				 reset.setVisible(false);
				 hut.setEnabled(true);
				 fut.setEnabled(true);
				 riaszt.setEnabled(true);
				 ontoz.setEnabled(true);
			}
			if (getResetBoolean()==true){
				reset.setVisible(true);
				hut.setEnabled(false);
				fut.setEnabled(false);
				riaszt.setEnabled(false);
				ontoz.setEnabled(false);
			}
		 }
		 
		 public void homersekletSet(){
			 akt_hom_txt.setText("Aktualis homerseklet: " + getHomerseklet());
		 }
					
		 GreenHouseGUI(){
			 super("Okos UvegHaz");
			 		  
			 setPreferredSize(new Dimension(800,600));
			 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
			 kinezet = new GreenHouseGraphics();
			 kinezet.setBounds(75, 15, 700, 400);
			 this.add(kinezet);
			
			 this.add(panel, BorderLayout.CENTER);
			 panel.setLayout(null);
			 
			 this.pack();
			
			riaszt.setBounds(120, 375, 142, 23);
			panel.add(riaszt);
			riaszt.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					label.setText(riaszt.getText());
					setResetBoolean(true);
					resetSet();
					setFut(false);
					setHut(false);
					setRiaszt(true);
					setManual(true);
				}
			});
			
			ontoz.setBounds(120, 425, 142, 23);
			panel.add(ontoz);
			ontoz.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try{
					addPercept(ASSyntax.parseLiteral("watercommand"));
					System.out.println("WATERCOMMAND");
					} catch (Exception ex) {
		            }
				}
			});
			
			hut.setBounds(320, 425, 142, 23);
			panel.add(hut);
			hut.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					label.setText(hut.getText());
					setResetBoolean(true);
					resetSet();
					setFut(false);
					setHut(true);
					setRiaszt(false);
					setManual(true);
				}
			});
		
			fut.setBounds(320, 375, 142, 23);
			panel.add(fut);
			fut.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					label.setText(fut.getText());
					setResetBoolean(true);
					resetSet();
					setFut(true);
					setHut(false);
					setRiaszt(false);
					setManual(true);
				}
			});
			
			uj_hom_btn.setBounds(545, 425, 120, 23);
			panel.add(uj_hom_btn);
			uj_hom_btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try{
					setHomerseklet(Integer.parseInt(uj_hom_txt.getText()));
					} catch (Exception ex){
						System.out.println("Nem megfelelo homerseklet ertek");
					}
					homersekletSet();
					setManual(false);
				}
			});
			
			uj_hom_txt.setBounds(495, 425, 42, 23);  
			panel.add(uj_hom_txt);
			
			akt_hom_txt.setBounds(520, 365, 300, 40);
			panel.add(akt_hom_txt);
			
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setBounds(400, 460, 100, 40);
			//label.setForeground(Color.RED);
			panel.add(label);
			
			reset.setBounds(400, 510, 100, 23);
			resetSet();
			panel.add(reset);
			
			reset.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					label.setText("");
					setResetBoolean(false);
					resetSet();
					setFut(false);
					setHut(false);
					setRiaszt(false);
					boolean tmp;
					if(getManual() == true) {
						tmp =false;
					} else {
						tmp =true;
					}
					setManual(tmp);
				}
			});
			 
			 pack();
             setVisible(true);
             this.repaint();
		 }
		 
	 }
	 
	 public class GreenHouseGraphics extends JComponent {
		 	Graphics2D g2d;
			
        @Override
        public void paint(Graphics g) {
			g2d = (Graphics2D) g;
			g2d.setColor(new Color(24, 231, 82));
			g2d.fillRect(0, 0, 600, 350);
            	g2d.setColor(new Color(150, 91, 8));
			g2d.fillRect(50, 30, 500, 290);
            	g2d.setColor(new Color(189, 182, 173));
		   //g2d.setColor(new Color(252, 8, 3));
			g2d.fillRect(54, 34, 492, 282);
            
			for(int j=0; j<3; j++){
					for(int i=0; i<9; i++){
						g2d.setColor(new Color(239 - j*100, 37 + 30*j, 47 + 100*j));
						g2d.fillOval(82 + 50*i, 62 + 90*j, 30, 30);
						g2d.setColor(new Color(239, 193, 37));
						g2d.fillOval(93 + 50*i, 73 + 90*j, 8, 8);
					}
				}
			if(getNovekedes() == 1){
				for(int j=0; j<3; j++){
					for(int i=0; i<9; i++){
						g2d.setColor(new Color(239 - j*100, 37 + 30*j, 47 + 100*j));
						g2d.fillOval(80 + 50*i, 60 + 90*j, 40, 40);
						g2d.setColor(new Color(239, 193, 37));
						g2d.fillOval(95 + 50*i, 75 + 90*j, 10, 10);
					}
				}
			}
			if(getNovekedes() == 2){
				for(int j=0; j<3; j++){
					for(int i=0; i<9; i++){
						g2d.setColor(new Color(239 - j*100, 37 + 30*j, 47 + 100*j));
						g2d.fillOval(80 + 50*i, 60 + 90*j, 50, 50);
						g2d.setColor(new Color(239, 193, 37));
						g2d.fillOval(98 + 50*i, 78 + 90*j, 12, 12);
					}
				}
			}
			if(getWater()==true){
				
					for(int k=0; k<9; k++){
						g2d.setColor(new Color(67, 199, 249));
						g2d.fillRect(60+20*k, 50+30*k, 10, 10);
					}
					for(int k=0; k<9; k++){
						g2d.setColor(new Color(67, 199, 249));
						g2d.fillRect(210+20*k, 50+30*k, 10, 10);
					}
					for(int k=0; k<9; k++){
						g2d.setColor(new Color(67, 199, 249));
						g2d.fillRect(360+20*k, 50+30*k, 10, 10);
					}
				}	
			
        }

       // @Override
       // public Dimension getPreferredSize() {
       //     return new Dimension(800, 500);
       // }
    }
	

    @Override
    public boolean executeAction(String agName, Structure action) {
		
        //logger.info("executing: "+action+", but not implemented!");
        /*if (true) { // you may improve this condition
             informAgsEnvironmentChanged();
        }*/
        System.out.println(action.getFunctor());
		if (action.getFunctor().equals("waterplants")) {
			removePercept(ASSyntax.createLiteral("watercommand"));
			setOntoz(true);
			System.out.println("WATERPLANTS");
			/*gui.label.setText(gui.riaszt.getText());
			setResetBoolean(true);
			gui.resetSet();
			setHomerseklet(getHomerseklet()-1);
			gui.homersekletSet();*/
		}
		else {
            logger.info("The action " + action + " is not implemented!");
            return false;
        }
     
		 gui.paintAll(gui.getGraphics());
		 return true; // the action was executed with success
    }



    /** Called before the end of MAS execution */

    @Override
    public void stop() {
        super.stop();
		gui.setVisible(false);
    }

}


