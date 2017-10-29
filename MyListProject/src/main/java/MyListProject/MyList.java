import java.util.*;
import java.lang.*;
import org.apache.commons.cli.*;

public class MyList{
	public static void main(final String... args)throws ParseException{
		
		

		final CommandLine cmd = new DefaultParser().parse(MyList.createCommandLineOptions(),args);
		
		boolean keyIsInt = true;
		boolean arrayIsInt = true;
		boolean typeIsInt = true;

		int intKey = 0;
		String stringKey = "";
		
		if(cmd.hasOption("type")){
			if(cmd.getOptionValue("type").equals("i") || cmd.getOptionValue("type").equals("I"))
				typeIsInt = true;
			else if(cmd.getOptionValue("type").equals("s") || cmd.getOptionValue("type").equals("S"))
				typeIsInt = false;
			else{
				System.out.println("Please make sure that you select an int or a string!");
				System.exit(0);
			}
		}
		
		String temp;

		if(cmd.hasOption("key")){
			if(typeIsInt){
				try{
					intKey = Integer.parseInt(cmd.getOptionValue("key"));
				}catch(NumberFormatException e){
					System.out.println("Please make sure that inputs match the options you selected!");
					System.exit(0);
				}
			}
			else
				stringKey = cmd.getOptionValue("key");

    			
		}
		
		String[] arrayString = cmd.getOptionValues("list");
		int[] array = new int[arrayString.length];
		
		try{
			for(int i =0; i < arrayString.length; i++){
				array[i] = Integer.parseInt(arrayString[i]);
			}
		}catch(NumberFormatException e){
			arrayIsInt = false;
		}

	 	
		if(typeIsInt == true && arrayIsInt == false){
			System.out.println("Please make sure that inputs match the options you selected!");
			System.exit(0);
		}
		
		
		boolean isThere = false;;
		if(typeIsInt){
			bubbleSort(array);
			isThere = 0 <= binSearch(array, intKey);
		}
		else{
			bubbleSort(arrayString);
			isThere = (0 <= binSearch(arrayString, stringKey));
		}

		if(isThere)
			System.out.println("The key is There!");
		else
			System.out.println("The Key Was Not Found!");

	}

	public static int binSearch(int[] array1, int objiv)
	{
		int top = 0;
		int bottom = 0;
		int middle = 0;
		top = array1.length - 1;

		while(top >= bottom) {
			middle = (bottom + top) / 2;
			if(array1[middle] == objiv) {
				return middle;
			}
			else if(array1[middle] < objiv) {
				bottom = middle + 1;
			}
			else if(array1[middle] > objiv) {
				top = middle - 1;
			}


		}
		middle = -1;
		return middle;
	}
	
	public static int binSearch(String[] array, String objiv)
	{
		int top = 0;
		int bottom = 0;
		int middle = 0;
		top = array.length - 1;

		while(top >= bottom) {
			middle = (bottom + top) / 2;
			if(array[middle].compareTo(objiv) < 0) {
				bottom = middle + 1;
			}
			else if(array[middle].compareTo(objiv) > 0) {
				top = middle - 1;
			}
			else  if(array[middle].compareTo(objiv) == 0){
				return middle;
			}

		}
		middle = -1;
		return middle;	}
	
	public static void bubbleSort(int[] arr){
		double n = arr.length;
		int temp = 0;
		int temp2 = 0;
		for(int i = 0; i < n; i++){
			for(int j = 1; j < (n-i); j++){
				if(arr[j-1] > arr[j]){
					temp = arr[j-1];
					arr[j-1] = arr[j];					arr[j] = temp;
				}
			}
		}
	}
	
	public static void bubbleSort(String[] arr){
		String temp = "";
		int temp2 = 0;
		for(int i = 0; i < arr.length - 1; i++){
			for(int j = 0; j < arr.length- i - 1; j++){
				if(arr[j+1].compareTo(arr[j]) < 0){
					temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}
	}
	public static boolean equals(double a, double b){
    		if(a == b)
			return true;  
		else if(Math.abs(a - b) < 0.000000000000001)
			return true;
		else
			return false;
	}
	public static boolean greaterThan(double a, double b){
    		if(a - b > 0.000000000000001)
			return true;
		else 
			return false;
	}
	public static boolean lessThan(double a, double b){
    		if(b - a > 0.000000000000001)
			return true;
		else 
			return false;
	}
	public static Options createCommandLineOptions() {

        	final Options options = new Options();

		final Option typeOfInput = Option.builder("t").hasArg(true).argName("type").longOpt("type").desc("Type of input").required(true).type(String.class).build();
		
		options.addOption(typeOfInput);
		
		final Option key = Option.builder("k").hasArg(true).argName("key").longOpt("key").desc("The Key to find").required(true).type(String.class).build();
		
		options.addOption(key);
			
 		
        	final Option list = Option.builder("l").hasArg(true).argName("list").longOpt("list").desc("List of values").required(true).type(String.class).build();

		list.setArgs(Option.UNLIMITED_VALUES);
		options.addOption(list);
		
		return options;
	}
}					
