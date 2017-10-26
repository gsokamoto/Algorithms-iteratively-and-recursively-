/*
 * Can do shell sort iteratively and recursively.
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
		int[] testArray = new int[10];
		for (int i = 0; i < testArray.length; i++) {
			testArray[i] = randomObj.nextInt(1000);
		}
		int[] sortedArray = iterativeSS(testArray);
		System.out.println("countMove: " + countMove);
		System.out.println("countCompare: " + countCompare);
	}
	public static int[] iterativeSS(int arraySS[]) {
		int k = (int)(Math.log(arraySS.length)/Math.log(2));
		int gap = (int)(Math.pow(2,k)-1);
		while (gap > 0)
	    {
	        for (int g = 0; g < gap; g++)
	        {
	            for (int d = g + gap; d < arraySS.length; d = d + gap)
	            {
	                for (int i = d; i - gap >= 0; i = i - gap)
	                {            
	                	countCompare++;
	                    if (arraySS[i - gap] <= (arraySS[i]) )
	                    {
	                        break;
	                    }
	                    countMove++;
	                    int temp = arraySS[i];
	                    arraySS[i] = arraySS[i-gap];
	                    arraySS[i-gap] = temp;

	                }
	            }
	        }

	        k = k -1;
	        gap = (int)(Math.pow(2,k)-1);
	    }
		return arraySS;
	}
}