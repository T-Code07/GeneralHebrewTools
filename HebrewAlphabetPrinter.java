public class HebrewAlphabetPrinter {

      public void PrintHebrewAlphabet(String input) {
        char inputChar = input.charAt(0);
        System.out.println(input);
        System.out.println(inputChar);
        System.out.println((int)inputChar);
        
        //if you start at א then you will print out the rest of the hebrew alphabet like this. 
        for(int i = 0; i< 27; i++) System.out.println(((char)((int)inputChar + i)));
    }
}
