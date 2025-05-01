import java.io.*;
import java.util.*;

class EdmondsKarp {
    private final FlowNetwork network;
    private List<List<Integer>> augmentingPaths = new ArrayList<>();
    private List<Integer> pathFlows = new ArrayList<>();

    public EdmondsKarp(FlowNetwork network) {
        this.network = network;
    }

    public int findMaxFlow(int source, int sink) {
        int maxFlow = 0;
        int iteration = 0;

        while (true) {
            iteration++;

            int[] parent = new int[network.getNumNodes()];
            Edge[] parentEdge = new Edge[network.getNumNodes()];
            Arrays.fill(parent, -1);

            Queue<Integer> queue = new LinkedList<>();
            queue.offer(source);
            parent[source] = -2;

            while (!queue.isEmpty() && parent[sink] == -1) {
                int current = queue.poll();

                for (Edge edge : network.getEdges(current)) {
                    int next = edge.getTo();

                    if (parent[next] == -1 && edge.getResidualCapacity() > 0) {
                        parent[next] = current;
                        parentEdge[next] = edge;
                        queue.offer(next);
                    }
                }
            }

            if (parent[sink] == -1) {
                break;
            }

            List<Integer> path = new ArrayList<>();
            for (int node = sink; node != source; node = parent[node]) {
                path.add(node);
            }
            path.add(source);
            Collections.reverse(path);
            augmentingPaths.add(path);

            int bottleneck = Integer.MAX_VALUE;
            for (int node = sink; node != source; node = parent[node]) {
                Edge edge = parentEdge[node];
                bottleneck = Math.min(bottleneck, edge.getResidualCapacity());
            }
            pathFlows.add(bottleneck);

            for (int node = sink; node != source; node = parent[node]) {
                Edge edge = parentEdge[node];
                edge.addFlow(bottleneck);
            }

            maxFlow += bottleneck;

            System.out.println("Iteration " + iteration + ": Found path with flow " + bottleneck + " (Total: " + maxFlow + ")");
        }

        System.out.println("\nSummary of augmenting paths:");
        for (int i = 0; i < augmentingPaths.size(); i++) {
            System.out.println("Path " + (i+1) + ": " + augmentingPaths.get(i) + " - Flow: " + pathFlows.get(i));
        }

        return maxFlow;
    }

    public String getDetailedSteps() {
        StringBuilder sb = new StringBuilder();
        sb.append("Edmonds-Karp Algorithm Steps:\n");

        for (int i = 0; i < augmentingPaths.size(); i++) {
            sb.append("Step ").append(i+1).append(":\n");
            sb.append("  Path: ").append(augmentingPaths.get(i)).append("\n");
            sb.append("  Flow: ").append(pathFlows.get(i)).append("\n");
        }

        return sb.toString();
    }
}