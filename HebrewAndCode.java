import java.util.Scanner;

public class HebrewAndCode {

    public static void main(String[] args) 
    {
        Scanner scr = new Scanner(System.in);

        try
        {
            //findStem("למד");
            findStem("לומדות");
            System.out.println("------TEST 2-------");
            findStem("לומדת");
            System.out.println("------TEST 3-------");
            findStem("לומדים");
            System.out.println("------TEST 4-------");
            findStem("לומד");
            
        }
        catch(UnrecognizedStemException stemE)
        {
            System.out.println(stemE.getMessage());
        }
    }

    private static void PrintHebrewAlphabet(String input) {
        char inputChar = input.charAt(0);
        System.out.println(input);
        System.out.println(inputChar);
        System.out.println((int)inputChar);
        
        //if you start at א then you will print out the rest of the hebrew alphabet like this. 
        for(int i = 0; i< 27; i++) System.out.println(((char)((int)inputChar + i)));
    }


    //TODO: find stem letters (only for present verbs for now pa'al)
    public static void findStem(String word) throws UnrecognizedStemException 
    {
        //TODO: deal with infinitive

        //To find step must:
        //Strip vov ו
        //take off the ending letters
        //check the last letter for mem or taf
        //  if it has one of these, check if there is a 
        //  yud or vov on the second to last letter

        char[] wordToArray = word.toCharArray();
        
        //Check first half of word for vov
        char currentL = wordToArray[0];
        int indexOfVov = 0;
        boolean foundVov = false;
        boolean validVovPlacement = false;

        //find vov.
        System.out.println("STEP 1: Finding Valid Vov....");
        for(indexOfVov = 0;  indexOfVov < wordToArray.length; indexOfVov++)
            {

                currentL = wordToArray[indexOfVov];
                if(currentL == 'ו') 
                    {
                        foundVov = true;
                        System.out.println("Found a VOV at index: " + indexOfVov);

                        //check if vov is mid or lower in index 
                        //(shouldn't be lower than that I don't think)
                        validVovPlacement = (indexOfVov / 2) <= wordToArray.length / 2;

                        break;
                    }
            }
       
       
        

        //remove vov if valid placement
        
        if(validVovPlacement)
        {
            //continue parsing

            //Remove vov.
            //NOTE: Latin "X" stands for NULL here.
            //TODO: implement something if ever hit an "X"
            System.out.println("STEP 2: Removing Vov....");

            //Shift each element back 1 index
            int newLength = shiftElementsBackAnIndex(wordToArray, indexOfVov, wordToArray.length);

            //TODO: fix the printing.
            PrintRightToLeft(wordToArray);

            if(newLength <= 3)
            {
                System.out.println("FOUND VERB STEM:");
               PrintRightToLeft(wordToArray, newLength);

                System.out.println("");
                return;

            } 
            //easily keep track of new last index
            int newLastIDX = newLength - 1;
            
            
            //STEP 3: Check ending letters 
            System.out.println("STEP 3: Check ending letters....");
            char lastLetter = wordToArray[newLastIDX];
            System.out.println(lastLetter);

            //TODO: finish step 3
            if(lastLetter == 'ת')
                {
                    //Check second to last letter for a vov
                    char secondToLast = wordToArray[newLastIDX - 1];
                    if(secondToLast == 'ו') 
                        {
                            System.out.println("Removing feminine plural ending:");

                            newLength = shiftElementsBackAnIndex(wordToArray, newLastIDX, newLength);
                            newLength = shiftElementsBackAnIndex(wordToArray, newLastIDX - 1, newLength);
                            PrintRightToLeft(wordToArray, newLength);
                        }
                    else
                        {
                            System.out.println("Removing feminine singular ending:");
                            newLength = shiftElementsBackAnIndex(wordToArray, lastLetter, newLength);
                            PrintRightToLeft(wordToArray, newLength);
                        }
                    
                    //check if it is three letters now
                    if(newLength <= 3) System.out.println("Verb Stem Complete.");
                }
                //Check for both end letter and error where mem is not the end letter.
            else if(lastLetter == 'ם' || lastLetter == 'מ')
                {
                    
                    char secondToLast = wordToArray[newLastIDX - 1];
                    if(secondToLast == 'י') 
                        {
                            System.out.println("Removing masculine plural ending:");

                            newLength = shiftElementsBackAnIndex(wordToArray, newLastIDX, newLength);
                            newLength = shiftElementsBackAnIndex(wordToArray, newLastIDX - 1, newLength);
                            PrintRightToLeft(wordToArray, newLength);
                        }
                    //TODO: this won't run because this gets caught earlier. 
                    else
                        {
                            System.out.println("Removing masculine singular ending:");
                            newLength = shiftElementsBackAnIndex(wordToArray, lastLetter, newLength);
                            PrintRightToLeft(wordToArray, newLength);
                        }
                    
                    //check if it is three letters now
                    if(newLength <= 3) System.out.println("Verb Stem Complete.");
                }

        }
        else
        {
            //No vov, so we don't know how to handle.
            throw new UnrecognizedStemException();
            
        }
    }

    private static int shiftElementsBackAnIndex(char[] wordToArray, int elementIndexToRemove, int length) {
        for(int idx = elementIndexToRemove; idx + 1 < length ; idx++)
        {
                wordToArray[idx] = wordToArray[idx + 1];                 
        }

        //Change the last letter to an X to represent the removal (and, print last letter).
        wordToArray[length - 1] = 'X';
        
        //return new length;
        return length - 1;
    }


    public static void PrintRightToLeft(char[] hebrewWordArray)
    {
          for(int idx = hebrewWordArray.length - 1; idx >= 0; idx--) 
                {
                    System.out.print(hebrewWordArray[idx]);
                }
            System.out.println("");
    }

    public static void PrintRightToLeft(char[] hebrewWordArray, int length)
    {
          for(int idx = length - 1; idx >= 0; idx--) 
                {
                    System.out.print(hebrewWordArray[idx]);
                }
            System.out.print("\n");
    }


    

}

class UnrecognizedStemException extends Exception
{
    public UnrecognizedStemException()
    {
        super("Can't handle this Stem");
    }
}