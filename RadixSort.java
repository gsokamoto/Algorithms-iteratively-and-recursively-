
/*
 * Can do radix sort iteratively and recursively.
 * @author Grant Okamoto
 * @version 1.0
 * 
 */
import java.util.Arrays;
import java.util.Random;

public class RadixSort {

	public static int countMove;
	public static int countCompare;
	public static void main(String[] args)
	{
		Random randomObj = new Random();
		int[] testArray = new int[1000];
		for (int i = 0; i < testArray.length; i++) {
			testArray[i] = randomObj.nextInt(1000);
		}
		int[] sortedArray = iterativeRS(testArray);
		System.out.println("countMove: " + countMove);
		System.out.println("countCompare: " + countCompare);
	}
	public static int[] iterativeRS(int[] arrayRS) {
		if (arrayRS.length == 0)
		{
			return arrayRS;
		}
		int[][] array2D = new int[arrayRS.length][2];
		int[] matrix = new int[0x100];
		int i, j, k, l, f = 0;
		for (k = 0; k < 4; k++) 
		{
			for (i = 0; i < (array2D.length - 1); i++)
			{
				array2D[i][1] = i + 1;
			}
			array2D[i][1] = -1;
			for (i = 0; i < matrix.length; i++)
			{
				matrix[i] = -1;
			}
			for (f = i = 0; i < arrayRS.length; i++) 
			{
				j = ((0xFF << (k << 3)) & arrayRS[i]) >> (k << 3);
				
				if (matrix[j] == -1)
				{
					l = matrix[j] = f;
				}
				else 
				{
					l = matrix[j];
					countCompare++;
					while (array2D[l][1] != -1)
					{
						l = array2D[l][1];
					}
					array2D[l][1] = f;
					l = array2D[l][1];
				}
				f = array2D[f][1];
				array2D[l][0] = arrayRS[i];
				array2D[l][1] = -1;
			}
			for (l = matrix[i = j = 0]; i < 0x100; i++)
			{
				for (l = matrix[i]; l != -1; l = array2D[l][1])
				{
					arrayRS[j++] = array2D[l][0];
					countMove++;
				}
			}
		}
		return arrayRS;
	}
	
	public static int getMax(int arrayRS[], int size) {
		int max = arrayRS[0];
		for (int i = 1; i < size; i++)
			if (arrayRS[i] > max)
				max = arrayRS[i];
		return max;
	}
	public static void countSort(int arrayRS[], int n, int exp) {
		int output[] = new int[n];
		int i;
		int count[] = new int[10];
		Arrays.fill(count, 0);
		for (i = 0; i < n; i++)
			count[(arrayRS[i] / exp) % 10]++;
		for (i = 1; i < 10; i++)
			count[i] += count[i - 1];
		for (i = n - 1; i >= 0; i--) {
			output[count[(arrayRS[i] / exp) % 10] - 1] = arrayRS[i];
			count[(arrayRS[i] / exp) % 10]--;
		}
		for (i = 0; i < n; i++)
			arrayRS[i] = output[i];
	}
	public static int[] recursiveRS(int arrayRS[], int n) {
		int m = getMax(arrayRS, n);
		for (int exp = 1; m / exp > 0; exp *= 10)
		{
			countSort(arrayRS, n, exp);
		}
		return arrayRS;
	}
}
