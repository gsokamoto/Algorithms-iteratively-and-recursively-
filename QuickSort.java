/*
 * Can do quick sort iteratively and recursively.
 * @author Grant Okamoto
 * @version 1.0
 * 
 */
import java.util.Random;

public class QuickSort {
	public static int countMove;
	public static int countCompare;
	public static void main(String[] args)
	{
		Random randomObj = new Random();
		int[] testArray = new int[1000];
		for (int i = 0; i < testArray.length; i++) {
			testArray[i] = randomObj.nextInt(1000);
		}
		int[] sortedArray = iterativeQS(testArray, 0, testArray.length - 1);
		System.out.println("countMove: " + countMove);
		System.out.println("countCompare: " + countCompare);
	}
	public static int partition(int arrayQS[], int low, int high) 
	{
		int pivot = arrayQS[high];
		int i = (low - 1);
		int tempValue;
		for (int j = low; j <= high - 1; j++) 
		{
			countCompare++;
			if (arrayQS[j] <= pivot) 
			{
				countMove++;
				i++;
				tempValue = arrayQS[j];
				arrayQS[j] = arrayQS[i];
				arrayQS[i] = tempValue;
			}
		}
		tempValue = arrayQS[i + 1];
		arrayQS[i + 1] = arrayQS[high];
		arrayQS[high] = tempValue;

		return i + 1;
	}
	
	public static int[] iterativeQS(int arrayQS[], int low, int high) 
	{
		int stack[] = new int[high - low + 1];
		int top = -1;
		stack[++top] = low;
		stack[++top] = high;
		while (top >= 0) 
		{
			high = stack[top--];
			low = stack[top--];
			int pivot = partition(arrayQS, low, high);
			if (pivot - 1 > low) 
			{
				stack[++top] = low;
				stack[++top] = pivot - 1;
			}
			if (pivot + 1 < high) 
			{
				stack[++top] = pivot + 1;
				stack[++top] = high;
			}
		}
		return arrayQS;
	}
	
	public static int[] recursiveQS(int arrayQS[], int low, int high) 
	{
		if (low < high) 
		{
			int pivot = partition(arrayQS, low, high);
			recursiveQS(arrayQS, low, pivot - 1);
			recursiveQS(arrayQS, pivot + 1, high);
		}
		return arrayQS;
	}
}
