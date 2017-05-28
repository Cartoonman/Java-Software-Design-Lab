
// Importing java.io for the file input and output methods
import java.io.*;

// The start of defining public class Benford
public class Benford {
	
	// Internal Variables //
	private int dataSize = 0; //dataSize is used to store the number of datapoints in our input
	private int[] benfordCountFrequency = new int[9]; //benfordCountFrequency is an integer array used to hold the frequencies of occurrence of each digit.

	
	// Main function of Benford, this uses Benford to create a Benford object, then run its methods.
	public static void main(String[] args) {
		Benford benfordObject = new Benford("data.txt"); // Creating a new Benford object and importing data.txt
		benfordObject.benfordPrint(); // Printing the processed Benford data.	
	}
	
	
	
	
	
	
	
	
	
	
	// Constructor that accepts a string as an input that contains the string of the name of the file that is desired to be the source of input data.
	public Benford(String fileName) { 
		this.fileInput(fileName); 
	}
	

	
	// fileInput is the function that handles file I/O
	private void fileInput(String fileName){ 
		// I/O Objects. These are used for file input and parsing.
		FileInputStream textfile;
		BufferedReader textlines;
		
		
		// We require a try/catch here because we are dealing with file I/O
		try { 
			textfile = new FileInputStream(fileName); // Opening the file named in 'fileName' with FileInputStream
			textlines = new BufferedReader(new InputStreamReader(textfile));
			
			// We read the data line by line
			String line = textlines.readLine();
			while(line != null){ 
				//dataSize++; // We increment the dataSize integer to be the size of our input data.
				benfordFrequencyCounter(line);
				line = textlines.readLine();			
			}
			
			textfile.close(); // To be nice and proper, we close the input file 
		}
		catch (Exception e) { // In the event that the file cannot be read, we will catch the exception here.
			System.out.printf("Error: File not found%n");
			e.printStackTrace(); // This will print the stack trace so as to debug the exception that was caught.
		}
	}
	
	// The benfordFrequencyCounter is the function that takes the inputed data and processes it. The results are stored in benfordCountFrequency.
	private void benfordFrequencyCounter(String tmp){ 
			
			// We then loop through each character in the string tmp.
			for(int charposition = 0; charposition < tmp.length(); charposition++){
				//For each character we check if it is not a '-', a '0', or a '.'. If it isn't any of these, it must be a digit from 1-9.
				if(tmp.charAt(charposition) != '-' && tmp.charAt(charposition) != '0' && tmp.charAt(charposition) != '.'){	
					//We convert the character at charposition to an int, subtract 1 from it to get the corresponding index (value-1) and then increment the value at the bedfordCountFrequency index.
					dataSize++; // We increment the dataSize integer to be the size of our input data. This only counts the values that are non-zero.
					benfordCountFrequency[(Character.getNumericValue(tmp.charAt(charposition)))-1]++;		
					return; // Don't search further in the string, go to the next number
				}						
			}	
	}
	
	// benfordPrint is a public function that the user can call to print the processed data in a nice visual manner.
	public void benfordPrint(){
		// To avoid a division-by-zero error, we check for the case if dataSize is equal to 0.
		if(dataSize == 0) {
			// Let the user know that the dataset is empty, then return out of this function
			System.out.println("Empty Data Set, No Results");
			return;
		}
		
		// Looping through each number in benfordCountFrequency array from 1-9 to print the results
		for(int i = 0; i < 9; i++){
			// percent is the float value of the percentage of the frequency at index i of the total data size.
			float percent = (float)(benfordCountFrequency[i]*100)/dataSize;
			
			//formats the output. Percentage displayed up to 3 significant digits. example: "5 (3.356%)"
			System.out.printf("%d (%.3f%%)\t : ",i+1,percent);
			// Will loop up to 'percent' times, rounded with Math.round
			for(int k = 0; k < Math.round(percent); k++){
				System.out.print("*"); //prints the star bargraph Math.round(percent) times
			}
			
			System.out.printf("%n"); // used to finish the current line and setup the next line
		}
		System.out.printf("%n"); // adds an additional space once all 9 outputs are done.
	}
	
}
