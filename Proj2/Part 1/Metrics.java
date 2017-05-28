package monteCarlo;

import java.util.ArrayList;

public class Metrics {

	public Metrics() {
	}
	
	// This function will give the percentage that values exists within a certain standard deviation.
	public static double verifyDistribution(ArrayList<Double> array, double mean, double stddev, double numstddev){
		int count = 0; // Initialize count = 0;
		for(Double value : array){ // traversing the array 
			if(value >= mean - (stddev*numstddev) && (value <= mean + (stddev*numstddev))){ // We check if the given value is within the interval we are searching
				count++; // If it is, we increment the count.
				continue;// and then loop back
			}
			if(count != 0) break; // this will cleverly break the loop only after the interval's count has been done. it wil not break at the start when count = 0.
								 // This is due to the array having been previously sorted.
		}
		return ((double)count/(double)array.size())*100.0; // Return the percentage of values that exist within the interval.
	}

}
