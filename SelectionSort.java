
/*
 * Can do selection sort iteratively and recursively.
 * @author Grant Okamoto
 * @version 1.0
 * 
 */
import java.util.Random;

public class SelectionSort {
	//iterative selection sort
	public static int countMove;
	public static int countCompare;
	public static int[] iterativeSS(int[] arraySS, int currentIndex)
	{
		for(int i = 0; i < arraySS.length; i++)
		{
			int minIndex = i;
			for(int j = i + 1; j < arraySS.length; j++)
			{
				countCompare++;
				if(arraySS[minIndex] > arraySS[j])
				{
					minIndex = j;
				}
			}
			int tempIndex = arraySS[i];
			arraySS[i] = arraySS[minIndex];
			arraySS[minIndex] = tempIndex;
			countMove++;
		}
		return arraySS;
	}
	
	public static int minIndex(int[] arraySS, int currentIndex, int nextIndex)
	{
		if(nextIndex >= arraySS.length)
		{
			return currentIndex;
		}
		else
		{
			if(arraySS[currentIndex] > arraySS[nextIndex])
			{
				currentIndex = nextIndex;
			}
			return minIndex(arraySS, currentIndex, nextIndex + 1);
		}
	}
	//recursive selection sort
	public static int[] recursiveSS(int[] arraySS, int currentIndex)
	{
		if(currentIndex >= arraySS.length)
		{
			return arraySS;
		}
		int minIndex = minIndex(arraySS, currentIndex, currentIndex);
		int tempValue = arraySS[currentIndex];
		arraySS[currentIndex] = arraySS[minIndex];
		arraySS[minIndex] = tempValue;
		
		return recursiveSS(arraySS, currentIndex + 1);
	}
	
	public static void main(String[] args)
	{
  		Random randomObj = new Random();
  		int[] testArray = new int[1000];
		for(int i = 0; i < testArray.length; i++)
		{
			testArray[i] = randomObj.nextInt(1000);
		}
		int[] sortedArray = iterativeSS(testArray, 0);
		System.out.println("countMove: " + countMove);
		System.out.println("countCompare: " + countCompare);
	}
}
