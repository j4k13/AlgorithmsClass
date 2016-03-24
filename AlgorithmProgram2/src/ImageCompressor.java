import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by jackie on 3/20/16.
 */
public class ImageCompressor {
	
	public enum Direction{ 
		LEFT(-1), NONE(0), RIGHT(1); 
		public final int value;
		private Direction(int value){
			this.value = value;
		}
	}
    //private
    public void compress(File image)
    {
        //some preliminary code to make set the length of the array list
        ArrayList<Integer> columnTally;
        String nextline = "";
        Integer index = 0;
        Integer minIndex = 0;
        try
        {
            Scanner in = new Scanner(image);
            if(in.hasNextLine())
            {
                //load first line
                nextline = in.nextLine();
            }
            else
            {
                //if file is empty do nothing
                return;
            }
            String [] allNumbers = nextline.split(" ");
            
            in.close();

        }
        catch(IOException e)
        {

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
    			System.out.printf("%3d",array[row][col]);
    			if(col < array[row].length - 1)
    				System.out.print(", ");
    		}
    		System.out.println();
    	}
    }
    
    public static void main(String [] args)
    {
    	try 
    	{
    		Scanner in = new Scanner(new File("given.txt"));
	
			String line = in.nextLine();
         
			//First, convert the file into an array.
	        Integer[][] array = lineToArray(line);
	        
	        //Print the array to console just to be sure it looks right!
	        printArray(array);
	         
	         
	         in.close();
		} 
    	catch (FileNotFoundException e)
    	{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
