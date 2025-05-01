import java.io.*;
import java.util.*;

class EdmondsKarp {
    private final FlowNetwork network;
    private List<List<Integer>> augmentingPaths = new ArrayList<>();
    private List<Integer> pathFlows = new ArrayList<>();

    public EdmondsKarp(FlowNetwork network) {
        this.network = network;
    }
}