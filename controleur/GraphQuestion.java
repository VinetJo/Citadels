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
import javax.swing.WindowConstants;

public class GraphQuestion {
	private static boolean ouiOunon = false;
	private static String test = "";
	private static int retourChoix =0;
	private static Object lock = new Object();
	
	
	public static boolean interfaceOuiOuNon(String question) throws InterruptedException {
		//Créer un nouveau frame pour stocker le bouton
	    JFrame frame = new JFrame("Question Oui ou Non");
	    
	    JLabel txt = new JLabel(question);
	    txt.setBounds(50,50, 180,20);
	    
	    //Créer le bouton
	    JButton btnO = new JButton("Oui");
	    //Définir la position du bouton
	    btnO.setBounds(90,100,100,40);
	  //Créer le bouton
	    JButton btnN = new JButton("Non");
	    //Définir la position du bouton
	    btnN.setBounds(90,150,100,40);
	    
	    btnO.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent e){
	                 ouiOunon = true;
	                 test = "oui";
	                 frame.dispose();
	        }
	    });
	    btnN.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent e){
	                ouiOunon = false;
	                test = "non";
	                frame.dispose();
	        }
	    });
	    frame.add(txt);
	    frame.add(btnO);
	    frame.add(btnN);
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
	    return ouiOunon;
	}
	
	public static int interfaceChoix(String question, ArrayList<String> listeChoix) throws InterruptedException {
		//Créer un nouveau frame pour stocker le bouton
	    JFrame frame = new JFrame("Choix multiple");
	    
	    ArrayList<JButton> listeBoutons = new ArrayList<JButton>();
	    
	    JLabel txt = new JLabel(question);
	    txt.setBounds(200,20, 300,20);
	    
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
	

}
