

/*************************************************************************
 *  Binary Search Tree class.
 *  	-Adapted from Sedgewick and Wayne.
 *
 *		How this code Works
 *		The binary search tree class was something we used in our Algorithms and Data Structures Module.
 *		Im using it to build the binary Tree that I can then use to find the LCA of any given Nodes.
 *		I added two recurvive functions that travel down the tree to find the Node and then go back up
 *		via returning while adding the path to an arrayList.
 *		I also added a mainline to test the code out
 *
 *
 *
 *************************************************************************/

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class BST<Key extends Comparable<Key>, Value> {

	private Node root;             // root of BST


	public String completeString="";

	private class Node {
		private Key key;           // sorted by key
		private Value val;         // associated data
		private Node left, right;  // left and right subtrees
		private int N;             // number of nodes in subtree
		

		public Node(Key key, Value val, int N) {
			this.key = key;
			this.val = val;
			this.N = N;
		}
	}


	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub


//		BST<Integer, Integer> bst = new BST<Integer, Integer>();      
//		//			bst.put(7, 7);
//
//
//				
//		bst.put(7, 7);   //        _7_
//		bst.put(8, 8);   //      /     \
//		bst.put(3, 3);   //    _3_      8
//		bst.put(1, 1);   //  /     \
//		bst.put(2, 2);   // 1       6
//		bst.put(6, 6);   //  \     /
//		bst.put(4, 4);   //   2   4
//		bst.put(5, 5);   //        \
//						 //         5
//
//
//		
//		
////		int node1=8;
////		int node2=4;
////		bst.findLCA(node1, node2);
//
//		
//		
//		int node3=8;
//		int node4=5;
//		bst.findLCA(node3, node4);

	}


	private List<Key> path1 = new ArrayList<>();
	private List<Key> path2 = new ArrayList<>();

	public boolean found1=false;
	public boolean found2=false;

	///////////////////////////////////////////////////////



	public void findLCA(Key one, Key two)
	{

		findNode1(root, one);
		findNode2(root, two);

//		for(int i=0;i<path1.size();i++)
//		{
//			System.out.println("Path1:"+ path1.get(i));
//		}
//		for(int i=0;i<path2.size();i++)
//		{
//			
//			System.out.println("Path2:"+ path2.get(i));
//		}
		
		boolean ancestorFound=false;
		
		for(int i=0;i<path1.size();i++)
		{
			if(ancestorFound==true)
			{
				break;
			}
			
			
			for(int j=0;j<path2.size();j++)
			{
				
				if(path1.get(i).equals(path2.get(j)))
				{
					ancestorFound=true;
					System.out.println("LCA is:"+path1.get(i));
					
				}	
				
			}

		}
		
	}
	
	
	
	void findNode1(Node node,Key key1)
	{
			
		if (node == null)
		{
			return;
		}

		if(node.key.equals(key1) || found1==true)//If we found the Node then we just exit each recursion and add the current Node to the path Array
		{
//			System.out.println("Found");
			found1=true;
			path1.add(node.key);
			return;	
		}

		findNode1(node.left,key1);
		
		if(found1==true)//exiting the recursion
		{
			path1.add(node.key);
			return;
		}
		
		findNode1(node.right,key1);
		if(found1==true)//exiting the recursion
		{
			path1.add(node.key);
			return;
		}
	}
	
	void findNode2(Node node,Key key2)
	{

		if (node == null)
		{
			return;
		}
		
		if(node.key.equals(key2) || found2==true)//If we found the Node then we just exit each recursion and add the current Node to the path Array
		{
			found2=true;
			path2.add(node.key);
			return;	
		}
		
		findNode2(node.left,key2);
		if(found2==true)
		{
			path2.add(node.key);
			return;
		}
		
		findNode2(node.right,key2);
		if(found2==true)
		{
			path2.add(node.key);
			return;
		}
	}
	
	
	








	///////////////////////////////////////////////////////

	public String printKeysInOrder() {
		if (isEmpty()) return "()";
		// TODO fill in the correct implementation


		return  printKeysInOrder(root);

	}


	private String printKeysInOrder(Node x)//, String keyString)
	{
		String keyString="";

		if(x==null)
		{
			return "()";
		}


		keyString=keyString+"(";
		keyString=keyString + printKeysInOrder(x.left);	
		keyString=keyString + x.key;			
		keyString=keyString+printKeysInOrder(x.right);//,keyString);
		keyString=keyString+")";


		return keyString;

	}




	// is the symbol table empty?
	public boolean isEmpty() { return size() == 0; }

	// return number of key-value pairs in BST
	public int size() { return size(root); }

	// return number of key-value pairs in BST rooted at x
	private int size(Node x) {
		if (x == null) return 0;
		else return x.N;
	}


	public boolean contains(Key key) {
		return get(key) != null;
	}


	public Value get(Key key) { return get(root, key); }

	private Value get(Node x, Key key) {
		if (x == null) return null;
		int cmp = key.compareTo(x.key);
		if      (cmp < 0) return get(x.left, key);
		else if (cmp > 0) return get(x.right, key);
		else              return x.val;
	}

	public void put(Key key, Value val) 
	{
		if (val == null) 
		{ delete(key); return; }
		root = put(root, key, val);
	}

	private Node put(Node x, Key key, Value val) 
	{
		if (x == null) return new Node(key, val, 1);
		int cmp = key.compareTo(x.key);
		if      (cmp < 0) x.left  = put(x.left,  key, val);
		else if (cmp > 0) x.right = put(x.right, key, val);
		else              x.val   = val;
		x.N = 1 + size(x.left) + size(x.right);
		return x;
	}


	public int height() {
		//TODO fill in the correct implementation.

		if(isEmpty()==true)
		{
			return -1;
		}
		else
		{
			//System.out.println("testHeight");
			return height(root);
		}
	}

	private int height(Node node) 
	{
		//	System.out.println("test1");


		if(node==null)
		{
			//	System.out.println("testNull");

			return -1;
		}

		int heightLeft=height(node.left);
		//	System.out.println("test2");

		int heightRight=height(node.right);
		//System.out.println("test3");

		if(heightLeft > heightRight)
		{
			return heightLeft+1;
		}
		else 
		{
			return heightRight+1;
		}


	}

	public Key median() {
		if (isEmpty()) return null;
		//TODO fill in the correct implementation. The running time should be Theta(h), where h is the height of the tree.


		int currentSize=size(root);
		int medianPoint = (currentSize-1)/2;		//K(N-1)/2



		return median(root, medianPoint);

	}



	private Key median(Node x, int median)
	{

		int t = size(x.left);

		if (t > median) 
		{
			//System.out.println("left key:"+x.left.key);
			return median(x.left, median);
		}
		else if (t < median)
		{
			//System.out.println("right key:"+x.right.key);
			return median(x.right, median-t-1);
		}
		else
		{
			return x.key;
		}		
	}

	public void delete(Key key) {
		//TODO fill in the correct implementation.

		root=delete(root, key);
	}

	private Node delete(Node x,Key key)
	{
		if(x==null)
		{
			return null;
		}
		int cmp = key.compareTo(x.key);
		if(cmp<0) 
		{
			x.left=delete(x.left,key);
		}
		else if(cmp>0)
		{
			x.right=delete(x.right,key);
		}
		else
		{
			if(x.right==null)
			{
				return x.left;
			}
			if(x.left==null)
			{
				return x.right;
			}
			Node t=x;

			x=max(t.left);
			x.left=deleteMax(t.left);
			x.right=t.right;


		}
		x.N=size(x.left)+size(x.right)+1;

		return x;

	}


	public void deleteMax()
	{
		if (isEmpty()) return;
		root = deleteMax(root);
	}
	private Node deleteMax(Node x)
	{
		if (x.right == null) return x.left;
		x.right = deleteMax(x.right);
		x.N = size(x.right) + size(x.left) + 1;
		return x;
	}

	public Key max() {
		if (isEmpty()==true)
		{
			return null;
		}
		else
			return max(root).key;
	} 

	private Node max(Node x) {
		if (x.right == null) 
		{
			return x; 
		}
		else        
		{return max(x.right); 

		}
	} 



}
