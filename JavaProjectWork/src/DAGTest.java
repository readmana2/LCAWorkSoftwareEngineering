import static org.junit.Assert.*;

import org.junit.Test;

public class DAGTest {

	@Test
	public void testCreateDAG()
	{
		DAG dag1 = new DAG(5);
						
		//Testing a Normal  Binary Tree Graph
		assertEquals("if Dag successfully adds node then should return true",true,dag1.add(0,1));
		dag1.add(0,2);
		dag1.add(1,3);
		dag1.add(2,4);
		
		
		//Testing a Graph construction where each Node only has one child
		dag1 = new DAG(5);
	
		dag1.add(0,1);	
		dag1.add(1,2);
		dag1.add(2,3);
		assertEquals("if Dag successfully adds node then should return true",true,dag1.add(3,4));
		
		//Testing a Graph construction where a node has multiple child Nodes
		dag1 = new DAG(5);
	
		dag1.add(0,1);	
		dag1.add(0,2);
		dag1.add(0,3);
		assertEquals("if Dag successfully adds node then should return true",true,dag1.add(0,4));
		
		
		assert(true);
			
		
		DAG dag2 = new DAG(10);
		dag2.add(0,1);   //        _0_
		dag2.add(0,2);   //      /     \
		dag2.add(2,3);   //    _2_      1
		dag2.add(2,4);   //  /     \
		dag2.add(3,5);   // 3       4
		dag2.add(4,6);   //  \     /
		dag2.add(6,7);   //   5   6
						 //        \
						 //         7
		assertEquals("if Dag successfully adds node then should return true",true,dag2.add(7,8));


	}
	
	@Test
	//Testing for Cycles occuring in the graph just to make sure its acyclic
	public void testCycle()
	{
		
		DAG dag1 = new DAG(5);
		
		dag1.add(0,1);	
		dag1.add(0,2);
		dag1.add(2,3);
		dag1.add(2,4);
		
		assertEquals(false, dag1.cycleExists);
		
		dag1.add(4, 0);
		assertEquals(true, dag1.cycleExists);
		
		dag1=new DAG(2);
		dag1.add(0, 1);
		dag1.add(1, 0);
		assertEquals(true, dag1.cycleExists);
		
		
		
		
		
	}
	
	@Test
	//Testing for LCA base cases
	public void DAGLCATest()
	{
		
		DAG dag1 = new DAG(5);
		
		dag1.add(0,1);	
		dag1.add(0,2);
		dag1.add(2,3);
		dag1.add(2,4);
		
		assertEquals("testing for ordinary binary tree",2,dag1.getLCA(4,3));
		
		
		
		assertEquals("testing again for when the two nodes are more separated",0,dag1.getLCA(1,4));
		
		
		//Testing a Graph construction where each Node only has one child
		dag1 = new DAG(6);    //0->1->2->3->4->5
	
		dag1.add(0, 1);
		dag1.add(1,2);	
		dag1.add(2,3);
		dag1.add(3,4);
		dag1.add(4,5);
		
		assertEquals("Only 1 link connecting each point",2,dag1.getLCA(2,5));
		
		
		dag1 = new DAG(10);
		
		dag1.add(0, 1);
		dag1.add(0,2);	
		dag1.add(0,3);
		dag1.add(3,4);
		dag1.add(3,5);
		
		dag1.add(3, 6);
		dag1.add(3,7);	
		dag1.add(1,8);
		dag1.add(7,8);
		dag1.add(8,9);
		
		assertEquals(1,dag1.getLCA(9,1));
		
		
		
		DAG dag2 = new DAG(10);
		dag2.add(0,1);   //        _0_
		dag2.add(0,2);   //      /     \
		dag2.add(2,3);   //    _2_      1
		dag2.add(2,4);   //  /     \
		dag2.add(3,5);   // 3       4
		dag2.add(4,6);   //  \     /
		dag2.add(6,7);   //   5   6
						 //        \
						 //         7
		assertEquals("Checking LCA tests from BST class 1",2,dag2.getLCA(5,7));
		assertEquals("Checking LCA tests from BST only 1 parent each",0,dag2.getLCA(2,1));
		assertEquals("Checking LCA tests from BST class The LCA is one of the nodes input",2,dag2.getLCA(2,7));
		assertEquals("Checking LCA tests from BST class Duplicate Nodes",5,dag2.getLCA(5,5));
		
		assertEquals("Checking LCA tests from BST class Duplicate Nodes",5,dag2.getLCA(5,5));


		
		
		
	
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
		
		
		
		DAG dag2 = new DAG(10);
		dag2.add(0,1);   //        _0_
		dag2.add(0,2);   //      /     \
		dag2.add(2,3);   //    _2_      1
		dag2.add(2,4);   //  /     \
		dag2.add(3,5);   // 3       4
		dag2.add(4,6);   //  \     /
		dag2.add(6,7);   //   5   6
						 //        \
						 //         7
		
		
		assertEquals("Checking  LCA works when  there is no LCA",-1,dag2.getLCA(2,12));
		assertEquals("Checking  LCA works when given nodes dont exist",-1,dag2.getLCA(24,12));


		DAG dag3 = new DAG(10);
		assertEquals("Graph doesnt have any Nodes",-1,dag3.getLCA(2,12));
		

		
		
				
	}

}
