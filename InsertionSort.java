
/*
 * Can do insertion sort iteratively and recursively.
 * @author Grant Okamoto
 * @version 1.0
 * 
 */
import java.util.Random;

public class InsertionSort {
	public static int countMove;
	public static int countCompare;
	public static void main(String[] args)
	{
		Random randomObj = new Random();
		int[] testArray = new int[1000];
		for (int i = 0; i < testArray.length; i++) {
			testArray[i] = randomObj.nextInt(1000);
		}
		int[] sortedArray = iterativeIS(testArray, 0);
		System.out.println("countMove: " + countMove);
		System.out.println("countCompare: " + countCompare);
	}
	public static int[] iterativeIS(int[] arrayIS, int currentIndex)
	{
		for(int i = 1; i < arrayIS.length; i++)
		{
			for(int j = i; j > 0; j--)
			{
				countCompare++;
				if(arrayIS[j] < arrayIS[j - 1])
				{
					int tempValue = arrayIS[j - 1];
					arrayIS[j - 1] = arrayIS[j];
					arrayIS[j] = tempValue;
					countMove++;
				}
			}
		}
		return arrayIS;
	}
	
	public static int[] sortArray(int[] arrayIS, int currentIndex)
	{
		if(currentIndex <= 0)
		{
			return arrayIS;
		}
		else
		{
			if(arrayIS[currentIndex] < arrayIS[currentIndex - 1])
			{
				int tempValue = arrayIS[currentIndex];
				arrayIS[currentIndex] = arrayIS[currentIndex - 1];
				arrayIS[currentIndex - 1] = tempValue;
			}
		}
		return sortArray(arrayIS, currentIndex - 1);
	}
	
	public static int[] recursiveIS(int[] arrayIS, int currentIndex)
	{
		if(currentIndex == arrayIS.length - 1)
		{
			return arrayIS;
		}
		else
		{
			arrayIS = sortArray(arrayIS, currentIndex + 1);
		}
		return recursiveIS(arrayIS, currentIndex + 1);
	}
}
