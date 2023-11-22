package socialIsland;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.StdOut;

public class RemoveEdgeTest {
	public static void main(String[] args) {
        Graph G = new Graph(5);
        G.addEdge(0, 1);
        G.addEdge(0, 2);
        G.addEdge(0, 4);
        G.addEdge(1, 4);
        G.addEdge(1, 2);
        StdOut.println("Adjacency List");
        StdOut.println("--------------");
        for (int v = 0; v < G.V(); v++) {
        	boolean first = true;
        	StdOut.printf("%d: ", v);
        	for (int adj : G.adj(v)) {
        		if (!first) {
        			StdOut.print(" -> ");
                } else {
                    first = false;
                }
        		StdOut.print(adj);
        	}
        	StdOut.println();
        }
        
        StdOut.println();
        
        Graph G2 = RemoveEdge(G, 1, 2);
        
        StdOut.println("Adjacency List2");
        StdOut.println("--------------");
        for (int v = 0; v < G2.V(); v++) {
        	boolean first = true;
        	StdOut.printf("%d: ", v);
        	for (int adj : G2.adj(v)) {
        		if (!first) {
        			StdOut.print(" -> ");
                } else {
                    first = false;
                }
        		StdOut.print(adj);
        	}
        	StdOut.println();
        }
	}
	
	public static Graph RemoveEdge(Graph G, int vertex, int adjacent) {	
		
		int verticeCounter = 0;
		boolean doublecounting = false; 
		
		for (int v = 0; v < G.V(); v++) {
			verticeCounter++;
		}
		
		Graph G2 = new Graph(verticeCounter);
		
		for (int v = 0; v < G.V(); v++) {
			for (int adj : G.adj(v)) {
				doublecounting = false;
				if ((adj == vertex && v == adjacent) || (adj == adjacent && v == vertex) ) {
				// This is used to skip adding vertex and adjacent as an Edge.
					continue;
				}
				else {
					for (int adj2 : G2.adj(v)) {
						if (adj2 == adj) 
							doublecounting = true;
					}
					if (doublecounting == false)
						G2.addEdge(v, adj);
				}
			}
		}
		return G2;
		
	}
	
}

