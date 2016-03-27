import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by Jacqueline Anderson on 3/20/16.
 *
 * authors Jacqueline Anderosn and Landon Reams
 */
public class ImageCompressor {
	
	public static int OPERATIONS = 0;
	public static Integer [][] indices;
	/**
	 * Converts the input array into an array that has the minimum path per each row
	 * @param array
	 * @return array
	 */
	public static Integer[][] transformArray(Integer[][] inputArray)
	{
		Integer[][] transformed = new Integer[inputArray.length][inputArray[0].length];
		
		for(int r = 0; r < transformed.length; r++){
			for(int c = 0; c < transformed[0].length; c++){
				transformed[r][c] = new Integer(inputArray[r][c]);
			}
		}
				
		for(int row = 1; row < inputArray.length; row++)
		{
			for(int column = 0; column < inputArray[0].length;column++)
			{
				if(row == inputArray.length -1)
				{
					break;
				}
				int value1;
				int value2;
				int value3;
				if(column != 0)
				{
					value1 = inputArray[row+1][column-1];
				}
				else
				{
					value1 = Integer.MAX_VALUE;
				}
				if(column != inputArray[0].length - 1)
				{
					value3 = inputArray[row + 1][column+1];
				}
				else
				{
					value3 = Integer.MAX_VALUE;
				}
				value2 = inputArray[row+1][column];
				transformed[row][column] += min(value1,value2,value3);
			}
		}
		return transformed;
	}

	/**
	 * finds the best path up, and saves the indices in 2d global array
	 * @param array
	 * @return int Disruption
	 */
	public static int findBestPath(Integer [][] inputArray)
	{
		int leastDisrupt = Integer.MAX_VALUE;
		indices = new Integer [inputArray.length][2];
		indices[indices.length - 1][0] = inputArray.length;
		int currentValue = 0;
		//loop through the bottom array to find min start value
		for(int iterator = 0; iterator < inputArray[inputArray.length -1].length;iterator++)
		{
			if(inputArray[inputArray.length - 1][iterator] < leastDisrupt)
			{
				leastDisrupt = inputArray[inputArray.length - 1][iterator];
				currentValue = iterator;
			}
		}
		indices[indices.length - 1][1] = currentValue;
		leastDisrupt = Trace(inputArray, leastDisrupt, indices.length, currentValue);
		return leastDisrupt;
	}

	/**
	* actually traces the path up for findBestPath
	* @param array
	* @return int Disruption
	*/
    public static int Trace(Integer [][] inputArray, int Disrupt, int row, int column)
	{
		//basecase
		if(row == 0)
		{
			return Disrupt;
		}
		else {
			int value1;
			int value2;
			int value3;
			//make sure to not run out of bounds on left
			if (column != 0) {
				value1 = inputArray[row - 1][column - 1];
			}
			else {
				value1 = Integer.MAX_VALUE;
			}
			//don't run out of bounds on right
			if (column < inputArray[0].length - 1) {
				value3 = inputArray[row - 1][column + 1];
			}
			else {
				value3 = Integer.MAX_VALUE;
			}
			//grab value straight above
			value2 = inputArray[row - 1][column];
			//add the min to the total disruption cost
			int disruptaddition = min(value1, value2, value3);
			Disrupt += disruptaddition;
			//set indices for trace
			indices[row - 1][0] = row - 1;
			if (disruptaddition == value1) {
				indices[row - 1][1] = column - 1;
			}
			if (disruptaddition == value2) {
				indices[row - 1][1] = column;
			}
			if (disruptaddition == value3) {
				indices[row - 1][1] = column + 1;
			}
		}
		row--;
		Trace(inputArray, Disrupt, row, indices[row][1]);
		return 0;
	}

    /**
     * Converts the input line into a 2D Integer array
     * @param line
     * @return array
     * @throws FileNotFoundException 
     */
    public static Integer[][] arrayFromFile(String file) throws FileNotFoundException 
    {
    	Scanner in = new Scanner(new File(file));
  
    	String line = in.nextLine();
    	String[] numbers = line.split(" ");
    	int width = Integer.parseInt(numbers[0]);
    	
    	if(numbers.length != (width * width + 1) )
    		throw new IllegalArgumentException("Input it snot a square array!");
    	
    	Integer[][] result = new Integer[width][width];
    	
    	for(int row = 0; row < width; row++)
    	{
    		for(int col = 0; col < width; col++)
    		{
    			result[row][col] = Integer.parseInt(numbers[row * width + col + 1]);
    		}
    	}
    	
    	return result;
    }
    
    public static Integer[][] arrayFromRandom(int size, int max, int min){
    	Random rand = new Random();
    	
		Integer[][] array = new Integer[size][size];
		
		for(int r = 0; r < size; r++){
			for(int c = 0; c < size; c++){
				array[r][c] = rand.nextInt(max + min) - min;
			}
		}
		
		return array;
    }

    
    /**
     * Simple array printing.
     * @param array
     */
    public static void printArray(Integer[][] array)
    {
    	for(int row = 0; row < array.length; row++)
    	{
    		for(int col = 0; col < array[row].length; col++)
    		{
    			System.out.printf("%3s", array[row][col] == null ? "-" : array[row][col]);
    			if(col < array[row].length - 1)
    				System.out.print(", ");
    		}
    		System.out.println();
    	}
    }
    
    private static int min(int a, int b, int c)
    {
    	if( a < b )
    		return a < c ? a : c;
    	else
    		return b < c ? b : c;
    }
    
    public static void reverse(Integer[] array){
    	List<Integer> list = Arrays.asList(array);
    	Collections.reverse(list);
    	array = list.toArray(array);
    }
    
    public static void main(String [] args)
    {
    	try
    	{
    		//Integer[][] array = arrayFromRandom(100, 150, 0);
			Scanner in = new Scanner(new File("given.txt"));
			Integer size = Integer.parseInt(in.next());
			Integer [][] array = new Integer[size][size];
			for(int rows = 0; rows < array.length; rows++)
			{
				for(int columns = 0 ; columns < array.length; columns++)
				{
					array[rows][columns] = Integer.parseInt(in.next());
				}
			}
	        printArray(array);
	        
			System.out.println();

			Integer[][] transformed = transformArray(array);

			printArray(transformed);
			
			System.out.println();

			findBestPath(transformed);
			
			int bestSum = 0;
			
			for(int r = 0; r < indices.length; r++){
				bestSum += array[r][indices[r][1]];
			}

			System.out.printf("Sum: %d%nPath: [",bestSum);
			for(int i = 0; i < indices.length; i++){
				System.out.printf("%d", indices[i][1]);
				if(i < indices.length - 1)
					System.out.print(", ");
				else
					System.out.println("]");
			}
			in.close();

		}
    	catch (Exception e)
    	{
			e.printStackTrace();
		}
    }
}
