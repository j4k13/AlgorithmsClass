/*
 Compute maximum revenue using dynamic programming

 Modified by Jacqueline Anderson
 */

public class CutRod {
    //array of prices
    static int [] PriceArray = {0,1,5,8,9,10,17,17,20,24,30};

    static int cut_rod(int LengthofRod, int PriceofCut){
        //r[i] is maximum possible revenue from rod of length i
        int [] ArrayofCutPieces = new int[LengthofRod+1];
        ArrayofCutPieces[0] = 0; //trivial initial condition
        int NextCut;
        //use r[0] to get r[1]; use r[0..1] to get r[2], use r[0..2] to get r[3] ......
        for (int FirstIterator=1; FirstIterator <= LengthofRod; FirstIterator++){
            NextCut = -1;
            for(int SecondIterator=1; SecondIterator<=FirstIterator; SecondIterator++){
                NextCut = Math.max(NextCut, PriceArray[SecondIterator] + ArrayofCutPieces[FirstIterator-SecondIterator] - PriceofCut); //maximize PriceArray[i] + r[j-i]
            }
            ArrayofCutPieces[FirstIterator] = NextCut;
        }
        return ArrayofCutPieces[LengthofRod];
    }

    public static void main(String[] args){
        int n = 9;
        int PricetoCut = 1;
        System.out.println("Maximum revenue: " + cut_rod( n , PricetoCut ));
    }


}
