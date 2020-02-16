/**
 * This class is used to store each word and the frequency of that word.
 * @author Dylan Borchert
 */
public class Token implements Comparable<Token>
{
	
	private String word;
	private int frequency = 1;

	/**
	 * Constructor that sets the word as an instance.
	 * @param word is used to be initialized.
	 */
	public Token(String word)
	{
		this.word = word;
	}

	/**
	 * Compares token word to this Token word
	 * @param token is teh Token that needs to be compared.
	 * @return integer depending on alphabetical order.
	 */
	@Override
	public int compareTo( Token token)
	{

		return this.word.compareTo(token.getWord());
	}

	/**
	 * Overrides equals method to compare the words instead of object reference.
	 * @param obj what is being compared to current this Object
	 * @return true if obj word is the same as this word,
	 * false if obj word is not the same as this word.
	 */
	@Override
	public boolean equals( Object obj)
	{
		//checks to see if object is an instance of Token for
		if(obj instanceof Token )
		{
			//obj is casted as Token type
			return ((Token) obj).getWord().equals( this.word);
		}
		return false;
	}

	/**
	 * Will increment frequency by 1.
	 */
	public void addFrequency() { frequency ++;}

	public int getFrequency() { return frequency;}
	
	public String getWord() { return word;}

	/**
	 * Is used to format print out.
	 */
	public String toString()
	{
		return word + " : " + frequency;
	}
}
