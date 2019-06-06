
import java.util.ArrayList;
import java.util.Random;
import javax.lang.model.element.*;
import java.util.Scanner;
import java.io.File;
import java.io.*;

class DummyDataAlgo {
	
	static ArrayList<Integer>[] arrayOfArrayListIndependentVariables;
	static Scanner scanner;
	static ArrayList<Integer>[] arrayListOfArraysActivity;
	static ArrayList<Integer> finalResults = new ArrayList<Integer>();
	static Random random = new Random();
	static int size = 3000;
	static int sizeIV = 15;
	static int errorProb = 1;
	static double threshold = 0.5;
	static int[] minIVRange;
	static int[] maxIVRange;
	static int[] threshArray;
	static double[] weightArray;
	static boolean[] upwardActivityIndicator;
	static String[] independentVariablesNames;
	
	public static void main(String[] args) throws Exception {
		
		DummyDataAlgo.retrieveDataFromFile();
		DummyDataAlgo.populateArrays();
		DummyDataAlgo.setResults();
		DummyDataAlgo.outputResults();
	}
	
	static void outputResults() throws Exception {
		PrintStream fileOut = new PrintStream("dummyData.csv");
		System.setOut(fileOut);
		
		for (int i = 0; i < independentVariablesNames.length + 1; i++) {
			if (i == independentVariablesNames.length) {
				System.out.printf("%s\n", "Results");
			} else {
				System.out.printf("%s,", independentVariablesNames[i]);
			}
			
		}
		for (int s = 0; s < size; s++) {
			for (ArrayList<Integer> array : arrayOfArrayListIndependentVariables) {
				System.out.printf("%d,", array.get(s));
			}
			System.out.printf("%d\n", finalResults.get(s));
		}
	}
	
	static void populateArrays() {
		arrayOfArrayListIndependentVariables = new ArrayList[sizeIV];
		arrayListOfArraysActivity = new ArrayList[sizeIV];
		
		for (int i = 0; i < sizeIV; i++) {
			arrayOfArrayListIndependentVariables[i] = DummyDataAlgo.randomData(minIVRange[i],maxIVRange[i]);
		}
		
		for (int i = 0; i < sizeIV; i++) {
			arrayListOfArraysActivity[i] = DummyDataAlgo.dataActiveIndicator(arrayOfArrayListIndependentVariables[i], errorProb, threshArray[i], upwardActivityIndicator[i]);
		}
	}
	
	static ArrayList<Integer> randomData(int rangeMin, int rangeMax) {
		ArrayList<Integer> al = new ArrayList<Integer>();
		for (int i = 0; i < size; i++) {
			int number = random.nextInt(rangeMax-rangeMin+1) + rangeMin;
			al.add(number);
		}
		return al;
	}
	
	static ArrayList<Integer> dataActiveIndicator(ArrayList<Integer> arrayList, int prob, int threshold, boolean biggerExit) {
		ArrayList<Integer> activityArray = new ArrayList<Integer>();
		for(Integer element : arrayList) {
			if ((random.nextInt(10) < prob)) {
				activityArray.add(random.nextInt(2));
			} else {
				if (biggerExit) {
					if (element > threshold) {
						activityArray.add(1);
					} else {
						activityArray.add(0);
					}
				} else {
					if (element < threshold) {
						activityArray.add(1);
					} else {
						activityArray.add(0);
					}
				}
			}
			
			
			
		}
		return activityArray;
		
	}
	
	static void setResults() {
		Double totalWeightSum = 0.00;
		for (Double element : weightArray) {
			totalWeightSum += element;
		}
		for (int i = 0; i < size; i++) {
			Double sum = 0.00;
			for (int k = 0; k < arrayListOfArraysActivity.length; k++) {
				sum += (weightArray[k]/totalWeightSum) * arrayListOfArraysActivity[k].get(i);
			}
			if (sum > 0.67) {
				finalResults.add(1);
			} else {
				finalResults.add(0);
			}
			
		}
	}
	
	static void retrieveDataFromFile() throws Exception {
		scanner = new Scanner(new File("dataSettings.csv"));
		int counter = 0;
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			switch (counter) {
				case 0:
					independentVariablesNames = line.split(",");
					break;
				case 1:
					size = Integer.parseInt(line);
					break;
				case 2:
					sizeIV = Integer.parseInt(line);
					minIVRange = new int[sizeIV];
					maxIVRange = new int[sizeIV];
					threshArray = new int[sizeIV];
					weightArray = new double[sizeIV];
					upwardActivityIndicator = new boolean[sizeIV];
					//independentVariablesNames = new String[sizeIV];
					break;
				case 3:
					errorProb = Integer.parseInt(line);
					break;
				case 4:
					String[] stringMinArray = line.split(",");
					int[] intMinArray = new int[sizeIV];
					for (int i = 0; i < stringMinArray.length; i++) {
						intMinArray[i] = Integer.parseInt(stringMinArray[i]);
					}
					minIVRange = intMinArray;
					break;
				case 5:
					String[] stringMaxArray = line.split(",");
					int[] intMaxArray = new int[sizeIV];
					for (int i = 0; i < stringMaxArray.length; i++) {
						intMaxArray[i] = Integer.parseInt(stringMaxArray[i]);
					}
					maxIVRange = intMaxArray;
					break;
				case 6:
					String[] stringThreshArray = line.split(",");
					int[] intThreshArray = new int[sizeIV];
					for (int i = 0; i < stringThreshArray.length; i++) {
						intThreshArray[i] = Integer.parseInt(stringThreshArray[i]);
					}
					threshArray = intThreshArray;
					break;
				case 7:
					String[] stringBoolArray = line.split(",");
					boolean[] booleanArray = new boolean[sizeIV];
					for (int i = 0; i < stringBoolArray.length; i++) {
						if (Integer.parseInt(stringBoolArray[i]) > 0) {
							booleanArray[i] = true;
						} else {
							booleanArray[i] = false;
						}
					}
					booleanArray = booleanArray;
					break;
				case 8:
					String[] stringWeightArray = line.split(",");
					double[] floatWeightArray = new double[sizeIV];
					for (int i = 0; i < stringWeightArray.length; i++) {
						floatWeightArray[i] = Double.parseDouble(stringWeightArray[i]);
					}
					weightArray = floatWeightArray;
					break;
				case 9:
					threshold = Double.parseDouble(line);
					break;
				default:
					break;
			}
			counter++;
		}
	}
}

 