package VerbParsing;

public class VerbStemFinderPaal 
{


    //TODO: find stem letters (only for present verbs for now pa'al)
    public void findStem(String word) throws UnrecognizedStemException 
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
            int newLength = HebrewArrayUtilities.shiftElementsBackAnIndex(wordToArray, indexOfVov, wordToArray.length);

            //TODO: fix the printing.
            HebrewArrayUtilities.PrintRightToLeft(wordToArray);

            if(newLength <= 3)
            {
                System.out.println("FOUND VERB STEM:");
               HebrewArrayUtilities.PrintRightToLeft(wordToArray, newLength);

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

                            newLength = HebrewArrayUtilities.shiftElementsBackAnIndex(wordToArray, newLastIDX, newLength);
                            newLength = HebrewArrayUtilities.shiftElementsBackAnIndex(wordToArray, newLastIDX - 1, newLength);
                            HebrewArrayUtilities.PrintRightToLeft(wordToArray, newLength);
                        }
                    else
                        {
                            System.out.println("Removing feminine singular ending:");
                            newLength = HebrewArrayUtilities.shiftElementsBackAnIndex(wordToArray, lastLetter, newLength);
                            HebrewArrayUtilities.PrintRightToLeft(wordToArray, newLength);
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

                            newLength = HebrewArrayUtilities.shiftElementsBackAnIndex(wordToArray, newLastIDX, newLength);
                            newLength = HebrewArrayUtilities.shiftElementsBackAnIndex(wordToArray, newLastIDX - 1, newLength);
                            HebrewArrayUtilities.PrintRightToLeft(wordToArray, newLength);
                        }
                    //TODO: this won't run because this gets caught earlier. 
                    else
                        {
                            System.out.println("Removing masculine singular ending:");
                            newLength = HebrewArrayUtilities.shiftElementsBackAnIndex(wordToArray, lastLetter, newLength);
                            HebrewArrayUtilities.PrintRightToLeft(wordToArray, newLength);
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


   
}
