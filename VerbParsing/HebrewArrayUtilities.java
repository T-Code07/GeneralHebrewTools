package VerbParsing;

public class HebrewArrayUtilities {
      //NOTE: this next two functions, for some reason, the commented out version is needed for
    //      for correct order in the VS Code terminal, but not in the file. 
    public static void PrintRightToLeft(char[] hebrewWordArray)
    {
            for(int idx = 0; idx < hebrewWordArray.length; idx++)
         // for(int idx = hebrewWordArray.length - 1; idx >= 0; idx--) 
                {
                    System.out.print(hebrewWordArray[idx]);
                }
            System.out.println("");
    }

    public static void PrintRightToLeft(char[] hebrewWordArray, int length)
    {
        for(int idx = 0; idx < length; idx++)
       //  for(int idx = length - 1; idx >= 0; idx--) 
                {
                    System.out.print(hebrewWordArray[idx]);
                }
            System.out.print("\n");
    }

    public static int shiftElementsBackAnIndex(char[] wordToArray, int elementIndexToRemove, int length) {
        for(int idx = elementIndexToRemove; idx + 1 < length ; idx++)
        {
                wordToArray[idx] = wordToArray[idx + 1];                 
        }

        //Change the last letter to an X to represent the removal (and, print last letter).
        wordToArray[length - 1] = 'X';
        
        //return new length;
        return length - 1;
    }
    
}
