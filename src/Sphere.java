
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Sphere {

	/**
	 * @param args
	 */
	static File dataFile;
	static double lightX = 1000;
	static double lightY = 1000;
	static double lightZ = 500;
	static double lightMagnitude = 1500;
	static double radius = 4900;
	
	public static void main(String[] args) {
		File userHomeDirectory = new File(System.getProperty("user.home"));
		dataFile = new File(userHomeDirectory, "image4.txt");
		double intensity = 0;
		int count = 0;
		PrintWriter output = null;
	 	FileOutputStream outputStream;
	 	try{
	 		outputStream = new FileOutputStream(dataFile);
	 		output = new PrintWriter(outputStream);
	 	}
	 	catch(Exception e){
	 	 e.printStackTrace();
	 	}
	 	
		for(double x = 0.1; x <= 30.1; x += 0.1){
			
			for(double y = 0.1; y <= 30.1; y += 0.1){
				
				intensity = getIntensity((x-15)*1000/3, (y-15)*1000/3);
				try {
					if (intensity == -1){
						output.println(x + " " + y + " " + 0 + " " + 0 + " " + 255); 
				 		count++;
					}
					else{
						output.println(x + " " + y + " " + intensity + " " + 0 + " " + 0);
						count++;
					}
			 	}catch(Exception e){
			 		System.out.println(x + " " + y);
			 	}
				
			}
			output.flush();
		}
		
		System.out.println(count);
	}
	
	public static double getIntensity(double sphereX, double sphereY){
		if (Math.pow(radius, 2) - Math.pow(sphereX, 2) - Math.pow(sphereY, 2) < 0){
			return -1;
		}
		double sphereZ = Math.sqrt(Math.pow(radius, 2) - Math.pow(sphereX, 2) - Math.pow(sphereY, 2));
		
		double theta = Math.acos((lightX*sphereX + lightY*sphereY + lightZ*sphereZ) / (lightMagnitude*radius));
		
		//most intensity when theta = 0 and least intensity when theta >= pi
		
		return 255*(Math.PI-theta) / Math.PI;
		
	}
	

}
