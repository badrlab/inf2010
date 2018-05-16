package PolyNet;

public class PolyNetConnection implements Comparable<PolyNetConnection> {
    private PolyNetNode connectedNode;
    private int distance;

    public PolyNetConnection(PolyNetNode connectedNode, int dist)
    {
        this.connectedNode = connectedNode;
        this.distance = dist;
    }

    public PolyNetNode getConnectedNode()
    {
        return this.connectedNode;
    }

    public int getDistance()
    {
        return this.distance;
    }

    public int compareTo(PolyNetConnection otherCable)
    {
        return ((Integer)this.getDistance()).compareTo(otherCable.getDistance());
    }
}
