import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Created by jackie on 3/20/16.
 */
public class ImageCompressor {
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
            String [] nextsetofvalues = nextline.split(" ");
            //set the size in one go
            columnTally = new ArrayList<Integer>(nextsetofvalues.length);
            //add all necesssary
            for(iterator = 0; iterator < nextsetofvalues.length; iterator++)
            {
                Integer nextValue = Integer.parseInt(nextsetofvalues[iterator]);
                //add first set of numbers
                columnTally.set(iterator, (nextValue));
                //compare to track min
                if(columnTally.get(minIndex) > nextValue)
                {
                    minIndex = iterator;
                }
            }
            while(in.hasNextLine())
            {
                //read line give
                nextline = in.nextLine();
                //break the line down
                nextsetofvalues = nextline.split(" ");
                //tally each column by adding what it in the line to what the sum of previous values
                columnTally.set(index, (Integer.parseInt(nextsetofvalues[index] + columnTally.get(index))));
                //continously track the current min value
                if(columnTally.get(minIndex) < nextValue)
                {
                    minIndex = index;
                }

            }
            in.close();

        }
        catch(IOException e)
        {

        }

    }

    public static void main(String [] args)
    {
        //give file

    }
}
