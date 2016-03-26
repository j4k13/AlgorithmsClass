import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by jackie on 3/20/16.
 */
public class ImageCompressor {
	
	public static int OPERATIONS = 0;
	//list of the paths
	public static ArrayList<ArrayList<Integer>> AllPaths = new ArrayList<>();

	public static Integer[] path;
	
    public static int getMinCost(Integer[][] array, int r, int c)
    {
    	if (r < 0 || c < 0 || r >= array.length || c >= array.length)
    		return Integer.MAX_VALUE;
    	else if (r == array.length - 1){
    		path[r] = 0; 
    		return array[r][c]; 
    	}
    	else {
    		
    		//System.out.printf("Checking (%d, %d)%n",r, c);
    		int left  = getMinCost(array, r + 1, c - 1); OPERATIONS++;
    		int none  = getMinCost(array, r + 1, c    ); OPERATIONS++;
    		int right = getMinCost(array, r + 1, c + 1); OPERATIONS++;
    		int min   = min( left, none, right );
    		
    		if(min == left)
    			path[r] = c - 1;
    		else if (min == none)
    			path[r] = c;
    		else
    			path[r] = c + 1;
    		
    		//adds list to the list of paths
			AllPaths.add((ArrayList<Integer>) Arrays.asList(path));
			OPERATIONS++;
    		return array[r][c] + min; 
    	}
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
    		Integer[][] array = arrayFromRandom(10, 9, 0);
    		
	        printArray(array);
	        
	        System.out.println();
	        
	        
	        Integer[] columns = new Integer[array.length];
	        //for(int i = 0; i < columns.length; i++){

		       // path = new Integer[array.length];
	        	//columns[i] = getMinCost(array, 0, i);
	        	//reverse(path);
	        	//path[0] = i;
	        	//System.out.print("Col "+i+": ");
		        //printArray(new Integer[][] {path});
	        //}
			//figure out the least disruption
			Integer winner = Collections.min(Arrays.asList(columns));
			//which column has the solution
			int indexWinner = (Arrays.asList(columns)).indexOf(winner);
			//init array for winner path
			Integer [] pathWinner = new Integer [AllPaths.get(indexWinner).size()];
			//build out the array
			for(int index = 0; index < AllPaths.get(indexWinner).size(); index++)
			{
				pathWinner[index] = AllPaths.get(indexWinner).get(index);
			}
			//print path array
			printArray(new Integer[][]{pathWinner});
			System.out.println();
	        printArray(new Integer[][] {columns});
	        System.out.printf("OPS: %,d",OPERATIONS);
	       
	       
	         
	         //in.close();
		} 
    	catch (Exception e)
    	{
			e.printStackTrace();
		}
    }
}
