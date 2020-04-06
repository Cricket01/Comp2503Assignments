import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * COMP 2503 Winter 2020 Assignment 4.
 * <p>
 * This program reads a text file and compiles a list of the Tokens together
 * with the frequency of the Tokens. Use HashMaps from Java collections
 * Framework for storing the Tokens. Tokens from a standard list of stop Tokens
 * are then deleted. Then create TreeMaps with ordering based on frequency
 * (ascending and descending) in order to produce the required output.
 * </p>
 * Updated Winter 2020
 */
public class A4 {

  private static final int TOP_NUM = 10;
  private Scanner inp = new Scanner(System.in);

  /* The HashMap of Tokens. */
  private HashMap<String, Token> token = new HashMap<>();
  /* The ordered tree maps of Tokens. */
  private TreeMap<Token, Token> wordsByNaturalOrder = new TreeMap<>(Comparator.naturalOrder());
  private TreeMap<Token, Token> wordsByLength = new TreeMap<>(Token.CompLengthDesc);
  private TreeMap<Token, Token> wordsByFreqDesc = new TreeMap<>(Token.CompFreqDesc);

  // there are 103 stopTokens in this list
  private String[] stopTokens = {"a", "about", "all", "am", "an", "and", "any",
      "are", "as", "at", "be", "been", "but", "by", "can", "cannot", "could",
      "did", "do", "does", "else", "for", "from", "get", "got", "had", "has",
      "have", "he", "her", "hers", "him", "his", "how", "i", "if", "in", "into",
      "is", "it", "its", "like", "more", "me", "my", "no", "now", "not", "of",
      "on", "one", "or", "our", "out", "said", "say", "says", "she", "so",
      "some", "than", "that", "thats", "the", "their", "them", "then", "there",
      "these", "they", "this", "to", "too", "us", "upon", "was", "we", "were",
      "what", "with", "when", "where", "which", "while", "who", "whom", "why",
      "will", "you", "your", "up", "down", "left", "right", "man", "woman",
      "would", "should", "dont", "after", "before", "im", "men"};

  private int totalTokenCount = 0;
  private int stopTokenCount = 0;

  public static void main(String[] args) {
    A4 a4 = new A4();
    a4.run();
  }

  /* Run the program. */
  private void run() {
    readFile();
    removeStop();
    createFreqLists();
    printResults();
  }

  /**
   * Does all print out for the assignment.
   */
  private void printResults() {

    System.out.println("Total Words: " + totalTokenCount);
    System.out.println("Unique Words: " + token.size());
    System.out.println("Stop Words: " + stopTokenCount);
    System.out.println();

    System.out.println("10 Most Frequent");

    printTopNum(wordsByFreqDesc);

    System.out.println();

    System.out.println("10 Longest");

    printTopNum(wordsByLength);

    System.out.println();

    System.out.println("The longest word is " + returnLongestWord(wordsByLength));
    System.out.println("The shortest word is " + returnShortestWord(wordsByLength));
    System.out.println("The average word length is " + avgLength());

    System.out.println();
    System.out.println("All");

    printAll(wordsByNaturalOrder);
  }


  /**
   * Will print the top num of items in the list provided.
   * @param listToPrint is the list to be printed.
   */
  private void printTopNum(TreeMap<Token, Token> listToPrint) {

    int amount = Math.min(listToPrint.size(), TOP_NUM);

    int count = 0;

    for (Token key : listToPrint.keySet()) {
      if (count >= amount) {
        return;
      }
      System.out.println(key);
      count++;
    }
  }

  /**
   * Will print the top num of items in the list provided.
   * @param listToPrint is the list to be printed.
   */
  private void printAll(TreeMap<Token, Token> listToPrint) {

    for (Token key : listToPrint.keySet()) {
      System.out.println(key);
    }

  }

  /**
   * Calculates the average word length in the length HashTree.
   * @return the average length of the words
   */
  private int avgLength() {

    int sum = 0;
    for (Token key : wordsByLength.keySet()) {
      sum += key.getLength();
    }
    int average = 0;
    if (!wordsByLength.isEmpty()) {
      average = sum / wordsByLength.size();
    }
    return average;
  }

  /**
   * Will get the shortest item in the list.
   * @param wordsByLength2 is the length tree map that is used to find the shortest word.
   * @return The shortest word in the HashTree.
   */
  private String returnShortestWord(TreeMap<Token, Token> wordsByLength2) {

    return wordsByLength2.isEmpty() ? "None" : wordsByLength2.lastKey().getWord();
  }

  /**
   * Will get the longest item in the list.
   * @param wordsByLength2 is the length tree map that is used to find the longest word.
   * @return The longest word in the HashTree.
   */
  private String returnLongestWord(TreeMap<Token, Token> wordsByLength2) {

    return wordsByLength2.isEmpty() ? "None" : wordsByLength2.firstKey().getWord();
  }

  /**
   * Will read the file and add it to the tree.
   */
  private void readFile() {

    while (inp.hasNext()) {
      String word = inp.next().toLowerCase().replaceAll("[^a-z]", "").trim();

      if (word.length() > 0) {
        checkAdd(word);
      }
    }
  }

  /**
   * Will check to see if the word can be added to the tree.
   * and either increase count or add.
   * @param curWord is the word to be added to the tree.
   */
  private void checkAdd(String curWord) {

    Token foundToken = token.get(curWord);
    if (foundToken != null) {
      foundToken.incrCount();
    } else {
      token.put(curWord, new Token(curWord));
    }
    totalTokenCount++;
  }

  /**
   * Removes all the stop words from the token Hashmap.
   */
  private void removeStop() {

    for (String stopWord : stopTokens) {
      if (token.remove(stopWord) != null) {
        stopTokenCount++;
      }
    }
  }

  /**
   * Will add the token hash map to the frequency lists.
   */
  private void createFreqLists() {

    Token blank = new Token("");
    for (String key : token.keySet()) {
      Token curToken = token.get(key);
      wordsByNaturalOrder.put(curToken, blank);
      wordsByLength.put(curToken, blank);
      wordsByFreqDesc.put(curToken, blank);
    }
  }
}