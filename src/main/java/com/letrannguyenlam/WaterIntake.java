package com.letrannguyenlam;

import java.util.Scanner;

public class WaterIntake {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int age, timeworkout;
		double weight;
		
		Scanner scan = new Scanner (System.in);
		
		System.out.println("Age: ");
		age = scan.nextInt();
		
		System.out.println("Weight: ");
		weight = scan.nextDouble();
		weight = kiloToPounds(weight);
		
		System.out.println("Time workout per day: ");
		timeworkout = scan.nextInt();
		
		System.out.println("Water intake per day: " + calculateWaterIntake(weight, age, timeworkout) + " litre");
	}
	
	public static double kiloToPounds (double kilo)
	{
		return kilo*2.20462262185;
	}
	
	public static double calculateWaterIntake(double weight, int age, int timeworkout)
	{
		double result;
		
		result = weight/2.2;
		
		if(age < 30)
			result = (result*40)/28.3;
		else if (age >= 30 && age < 55)
			result = (result*35)/28.3;
		else 
			result = (result*30)/28.3;
		
		// Convert ounces to litre
		return (result + (timeworkout/30*12))*0.02957;
	}

}
