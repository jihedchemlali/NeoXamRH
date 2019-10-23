package tn.esprit.neo.dao;

import java.util.Random;

import java.lang.String;
public class GeneratorPassword {
	private static final Random rand = new Random();
	   private static final   String Xsi ="abcdefghijklmnopqrstuvwxyz";
	   private static final String Gamm ="ABCDEFGHIJKLMNOPQRSTUVWXYZ";  
	   private static final String Iot = "1234567890";
	   
	   private static String demo =""; 
	   private static double i =0;
	   
	   public String getPassword()
	   {
		   demo="";
		   while ( (demo.length() != 10)) {
			 int rPick=rand.nextInt(4);
			 if (rPick ==0) {
			     int erzat=rand.nextInt(25);
			     demo+=Xsi.charAt(erzat);
			 } else if (rPick == 1) {
			     int erzat=rand.nextInt(9);
			     demo+=Iot.charAt(erzat);
			 } else if (rPick==2) {
			     int erzat=rand.nextInt(25);
			     demo+=Gamm.charAt(erzat);
			 }
			 }
		   return demo;
	   }
}
