import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
/*
 * 
 * To construct the Directed Graph I used resources I found in the
 * algorithms textbook found here:
 * https://algs4.cs.princeton.edu/
 * https://algs4.cs.princeton.edu/42digraph/Digraph.java.html
 * 
 * I used these resources as a reference and
 * modified it to better suit my purposes and removed unnecessary code
 * 
 * 
 * 
 * 
 * 
 * 
 */


 
public class DAG 
{

	//Stuff for creating the Graph
	private final int V;   //vertices        
	private int E;         //Edges        

	private ArrayList<Integer>[] adj;//Replacing the original BAG data structure with a fixed size array where every node corresponds to its 
									//index and each index contains an arrayList of all children
	private ArrayList<Integer>[] parents;  //Array for the parents of each Node
								




	//Stuff for Directed Cycle
	private boolean[] marked;                  
	private boolean[] onStack;       
	public boolean cycleExists=false;



	
	//Making the empty graph
	/*
	 * 
	 * 
	 */
	public DAG(int V) 
	{
		if (V < 0) throw new IllegalArgumentException("Can't have Negative Vertices");
		this.V = V;
		this.E = 0;

		
		marked = new boolean[V];
		onStack = new boolean[V];
		
		parents = new ArrayList[V]; 
		adj =  new ArrayList[V];//Eclipse complains about this for some reason


		for(int v = 0; v < V; v++)
		{
			adj[v] = new ArrayList<Integer>();	
			parents[v] = new ArrayList<Integer>();
		}
	}
	
	

	/*This add function was based on code found here:
	 * https://algs4.cs.princeton.edu/42digraph/Digraph.java.html
	 * We studied these graphs in our Algorithms and Data Structure Modules 
	 * There was several modifications to make it work for this class
	 */
	public boolean add(int v, int w) 
	{
		if(v<0||w<0||v>V||w>V)
		{
			return false;
		}
		
		adj[v].add(w);//Add w to the arraylist of vertex v's children
		parents[w].add(v);//Add w to the arraylist of vertex w's parents
		
		
		E++;
		
		
		
		//Checking to see if any cycles have been created
		dfs(v);//Check if a new cycle has been formed
		resetCycleDetection();
		dfs(w);//Check if a new cycle has been formed
		resetCycleDetection();
		

		return true;
		
	}
	
	
	
	//Avoiding the problem of multiple cycle checks failing because marked and OnStack are not cleared
	//Does not return anything
	public void resetCycleDetection()
	{
		
		for(int i=0;i<V;i++)
		{
			marked[i]=false;
			onStack[i]=false;
		}
		
	}

	

	//from https://algs4.cs.princeton.edu/42digraph/Digraph.java.html
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
				System.out.println("Testing");
				cycleExists=true;
				return;
			}

		}
		onStack[v] = false;
	}


	
	

	/*Similar to my binary search tree implementation of the LCA, this function, finds all the ancestors of a particular Node and returns them in an ArrayList
	 * It then repeats this for the second Node and then compares these lists to find which one they have in common. The first one they find is the
	 * Lowest common Ancestor.
	 * In the event where they have two least common ancestors it will just pick the first one found and return it as an integer.
	 * 
	 * returns -1 if the there is no LCA or the function failed
	 */
	
	public int getLCA(int a, int b)
	{
		if(a<0 ||b<0 ||a>V||b>V|| E==0||cycleExists==true)
		{
			
			return -1;
			
		}
			
		ArrayList<Integer> parentsOfA = bfs(a);
		ArrayList<Integer> parentsOfB = bfs(b);
		
		ArrayList<Integer> ancestorsInCommonInOrder = new ArrayList<Integer>();

		for(int i = 0; i < parentsOfA.size(); i++)
		{
			for(int k = 0; k < parentsOfB.size(); k++)
			{
				if(parentsOfA.get(i).equals(parentsOfB.get(k)))
				{
					ancestorsInCommonInOrder.add(parentsOfA.get(i));				
				}
			}
		}
		if(ancestorsInCommonInOrder.size()!=0)
		{
			int lca=ancestorsInCommonInOrder.get(0);
			return lca;
		}
		else
		{
			return -1; 
		}
	}
	
	
	
	
	//breadth first search algorithm I found online here:https://www.geeksforgeeks.org/breadth-first-search-or-bfs-for-a-graph/
	/*I modified it slightly to record the ancestors instead of just searching through the graph
	 * This breadth first search will go through each of the ancestors of a node and add them to an arrayList
	 * Eventually it will reach a point where there are no more ancestors
	 * 
	 */
	public ArrayList<Integer> bfs(int s)
	{
		
		LinkedList<Integer> queue = new LinkedList<Integer>();
		
		boolean visited[] = new boolean[V]; 
		
		visited[s] = true;
		queue.add(s);
		
		ArrayList<Integer> parentsOfNode = new ArrayList<Integer>();
		while(queue.size() != 0)
		{
			
			
			s = queue.poll(); 
			
			parentsOfNode.add(s);
			Iterator<Integer> i = parents[s].listIterator();
			
			while(i.hasNext())
			{
				
				int n = i.next();
				if(!visited[n])
				{
					
					visited[n] = true;
					queue.add(n);
					
				}
			}
			
		}
		return parentsOfNode;
	}
	

}
