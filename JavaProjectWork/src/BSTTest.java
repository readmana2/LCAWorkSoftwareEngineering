import static org.junit.Assert.*;

import org.junit.Test;

class BSTTest {

	
	/*
	 * This test should construct a binary Tree and ensure the print Keys in Order method matches the way it is created
	 * 
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
		
		
	}

}
