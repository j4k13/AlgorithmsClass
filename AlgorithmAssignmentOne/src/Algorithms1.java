import java.util.Random;


public class Algorithms1 {
	
	public static final int SIZE = 16;
	public static final int  MAX = 10;
	public static void algor1(int[] array)
	{
		int size = array.length;
		int j = 1;
		int[] copy = new int [size];
		
		int i, m, r, u, v;
		
		
		while(j <= size - 1)
		{
			i = 1;
			while(i <= size - j)
			{
				m = i + j - 1;
				r = Math.min(m+j,size); //not sure what this min function is
				
				for(u = i; u <= r; u++)
				{
					copy[u - 1] = array[u - 1];
				}
				u = i;
				v = m + 1;
				for(int w = i; w <= r;w++)
				{
					if(u > m || (v <= r && copy[v - 1] < copy[u - 1]))
					{
						array[w - 1] = copy[v - 1];
						v = v + 1;
					}
					else
					{
						array[w - 1] = copy[u - 1];
						u = u + 1;
					}
					i = i + (2 * j);
				}
			j = 2 * j;
			}
		}
	}
	public static void compute(int[] array, int[] copy, int i)
	{
		if(i >= array.length){
			for(int j = 0; j < array.length; ++j){
				if(copy[j] > 0){
					System.out.println(copy[j]);
				}
			}
		} else {
		copy[i] = 0;
		compute(array, copy, i + 1);
		copy[i] = array[i];
		compute(array, copy, i + 1);
		copy[i] = 0;
		}
	}

	public static void printArray(int[] array){
		for(int i = 0; i < array.length; ++i){
			System.out.printf("[%d]: %d%n", i, array[i]);
		}
	}
	
	public static void compareArrays(int[] a, int[] b){
		for(int i = 0; i < Math.min(a.length, b.length); ++i){
			int j = a[i];
			int k = b[i];
			
			String equal = k == j ? "Unchanged" : "Changed";
			
			System.out.printf("[%"+ (SIZE%10 + 1) +"d]: %"+ (MAX%10 + 3) +"d => %"+ (MAX%10 + 3) +"d (%s)%n", i, k, j, equal);
		}
	}
	
	public static void main(String[] args){
		Random rand = new Random();
		
		int[] array = new int[SIZE];
		int[] copy  = new int[SIZE];

		array = new int[] {0, 1, -1, 2, -2, 3, -3, 4, -4, 5, -5, 6, -6, 7, -7, 8};
		
		for(int i = 0; i < SIZE; i++){
			//array[i] = rand.nextInt(2 * MAX) - MAX;
			copy[i] = array[i];
		}
		
		
		algor1(array);
		compareArrays(array, copy);
	}
	
//	public static void main(String[] args){
//		int[] array = new int[SIZE];
//		int[] copy  = new int[SIZE];
//		
//		array = new int[] {-1, 2, -3, 4, -5, 6, -7, 8, -9, 10};
//		compute(array, copy, 0);
//	}
	
}
