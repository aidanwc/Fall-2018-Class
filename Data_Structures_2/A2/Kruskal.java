//Aidan Weber-Concannon
//260708481
//Collaborators: Discussion board 
package A2;
import java.util.*;

public class Kruskal{

    public static WGraph kruskal(WGraph g){

        //Runs algorithim
        WGraph MST= new WGraph(); //Makes mst 
        ArrayList<Edge> sortedEdges = g.listOfEdgesSorted();//sorted edges 
        DisjointSets library=new DisjointSets(g.getNbNodes()); //keeps track of sets that are connected 
        
        while(MST.getNbNodes()<g.getNbNodes()&&sortedEdges.size()>0){ //Continue while #nodesMST<#nodes in graph 
        	Edge temp =sortedEdges.remove(0);
        	
        	if(IsSafe(library,temp)){
        		MST.addEdge(temp); //add the minumum safe edge 
        		library.union(temp.nodes[0],temp.nodes[1]);//update library
        		
        	}
        }
        return MST;
        
    }
    //Calculates if edge is safe to add, in two seperate sets
    public static Boolean IsSafe(DisjointSets p, Edge e){

        
        if(p.find(e.nodes[0])==p.find(e.nodes[1])){
        	return false; 
        }
		return true;
    
    }

    public static void main(String[] args){

        String file = args[0];
        WGraph g = new WGraph(file);
        WGraph t = kruskal(g);
        System.out.println(t);

   } 
}
