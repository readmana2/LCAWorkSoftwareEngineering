import java.util.ArrayList;
/*
 * 
 * To construct the Directed Graph I used resources I found in the
 * algorithms textbook found here: https://algs4.cs.princeton.edu/
 * I used these resources as a reference an
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
	

    private final int V;   //vertices        
    private int E;         //Edges        
    
	private ArrayList<Integer>[] adj;//Replacing the original BAG data structure with a fixed size array where every node corresponds to its 
									//index and each index contains an arrayList of all children
    private int[] indegree;       //How many incomign links this node has
    

    //Making the empty graph
    public DAG(int V) 
    {
        if (V < 0) throw new IllegalArgumentException("Number of vertices in a Digraph must be non-negative");
        this.V = V;
        this.E = 0;
        
        indegree = new int[V];
        adj = (ArrayList<Integer>[]) new ArrayList[V];
		
		
		for(int v = 0; v < V; v++)
		{
			adj[v] = new ArrayList<Integer>();		
		}
    }
    
    
    
    public void add(int v, int w) 
    {

        adj[v].add(w);//Add w to the arraylist of vertex v's children
        indegree[w]++;//Increase the number of links that are attached to w.
        E++;
    }
    
    public int getLCA(int a, int b)
    {
		return -1;
    	
    }
	
	
	

}
