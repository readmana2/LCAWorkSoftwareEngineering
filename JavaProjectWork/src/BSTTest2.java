import static org.junit.Assert.*;

import org.junit.Test;

public class BSTTest2 {


	

	/*
	 * This test should construct a binary Tree and ensure the print Keys in Order method matches the way it is created
	 * The tests are designed to ensure the tree works as intended
	 */
	@Test
	public void testTree() {
		BST<Integer, Integer> bst = new BST<Integer, Integer>();
		bst.delete(1);
		assertEquals("Deleting from empty tree", "()", bst.printKeysInOrder());

		bst.put(7, 7);   //        _7_
		bst.put(8, 8);   //      /     \
		bst.put(3, 3);   //    _3_      8
		bst.put(1, 1);   //  /     \
		bst.put(2, 2);   // 1       6
		bst.put(6, 6);   //  \     /
		bst.put(4, 4);   //   2   4
		bst.put(5, 5);   //        \
						 //         5

		assertEquals("Ensuring Tree is constructed correctly","(((()1(()2()))3((()4(()5()))6()))7(()8()))", bst.printKeysInOrder());

		bst.delete(8);
		assertEquals("Deleting node 8 and checking that the tree is still correct", "(((()1(()2()))3((()4(()5()))6()))7())", bst.printKeysInOrder());

		bst.delete(6);
		assertEquals("Deleting a node with child nodes","(((()1(()2()))3(()4(()5())))7())", bst.printKeysInOrder());

		bst.delete(3);assertEquals("Deleting node with two child nodes","(((()1())2(()4(()5())))7())", bst.printKeysInOrder());
		
		//Deleting all nodes
		bst.delete(1);
		bst.delete(2);
		bst.delete(4);
		bst.delete(5);
		bst.delete(7);
		assertEquals("Empty Tree","()", bst.printKeysInOrder());
		
		
		//Testing binary Search tree constructed  with only nodes going to the right
		
		bst.put(7, 7);   //        _7-->8-->9-->10-->11
		bst.put(8, 8);   //           
		bst.put(9, 9);   //             
		bst.put(10, 10);   // 
		bst.put(11, 11);   // 
		
		assertEquals("Testing a Graph with only right Nodes","(()7(()8(()9(()10(()11())))))",bst.printKeysInOrder());
		
		//System.out.println(bst.printKeysInOrder());
		
	}
	
	
	/*
	 * 
	 * This test should do a basic check of the LCA and make sure it is returning the correct result
	 */
	@Test
	public void testLCA() {
		
		
		
		//Tree 1
		
		BST<Integer, Integer> bst = new BST<Integer, Integer>();
		
		bst.put(7, 7);   //        _7_
		bst.put(8, 8);   //      /     \
		bst.put(3, 3);   //    _3_      8
		bst.put(1, 1);   //  /     \
		bst.put(2, 2);   // 1       6
		bst.put(6, 6);   //  \     /
		bst.put(4, 4);   //   2   4
		bst.put(5, 5);   //        \
						 //         5
		//assertEquals("Deleting a node with child nodes","(((()1(()2()))3((()4(()5()))6()))7(()8()))", bst.printKeysInOrder());
		//Comparable<key> lca =  bst.findLCA(2, 5);
		//bst.findLCA(2, 5);
		
		Comparable<Integer> testKey = bst.findLCA(2, 5);
		assertEquals("Finding LCA of 2 and 5",testKey,3);
		
		Comparable<Integer> testKey2 = bst.findLCA(3, 8);
		assertEquals("Finding LCA of 3 and 8",testKey2,7);
		
		Comparable<Integer> testKey3 = bst.findLCA(5, 3);
		assertEquals("Finding LCA of 5 and 3",testKey3,3);
		
		Comparable<Integer> testKey4 = bst.findLCA(5, 5);	
		assertEquals("Checking  LCA works  when given duplicate nodes",testKey4,5);
		
		
		
		//Tree 2
		
		BST<Integer, Integer> bst2 = new BST<Integer, Integer>();

		bst2.put(7, 7);   //        _7-->8-->9-->10-->11
		bst2.put(8, 8);   //           
		bst2.put(9, 9);   //             
		bst2.put(10, 10);   // 
		bst2.put(11, 11);   // 
		
		Comparable<Integer> testKey5 = bst2.findLCA(8, 11);
	
		assertEquals("Checking  LCA works  when given duplicate nodes",testKey5,8);
		
	}
	
	/*
	 * 
	 * 
	 * THis test should test special cases that should return null and not create an error messsage
	 * 
	 */
	@Test
	public void testSpecialCases() 
	{
		BST<Integer, Integer> bst = new BST<Integer, Integer>();
		
		bst.put(7, 7);   //        _7_
		bst.put(8, 8);   //      /     \
		bst.put(3, 3);   //    _3_      8
		bst.put(1, 1);   //  /     \
		bst.put(2, 2);   // 1       6
		bst.put(6, 6);   //  \     /
		bst.put(4, 4);   //   2   4
		bst.put(5, 5);   //        \
						 //         5

		Comparable<Integer> testKey = bst.findLCA(2, 9);	
		assertEquals("Checking  LCA works when  there is no LCA",testKey,null);
		
		
		Comparable<Integer> testKey2 = bst.findLCA(10, 9);	
		assertEquals("Checking  LCA works when given nodes dont exist",testKey2,null);
		

		
		
		BST<Integer, Integer> bst2 = new BST<Integer, Integer>();
		Comparable<Integer> testKey3 = bst2.findLCA(10, 9);
		assertEquals("Checking  LCA works when given tree is empty",testKey3,null);
		
		
				
	}
	/*
	 * 
	 * 
	 * THis test should test the functions used to create the tree such as delete and put
	 * 
	 */
	@Test
	public void testExtraFunctions() 
	{
		BST<Integer, Integer> bst = new BST<Integer, Integer>();
		
		bst.put(7, 7);   //        _7_
		bst.put(8, 8);   //      /     8

		//Testing delete
		bst.delete(8);	
		assertEquals("Testing delete works","(()7())",bst.printKeysInOrder());
		
		bst.put(8, 8);
		assertEquals("testing put works correctly","(()7(()8()))",bst.printKeysInOrder());
		

		
		
				
	}

}
