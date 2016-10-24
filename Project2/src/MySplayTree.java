import tree.BinaryTree;


public class MySplayTree<K extends Comparable<K>,V>
{

	private BinaryTree<Element> map;
	java.util.Set<K> keys;  // to return keys in order
	private int size;
		
//  public boolean containsKey(K key)
//  public V put(K key, V value)
//  public V get(K key)
//  public V remove(K key)
//  public int size()
//  public int height()
//  public String toString()
//  public java.util.Set<K> keySet()
   // public static void main(String[] args) 
	{
    	/*
		//create Tree
		BinaryTree<Element> Tree = populateTree('Z', null);
		
		
		//Populate Tree
		String characters = "WERTYUDFGHJKCVBNM";
		
		for(int i = 0; i < characters.length(); i++)
		{
			 populateTree(characters.charAt(i),Tree);
		}
		
		//Output Tree
		
		//Remove Elements
		
		//Output Tree
		System.out.println(Tree);
		*/
	}
	
	private class Element
	{
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
			return key + " :: " + value;
		}
	}
	
	// Private BST methods to support map operations
	// Implements a top-down splay tree using BinaryTree methods
	// Based on Danny Sleator's pointer-based java code: http://www.link.cs.cmu.edu/splay/
	// Also see referenced article by Sleator and Tarjan, page 667-670.
	//
	private Element search(Element element)
	{
		
	}
	private Element insert(Element element)
	private Element delete(Element element)			
	private void inorder(BinaryTree<Element> tree)
	private int height(BinaryTree<Element> tree)
	
	/*public static BinaryTree<Element> populateTree(char key, BinaryTree<Element> tree)
	{
		if(tree != null)
		{
			if(tree.getRoot()==key) return tree;
			if(tree.getRoot() > key)
			{
				if(tree.getLeft() == null)
				{
					return tree.setLeft(new BinaryTree<Element>(key));
				}else{
					return populateTree(key,tree.getLeft());
				}
			}else if(tree.getRoot() < key)
			{
				if(tree.getRight() == null)
				{
					return tree.setRight(new BinaryTree<Element>(key));
				}else{
					return populateTree(key,tree.getRight());
				}
			}
		}
		return new BinaryTree<Element>(key);
	}*/
	private static MySplayTree empty = new MySplayTree(); // For splay
    
	private void splay(Element e)
	{
		MySplayTree left, right, parent, element;
		left = right = empty;
		parent = map;
		empty.left = empty.right = null;
		
		
	}
	private void splay(Comparable key) {
		BinaryNode l, r, t, y;
		l = r = header;
		t = map;
		header.left = header.right = null;
		for (;;) {
		    if (key.compareTo(t.key) < 0) {
			if (t.left == null) break;
			if (key.compareTo(t.left.key) < 0) {
			    y = t.left;                            /* rotate right */
			    t.left = y.right;
			    y.right = t;
			    t = y;
			    if (t.left == null) break;
			}
			r.left = t;                                 /* link right */
			r = t;
			t = t.left;
		    } else if (key.compareTo(t.key) > 0) {
			if (t.right == null) break;
			if (key.compareTo(t.right.key) > 0) {
			    y = t.right;                            /* rotate left */
			    t.right = y.left;
			    y.left = t;
			    t = y;
			    if (t.right == null) break;
			}
			l.right = t;                                /* link left */
			l = t;
			t = t.right;
		    } else {
			break;
		    }
		}
		l.right = t.left;                                   /* assemble */
		r.left = t.right;
		t.left = header.right;
		t.right = header.left;
		map = t;
	}
	
	private void rotateRight(BinaryTree<Element> t)
	private void rotateLeft(BinaryTree<Element> t)
	
	/*
	public void deleteNode(char key, BinaryTree<Element> tree)
	{
		
	}
	
	public BinaryTree<Element> get(char key, BinaryTree<Element> tree)
	{
		return null;
	}*/
	

}
