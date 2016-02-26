import java.util.Random;


public class Algorithms1 {
	
	//public static final int SIZE = 5;
	public static int compCount = 0;
	public static int a1Count = 0;
	public static final int  MAX = 10;
	public static void algor1(int[] array)
	{
		int size = array.length;
		a1Count++;
		int j = 1;
		a1Count++;
		int[] copy = new int [size];
		a1Count++;
		
		int i, m, r, u, v;
		a1Count+= 5;
			
		
		while(j < size - 1)
		{
			i = 1;
			a1Count++;
			while(i < size - j)
			{
				m = i + j - 1;
				a1Count++;
				r = Math.min(m+j,size-1); //not sure what this min function is
				a1Count++;
				for(u = i; u <= r; u++)
				{
					copy[u ] = array[u ];
					a1Count++;
				}
				u = i;
				a1Count++;
				v = m + 1;
				a1Count++;
				for(int w = i; w <= r;w++)
				{
					if(u > m || (v <= r && copy[v ] < copy[u ]))
					{
						array[w ] = copy[v ];
						a1Count++;
						v = v + 1;
						a1Count++;
					}
					else
					{
						array[w ] = copy[u ];
						a1Count++;
						u = u + 1;
						a1Count++;
					}
				}
			i = i + (2 * j);
			a1Count++;
			}
		j = 2 * j;
		a1Count++;
		}
	}
	public static void compute(int[] array, int[] copy, int i)
	{
		if(i >= array.length){
			for(int j = 0; j < array.length; ++j){
				if(copy[j] > 0){
					System.out.print(copy[j]);
					compCount++;
				}
				compCount++;
			}
			System.out.println("");
			compCount++;
		}
		else {
		copy[i] = 0;
		compCount++;
		compute(array, copy, i + 1);
		copy[i] = array[i];
		compCount++;
		compute(array, copy, i + 1);
		copy[i] = 0;
		compCount++;
		}
		compCount++;
	}

	public static void printArray(int[] array){
		for(int i = 0; i < array.length; ++i){
			System.out.printf("[%d]: %d%n", i, array[i]);
		}
	}
	
	/*public static void compareArrays(int[] a, int[] b){
		for(int i = 0; i < Math.min(a.length, b.length); ++i){
			int j = a[i];
			int k = b[i];
			
			String equal = k == j ? "Unchanged" : "Changed";
			
			System.out.printf("[%"+ (SIZE%10 + 1) +"d]: %"+ (MAX%10 + 3) +"d => %"+ (MAX%10 + 3) +"d (%s)%n", i, k, j, equal);
		}
	}*/
	
	/*public static void main(String[] args){
		//Random rand = new Random();
		
		
		int[] array = new int[givenarray.length];
		array[0] = 0;
		
		this code would make a new array with the first index as 0
		this is because Algorithm 1 relies on ignoring the first index
		it is commented out because it is easier for me to test by manually inputing numbers
		
		
		
		//int[] array = new int[15];
		//int[] copy  = new int[5];
		int [] array = new int[] {0,9,8,1,2,4,5,6,3,7,10,12,25,30,15,66,69,13,55,57,48,45,49,37,36};
		
		for(int i = 0; i < 7; i++){
			//array[i] = rand.nextInt(2 * MAX) - MAX;
			copy[i] = array[i];
		}
		
		a1Count = 0;
		algor1(array);
		//compareArrays(array, copy);
		System.out.println("I did this many operations: "+a1Count);
		
	}*/
	
	public static void main(String[] args){
		int[] array = new int[10];
		int[] copy  = new int[10];
		
		compCount = 0;
		array = new int[] {1, 2,3,4,5,6,7,8,9,10};
		compute(array, copy, 0);
		System.out.println("I did this many operations: "+compCount);
	}
	
}
