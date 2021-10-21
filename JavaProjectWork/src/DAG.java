import java.util.ArrayList;
/*
 * 
 * To construct the Directed Graph I used resources I found in the
 * algorithms textbook found here: https://algs4.cs.princeton.edu/
 * I used these resources as a reference and
 * modified it to better suit my purposes and removed unneccesary code
 * 
 * 
 * 
 * 
 * 
 */


/*Coding Notes so far
 * 
 * Create a Directed graph Structure according to the Unit tests
 * Need to make sure the graph is not cyclic
 * Need to find a way to find the LCA by moving through the graph
 */
public class DAG 
{

	//Stuff for creating the Graph
	private final int V;   //vertices        
	private int E;         //Edges        

	private ArrayList<Integer>[] adj;//Replacing the original BAG data structure with a fixed size array where every node corresponds to its 
	//index and each index contains an arrayList of all children
	private int[] indegree;       //How many incomign links this node has



	//Stuff for Directed Cycle
	private boolean[] marked;                  
	private boolean[] onStack;       
	public boolean cycleExists=false;



	//Making the empty graph
	public DAG(int V) 
	{
		if (V < 0) throw new IllegalArgumentException("Can't have Negative Vertices");
		this.V = V;
		this.E = 0;

		indegree = new int[V];
		marked = new boolean[V];
		onStack = new boolean[V];
		
		
		adj =  new ArrayList[V];//Eclipse complains about this for some reason


		for(int v = 0; v < V; v++)
		{
			adj[v] = new ArrayList<Integer>();		
		}
	}



	public boolean add(int v, int w) 
	{
		if(v<0||w<0||v>V||w>V)
		{
			return false;
		}
		
		adj[v].add(w);//Add w to the arraylist of vertex v's children
		indegree[w]++;//Increase the number of links that are attached to w.
		E++;
		
		dfs(v);//Check if a new cycle has been formed
		resetCycleDetection();
		dfs(w);//Check if a new cycle has been formed
		resetCycleDetection();
		

		return true;
	}
	
	public void resetCycleDetection()
	{
		
		for(int i=0;i<V;i++)
		{
			marked[i]=false;
			onStack[i]=false;
		}
		
	}



	public Iterable<Integer> adj(int v) 
	{    
		return adj[v];
	}


	//I read up on the algorithm for finding cycles in a Directed Graph here:
	// https://www.algotree.org/algorithms/tree_graph_traversal/depth_first_search/cycle_detection_in_directed_graphs/
	//This was similar to the implementation I found below
	// Depth First Search function found here: https://algs4.cs.princeton.edu/44sp/DirectedCycle.java.html
	//I decided to use that implementation as the base while adhering to the explanation of how to find a cycle in the other explanation
	//I removed the parts that tried to record the cycle and left only the stuff that finds the cycle and declares one as existing
	//Then I modified and restructured it to suit my code while adding the  resetCycleDetection function to make it work on repeat
	//Starting at the root Node, it keeps going down the graph marking each Node and adding them to a stack
	//Until it can no longer find any Nodes. At this point it removes each Node from the stack as it returns
	/*However if it finds a Node that  is already on the stack then that means a cycle exists
	 * 
	 */
	private void dfs(int v) 
	{
		marked[v] = true;
		onStack[v] = true;
		
		for (int w : adj(v))//For all adjacent nodes to source Node V 
		{

			// found new vertex, so recur
			if (marked[w]==false)
			{				
				dfs(w);
			}
			// 
			else if (onStack[w]==true)
			{
				
				cycleExists=true;
				return;
			}

		}
		onStack[v] = false;
	}




	public int getLCA(int a, int b)
	{
		return -1;

	}




}
