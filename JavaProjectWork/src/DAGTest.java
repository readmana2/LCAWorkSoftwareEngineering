import static org.junit.Assert.*;

import org.junit.Test;

public class DAGTest {

	@Test
	public void testCreateDAG()
	{
		DAG dag1 = new DAG();
						
		//Testing a Normal  Binary Tree Graph
		assert("if Dag successfully adds node 2 to 1 then it should return true",true,dag1.add(1,2));
		dag1.add(1,3);
		dag1.add(3,1);
		dag1.add(3,4);
		
		
		//Testing a Graph construction where each Node only has one child
		dag1 = new DAG();
	
		dag1.add(1,2);	
		dag1.add(2,3);
		dag1.add(3,4);
		dag1.add(4,5);
		
		//Testing a Graph construction where a node has multiple child Nodes
		dag1 = new DAG();
	
		dag1.add(1,2);	
		dag1.add(1,3);
		dag1.add(1,4);
		dag1.add(1,5);
		
		
		Assert(true);
		

	}
	
	@Test
	//Testing for LCA base cases
	public void DAGLCATest()
	{
		
		DAG dag1 = new DAG();
		
		dag1.add(1,2);	
		dag1.add(1,3);
		dag1.add(3,1);
		dag1.add(3,4);
		
		assert(3,dag1.getLCA(1,4));
		assert(1,dag1.getLCA(2,4));
		
		
		//Testing a Graph construction where each Node only has one child
		dag1 = new DAG();
	
		dag1.add(1,2);	
		dag1.add(2,3);
		dag1.add(3,4);
		dag1.add(4,5);
		
		assert(2,dag1.getLCA(2,5));
		
		
		
	
	}

}
