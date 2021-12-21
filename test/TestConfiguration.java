package test;

import java.util.Arrays;

import application.Configuration;
import modele.Pioche;

public class TestConfiguration {
	
	public static void main(String[] args) {
		TestConfiguration test = new TestConfiguration();
		test.test1();
	}
	
	public void test1() {
		Configuration config = new Configuration();
		Pioche p = new Pioche(); 
		p = config.nouvellePioche();
		int rel = 0;
		int mil = 0;
		int nob = 0;
		int com = 0;
		for(int i = 0; i<p.getListe().size();i++) {
	        System.out.println(p.getListe().get(i));
	        
	        if(p.getListe().get(i).getType().equals("RELIGIEUX")) {
	        	rel++;
	        }
	        if(p.getListe().get(i).getType().equals("MILITAIRE")) {
	        	mil++;
	        }
	        if(p.getListe().get(i).getType().equals("NOBLE")) {
	        	nob++;
	        }
	        if(p.getListe().get(i).getType().equals("COMMERCANT")) {
	        	com++;
	        }
	  }
		Test.test(p.nombreElements()==54, "test nb cartes");
		Test.test(rel==11, "nb carte religieux");
		Test.test(mil==11, "nb carte militaire");
		Test.test(nob==12, "nb carte noble");
		Test.test(com==20, "nb carte commerçant");
	}
}
