package PolyNet;

import java.util.ArrayList;

public class PolyNetNode {
    private ArrayList<PolyNetConnection> connections = new ArrayList<>();

    public ArrayList<PolyNetConnection> getConnections()
    {
        return this.connections;
    }

    public void addConnection(PolyNetNode otherNode, int distance)
    {
        this.connections.add(new PolyNetConnection(otherNode, distance));
    }
}
