/*
 * Can do shell sort iteratively and recursively (using Hibbard sequence).
 * @author Grant Okamoto
 * @version 1.0
 * 
 */
import java.util.Random;

public class ShellSort {

	public static int countMove;
	public static int countCompare;
	public static void main(String[] args)
	{
		Random randomObj = new Random();
		int hibbardSeq = 3;
		int[] testArray = new int[10];
		for (int i = 0; i < testArray.length; i++) {
			testArray[i] = randomObj.nextInt(1000);
		}
		int[] sortedArray = recursiveSS(testArray, hibbardSeq, 0);
		sortedArray = recursiveIS(testArray, 0);
		for (int i = 0; i < testArray.length; i++) {
			System.out.println(sortedArray[i]);
		}
		System.out.println("countMove: " + countMove);
		System.out.println("countCompare: " + countCompare);
	}
	public static int[] iterativeSS(int arraySS[]) {
		int decrement = (int)(Math.log(arraySS.length)/Math.log(2));
		int gap = (int)(Math.pow(2,decrement)-1);
		while (gap > 0)
	    {
	        for (int i = 0; i < gap; i++)
	        {
	            for (int j = i + gap; j < arraySS.length; j = j + gap)
	            {
	                for (int k = j; k - gap >= 0; k = k - gap)
	                {            
	                	countCompare++;
	                    if (arraySS[k - gap] <= (arraySS[k]) )
	                    {
	                        break;
	                    }
	                    countMove++;
	                    int temp = arraySS[k];
	                    arraySS[k] = arraySS[k-gap];
	                    arraySS[k-gap] = temp;

	                }
	            }
	        }

	        decrement = decrement -1;
	        gap = (int)(Math.pow(2,decrement)-1);
	    }
		return arraySS;
	}
	
	public static int[] recursiveSS(int[] arraySS, int hibbardSeq, int i)
	{
		if(i != hibbardSeq)
		{
			int numOfComparisons = arraySS.length / hibbardSeq + 1;
			if(i + (numOfComparisons - 1) * hibbardSeq > arraySS.length - 1)
			{
				numOfComparisons--;
			}
		outerLoop(arraySS, numOfComparisons, i, 1, hibbardSeq);
		i++;
		recursiveSS(arraySS, hibbardSeq, i);
		}
		return arraySS;
	}
	public static void outerLoop(int[] arraySS, int numOfComparisons, int i, int j, int hibbardSeq)
	{
		if (j != numOfComparisons)
		{
			int k = j;
			innerLoop(arraySS, i, k, hibbardSeq);
			j++;
			outerLoop(arraySS, numOfComparisons, i, j, hibbardSeq);
		}
	}

	public static void innerLoop(int[] arraySS, int i, int k, int hibbardSeq)
	{
		if (k != 0)
		{
			if (arraySS[(k - 1) * hibbardSeq + i] > arraySS[k * hibbardSeq + i])
			{
				int temp = arraySS[(k - 1) * hibbardSeq + i];
				arraySS[(k - 1) * hibbardSeq + i] = arraySS[k * hibbardSeq + i];
				arraySS[ k * hibbardSeq + i] = temp;
				k--;
				innerLoop(arraySS, i, k, hibbardSeq);
			}
		}
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