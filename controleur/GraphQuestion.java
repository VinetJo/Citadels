package controleur;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class GraphQuestion {
	private static boolean ouiOunon = false;
	private static int retourChoix =0;
	private static Object lock = new Object();
	
	
	public static boolean interfaceOuiOuNon(String question) throws InterruptedException {
		//Cr�er un nouveau frame pour stocker le bouton
	    JFrame frame = new JFrame("Question Oui ou Non");
	    
	    JLabel txt = new JLabel(question);
	    txt.setBounds(50,50, 500,20);
	    
	    //Cr�er le bouton
	    JButton btnO = new JButton("Oui");
	    //D�finir la position du bouton
	    btnO.setBounds(120,100,100,40);
	  //Cr�er le bouton
	    JButton btnN = new JButton("Non");
	    //D�finir la position du bouton
	    btnN.setBounds(120,150,100,40);
	    
	    btnO.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent e){
	                 ouiOunon = true;
	                 frame.dispose();
	        }
	    });
	    btnN.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent e){
	                ouiOunon = false;
	                frame.dispose();
	        }
	    });
	    frame.add(txt);
	    frame.add(btnO);
	    frame.add(btnN);
	    frame.setSize(400,300);
	    frame.setLayout(null);
	    frame.setVisible(true); 
	    frame.setLocationRelativeTo(null);
	    frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
	    Thread t = new Thread() {
	        public void run() {
	            synchronized(lock) {
	                while (frame.isVisible())
	                    try {
	                        lock.wait();
	                    } catch (InterruptedException e) {
	                        e.printStackTrace();
	                    }
	                
	            }
	        }
	    };
	    t.start();

	    frame.addWindowListener(new WindowAdapter() {

	        public void windowClosed(WindowEvent arg0) {
	            synchronized (lock) {
	                frame.setVisible(false);
	                lock.notify();
	            }
	        }

	    });

	    t.join();
	    return ouiOunon;
	}
	
	public static int interfaceChoix(String question, ArrayList<String> listeChoix) throws InterruptedException {
		//Cr�er un nouveau frame pour stocker le bouton
	    JFrame frame = new JFrame("Choix multiple");
	    
	    ArrayList<JButton> listeBoutons = new ArrayList<JButton>();
	    
	    JLabel txt = new JLabel(question);
	    txt.setBounds(200,20, 550,20);
	    
	    for(int i =0; i<listeChoix.size();i++) {
	    	JButton btn = new JButton(listeChoix.get(i));
	    	if(i%2==0) {
	    		btn.setBounds(90+(i*100),100,170,70);
	    	}else {
	    		if(i==1) {
	    			btn.setBounds(90,190,170,70);
	    		}else {
	    			btn.setBounds(90+((i-1)*100),190,170,70);
	    		}
	    		
	    	}
	    	
	    	listeBoutons.add(btn);
	    }
	    
	    for(int z = 0; z<listeBoutons.size();z++) {
	    	listeBoutons.get(z).addActionListener(new ActionListener(){
		        public void actionPerformed(ActionEvent e){
		        	Object source = e.getSource();
		        	for(int y = 0; y<listeBoutons.size(); y++) {
		        		if(source==listeBoutons.get(y)) {
			        		retourChoix = y;
			        		break;
			        	}else {
			        		retourChoix = 50;
			        	}
		        	}
		        	
		        	
		        	frame.dispose();
		        }
		    });
	    }
	    frame.add(txt,BorderLayout.NORTH);
	    for(int y = 0; y<listeBoutons.size();y++) {
	    	frame.add(listeBoutons.get(y));
	    }
	    frame.setSize(900,600);
	    frame.setLayout(null);
	    frame.setVisible(true); 
	    frame.setLocationRelativeTo(null);
	    frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
	    Thread t = new Thread() {
	        public void run() {
	            synchronized(lock) {
	                while (frame.isVisible())
	                    try {
	                        lock.wait();
	                    } catch (InterruptedException e) {
	                        e.printStackTrace();
	                    }
	                
	            }
	        }
	    };
	    t.start();

	    frame.addWindowListener(new WindowAdapter() {

	        public void windowClosed(WindowEvent arg0) {
	            synchronized (lock) {
	                frame.setVisible(false);
	                lock.notify();
	            }
	        }

	    });

	    t.join();
		
		
		return retourChoix;
	}
	
	public int interfaceLecture(String question) throws InterruptedException {
		
		//Cr�er un nouveau frame pour stocker le bouton
	    JFrame frame = new JFrame("Lecture Resultat");
	    
	    JLabel txt = new JLabel(question);
	    txt.setBounds(50,50, 500,20);
	    
	    JTextField textField1 = new JTextField();
	    textField1.setBounds(50,100, 180,20);
	    
	    //Cr�er le bouton
	    JButton btn = new JButton("Valider");
	    //D�finir la position du bouton
	    btn.setBounds(50,150,100,40);
	    
	    btn.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent e){
	        	String temp = textField1.getText();
	        	retourChoix = Integer.parseInt(temp);
	            frame.dispose();
	        }
	    });
	    frame.add(txt);
	    frame.add(textField1);
	    frame.add(btn);
	    frame.setSize(300,300);
	    frame.setLayout(null);
	    frame.setVisible(true); 
	    frame.setLocationRelativeTo(null);
	    frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
	    Thread t = new Thread() {
	        public void run() {
	            synchronized(lock) {
	                while (frame.isVisible())
	                    try {
	                        lock.wait();
	                    } catch (InterruptedException e) {
	                        e.printStackTrace();
	                    }
	                
	            }
	        }
	    };
	    t.start();

	    frame.addWindowListener(new WindowAdapter() {

	        public void windowClosed(WindowEvent arg0) {
	            synchronized (lock) {
	                frame.setVisible(false);
	                lock.notify();
	            }
	        }

	    });

	    t.join();
	    return retourChoix;
	}

}
