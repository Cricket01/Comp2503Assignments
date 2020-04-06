
import java.util.Scanner;
import java.util.Iterator;

/** 
* COMP 2503 Winter 2020 Assignment 3 
* 
* This program reads a text file and compiles a list of the 
* words together with the frequency of the words.
* Use a BST for storing the words. 
* Words from a standard list of stop words are then deleted.
* 
* BSTs with alternative orderings are constructed to show the
* required output.
* 
* Updated Winter 2020
*/

public class A3 
{

    private static final int TOP_NUM = 10;
   /* The lists (trees) of words. Alphabetic, by Frequency 
      and by length. */
   private BST<Token> wordsByNaturalOrder = new BST<Token>();
   private BST<Token> wordsByFreqDesc = new BST<Token>(Token.CompFreqDesc);
   private BST<Token> wordsByLength = new BST<Token>(Token.CompLengthDesc);
   
   // there are 103 stopwords in this list
   private String[] stopwords = { 
         "a","about","all","am","an","and","any","are","as","at", 
         "be","been","but","by","can","cannot","could", "did", "do", "does", 
         "else", "for", "from", "get", "got", "had", "has", "have", "he", "her", 
         "hers", "him", "his", "how", "i", "if", "in", "into", "is", "it", 
         "its",  "like", "more", "me", "my", "no", "now", "not", "of", "on", 
         "one", "or", "our", "out", "said", "say", "says", "she", "so", "some",
         "than", "that", "thats", "the", "their", "them", "then", "there", "these", "they", "this", 
         "to", "too", "us", "upon", "was", "we", "were", "what", "with", "when", 
         "where", "which", "while", "who", "whom", "why", "will", "you", "your", "up", "down", "left", "right",
         "man", "woman", "would", "should", "dont", "after", "before", "im", "men"
   };

   private int totalwordcount = 0;
   private int stopwordcount = 0;

   private Scanner inp = new Scanner( System.in);

   public static void main( String[ ] args) 
   {	
      A3 a3 = new A3();
      a3.run();
   }

   private void printResults() 
   {
       System.out.println("Total Words: " + totalwordcount);
       System.out.println("Unique Words: " + wordsByNaturalOrder.size()); 
       System.out.println("Stop Words: " + stopwordcount);
       System.out.println();
       System.out.println("10 Most Frequent");

       printTopNum(wordsByFreqDesc);

       System.out.println();
       System.out.println("10 Longest");

       printTopNum(wordsByLength);

       System.out.println();
       System.out.println("The longest word is " + wordsByLength.min());
       System.out.println("The average word length is " + avgLength());
       System.out.println();        
       System.out.println("All");

       for(Token t : wordsByNaturalOrder) {
           System.out.println(t);
       }

       System.out.println();
       System.out.println("Alphabetic Tree: (Optimum Height: " + 
             optHeight(wordsByNaturalOrder.size()) + ") (Actual Height: " 
             + wordsByNaturalOrder.height() + ")");
       System.out.println("Frequency Tree: (Optimum Height: " + 
             optHeight(wordsByFreqDesc.size()) + ") (Actual Height: " 
             + wordsByFreqDesc.height() + ")");
       System.out.println("Length Tree: (Optimum Height: " + 
             optHeight(wordsByLength.size()) + ") (Actual Height: " 
             + wordsByLength.height() + ")");
   }

    /**
     * Will print the top num of items in the list provided.
     * @param listToPrint is the list to be printed
     */
    private void printTopNum(BST<Token> listToPrint) {

        int amount = Math.min( listToPrint.size(), TOP_NUM);

        int count = 0;

        for(Token curToken : listToPrint) {
            if(count >= amount){return;}
            System.out.println(curToken);
            count++;
        }
    }
   
   /* Read the file and add words to the list/tree. 
    */
   private void readFile() 
   {

      while (inp.hasNext()) 
      {
         String word = inp.next().toLowerCase().replaceAll("[^a-z]","").trim();

         if (word.length() > 0) 
         {
             checkAdd(new Token(word));
         }
      }
   }

    private void checkAdd(Token curToken) {
        Token foundToken = wordsByNaturalOrder.find(curToken);
        if(foundToken != null) {
            foundToken.incrCount();
        } else {
            wordsByNaturalOrder.add(curToken);
        }
        totalwordcount ++;
    }

   /* Create the frequency and length lists. */
   private void createFreqLists()
   {
	   for(Token curToken : wordsByNaturalOrder) {
           wordsByLength.add(curToken);
	       if(curToken.getCount() > 2) {
               wordsByFreqDesc.add(curToken);
           }
       }

   }

   /* Calculate the average length of words stored the wordsByNaturalOrder tree*/
   private int avgLength() 
   {
       int sum = 0;
       for(Token curToken : wordsByNaturalOrder) {
           sum += curToken.getLength();
       }
       int average = 0;
       if(!wordsByNaturalOrder.isEmpty())
           average = sum / wordsByNaturalOrder.size();

	   return average;
   }

   /* Remove stop words from the tree. */
   private void removeStop() 
   {
	   for(String stopWord : stopwords) {
         Token word = new Token(stopWord);
         Token foundWord = wordsByNaturalOrder.find(word);
         if(foundWord != null) {
             wordsByNaturalOrder.delete(word);
             stopwordcount++;
         }
       }
   }

   /* Calculate the optimal height for a tree of size n. 
      Round to an int. 
    */
   private int optHeight(int n) 
   {
	     double h = Math.log(n+1) / Math.log(2) - 1;  
	     if (Math.round(h) < h)
	    	  return (int)Math.round(h) + 1;
	     else
	    	  return (int)Math.round(h);
   }

   /* Run the program. */
   private void run() 
   {
      readFile();
      removeStop();
      createFreqLists();
      printResults();
   }
}
