package monteCarlo;

import java.util.ArrayList; // Importing the libraries we need
import java.util.Collections; 
import java.util.Random;

public class Simulation {

	public Simulation() {
		
	}
	// These instance variables are used to hold important information for the getBins method, as well
	// as for adding support for additional classes like Histogram
	private int[] binArray;
	private double min,max,range,BinWidth;
	private int bins;
	
	// The generateNormalRandomNumbers will return a ArrayList of Doubles.
	public ArrayList<Double> generateNormalRandomNumbers(ArrayList<Double> array, int n){
		if (!array.isEmpty()) return array; //This array should have been empty.
		Random rand = new Random(); // Creating a new Random object for random number generation
		
		while(array.size() != n){ // We will loop until we reach out desired number of elements in the array
			array.add(rand.nextGaussian()); // We generate a new Gaussian distributed number and place it in the array.
		}
		
		Collections.sort(array); // Sorting the array (Mergesort at O(nlogn) at creation will make our calculations later on much faster. Min and Max at O(1) and Metrics O(n).
		return array;
	}
	
	// The generateNormalRandomNumbers will return an array, which are the bins.
	public int[] makeBins(ArrayList<Double> array, int n){
		// Here we are initializing our variables to make bins, as well as to use later on in getBins().
		bins = n;
		binArray = new int[bins];
		min = getMin(array);
		max = getMax(array);
		range = getRange(array);
		BinWidth = (range / (double)bins);
		// We will now traverse through the array of gaussian values and fill the bin array.
		for(Double value : array){
			binArray[getBin(value)]++; // we call the getBin() method to get the index of the bin to increment the value of
		}
		return binArray;
	}

	
	private int getBin(Double value){ //The getBin method takes in the gaussian value and returns the int of the bin index
		
		if(value.doubleValue() == max) return bins-1; // This is required here to catch the value that is equal to the largest value in the gaussian value array and place it in the last array.
		for(int i = 0; i < bins; i++){
			boolean upper = (value.doubleValue() < (min+(BinWidth*(i+1)))); // The boolean upper represents whether the value is strictly less than the upper interval of the bin
			boolean lower = (value.doubleValue() >= (min+(BinWidth*i)));	// The boolean lower represents whether the value is larger than or equal to the lower interval of the bin
			if(lower && upper)  return i;	// If the value is within the interval, return i, the index.
			}
		return -1; // We need this here for compilation reasons. The code should even reach this statement. If it does, it will crash as expected.
	}

	
	private double getMin(ArrayList<Double> array){return array.get(0).doubleValue();} // Returns the minimum value, which in the sorted array, is equal to the first value in the array.
	private double getMax(ArrayList<Double> array){return array.get(array.size()-1).doubleValue();} // Returns the maximum value, which in the sorted array, is equal to the last value in the array.
	private double getRange(ArrayList<Double> array){return (array.get(array.size()-1)-array.get(0).doubleValue());} // Returns the range, which is equal to the last value in the array minus the first value in the array.

}