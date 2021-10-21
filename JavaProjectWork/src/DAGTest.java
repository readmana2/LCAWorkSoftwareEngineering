import static org.junit.Assert.*;

import org.junit.Test;

public class DAGTest {

	@Test
	public void testCreateDAG()
	{
		DAG dag1 = new DAG();
						

		assert("if Dag successfully adds node 2 to 1 then it should return true",true,dag1.add(1,2));
		
		dag1.add(1,3);
		dag1.add(3,1);
		dag1.add(3,4);
		
		

	}

}
