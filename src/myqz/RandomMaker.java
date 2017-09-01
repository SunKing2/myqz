package myqz;

import java.util.*;

public class RandomMaker {

	// creates a hashtable of probabilities of each of the array items
	// example input array is:
	// 31 100 67 100
	// hashtable is 0=.1250, 1=1.0, 2=.5, 3=1.0
	// if the array item is >= this , the probability is

	/*
		100	1.0000
		67	0.5000
		45	0.2500
		30	0.1250
		20	0.0625
		13	0.0313
		9	0.0156
		6	0.0078
		4	0.0039
		3	0.0020
		2	0.0010
		1	0.0005
	*/
	
	public static HashMap<Integer, Double> randomMaker(List<Integer> list1) {
		HashMap<Integer, Double> map = new HashMap<>();
		for (int i = 0; i < list1.size(); i++) {
			int item = list1.get(i);
			double prob;
			prob = getProbPercentageOfItem(item);
			map.put(i, prob);
		}
		return map;
	}
	public static double getProbPercentageOfItem(int item) {
		
		// this loop replaces a bunch of if statements:
		/*
		if (item >= 100) prob = 1.0;
		else if (item >= 67)  prob = 0.5;
		else if (item >= 45)  prob = 0.25;
		else if (item >= 30)  prob = 0.1250; etc including item >=1
		*/
		int rating = 100;
		double prob = 1.0;
		while (rating > 1) {
			if (item >= rating) {
				return prob;
			}
			prob = prob / 2.0;
			rating = reduceRating(rating, 0);
		}
		//System.out.println("unusual condition getProbPercentageOfItem, returning very low prob on item:" + item);
		return 0.0005;
	}
	public static int reduceRating(int rating, int time) {
		return (1 + 2 * rating + time) / 3;
	}
	
	// look at the probPercentageOf rating , then
	// generate a random number, 
	// return true if random number < that probPercentage
	public static boolean isChosen(int rating) {
		Double probPercentage = getProbPercentageOfItem(rating);
		Double ran = Math.random();
		return (ran < probPercentage);
	}
}
