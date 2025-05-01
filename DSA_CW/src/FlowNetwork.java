import java.util.*;

class FlowNetwork {
    private final int numNodes;
    private List<List<Edge>> adjacencyList;

    public FlowNetwork(int numNodes) {
        this.numNodes = numNodes;
        this.adjacencyList = new ArrayList<>(numNodes);

        for (int i = 0; i < numNodes; i++) {
            adjacencyList.add(new ArrayList<>());
        }
    }
}