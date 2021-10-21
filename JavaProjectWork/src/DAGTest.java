import static org.junit.Assert.*;

import org.junit.Test;

public class DAGTest {

	@Test
	public void testCreateDAG()
	{
		DAG dag1 = new DAG(4);
						
		//Testing a Normal  Binary Tree Graph
		assertEquals("if Dag successfully adds node 2 to 1 then it should return true",true,dag1.add(1,2));
		dag1.add(1,3);
		dag1.add(3,1);
		dag1.add(3,4);
		
		
		//Testing a Graph construction where each Node only has one child
		dag1 = new DAG(4);
	
		dag1.add(1,2);	
		dag1.add(2,3);
		dag1.add(3,4);
		dag1.add(4,5);
		
		//Testing a Graph construction where a node has multiple child Nodes
		dag1 = new DAG(4);
	
		dag1.add(1,2);	
		dag1.add(1,3);
		dag1.add(1,4);
		dag1.add(1,5);
		
		
		assert(true);
		

	}
	
	@Test
	//Testing for LCA base cases
	public void DAGLCATest()
	{
		
		DAG dag1 = new DAG(4);
		
		dag1.add(1,2);	
		dag1.add(1,3);
		dag1.add(3,1);
		dag1.add(3,4);
		
		assertEquals(3,dag1.getLCA(1,4));
		assertEquals(1,dag1.getLCA(2,4));
		
		
		//Testing a Graph construction where each Node only has one child
		dag1 = new DAG(4);
	
		dag1.add(1,2);	
		dag1.add(2,3);
		dag1.add(3,4);
		dag1.add(4,5);
		
		assertEquals(2,dag1.getLCA(2,5));
		
		
		
	
	}

}
