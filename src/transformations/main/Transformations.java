package transformations.main;

import java.awt.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.lang.*;

public class Transformations extends JPanel {

	static File file;
	static double[][] datalines;
	static double[][] matrix;
	static int num;
	static double tx, ty;
	static double sx, sy;
	static double cx, cy;
	static double angle;
	static int command;
	static int xmax, ymax;
	
	
	
	/* User should provide input: file containing datalines, what transformation 
	 * they want to do to datalines, any values needed for the transformation
	 */
	public static void main(String[] args) {
		
		xmax = 1000;
		ymax = 1000;
		JFrame f = new JFrame();
		f.getContentPane().add(new Transformations(), BorderLayout.CENTER);
		f.pack();
        f.setSize(xmax, ymax);
		
		Transformations t = new Transformations();
		boolean input = true;
		
		Scanner keyboard = new Scanner(System.in);
	
		while(input)
		{
			//Command Prompt
			System.out.println("\t Commands");
			System.out.println("0: Quit program");
			System.out.println("1: Input Lines");
			System.out.println("2: Output Lines");
			System.out.println("3: Draw Lines");
			System.out.println("4: Display Data Lines");
			System.out.println("5: Display Transformation Matrix");
			System.out.println("6: Basic Translate");
			System.out.println("7: Basic Scale");
			System.out.println("8: Basic Rotate");
			System.out.println("9: General Scale");
			System.out.println("10: General Rotate");
			System.out.println("11: Change Max Window Size");
			System.out.println();
			
			System.out.println("Select a command number: ");
			
			//making sure an integer is entered
			try {
				command = keyboard.nextInt();
				keyboard.nextLine();
				System.out.println();
			}
			
			//input is not an integer
			catch(InputMismatchException i) {
				System.out.println("Please input an integer between 0 and 10");
				keyboard.nextLine();
				System.out.println();
			}
			
			//Command 0: Quit program - Exits the program
			if(command == 0) {
				System.out.println("Quitting program...");
				System.exit(0);
			}
			
			/* Command 1: Input Lines - User provides a file location containing lines to be used in the graphics package 
			 * File should contain four numbers on each line corresponding to x0 y0 x1 y1 respectively. Numbers should be 
			 * separated by spaces. Lines should be saved in a .txt file.*/
			else if(command == 1) {
				System.out.println("Input a file containing 2D lines: ");
				file = new File(keyboard.nextLine());
				num = t.inputLines(datalines, num);
				System.out.println();
			}
			
			/* Command 2: Output Lines - User provides a file location that will contain the lines in the datalines array 
			 * Each row of datalines will contain one line, with the file containing the number of lines in "num" */
			else if(command == 2) {
				if(datalines == null) {
					System.out.println("No lines have been provided! Use the Input Lines command to begin.");
					System.out.println();
				}
				
				else {
					System.out.println("Input a file to hold 2D lines: ");
					file = new File(keyboard.nextLine());
					t.outputLines(datalines, num);
					System.out.println();
				}
			}
			
			/* Command 3: Draw Lines - Draws the lines contained in datalines to a separate graphical window */
			else if(command == 3) {
				if(datalines == null) {
					System.out.println("No lines have been provided! Use the Input Lines command to provide lines.");
					System.out.println();
				}
				
				else {
					f.setVisible(true);
					t.toDraw();
				}
			}
			
			/* Command 4: Display Data Lines - Displays a matrix of the points contained in datalines. */
			else if(command == 4) {
				if(datalines == null) {
					System.out.println("No lines have been provided! Use the Input Lines command to provide lines.");
					System.out.println();
				}
				
				else {
					System.out.println("Data lines:");
					t.displayDatalines(datalines, num);
					System.out.println();
				}
			}
			
			/* Command 5: Display Transformation Matrix - Displays the current transformation matrix (points contained in "matrix" array). */
			else if(command == 5) {
				if(matrix == null) {
					System.out.println("No transformation matrix has been defined yet. Use one of the transformation commands.");
					System.out.println();
				}
				
				else {
					System.out.println("Transformation Matrix:");
					t.displayMatrix(matrix);
					System.out.println();
				}
			}
			
			/* Command 6: Basic Translate - Translates the provided lines in datalines. User should specify the horizontal and vertical displacements (Tx and Ty). */
			else if(command == 6) {
				if(datalines == null) {
					System.out.println("No lines have been provided! Use the Input Lines command to provide lines.");
					System.out.println();
				}
				
				else {
					System.out.println("Provide horizontal displacement (Tx): ");
					tx = keyboard.nextDouble();
					keyboard.nextLine();
					
					System.out.println("Provide vertical displacement (Ty): ");
					ty = keyboard.nextDouble();
					keyboard.nextLine();
					
					t.basicTranslate(tx, ty);
				}
			}
			
			/* Command 7: Basic Scale - Scales the provided lines in datalines. User should specify the horizontal and vertical scaling factors (Sx and Sy). */
			else if(command == 7) {
				if(datalines == null) {
					System.out.println("No lines have been provided! Use the Input Lines command to provide lines.");
					System.out.println();
				}
				
				else {
					System.out.println("Provide horizontal scaling factor (Sx): ");
					sx = keyboard.nextDouble();
					keyboard.nextLine();
					
					System.out.println("Provide vertical scaling factor (Sy): ");
					sy = keyboard.nextDouble();
					keyboard.nextLine();
					
					t.basicScale(sx, sy);
				}
			}
			
			/* Command 8: Basic Rotate - Rotates the provided lines in datalines. User should specify the rotation angle in degrees */
			else if(command == 8) {
				if(datalines == null) {
					System.out.println("No lines have been provided! Use the Input Lines command to provide lines.");
					System.out.println();
				}
				
				else {
					System.out.println("Provide rotation angle (in degrees): ");
					angle = keyboard.nextDouble();
					keyboard.nextLine();
					
					t.basicRotate(angle);
				}
			}
			
			/* Command 9: General Scale - Scales the provided lines in datalines. User should specify the horizontal and vertical scaling factors (Sx and Sy). 
			 * User should also provide the center of scale (Cx and Cy). */
			else if(command == 9) {
				if(datalines == null) {
					System.out.println("No lines have been provided! Use the Input Lines command to provide lines.");
					System.out.println();
				}
				
				else {
					System.out.println("Provide horizontal scaling factor (Sx): ");
					sx = keyboard.nextDouble();
					keyboard.nextLine();
					
					System.out.println("Provide vertical factor (Sy): ");
					sy = keyboard.nextDouble();
					keyboard.nextLine();
					
					System.out.println("Provide X coordinate for center of scale (Cx): ");
					cx = keyboard.nextDouble();
					keyboard.nextLine();
					
					System.out.println("Provide Y coordinate for center of scale (Cy): ");
					cy = keyboard.nextDouble();
					keyboard.nextLine();
					
					t.scale(sx, sy, cx, cy);
				}
			}
			
			/* Command 10: General Rotate - Rotates the provided lines in datalines. User should specify the rotation angle in degrees.
			 * User should also provide the center of scale (Cx and Cy). */
			else if(command == 10) {
				if(datalines == null) {
					System.out.println("No lines have been provided! Use the Input Lines command to provide lines.");
					System.out.println();
				}
				
				else {
					System.out.println("Provide rotation angle (in degrees): ");
					angle = keyboard.nextDouble();
					keyboard.nextLine();
					
					System.out.println("Provide X coordinate for center of scale (Cx): ");
					cx = keyboard.nextDouble();
					keyboard.nextLine();
					
					System.out.println("Provide Y coordinate for center of scale (Cy): ");
					cy = keyboard.nextDouble();
					keyboard.nextLine();
					
					t.rotate(angle, cx, cy);
				}
			}
			
			/* Command 11: Change Max Window Size - Changes window size of frame used to draw lines to the user's screen.
			 * The user has this option because any coordinates used outside of the max window size will not be visible 
			 * when the user uses the draw lines command. 
			 */
			else if(command == 11) {
				System.out.println("Note: any coordinates used outside of the max window size will not be visible when using the draw lines command.");
				
				System.out.println("Enter the max window size for the X direction: ");
				xmax = keyboard.nextInt();
				keyboard.nextLine();
				
				System.out.println("Enter the max window size for the Y direction: ");
				ymax = keyboard.nextInt();
				keyboard.nextLine();
				
				f.setSize(xmax, ymax);
				
				System.out.println("New window size: " + xmax + " x " + ymax);
				System.out.println();
			}
			
			else {
				System.out.println("Invalid Input! Please provide an integer between 0 and 11 based on the commands in the list");
				System.out.println();
			}
			
			
		}
			
	}
	
	/* inputlines: Reads ‘datalines’ from an external file (name of file is provided by the user). On 
	return `num' will contain the number of lines read from the file. */
	public int inputLines(double[][] datalines, int num) {
		
		try {
			
			
			//getting inputs from file
			Scanner getLines = new Scanner(new BufferedReader(new FileReader(file)));
			
			num = 0;
			
			//count number of lines in file
			while(getLines.hasNextLine())
			{
				getLines.nextLine();
				num++;
			}
			
			datalines = new double[num][4];
			
			Scanner inputFile = new Scanner(new BufferedReader(new FileReader(file)));
			
			//enter numbers from file into array
			//Source: https://www.tutorialspoint.com/How-to-read-a-2d-array-from-a-file-in-java
			while(inputFile.hasNextLine())
			{
				for(int i = 0; i < num; i++)
				{
					String[] line = inputFile.nextLine().trim().split(" ");
					for(int j = 0; j < 4; j++)
					{
						try {
							
							datalines[i][j] = Double.parseDouble(line[j]);
						}
						 catch(NumberFormatException n)
						 {
							 System.out.println("The file should only contain numbers");
						 }
					}
				}
			}
			
			this.datalines = datalines;
			return num;
		
		}
		
		catch(FileNotFoundException f) {
			
			System.out.println("No file found. Try again");
		}
		
		catch(Exception e) {
			System.out.println("Something went wrong. Please check file and try again");
		}
		
		return num;
	}
	
	
	/* applyTransformation: applies the transformation matrix to the lines that appear in “datalines” 
	 * Source: https://knpcode.com/java-programs/matrix-multiplication-java-program/ 
	*/
	public double[][] applyTransformation(double[][] matrix, double[][] datalines){
		
		//arrays to store x0 and x1 based on 'num' in datalines
		double point1[] = new double[3];
		double point2[] = new double[3];
		
		//stores new points calculated
		double result1[] = new double[3];
		double result2[] = new double[3];
		
		double sum1 = 0;
		double sum2 = 0;
		
		for(int i = 0; i < num; i++)
		{
			//storing points from datalines into point arrays
			point1[0] = datalines[i][0];
			point1[1] = datalines[i][1];
			point1[2] = 1;
			
			point2[0] = datalines[i][2];
			point2[1] = datalines[i][3];
			point2[2] = 1;
			
			
			//column number of 3x3 matrix
			for(int j = 0; j < 3; j++)
			{
				//column number of 3x1 matrix
				for(int k = 0; k < 3; k++)
				{
					sum1 = sum1 + point1[k] * matrix[k][j];
					sum2 = sum2 + point2[k] * matrix[k][j];
				}
				result1[j] = Math.round(sum1 * 10.0) / 10.0;
				result2[j] = Math.round(sum2 * 10.0) / 10.0;
				sum1 = 0;
				sum2 = 0;
			}
			
			//store results in correct datalines location
			datalines[i][0] = result1[0];
			datalines[i][1] = result1[1];
			datalines[i][2] = result2[0];
			datalines[i][3] = result2[1];
			
		}
		
		return datalines;
	}
	
	//Used so the main method is able to call the paint component to draw lines
	public void toDraw() {
		repaint();
	}
	
	/* displayPixels: Displays (i.e., scan-converts) ‘datalines’ containing `num' lines 
	 * Using DDA Algorithm from previous assignment
	 */
	public void displayPixels(double[][] datalines, int num, Graphics g) {
		double x, y, x0, y0, x1, y1;
		double deltax, deltay;
		double yinc;
		
		for(int i = 0; i < num; i++)
		{
			x0 = datalines[i][0];
			y0 = datalines[i][1];
			x1 = datalines[i][2];
			y1 = datalines[i][3];
			
			x = x0;
			y = y0;
			
			deltax = x1 - x0;
			deltay = y1 - y0;
			
			
			//if x0 = x1, calculating yinc will cause an error because we are trying to divide by 0
			try {
				
				yinc = deltay / deltax;
			}
			
			catch(ArithmeticException a)
			{
				yinc = 0;
			}
			
			g.fillRect((int)x, (int)(-y), 3, 3);
			
			if(x1 > x0 && y1 > y0 && yinc > 0) {
				//Case 1: x1 > x0, y1 > y0, yinc > 0 (diagonal, bottom left to top right)
				for(int j = 0; j <= (deltax - 1); j++)
				{  
					x = x + 1;
					y = y + yinc;
					//System.out.println("X: " + x + " Y: " + Math.round(y));
					g.fillRect((int)x, (int)(-y), 3, 3);
				}
			}
			
			else if(y0 == y1 && x1 > x0 && yinc == 0) {
				//Case 2: y0 = y1, x1 > x0, yinc = 0 (horizontal going to the right)
				for(int j = 0; j <= (deltax - 1); j++)
				{  
					x = x + 1;
					y = y0;
					//System.out.println("X: " + x + " Y: " + Math.round(y));
					g.fillRect((int)x, (int)(-y), 3, 3);
				}
			}
			
			else if(y0 == y1 && x0 > x1 && yinc == 0.0) {
				//Case 3: y0 = y1, x0 > x1, yinc = 0 (horizontal going to the left)
				for(int j = (int)x0 - 1; j >= x1; j--)
				{  
					x = x - 1;
					y = y0;
					//System.out.println("X: " + x + " Y: " + Math.round(y));
					g.fillRect((int)x, (int)(-y), 3, 3);
				}
			}
			
			else if(x0 == x1 && y0 > y1) {
				//Case 4: x0 = x1, y0 > y1, yinc = undefined (vertical going down)
				for(int j = (int)y0 - 1; j >= y1; j--)
				{  
					x = x0;
					y = y - 1;
					//System.out.println("X: " + x + " Y: " + Math.round(y));
					g.fillRect((int)x, (int)(-y), 3, 3);
				}
			}
			
			else if(x0 == x1 && y1 > y0) {
				//Case 5: x0 = x1, y1 > y0, yinc = undefined (vertical going up)
				for(int j = 0; j <= (deltay - 1); j++)
				{  
					x = x0;
					y = y + 1;
					//System.out.println("X: " + x + " Y: " + Math.round(y));
					g.fillRect((int)x, (int)(-y), 3, 3);
				}
			}
			
			else if(x0 > x1 && y0 > y1 && yinc > 0) {
				//Case 6: x0 > x1, y0 > y1, yinc < 0 (diagonal, top right to bottom left)
				for(int j = (int)x0 - 1; j >= x1; j--)
				{  
					x = x - 1;
					y = y - yinc;
					//System.out.println("X: " + x + " Y: " + Math.round(y));
					g.fillRect((int)x, (int)(-y), 3, 3);
				}
			}
			
			else if(x1 > x0 && y0 > y1 && yinc < 0) {
				//Case 7: x1 > x0, y0 > y1, yinc < 0 (diagonal, top left to bottom right)
				for(int j = 0; j <= (deltax - 1); j++)
				{  
					x = x + 1;
					y = y + yinc;
					//System.out.println("X: " + x + " Y: " + Math.round(y));
					g.fillRect((int)x, (int)(-y), 3, 3);
				}
			}
			
			else if(x0 > x1 && y1 > y0 && yinc < 0) {
				//Case 8: x0 > x1, y0 > y1, yinc < 0 (diagonal, bottom right to top left)
				for(int j = (int)x0 - 1; j >= x1; j--)
				{  
					x = x - 1;
					y = y - yinc;
					//System.out.println("X: " + x + " Y: " + Math.round(y));
					g.fillRect((int)x, (int)(-y), 3, 3);
				}
			}
			
			else {
				System.out.println("Different case found! Try again!");
				System.exit(0);
			}
			
		}
	}
	
	/* outputLines: Outputs ‘datalines’ containing `num' lines to an external file (name of file is provided 
 	by the user). */
	public void outputLines(double[][] datalines, int num) {
		
		try {
			
			if(file.createNewFile()) {
				System.out.println("File created: " + file.getName());
			}
			
			else {
				System.out.println("File already exists. Data overwritten"
						+ ".");
			}
			
			FileWriter writeFile = new FileWriter(file);
			
			for(int i = 0; i < num; i++)
			{
				for(int j = 0; j < 4; j++)
				{
					writeFile.write(datalines[i][j] + " ");
				}
				
				if(i < num - 1) {
					writeFile.write("\n");
				}
			}
			
			writeFile.close();
		}
		
		catch(IOException i) {
			
			System.out.println("Something went wrong. Try again.");
		}
		
		
	}
	
	/* basicTranslate: Translation - 'Tx' is the horizontal and 'Ty' is the vertical displacements */
	public void basicTranslate(double tx, double ty) {
		matrix = new  double[][] { {1, 0, 0}, {0, 1, 0}, {tx, ty, 1} };
		datalines = applyTransformation(matrix, datalines);
	}
	
	/* basicScale: Scale - 'Sx' and 'Sy' are the horizontal and vertical scaling factors;
	 * Center of scale is at the origin of the Coordinate System */
	public void basicScale(double sx, double sy) {
		matrix = new  double[][] { {sx, 0, 0}, {0, sy, 0}, {0, 0, 1} };
		datalines = applyTransformation(matrix, datalines);
	}
	
	/* basicRotate: Rotation - angle of rotation is 'angle' degrees (clockwise);
	 * Center of rotation is at the origin of the Coordinate System */
	public void basicRotate(double angle) {
		double cosine = Math.round(Math.cos(Math.toRadians(angle)) * 10.0) / 10.0;
		double sine = Math.round(Math.sin(Math.toRadians(angle)) * 10.0) / 10.0;
		matrix = new double[][] { {cosine, -sine, 0}, {sine, cosine, 0}, {0, 0, 1} };
		datalines = applyTransformation(matrix, datalines);
	}
	
	/* scale: Scale - 'Sx' and 'Sy' are the horizontal and vertical scaling factors; 
	 * Center of scale is at Cx, Cy */
	public void scale(double sx, double sy, double cx, double cy) {
		matrix = new double[3][3];
		double[][] translate = { {1, 0, 0}, {0, 1, 0}, {-cx, -cy, 1} };
		double[][] scale = { {sx, 0, 0}, {0, sy, 0}, {0, 0, 1} };
		
		matrix = concatenate(translate, scale);
		
		translate = new double[][] { {1, 0, 0}, {0, 1, 0}, {cx, cy, 1} };
		
		matrix = concatenate(matrix, translate);
		datalines = applyTransformation(matrix, datalines);
	}
	
	/* rotate: Rotation - angle of rotation is 'angle' degrees (clockwise);
	 * Center of rotation is at Cx, Cy */
	public void rotate(double angle, double cx, double cy) {
		matrix = new double[3][3];
		double cosine = Math.round(Math.cos(Math.toRadians(angle)) * 10.0) / 10.0;
		double sine = Math.round(Math.sin(Math.toRadians(angle)) * 10.0) / 10.0;
		
		double[][] translate = { {1, 0, 0}, {0, 1, 0}, {-cx, -cy, 1} };
		double[][] rotate = { {cosine, -sine, 0}, {sine, cosine, 0}, {0, 0, 1} };
		
		matrix = concatenate(translate, rotate);
		
		translate = new double[][] { {1, 0, 0}, {0, 1, 0}, {cx, cy, 1} };
		
		matrix = concatenate(matrix, translate);
		datalines = applyTransformation(matrix, datalines);
	}
	
	/* displayDatalines: prints datalines in matrix form to console */
	// Source: https://stackoverflow.com/questions/5061912/printing-out-a-2d-array-in-matrix-format
	public void displayDatalines(double[][] datalines, int num) {
		try {
			String str = "|\t";
			
			for(int i = 0; i < num; i++)
			{
				for(int j = 0; j < 4; j++)
				{
					str += datalines[i][j] + "\t";
				}
				System.out.println(str + "|");
				str = "|\t";
			}
		}
		
		catch (Exception e) {
			System.out.println("Matrix is empty!");
		}
	}
	
	/* displayMatrix: prints Transformation matrix in matrix form to console
	 * Source: //https://stackoverflow.com/questions/5061912/printing-out-a-2d-array-in-matrix-format 
	 */
	public void displayMatrix(double[][] matrix) {
		try {
			String str = "|\t";
			
			for(int i = 0; i < 3; i++)
			{
				for(int j = 0; j < 3; j++)
				{
					str += matrix[i][j] + "\t";
				}
				System.out.println(str + "|");
				str = "|\t";
			}
		}
		
		catch (Exception e) {
			System.out.println("Matrix is empty!");
		}
	}
	
	/* concatenate: multiplies two 3x3 matrices and returns one 3x3 matrix.
	 * Used in general scale and general rotation for the concatenation process
	 * Source: https://www.javatpoint.com/java-program-to-multiply-two-matrices
	 */
	public double[][] concatenate(double[][] matrix1, double[][] matrix2)
	{
		double matrix[][] = new double[3][3];
		
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				matrix[i][j] = 0;
				for(int k = 0; k < 3; k++)
				{
					matrix[i][j] += matrix1[i][k] * matrix2[k][j];
				}
			}
		}
		
		return matrix;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.translate(xmax/2, ymax/2);
		displayPixels(datalines, num, g);
	}

}
