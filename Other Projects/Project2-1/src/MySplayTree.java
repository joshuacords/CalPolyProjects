import tree.BinaryTree;
/**
 *	TreeMap implemented using Self-adjusting Binary Search Tree (Splay Tree)
 */
public class MySplayTree<K extends Comparable<K>,V> {
	
	// Element class
	class Element
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
			return key + " = " + value;
		}
		
	// Private BST methods to support map operations
	// Implements a top-down splay tree using BinaryTree methods
	// Based on Danny Sleator's pointer-based java code: http://www.link.cs.cmu.edu/splay/
	// Also see referenced article by Sleator and Tarjan, page 667-670.
	//
		private Element search(Element element)
		{
			//debug
			System.out.println("\nElement search: " + top);
			
			BinaryTree<Element> tree = splay(top, element);
			top = tree;
			if(tree == null) return null;
			if(0 == tree.getRoot().key.compareTo(element.key))	return tree.getRoot();
			return null;
		}
		private Element insert(Element element)
		{			
			if(search(element) == null)				//element is not in tree
			{
				System.out.println("In insert: " + top);
				if(top == null)			//tree is empty
				{
					top = new BinaryTree<Element>(element);
					System.out.println("String inserted at top: " + top);
				}else if(0 > element.key.compareTo(top.getRoot().key))
				{
					BinaryTree<Element> temp = new BinaryTree<Element>(element);
					temp.setLeft(top.getLeft());
					temp.setRight(top);
					top.setLeft(null);
					top = temp;
					System.out.println("String inserted pushing top downright: " + top);
				}else if(0 < element.key.compareTo(top.getRoot().key)){
					BinaryTree<Element> temp = new BinaryTree<Element>(element);
					temp.setRight(top.getRight());
					temp.setLeft(top);
					top.setRight(null);
					top = temp;
					System.out.println("String inserted pushing top downleft: " + top);
				}
			}
			return element;
		}
		private Element delete(Element element){return null;}
		private void inorder(BinaryTree<Element> tree){}
		private int height(BinaryTree<Element> tree){return 1;}
	}
		
	private BinaryTree<Element> top;
	java.util.Set<K> keys;  // to return keys in order
	private int size;
	
	public boolean containsKey(K key)
	{
		Element e = new Element(key, null);
		e = e.search(e);
		if(e != null)	return true;
		return false;
	}
	public V put(K key, V value)
	{
		Element e = new Element(key,value);
		e = e.insert(e);
		return e.value;
	}
	public V get(K key)
	{
		Element e = new Element(key, null);
		e = e.search(e);
		if(e != null)	return e.value;
		return null;
	}
	//public V remove(K key)
	//public int size()
	//public int height()
	public String toString()
	{
		return top.toString();
	}
	
	//public java.util.Set<K> keySet()
	
	
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
		private BinaryTree<Element> splay(BinaryTree<Element> top, Element e)
		{
			System.out.println("From splay: " + top + " insert: " + e);
			if(top == null) return null;									//if tree is empty
			if(0 > e.compareTo(top.getRoot()))
			{
				if(top.getLeft() == null) return top;						//left side empty and key is to left
				if(0 > e.compareTo(top.getLeft().getRoot()))		//can rotate tree to right knowing the top.left.right tree does not need to be checked
				{
					//top.setLeft(rotateRight(top));
					top = rotateRight(top);
					System.out.println("After rotateRight: " + top);
				}else{														//else splay subtree to bring key to the left side until key or null is found
					top.setLeft(splay(top.getLeft(),e));
					return top;		
				}
			}else if(0 < e.compareTo(top.getRoot()))
			{
				if(top.getRight() == null) return top;						//key to the right and right is empty
				if(0 > e.compareTo(top.getRight().getRoot()))			    //key should be top.right.left...somewhere
				{	//System.out.println("e.key= " + e.key + " top.right.key= " + top.getRight().getRoot().key);														
					top.setRight(splay(top.getRight(),e));
					return top;							//splay subtree to bring key to the right side until key or null is found
				}else{														//else can rotate tree to left knowing the top.right.left tree does not need to be checked
					//top.setRight(rotateLeft(top));
					top = rotateLeft(top);
					System.out.println("After rotateLeft: " + top);
				}
			}else{
				return top;							//top matches the key
			}
			return splay(top,e);					//tree has been rotated left or right, now splay the tree
		}
		private BinaryTree<Element> rotateRight(BinaryTree<Element> top)
		{
			BinaryTree<Element> temp = new BinaryTree<Element>(null);
			temp = top.getLeft();						//temp is becoming root of the tree
			top.setLeft(top.getLeft().getRight());				
			temp.setRight(top);
			top = temp;
			System.out.println("RotatedRight");
			return top;
		}
		private BinaryTree<Element> rotateLeft(BinaryTree<Element> top)
		{
			BinaryTree<Element> temp = new BinaryTree<Element>(null);
			temp = top.getRight();
			top.setRight(top.getRight().getLeft());
			temp.setLeft(top);
			top = temp;
			System.out.println("RotatedLeft: Top is now " + top.getRoot());
			//System.out.println("RotatedLeft: Top is now " + top.getRoot() + "\nTop.left is: " + top.getLeft().getRoot() + "\n Tree is: " + top);
			return top;
		}

}