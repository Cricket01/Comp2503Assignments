import java.util.*;

public class BST<T extends Comparable<T>> implements Iterable<T> {

    private Comparator<T> comp;
    private BSTNode root;
    private int size;

    /**
     * Will initialize root to null and size to 0
     * also will initialize comp as natural order from comparator.
     */
    public BST() {

        root = null;
        size = 0;
        comp = Comparator.naturalOrder();
    }

    /**
     * Will initialize root to null and size to 0.
     * @param comparator is the comparator to use when adding items to the list.
     */
    public BST( Comparator<T> comparator) {

        root = null;
        size = 0;
        comp = comparator;
    }

    /**
     * @return the number of BSTNodes in the tree.
     */
    public int size() {
        return size;
    }

    /**
     * Will tell if there are no item in a tree.
     * @return
     */
    public boolean isEmpty() {return root == null;}

    /**
     * @return the height of the tree.
     */
    public int height() {
        return height(root);
    }

    /**
     * @param r the root of the tree.
     * @return the height of the tree.
     */
    private int height( BSTNode r) {

        if (r == null) {
            return -1;
        }

        int lefth = height(r.left);
        int righth = height(r.right);

        return 1+ Math.max(lefth, righth);
    }

    /**
     * @return the data of the smallest item in the tree.
     */
    public T min() {
        return min(root);
    }

    /**
     * @param r is the root.
     * @return the data of the smallest item in the tree.
     */
    private T min(BSTNode r) {

        if (r == null) {
            return null;
        }
        if (r.getLeft() != null) {
            return min(r.getLeft());
        }
        return r.getData();
    }

    /**
     * Add element d to the tree.
     */
    public void add( T d) {

        BSTNode n = new BSTNode(d);
        if ( root == null ) {
            size++;
            root = n;
        } else {
            add(root, n);
        }
    }

    /**
     * Will add n to the tree.
     * @param r the root of the tree.
     * @param n the BSTNode that is bing added.
     */
    private void add( BSTNode r, BSTNode n) {

        int c = comp.compare( n.getData(), r.getData());
        if ( c < 0)
        {
            // the element to be added is less than the root
            if ( r.getLeft() == null)
            {
                // there is no left node so
                // we can add it here
                r.setLeft( n);
                size++;
            }
            else
            {
                // add it to the left sub-tree
                add( r.getLeft(), n);
            }
        }
        else  if ( c > 0)
        {
            // the element to be added is greater than the root
            if ( r.getRight() == null)
            {
                // there is no right node so
                // we can add it here
                r.setRight( n);
                size++;
            }
            else
            {
                // add it to the right sub-tree
                add( r.getRight(), n);
            }
        }
    }

    /**
     * Will find the data in the list and return it.
     * @param d is the data that needs to be found.
     * @return the data that is found, if not found return null.
     */
    public T find( T d) {

        return find( d, root);
    }

    /**
     * Will find the data in the list and return it.
     * @param d is the data that needs to be found.
     * @param r is the root.
     * @return the data that is found, if not found return null.
     */
    private T find( T d, BSTNode r) {

        if ( r == null)
            return null;
        int c = d.compareTo( r.getData());
        if ( c == 0)
            return r.getData();
        else if ( c < 0)
            return find( d, r.getLeft());
        else
            return find( d, r.getRight());
    }

    /**
     * Is called to remove d from the tree.
     * @param d is the data that needs to be deleted.
     */
    public void delete(T d) {

        root = delete(root,d);
        size --;
    }

    // Help form Pedro to get working
    /**
     * Will find and delete d from the tree changing the tree according.
     * @param r is the root.
     * @param d is the data that needs to be deleted.
     * @return will return the root.
     */
    private BSTNode delete(BSTNode r, T d) {

        if(r == null) {
            return null; // check to see if the tree is empty
        }
        if (comp.compare(r.getData(), d) == 0){
            if(r.isLeaf()) { // will check to see the BSTNode is a leaf
                return null;
            } else if (r.getRight() == null && r.getLeft() != null) {
                //if there is one child to the left
                return r.getLeft();
            } else if (r.getRight() != null && r.getLeft() == null) {
                //if there is one child to the right
                return r.getRight();
            } else { // if there are two children
                T min = min(r.getRight());
                r.setData(min);
                r.setRight(delete(r.getRight(), min));
            }
        }

        //will recur down the tree
        if (comp.compare(r.getData(), d) > 0) {
            r.setLeft(delete(r.getLeft(), d));
        } else {
            r.setRight(delete(r.getRight(), d));
        }
        return r;

    }

    private class BSTNode implements Comparable<BSTNode> {

        private T data;
        private BSTNode left;
        private BSTNode right;

        public BSTNode( T d)
        {
            setLeft( null);
            setRight( null);
            setData( d);
        }

        public T getData() { return data; }
        public void setData( T d) { data = d; }

        public void setLeft( BSTNode l) { left = l;}
        public void setRight( BSTNode r) { right = r;}
        public BSTNode getLeft()  { return left;}
        public BSTNode getRight()  { return right;}
        public boolean isLeaf()
        {
            return ( getLeft() == null) && ( getRight() == null);
        }

        public int compareTo( BSTNode o)
        {
            return this.getData().compareTo( o.getData());
        }

    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {

            Queue<T> inOrderQueue; {
                inOrderQueue = new LinkedList<>();
                fillQueue(root);
            }

            /**
             * Will transverse through tree inorder and add it to the queue.
             * @param r the root of the tree.
             */
            private void fillQueue(BSTNode r) {

                if ( r != null ) {
                    fillQueue(r.getLeft());
                    visit(r);
                    fillQueue(r.getRight());
                }
            }

            /**
             * Will visit the BSTNode.
             * @param n is the BSTNode to be visited.
             */
            private void visit(BSTNode n) {

                inOrderQueue.add(n.getData());
            }

            /**
             * Will check to see if the queue is empty
             * @return true if there is more in the queue
             */
            @Override
            public boolean hasNext() {

                return !inOrderQueue.isEmpty();
            }

            /**
             * Will dequeue from the queue
             * @return the next data in the queue
             */
            @Override
            public T next() {

                return inOrderQueue.poll();
            }
        };
    }
}
