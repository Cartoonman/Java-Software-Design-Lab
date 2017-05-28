package monteCarlo;

import java.awt.Color;
import java.awt.Graphics;
import java.text.DecimalFormat;
import javax.swing.JPanel;


public class Histogram extends JPanel {
	final int TOP_MARGIN = 20;
	final int BOTTOM_MARGIN = 20;
	final int LEFT_MARGIN = 20;
	final int RIGHT_MARGIN = 20;
	private int[] bins; // The bins array, which holds the count of elements in each bin
	private int n, maxval; // Ints n and maxval, where n = number of bins in the array of bins, and maxval is the largest value in the array bins
	private double min,range,binWidth; // We hold the values of min,range,and binWidth which will be used to draw the histogram

	
	public Histogram(Simulation s) {
		setBackground(Color.WHITE);
		
		bins = s.getBins(); // We call the Get function to get the array of Bins from Simulation s
		min = s.getMin(); // We call the Get function to get the minimum value from Simulation s
		range = s.getRange(); // We call the get function to get the range from Simulation s
		n = s.getBinCount(); // We call the get function to get the number of bins from Simulation s.
		binWidth = (range /(double) n); // We divide range by n to get the bin width
		
		maxval = 0; // We calculate from bins how many elements are in the bin with the most elements inside.
					// This will be very important when we scale the histogram with respect to the Y-Axis
		for(int i : bins){
			if(i > maxval) maxval = i;
		}
	}
// paintConponent draws the histogram
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawXAxis(g);
		drawYAxis(g);
		drawBins(g);
		drawXLabels(g);
		drawYLabels(g);
	}

	
	// drawXAxis Draws the x-axis
	private void drawXAxis(Graphics g) {
		int x1 = LEFT_MARGIN;
		int y1 = getHeight() - BOTTOM_MARGIN;
		int x2 = getWidth() - RIGHT_MARGIN;
		int y2 = y1;
		g.drawLine(x1, y1, x2, y2);
	}
	
	
// drawYAxis Draws the y-axis
	private void drawYAxis(Graphics g) {
		int x1 = LEFT_MARGIN;
		int y1 = getHeight() - BOTTOM_MARGIN;
		int x2 = x1;
		int y2 = TOP_MARGIN;
		g.drawLine(x1, y1, x2, y2);
	}
	
	
	//                  ||
// My work starts below \/	
	
	
// drawBins draws the bins
	private void drawBins(Graphics g) {
		g.setColor(Color.GRAY);
		int width = (getWidth()-2*RIGHT_MARGIN)/n; // Width is fixed, based on width of window divided by number of bins in array
		int height; // Height is not fixed, and is dynamically defined later
		int x = LEFT_MARGIN; // The x coordinate is initialized to the left margin.
		int y; // y is not fixed and is dynamically defined later
		
		for(int i : bins){ // We start a forloop that will traverse each element in bins and will 
						  //draw rectangles according to the number of elements in the array, as well as it's 
					     //relative scale to the bin with the largest number of elements (which will 
						//be our point of reference for scale)
			 
			height = (int)((double)(getHeight()-2*TOP_MARGIN/*See Below*/)*((double)i/(double)maxval));
			 // We calculate the height by applying a ratio formula [(Height-const)* (bin size /largest bin size). 
			// Based on this formula, the bin with the most values in it will have height equal to (Height-const).
		   // The const is required to give enough room for the Y-axis values to be printed on top of the bar, as
		  // well as to keep the bars from exceeding the drawn Y-axis.
			 
			y = getHeight()-BOTTOM_MARGIN - height; //We calculate the y value of the origin where the rectangle should be 
													//drawn by taking the height-bottom margin, then subtracting the height calculated earlier.
			 g.fill3DRect(x, y, width, height,true);// This function will draw a 3D-filled rectangle. the boolean 'true' is used to denote the style of the rectangle drawn.
			 x += width; // Incrementing x by the width calculated earlier.			
		}
	}
	
	
// drawXLabels draws the labels along the x-axis
	private void drawXLabels(Graphics g) {
		g.setColor(Color.BLACK);
		DecimalFormat formatter = new DecimalFormat();
		formatter.setMinimumFractionDigits(2);
		formatter.setMaximumFractionDigits(2);
		
		
		double labelVal = min; // We initialize labelVal to min.
		int x = LEFT_MARGIN; //We initialize x to be equal to the left margin.
		int y = (getHeight() - BOTTOM_MARGIN) + 12; // we initialize y to be the (height of the window - Bottom_margin)+12
		for (int b : bins) { 
					//We start by traversing through the array of bin
			g.drawString(formatter.format(labelVal), x-12, y); // Here we format the drawstring to take in the value from labelVal and display
			x += (getWidth()-2*RIGHT_MARGIN)/n; // we increment x by the width of each bin. Note we shift the width by 2*Right_margin, to give the last number on the right space
			labelVal += binWidth; // we increment labelval by the binwidth
		}
		g.drawString(formatter.format(labelVal), x-12, y); // We have this here to write the last value on the very right
	}
	
	
// drawYLabels draws the labels along the y-axis,
// i.e., draws the count of the bins on top of the bins
	private void drawYLabels(Graphics g) {
		g.setColor(Color.BLUE);
		
		DecimalFormat formatter = new DecimalFormat("#,###");
		// Sample code (which you may or may not choose to use)
		int labelVal; // We initialize the labelval for the bin size label.
		int x = LEFT_MARGIN; // we initialize x to be LEFT_MARGIN
		int y;  // y will change dynamically later.
		for (int i : bins) {
			// the y value is set to sit at the upper left corner of each bar. The casting is for the ratios to properly scale.
			y = (getHeight()-BOTTOM_MARGIN-2)-((int)((double)(getHeight()-TOP_MARGIN-TOP_MARGIN)*((double)i/(double)maxval)));
			labelVal = i; //we set labelVal to i, the value in the bin
			g.drawString(formatter.format(labelVal), x, y); // writing the string of # of values in the bin.
			x += (getWidth()-2*RIGHT_MARGIN)/n; // x is incremented by the modified value of width divided by number of bins.
		}
	}
}