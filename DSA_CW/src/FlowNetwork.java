//Student ID: 20233155
//Student Name: Shavinkar Ravichandran

import java.util.*;

class FlowNetwork {
    private final int numOfNodes;
    private List<List<Edge>> adjacencyList;

    public FlowNetwork(int numOfNodes) {
        this.numOfNodes = numOfNodes;
        this.adjacencyList = new ArrayList<>(numOfNodes);

        for (int i = 0; i < numOfNodes; i++) {
            adjacencyList.add(new ArrayList<>());
        }
    }

    public void addEdge(int from, int to, int capacity) {
        Edge forward = new Edge(from, to, capacity);
        Edge backward = new Edge(to, from, 0);

        forward.setResidual(backward);
        backward.setResidual(forward);

        adjacencyList.get(from).add(forward);
        adjacencyList.get(to).add(backward);
    }

    public List<Edge> getEdges(int node) {
        return adjacencyList.get(node);
    }

    public int getNumNodes() {
        return numOfNodes;
    }

}