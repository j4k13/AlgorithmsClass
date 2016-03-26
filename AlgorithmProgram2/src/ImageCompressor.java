import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Created by jackie on 3/20/16.
 */
public class ImageCompressor {
	
	public static Integer[] path;
	
    public static int getMinCost(Integer[][] array, int r, int c)
    {
    	if (r < 0 || c < 0 || r >= array.length || c >= array.length)
    		return Integer.MAX_VALUE;
    	else if (r == array.length - 1){
    		return array[r][c];
    	}
    	else {
    		
    		//System.out.printf("Checking (%d, %d)%n",r, c);
    		int left  = getMinCost(array, r + 1, c - 1);
    		int none  = getMinCost(array, r + 1, c    );
    		int right = getMinCost(array, r + 1, c + 1);
    		int min   = min( left, none, right );
    		
    		if(min == left)
    			path[r + 1] = c - 1;
    		else if (min == none)
    			path[r + 1] = c;
    		else
    			path[r + 1] = c + 1;
    		
    		return array[r][c] + min;
    	}
    }
    
    
    /**
     * Converts the input line into a 2D Integer array
     * @param line
     * @return array
     */
    public static Integer[][] lineToArray(String line)
    {
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
    		Scanner in = new Scanner(new File("myTester"));
	
			String line = in.nextLine();
         
			//First, convert the file into an array.
	        Integer[][] array = lineToArray(line);
	        
	        //Print the array to console just to be sure it looks right!
	        printArray(array);
	        
	        System.out.println();
	        
	        
	        Integer[] columns = new Integer[array.length];
	        for(int i = 0; i < columns.length; i++){

		        path = new Integer[array.length];
	        	columns[i] = getMinCost(array, 0, i);
	        	//reverse(path);
	        	//path[0] = i;
	        	System.out.print("Col "+i+": ");
		        printArray(new Integer[][] {path});
	        }
	        printArray(new Integer[][] {columns});
	       
	       
	         
	         in.close();
		} 
    	catch (Exception e)
    	{
			e.printStackTrace();
		}
    }
}
