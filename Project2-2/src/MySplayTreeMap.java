import java.util.TreeSet;
import tree.BinaryTree;


/**
 *	TreeMap implemented using Self-adjusting Binary Search Tree (Splay Tree)
 */
public class MySplayTreeMap<K extends Comparable<K>,V> {
	private BinaryTree<Element> map;
	java.util.Set<K> keys = new TreeSet<K>();  // to return keys in order
	
	// Element class
	private class Element{
		private K key;
		private V value;
		
		public Element(K key, V value)
		{
			this.key = key;
			this.value = value;
		}
		
		public int compareTo(Element that)
		{
			return key.compareTo(that.key);
		}
		
		public String toString()
		{
			return key + " = " + value;
		}
	}
		
	public boolean containsKey(K key)
	{
		if(get(key) != null) return true;
		return false;
	}
	public V put(K key, V value)
	{
		Element e = new Element(key,value);
		e = insert(e);
		if(e != null)
		{
			keys.add(key);
			return e.value;
		}
		return null;
	}
	public V get(K key)
	{
		Element e = new Element(key, null);
		e = search(e);
		if(e != null)	return e.value;
		return null;
	}
	public V remove(K key)			//return null if not there to delete, else returns value of deleted key
	{
		Element e = new Element(key,null);
		e = delete(e);
		if(e != null)
		{
			keys.remove(key);
			return e.value;
		}
		return null;
	}
	
	public int size()
	{
		return keys.size();
	}
	
	public int height()
	{
		if(map == null) return -1;
		return height(map,-1);
	}
	public String toString()
	{
		if(map != null)	return map.toString();
		return "Tree empty";
	}
	public java.util.Set<K> keySet()
	{
		inorder(map);
		System.out.println(keys);
		return keys;
	}


	/**
     * Find an item in the tree.
     */
    private Element search(Element element) 
    {
    	if (map == null) return null;
    	splay(element);
        if(map.getRoot().compareTo(element) != 0) return null;
        return map.getRoot();
    }
    
    private Element insert(Element element) 	//returns null if duplicate, else returns element inserted
    {
    	BinaryTree<Element> n;
    	int c;
    	if (map == null) {							//map is empty, construct map with element as root
    	    map = new BinaryTree<Element>(element);
    	    return map.getRoot();
    	}
    	splay(element);
    	if ((c = element.compareTo(map.getRoot())) == 0) {
    	    //	    throw new DuplicateItemException(x.toString());	    
    	    return null;
    	}
    	n = new BinaryTree<Element>(element);
    	if (c < 0) {
    	    n.setLeft(map.getLeft());
    	    n.setRight(map);
    	    map.setLeft(null);
    	} else {
    	    n.setRight(map.getRight());
    	    n.setLeft(map);
    	    map.setRight(null);
    	}
    	map = n;
    	return map.getRoot();
    }
    /**
     * Remove from the tree.
     * @param x the item to remove.
     */
    private Element delete(Element element) 		//returns null if element not found, else returns element
    {
    	if(map == null) return null;
    	BinaryTree<Element> x;
		splay(element);
		if (element.compareTo(map.getRoot()) != 0) {
		    //            throw new ItemNotFoundException(x.toString());
		    return null;
		}
		// Now delete the map
		if (map.getLeft() == null) {
		    map = map.getRight();
		} else {
		    x = map.getRight();
		    map = map.getLeft();
		    splay(element);
		    map.setRight(x);
		}
		return element;
    }
	private void inorder(BinaryTree<Element> tree)
	{
		if(tree.getLeft() != null) inorder(tree.getLeft());
		keys.add(tree.getRoot().key);
		if(tree.getRight() != null) inorder(tree.getRight());
	}
	
	private int height(BinaryTree<Element> tree, int h)
	{
		h++;
		if(tree.getLeft()==null && tree.getRight()==null) return h;
		int left = 0;
		int right = 0;
		if(tree.getLeft()!=null) left = height(tree.getLeft(),h);
		if(tree.getRight()!=null) right = height(tree.getRight(),h);
		return Math.max(left, right);
	}
 /**
 * Internal method to perform a top-down splay.
 * 
 *   splay(key) does the splay operation on the given key.
 *   If key is in the tree, then the node containing
 *   that key becomes the root.  If key is not in the tree,
 *   then after the splay, the key at the root is either
 *	 the greatest key in the tree that is less than the key 
 *   or the least key in the tree greater than the key.
 *
 *   This means, among other things, that if you splay with
 *   a key that's larger than any in the tree, the rightmost
 *   node of the tree becomes the root.  This property is used
 *   in the delete method.
 */
	private BinaryTree<Element> header = new BinaryTree<Element>(null); // For splay
    
	private void splay(Element element) 		//based on other's code
	{
		BinaryTree<Element> l, r, t, y;
		l = r = header;
		t = map;
		header.setLeft(null);
		header.setRight(null);
		for (;;) 
		{
		    if (element.compareTo(t.getRoot()) < 0) 
		    {
		    	if (t.getLeft() == null) break;
		    	if (element.compareTo(t.getLeft().getRoot()) < 0) 
		    	{
		    		y = t.getLeft();                            /* rotate right */
		    		t.setLeft(y.getRight());
		    		y.setRight(t);
		    		t = y;
			    	if (t.getLeft() == null) break;
		    	}
		    	r.setLeft(t);                                 /* link right */
		    	r = t;
		    	t = t.getLeft();
		    } else if (element.compareTo(t.getRoot()) > 0) 
		    {
				if (t.getRight() == null) break;
				if (element.compareTo(t.getRight().getRoot()) > 0) 
				{
				    y = t.getRight();                            /* rotate left */
				    t.setRight(y.getLeft());
				    y.setLeft(t);
				    t = y;
				    if (t.getRight() == null) break;
				}
				l.setRight(t);                                /* link left */
				l = t;
				t = t.getRight();
			} else {
				break;
		    }
		}
		l.setRight(t.getLeft());                                   /* assemble */
		r.setLeft(t.getRight());
		t.setLeft(header.getRight());
		t.setRight(header.getLeft());
		map = t;
	}
	
	//Professor indicated rotateRight and rotateLeft were unnecessary
	
}