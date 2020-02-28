import java.util.Comparator;

/**
 * This class is in charge of sorting wordList in
 * A1.java by least frequency alphabetically.
 * @author Dylan Borchert
 */
public class LeastFrequentWords implements Comparator<Token>
{

	/**
	 * <p>
	 * Will compare two words to see which one has the highest frequency,
	 * if they have the same frequency then it will compare the words.
	 * </p>
	 * @return If t1 > t2 then return < 0 ; If t1 < t2 then return > 0.
	 * @param t1 first word to be compared.
	 * @param t2 second word to be compared.
	 */
	@Override
	public int compare(Token t1, Token t2)
	{
		int diff = t1.getFrequency() - t2.getFrequency();
		if( diff == 0)
		{
			diff = t1.getWord().compareTo(t2.getWord());
		}
		return diff;
	}
	
}
