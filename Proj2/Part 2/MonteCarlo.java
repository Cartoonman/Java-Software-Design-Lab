package monteCarlo;

import monteCarlo.Simulation;
import monteCarlo.Histogram;
import java.util.ArrayList;

import javax.swing.JFrame;


public class MonteCarlo {

	public MonteCarlo() {
	}

	public static void main(String[] args) {
		Simulation s = new Simulation(); // We generate a new simulation object
		
		ArrayList<Double> ArrList = new ArrayList<Double>(); // We initialize a new arraylist of Doubles
		
		ArrList = s.generateNormalRandomNumbers(ArrList, 1000000); // Running the generateNormalRandomNumbers function to populate the array
		
		s.makeBins(ArrList, 15); // Making 15 bins.
	
			
		Histogram h = new Histogram(s); // Initializing the histogram here.
		// JFrame visuals will take care of setting up the GUI.
		JFrame visuals = new JFrame();
		visuals.setTitle("CSc 221 Histogram");
		visuals.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		visuals.add(h);
		visuals.setSize(1200, 800);
		visuals.setVisible(true);
		
	}

}
