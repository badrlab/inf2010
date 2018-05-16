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
        
        visitedNodes.add(nodes[0]); //initializing our set with the first node, taken as the first node of PolyNetNode[] nodes

        for (PolyNetConnection connection : nodes[0].getConnections()) //adding connections to first node to our priority queue before initializing the while loop
        {
            knownConnections.add(connection);
        }

        while(visitedNodes.size() < nodes.length) //while every node has not been visited yet
        {
            PriorityQueue<PolyNetConnection> tmpQueue = new PriorityQueue<PolyNetConnection>(knownConnections);
            PolyNetConnection minConnection = knownConnections.poll(); //taking the connection with the minimal distance from latest node added in visitedNodes
            totalCableLength += minConnection.getDistance(); //adding the distance to our totalLength
            PolyNetNode newVisitedNode = minConnection.getConnectedNode();
            visitedNodes.add(newVisitedNode); //we can consider the node with the minimal distance from latest node added in visitedNodes as visited
            ArrayList<PolyNetConnection> adjacentNodes = newVisitedNode.getConnections();

            for (PolyNetConnection remainingConnection : knownConnections)
            {
                PolyNetNode associatedNode = remainingConnection.getConnectedNode();
                if (areConnected(associatedNode, newVisitedNode)) //Among the remaining connections in the queue, we check if they are connected to the node we previously poll()
                {
                    int distanceByNewNode = minConnection.getDistance() + getDistanceBetweenNodes(associatedNode, newVisitedNode);
                    if (distanceByNewNode < remainingConnection.getDistance()) //If we can update distances we do it here
                    {
                        tmpQueue = updateDistances(remainingConnection, distanceByNewNode, tmpQueue, associatedNode);
                    }
                }
            }
            knownConnections = tmpQueue;
            for (PolyNetConnection newConnection : adjacentNodes)
            {
                knownConnections.add(newConnection);
            }
                      
        }
        return totalCableLength;
    }

    private boolean areConnected(PolyNetNode node1, PolyNetNode node2)
    {
        ArrayList<PolyNetConnection> node1Connections = node1.getConnections();
        for (PolyNetConnection connection : node1Connections)
        {
            if (connection.getConnectedNode() == node2) 
                return true;
        }
        return false;
    }

    private int getDistanceBetweenNodes(PolyNetNode node1, PolyNetNode node2)
    {
        ArrayList<PolyNetConnection> node1Connections = node1.getConnections();
        for (PolyNetConnection connection : node1Connections)
        {
            if (connection.getConnectedNode() == node2)
            {
                return connection.getDistance();
            }
        }
    }

    private PriorityQueue<PolyNetConnection> updateDistances(PolyNetConnection connection, int distance, PriorityQueue<PolyNetConnection> queue, PolyNetNode connectedNode)
    {
        queue.remove(connection);
        PolyNetConnection newConnection = new PolyNetConnection(connectedNode, distance);
        queue.add(newConnection);
        return queue;
    }
   
}
