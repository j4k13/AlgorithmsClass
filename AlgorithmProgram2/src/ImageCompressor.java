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
        ArrayList<Integer> columnTally = new ArrayList<Integer>();
        String nextline = "";
        int index = 0;
        try
        {
            Scanner in = new Scanner(image);
            while(in.hasNextLine())
            {
                //read line give
                nextline = in.nextLine();
                //break the line down
                String [] nextsetofvalues = nextline.split(" ");
                //tally each column
                columnTally.set(index, (Integer.parseInt(nextsetofvalues[index] + columnTally.get(index))));

            }
            in.close();
            int leastdisruptive = Collections.min(columnTally);
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
