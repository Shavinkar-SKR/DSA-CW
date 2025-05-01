import java.io.*;
import java.util.*;

public class MaxFlow {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java MaxFlow <input_file>");
            return;
        }

        String filename = args[0];
        FlowNetwork network = parseInputFile(filename);

        if (network != null) {
            EdmondsKarp solver = new EdmondsKarp(network);
            long startTime = System.currentTimeMillis();
            int maxFlow = solver.findMaxFlow(0, network.getNumNodes() - 1);
            long endTime = System.currentTimeMillis();

            System.out.println("Maximum flow: " + maxFlow);
            System.out.println("Computation time: " + (endTime - startTime) + " ms");
        }
    }

    private static FlowNetwork parseInputFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            // Read number of nodes
            int numNodes = Integer.parseInt(reader.readLine().trim());
            FlowNetwork network = new FlowNetwork(numNodes);

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");
                if (parts.length >= 3) {
                    int from = Integer.parseInt(parts[0]);
                    int to = Integer.parseInt(parts[1]);
                    int capacity = Integer.parseInt(parts[2]);
                    network.addEdge(from, to, capacity);
                }
            }

            return network;
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return null;
        } catch (NumberFormatException e) {
            System.err.println("Error parsing number: " + e.getMessage());
            return null;
        }
    }
}

