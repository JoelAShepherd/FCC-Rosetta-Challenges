package com.jshep.sumto_100;

import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Sum1 {
	private static List<String> numbers = new ArrayList<String>();
	private static List<Integer> convList = new ArrayList<Integer>();
	private static List<String> dubList = 	new ArrayList<String>();
	


public static void solve(int valueX) {
	//Creates ArrayList of String numbers 1-9
	int w = 1;
	while (w<10) {  
		String num = String.valueOf(w);
		numbers.add(num);
		w++;
	}
	
	//Creates an ArrayList of all needed 9 digit ternary numbers
	DecimalFormat df = new DecimalFormat("000000000");
	int m = 0;
	int i = 0;
	while (i<19683) {  
		m = convertToTernary(i);
		double dnum = m;
		String newDub = df.format(dnum);
		char check = newDub.charAt(0);
		int checkChars = Character.compare(check, '2');
		if (checkChars != 0) {
			dubList.add(newDub);
		}
		i++;
	}
	
	int dubListSize = dubList.size();
	ArrayList<String> matchingSums = new ArrayList<>();
	
	// Makes a new ArrayList of alternate numbers/symbols
	int count = 0;
	while (count<dubListSize) {
		String ternNum = dubList.get(count);
		int dubCount = 0;
		ArrayList<String> chars = new ArrayList<>();
		while (dubCount<9) {  
			char nextSym = ternNum.charAt(dubCount);
			String strToSym = Character.toString(nextSym);
			String newSym = numToSymbol(strToSym);
			chars.add(newSym);
			chars.add(numbers.get(dubCount));
			dubCount++;
			
		}
		//Reduces the ArrayList of symbols and numbers to a string
		String charsString = Stream.of(chars).map(arr -> new String(arr.toString())).collect(Collectors.joining());
		String charsString2 = compress(charsString); 
		
		
		//removes unnecessary 'd' character from front of String charsString2
		char check = charsString2.charAt(0);
		int compareChars = Character.compare(check, 'd');
		if (compareChars == 0) {
			charsString2 = charsString2.replaceFirst("d", "");
		}
		
		//adds the String numbers as long to find total
		String[] sumList = charsString2.split("d");
		ArrayList<String> sumList2 = new ArrayList<>(Arrays.asList(sumList));
		int sum = 0;
		for (String a: sumList2) { 
			long e = Long.parseLong(a);
			sum += e;
			
		}
		
		//represents the sum of numbers as a String
		String charsString3 = Stream.of(sumList2).map(arr -> new String(arr.toString())).collect(Collectors.joining());
		String charsString4 = compress(charsString3);
		
		//if the sum of all the values is equal to the input value, 
		//a string of the sum is added to a list to record it
		if (sum == valueX) { 
			matchingSums.add(charsString4);
		}
		
		count++;
		
		}
	//print results
	System.out.println("Results for number:" + valueX);	
	System.out.println(matchingSums);
	System.out.println("Number of solutions:" + matchingSums.size());
	}


//converts a decimal number to a ternary number
public static int convertToTernary(int n) {
	if (convList.size()>0) {
		convList.clear();
	}
	int k = 0;
	int p = n;
	while (p>2) {
		int r = p%3;
		convList.add(r);
		p /= 3;
	}
	convList.add(p);
	Collections.reverse(convList);
	
	String listAsString = Stream.of(convList).map(arr -> new String(arr.toString())).collect(Collectors.joining());
	String compString = compress(listAsString);
	
	k = Integer.parseInt(compString);
	return k;
} 


//removes unnecessary chars from string
public static String compress (String asList) {
	String r = asList;
	r = r.replaceAll(",", "");
	r = r.replaceAll(" ", "");
	r = r.replaceAll("[\\[\\](){}]","");
	return r;
}


//converts 0-2 to symbols for the sum. 'd' is used as the delineator.
public static String numToSymbol (String numberToChange) {
	String symbol = "";
	if (numberToChange.contentEquals("0")) {
		symbol = "";
	}
	else if (numberToChange.contentEquals("1")) {
		symbol = "d-";
	}
	else if (numberToChange.contentEquals("2")) {
		symbol = "d+";
	}
	return symbol;
}


public static void main(String[] args) {
	System.out.println("Enter a number up to 19683 to find out if the numbers"
			+ " 1-9 can be used consecutively to make that number using only"
			+ "+ and - symbols");
	Scanner myScan = new Scanner(System.in);
	System.out.println("What is your number?");
	String number = myScan.nextLine();
	int number2 = Integer.parseInt(number);
	solve(number2);
	myScan.close();
	
}



}
