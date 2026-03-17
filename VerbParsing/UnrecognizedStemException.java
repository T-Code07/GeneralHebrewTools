package VerbParsing;
public class UnrecognizedStemException extends Exception
{
    public UnrecognizedStemException()
    {
        super("Can't handle this Stem");
    }
}
