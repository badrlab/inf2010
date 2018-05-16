package PolyNet;

import java.util.HashSet;
import java.util.PriorityQueue;

public class PolyNet {
    private PolyNetNode[] nodes;

    public PolyNet(PolyNetNode[] nodes) {
        this.nodes = nodes;
    }

    // MST (using Prim's algorithm).
    public int computeTotalCableLength() {

        int totalCableLength = 0;
        PriorityQueue<PolyNetConnection> knownConnections = new PriorityQueue<>();
        HashSet<PolyNetNode> visitedNodes = new HashSet<>();
        
        visitedNodes.add(nodes[0]);
        for (PolyNetConnection connection : nodes[0].getConnections()) knownConnections.add(connection);
        PolyNetConnection connection = knownConnections.poll();
        while(connection!=null) {
        	PolyNetNode node = connection.getConnectedNode();
	        if (!visitedNodes.contains(node)) {
	        	visitedNodes.add(node);
	        	totalCableLength += connection.getDistance();
	        	for(PolyNetConnection conn : node.getConnections()) knownConnections.add(conn);
	        }
        	connection = knownConnections.poll();
        }
       return totalCableLength;
    }
   
}
