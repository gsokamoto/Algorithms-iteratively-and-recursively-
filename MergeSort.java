/*
 * Can do merge sort iteratively and recursively.
 * @author Grant Okamoto
 * @version 1.0
 * 
 */
import java.util.Random;

public class MergeSort 
{
	public static int countMove;
	public static int countCompare;
	public static void main(String[] args)
	{
		Random randomObj = new Random();
		int[] testArray = new int[1000];
		for (int i = 0; i < testArray.length; i++) {
			testArray[i] = randomObj.nextInt(1000);
		}
		int[] sortedArray = iterativeMS(testArray, 0, testArray.length);
		System.out.println("countMove: " + countMove);
		System.out.println("countCompare: " + countCompare);
	}
	public static int[] merge(int arrayMS[], int left, int right, int mid) 
	{
		int first = mid - left + 1;
		int second = right - mid;

		int a = 0, b = 0, c = left;
		int Left[] = new int[first];
		int Right[] = new int[second];

		for (int i = 0; i < first; i++) 
		{
			Left[i] = arrayMS[left + i];
		}
		for (int j = 0; j < second; j++) 
		{
			Right[j] = arrayMS[mid + 1 + j];
		}

		while (a < first && b < second) 
		{
			countCompare++;
			if (Left[a] < Right[b]) 
			{
				countMove++;
				arrayMS[c] = Left[a];
				a++;
				c++;
			} 
			else 
			{
				countMove++;
				arrayMS[c] = Right[b];
				b++;
				c++;
			}
		}

		while (a < first) 
		{
			countMove++;
			arrayMS[c] = Left[a];
			a++;
			c++;
		}

		while (b < second) 
		{
			countMove++;
			arrayMS[c] = Right[b];
			b++;
			c++;
		}
		return arrayMS;
	}

	public static int[] recursiveMS(int arrayMS[], int start, int end) 
	{

		if (start < end) 
		{
			int mid = (end + start) / 2;
			recursiveMS(arrayMS, start, mid);
			recursiveMS(arrayMS, mid + 1, end);
			arrayMS = merge(arrayMS, start, end, mid);
		}
		return arrayMS;
	}

	public static int[] iterativeMS(int arrayMS[], int start, int end) 
	{
		int i = 2, j = 0;
		while (i < arrayMS.length) 
		{
			j = 0;
			while (j < arrayMS.length - 1) 
			{
				int right = (j + i) - 1;
				int left = j;
				if (right > arrayMS.length) 
				{
					right = arrayMS.length - 1;
				}
				int mid = (left + right) / 2;
				merge(arrayMS, left, right, mid);
				j = j + i;
			}
			i = i * 2;
			if (i >= arrayMS.length) 
			{
				i = i / 2;
				merge(arrayMS, 0, arrayMS.length - 1, (i - 1));
				i = arrayMS.length;
			}
		}
		return arrayMS;
	}
	
}