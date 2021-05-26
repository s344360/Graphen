/*
 * 
 */
package jpp.simplegraph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// TODO: Auto-generated Javadoc
//bfs(source, target)
//queue = {}
//queue.enqueue(source)
//
//while(not queue.isEmpty()) {
//    c = queue.dequeue()
//    if (c == target)
//        return getWay(min)
//
//    for each successor s of c {
//        if s not visited
//            queue.enqueue(s)
//    }
//}
//return fail

/**
 * The Class BFS.
 */
public class BFS {
	
	/**
	 * Search.
	 *
	 * @param graph the graph
	 * @param source the source
	 * @param target the target
	 * @return true, if successful
	 */
	public static boolean search(ISimpleGraph graph, int source, int target){
		if(graph.getNodeCount()<1)
			return false;
		int c=0;
		ArrayList<Integer> visited = new ArrayList<Integer>();
		Queue<Integer> queue = new LinkedList<Integer>();
		
		queue.offer(source);
		
		while(! queue.isEmpty()){
			c = queue.poll();
			visited.add(c);
			if(c==target)
				return true;
			
			if(graph.getSuccessors(c) !=null)
			for(int i :graph.getSuccessors(c))
				if(!visited.contains(i)){
					queue.offer(i);
					visited.add(i);
				}
			
		}
			
		
		return false;
		
	}
//    Diese Methode soll mittels Breitensuche prüfen, ob ein Weg vom Startknoten source zum Zielknoten target
//    existiert.
	
	/**
 * The main method.
 *
 * @param args the arguments
 */
public static void main(String[] args) {
		double[][] matrix = {{0.0,1.2,2.1,-1.0} , {2.4,0.0,-1.0,1.0} , {-1.0,-1.0,0.0,-1.0}, {-1.0,-1.0,-1.0,0.0}};
		ISimpleGraph graph = new SimpleGraph(matrix);

		
		System.out.println(BFS.search(graph, 0, 2));
	}
	
}


