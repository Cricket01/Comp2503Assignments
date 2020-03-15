import java.util.Iterator;
import java.util.Comparator;

public class BST<T extends Comparable<T>> implements Iterable<T>
{

	BSTNode root = null;

	public BST()
	{
		// TODO Auto-generated method stub
		// Create a new BST using the natural ordering of T.
	}

	public BST( Comparator<T> c)
	{
		// TODO Auto-generated method stub	
		// Create a new BST using the ordering determined by c
	}
	
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int height() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {

			@Override
			public boolean hasNext() {
				return true;
			}

			@Override
			public T next() {
				return T;
			}

		};
	}

	public T min() {
		// TODO Auto-generated method stub
		return null;
	}

	/** Add element d to the tree.
	 */
	public void add( T d)
	{
		BSTNode n = new BSTNode( d);
		if ( root == null)
		{
			size++;
			root = n;
		}
		else
		{
			add( root, n);
		}
	}
}
