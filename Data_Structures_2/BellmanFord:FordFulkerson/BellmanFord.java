//Aidan Weber-Concannon

//260708481
//Collaborators: Discussion board, brendan kadota 
package A3;
import java.util.*;
public class BellmanFord{

	
	/**
	 * Utility class. Don't use.
	 */
	public class BellmanFordException extends Exception{
		private static final long serialVersionUID = -4302041380938489291L;
		public BellmanFordException() {super();}
		public BellmanFordException(String message) {
			super(message);
		}
	}
	
	/**
	 * Custom exception class for BellmanFord algorithm
	 * 
	 * Use this to specify a negative cycle has been found 
	 */
	public class NegativeWeightException extends BellmanFordException{
		private static final long serialVersionUID = -7144618211100573822L;
		public NegativeWeightException() {super();}
		public NegativeWeightException(String message) {
			super(message);
		}
	}
	
	/**
	 * Custom exception class for BellmanFord algorithm
	 *
	 * Use this to specify that a path does not exist
	 */
	public class PathDoesNotExistException extends BellmanFordException{
		private static final long serialVersionUID = 547323414762935276L;
		public PathDoesNotExistException() { super();} 
		public PathDoesNotExistException(String message) {
			super(message);
		}
	}
	
    private int[] distances = null;
    private int[] predecessors = null;
    private int source;

    BellmanFord(WGraph g, int source) throws BellmanFordException{
        /* Constructor, input a graph and a source
         * Computes the Bellman Ford algorithm to populate the
         * attributes 
         *  distances - at position "n" the distance of node "n" to the source is kept
         *  predecessors - at position "n" the predecessor of node "n" on the path
         *                 to the source is kept
         *  source - the source node
         *
         *  If the node is not reachable from the source, the
         *  distance value must be Integer.MAX_VALUE
         *  
         *  When throwing an exception, choose an appropriate one from the ones given above
         */
        
        /* YOUR CODE GOES HERE */
    	this.source=source;
    	distances = new int[g.getNbNodes()];
    	predecessors = new int[g.getNbNodes()];//Initilize variables 
    	
    	for(int i=0;i<g.getNbNodes();i++){//Put infinity in all distances except source 
    		if(i==source){
    			distances[i]=0;
    		}else{
    			distances[i]=Integer.MAX_VALUE;
    		}
    	}
    	
    	for(int i=1; i<g.getNbNodes();i++){//V-1 iterations 
    		ArrayList<Edge> edges= g.getEdges();
    		for(Edge e:edges){//Relax each edge so long as its not an infinite one 
    			if(distances[e.nodes[0]]==Integer.MAX_VALUE){
    				continue;
    			}
    			if(distances[e.nodes[1]]>distances[e.nodes[0]]+e.weight){ //Relax edge 
    				distances[e.nodes[1]]=distances[e.nodes[0]]+e.weight;
    				predecessors[e.nodes[1]]=e.nodes[0];
    			}
    		}
    	}
    	
    	ArrayList<Edge> edges= g.getEdges();//Get edges 
		for(Edge e:edges){
			if(distances[e.nodes[1]]>distances[e.nodes[0]]+e.weight&&distances[e.nodes[0]]!=Integer.MAX_VALUE){
				throw new NegativeWeightException("Negative cycle found"); //Test for negative cycle
			}
		}
    }
    

    public int[] shortestPath(int destination) throws BellmanFordException{
        /*Returns the list of nodes along the shortest path from 
         * the object source to the input destination
         * If not path exists an Exception is thrown
         * Choose appropriate Exception from the ones given 
         */

        /* YOUR CODE GOES HERE (update the return statement as well!) */
    	if(distances[destination]==Integer.MAX_VALUE||destination>=predecessors.length){//Make sure node is reachable and within range
    		throw new PathDoesNotExistException("Path does not exist");
    	}
    	if(destination==source){ //If destination= source then the path is 1
    		return new int[]{source};
    	}
        Stack<Integer> s=new Stack<Integer>();
        int cur=destination;
        s.push(cur);
        
        for(int i=0;i<this.predecessors.length;i++){//Path will not be longer than number of vertices or it would have cycles 
        	cur=predecessors[cur];
        	s.push(cur); //add to stack
        	
        	if(cur==source){//once reach source 
        		int[] path = new int[s.size()];
        		int size=s.size();
        		for(int j=0;j<size;j++){
        			path[j]=s.pop();//reverse path and store in array
        		}
        		return path;
        	}
        }
        throw new PathDoesNotExistException("Path does not exist");//If still hasn't found it throw error
    }

    public void printPath(int destination){
        /*Print the path in the format s->n1->n2->destination
         *if the path exists, else catch the Error and 
         *prints it
         */
        try {
            int[] path = this.shortestPath(destination);
            for (int i = 0; i < path.length; i++){
                int next = path[i];
                if (next == destination){
                    System.out.println(destination);
                }
                else {
                    System.out.print(next + "-->");
                }
            }
        }
        catch (BellmanFordException e){
            System.out.println(e);
        }
    }

    public static void main(String[] args){

        String file ="/Users/Aidan/Desktop/p3.txt"; //args[0];
        WGraph g = new WGraph(file);
        try{
            BellmanFord bf = new BellmanFord(g, g.getSource());
            bf.printPath(g.getDestination());
        }
        catch (BellmanFordException e){
            System.out.println(e);
        }

   } 
}
