//Aidan Weber-Concannon
//260708481
//Collaborators: Discussion board, brendan kadota 
package A3;
import java.io.*;
import java.util.*;




public class FordFulkerson {

	
	public static ArrayList<Integer> pathDFS(Integer source, Integer destination, WGraph graph){
		ArrayList<Integer> Stack = new ArrayList<Integer>();
		// YOUR CODE GOES HERE
		
		ArrayList<Integer> visited = new ArrayList<>();//colour nodes 
		Stack<Integer> dfs = new Stack<Integer>(); //is a stack
		ArrayList<Edge> edges= graph.getEdges();
		int[] parent= new int[graph.getNbNodes()];
		
		dfs.push(source); //put source on stack
		visited.add(source); //visit
		
		while(!dfs.isEmpty()){ //while nodes to discover 
			Integer current= dfs.pop();
			
			if(current.equals(destination)){//find source and make list
				Stack<Integer> reverse = new Stack<Integer>();
				reverse.push(current);
				
				while(parent[current]!=source){//reverse order 
					current=parent[current];
					reverse.push(current);
				}
				reverse.add(source); 
				
				while(!reverse.isEmpty()){
					Stack.add(reverse.pop());//put them on arraylist
				}
				return Stack;
			}
			
			for(Edge e: edges){
				if(e.nodes[0]==current&&e.weight!=0&&!visited.contains(e.nodes[1])){//Add the edge to stack if is from u, weight isnt zero and other vertex isnt in
					Integer child= e.nodes[1];
					dfs.push(child);
					visited.add(child);
					parent[child]=current; //discover child 
				}
			}
			
			
		}
		

		
		
		return Stack;
	}
	
	
	
	public static void fordfulkerson(Integer source, Integer destination, WGraph graph, String filePath){
		String answer="";
		String myMcGillID = "260708481"; //Please initialize this variable with your McGill ID
		int maxFlow = 0;
		
				// YOUR CODE GOES HERE
		ArrayList<Edge> cancel = graph.getEdges();
		boolean error=false;
		
		for(Edge c: cancel){//test for dual edges that would crash program
			if(graph.getEdge(c.nodes[1], c.nodes[0])!=null){
				error=true;
			}
		}
		
		WGraph capacities = new WGraph(graph);//BACKUP
		ArrayList<Edge> edges= graph.getEdges();
		
		for(Edge e: edges ){
			graph.setEdge(e.nodes[0], e.nodes[1], 0); //set flow to zero
		}
		
		WGraph residual = residual(graph,capacities); //make residual graph
		ArrayList<Integer> path = pathDFS(source,destination,residual); //find path
		
		if(path.isEmpty()){//no path then flow is zero
			maxFlow=0;
		}else if(source==destination||error){ //return error 
			maxFlow=-1;
		}else{
			while(!path.isEmpty()){
				int bottleNeck= bottleNeck(residual, capacities, path);//find bottleneck
				augment(graph, capacities, path, bottleNeck);//change original 
				maxFlow+= bottleNeck;//add maxflow 
				
				residual= residual(graph, capacities);//update residual
				path= pathDFS(source,destination,residual);//find new path
				
			}
		}
		
		
		//CODE ENDS HERE 
		
		
		answer += maxFlow + "\n" + graph.toString();	
		writeAnswer(filePath+myMcGillID+".txt",answer);
		System.out.println(answer);
	}
	
	
	public static void writeAnswer(String path, String line){
		BufferedReader br = null;
		File file = new File(path);
		// if file doesnt exists, then create it
		
		try {
		if (!file.exists()) {
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(line+"\n");	
		bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	//Helper method that finds bottleneck 
	public static int bottleNeck(WGraph graph,WGraph capacity,ArrayList<Integer> path){
		int bottleNeck =2147483647;//max possible value 
		
		for(int i=0;i<path.size()-1;i++){//terminate at right place 
			Edge edge = graph.getEdge(path.get(i), path.get(i+1));//get edges 
			Edge control = capacity.getEdge(path.get(i), path.get(i+1));//attempt to get edge 
			
			int beta=0;
			if(control!=null){
			
				beta= edge.weight;
				if(beta<bottleNeck){
					bottleNeck =beta;
				}
			}else{
				beta= edge.weight;
				if(beta<bottleNeck){
					bottleNeck =beta;
				}//find smallest bottle neck 
			}
		}
		return bottleNeck;
	}
	
	//Helper to create residual graph
	public static WGraph residual(WGraph graph, WGraph capacity){
		
		WGraph inverse = new WGraph(graph);
		ArrayList<Edge> edges = graph.getEdges();//get edges 
		
		for(Edge e: edges){
			if(e.weight<=capacity.getEdge(e.nodes[0], e.nodes[1]).weight){//update forward edge 
				inverse.setEdge(e.nodes[0], e.nodes[1],capacity.getEdge(e.nodes[0], e.nodes[1]).weight-e.weight);
			}
			if(e.weight>0){//add backwards edge 
				inverse.addEdge(new Edge(e.nodes[1],e.nodes[0],e.weight));
			}
		}
		return inverse;//return inverse 
	}
	
	//Helper method for augmenting 
	public static void augment(WGraph graph, WGraph capacity, ArrayList<Integer> path, int bottleneck){
		
		for(int i=0;i<path.size()-1;i++){//terminate at right place 
			Edge edge = graph.getEdge(path.get(i), path.get(i+1));
			
			if(edge!=null){//if edge is not null then it is forward 
				graph.setEdge(edge.nodes[0], edge.nodes[1], edge.weight+bottleneck);
			}else{//edge is backwards 
				graph.setEdge(edge.nodes[1], edge.nodes[0], edge.weight-bottleneck);//must be a backward edge
			}
		}
	}
	
	
	
	 public static void main(String[] args){
		 String file = "/Users/Aidan/Desktop/p3.txt";
		 File f = new File(file);
		 WGraph g = new WGraph(file);
		 fordfulkerson(g.getSource(),g.getDestination(),g,f.getAbsolutePath().replace(".txt",""));
	 }
}
