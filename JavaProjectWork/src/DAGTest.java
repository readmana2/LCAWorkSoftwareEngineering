import static org.junit.Assert.*;

import org.junit.Test;

public class DAGTest {

	@Test
	public void testCreateDAG()
	{
		DAG dag1 = new DAG();
						
		
		dag1.add(1,2);
		dag1.add(1,3);
		dag1.add(3,1);
		dag1.add(3,4);

	}

}
