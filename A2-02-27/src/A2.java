import java.util.*;
//--------------------------------------------------------------------
/**
 * COMP 2503 Winter 2020 Assignment 2.
 * <p>
 * This program reads a text file and compiles a list of the
 * words together with how many each word appears.
 * </p>
 * <p>
 * Words from a standard list of stop words are not included.
 * </p>
 * Updated Winter 2020
 *
 * @author Maryam Elahi
 * @author Dylan Borchert
 */
public class A2 {
    
    private static final int MAX_DISPLAY = 10;

    private SLL<Token> list = new SLL<Token>();

    private String[] stopwords = {"a", "about", "all", "am", "an",
            "and", "any", "are", "as", "at", "be", "been", "but", "by", "can",
            "cannot", "could", "did", "do", "does", "else", "for", "from",
            "get", "got", "had", "has", "have", "he", "her", "hers", "him",
            "his", "how", "i", "if", "in", "into", "is", "it", "its", "like",
            "more", "me", "my", "no", "now", "not", "of", "on", "one",
            "or", "our", "out", "said", "say", "says", "she", "so", "some",
            "than", "that", "the", "their", "them", "then", "there", "these",
            "they", "this", "to", "too", "us", "upon", "was", "we", "were",
            "what", "with", "when", "where", "which", "while", "who",
            "whom", "why", "will", "you", "your"};

    private int totalWordList = 0;

    private int stopWordList = 0;

    private Scanner input = new Scanner( System.in);

    public static void main( String[] args) {
        A2 a = new A2();
        a.run();
    }


    /**
     * Will sort and print the final result of the wordList.
     */
    private void printResults()
    {

        System.out.println( "Total Words: " + totalWordList);
        System.out.println( "Unique Words: " + list.size());
        System.out.println( "Stop Words: " + stopWordList);
        System.out.println();
        System.out.println( MAX_DISPLAY + " Most Frequent");

        printTopTen(mostfreq());

        System.out.println();
        System.out.println( MAX_DISPLAY + " Least Frequent");

        printTopTen(leastFreq());

        System.out.println();
        System.out.println( "All");

        printList();


    }

    private void printTopTen(SLL<Token> listToPrint) {
    	//TODO:
    }

    private void printList() {
        System.out.println("ran print list");
        for(Token curToken : list) {
            System.out.println(curToken.getWord() + ":" + curToken.getFrequency());
        }
    }

    private SLL<Token> leastFreq() {
        SLL<Token> leastFreqList = new SLL<Token>(new LeastFrequentWord());
        //TODO:
        return leastFreqList;
    }

    private SLL<Token> mostfreq() {
        SLL<Token> mostFreqList = new SLL<Token>(new MostFrequentWords());
        //TODO:
        return mostFreqList;
    }



    /**
     * Will use Scanner to read the input and add it to wordList.
     * <p>
     * If the word is a stop word, then it will not be added
     * </p>
     * <p>
     * If the word is unique, then it will be added to the wordList
     * </p>
     */
    private void readFile() {

        while ( input.hasNext()) {
            String word = input.next().toLowerCase().
                    replaceAll("[^a-z]", "").trim();

            if ( word.length() > 0) {
                if ( isStopWord( word)) {
                    stopWordList++;
                } else {
                    checkAdd(new Token(word));
                }

                totalWordList++;
            }
        }
    }

    private void checkAdd(Token curToken) {
        if(list.isEmpty()) { list.addInOrder(curToken); }
        for(Token t : list)
        {
            if ( t.equals(curToken) )
            {
                t.addFrequency();
            } else
            {
                list.addInOrder(curToken);
            }
        }
    }

    /**
     * Will return true or false depending if the word is a stop word or not.
     * @return true, if the inputWord is a stop word
     *  false, if the inputWord is not a stop word.
     * @param inputWord is the word to be check if it's a stop word.
     */
    private boolean isStopWord( String inputWord) {
        //Converts the Array to an ArrayList to use .contains()
        return Arrays.asList( stopwords).contains( inputWord);
    }


    /**
     * Run the program. Read the file, then print the results.
     */
    public void run() {
        readFile();
        printResults();
    }

}