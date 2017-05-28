package monteCarlo;

import monteCarlo.Simulation; // Importing libraries.
import monteCarlo.Metrics;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class MonteCarlo {

	public MonteCarlo() {
	}

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException { // Exception is for the file output.
		Simulation s = new Simulation(); // We generate a new simulation object
		
		ArrayList<Double> ArrList = new ArrayList<Double>(); // We initialize a new arraylist of Doubles
		
		ArrList = s.generateNormalRandomNumbers(ArrList, 100000); // Running the generateNormalRandomNumbers function to populate the array
		
		int[] bins = s.makeBins(ArrList, 11); // Making 15 bins.
		
		PrintWriter writer = new PrintWriter("Gauss.txt", "UTF-8"); // Setting up the writing object that will write to file Gauss.txt
		
		for (int i : bins){ // Printing out the contents of the bins
			System.out.println(i);//Prints to console
			writer.println(i);// Prints to file
		}

		writer.close(); // Being a good programmer and closing the file properly.
		
		System.out.println("The percentages for verifyDistribution: ");
		// Printing out the values for VerifyDistribution.
		System.out.println(Metrics.verifyDistribution(ArrList, 0.0, 1.0, 1.0));
		System.out.println(Metrics.verifyDistribution(ArrList, 0.0, 1.0, 2.0));
		System.out.println(Metrics.verifyDistribution(ArrList, 0.0, 1.0, 3.0));

		

	}

}
