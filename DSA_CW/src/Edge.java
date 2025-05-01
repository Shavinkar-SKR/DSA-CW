// Class to represent an edge in the flow network
class Edge {
    private final int from;
    private final int to;
    private final int capacity;
    private int flow;
    private Edge residual;

    // Constructor
    public Edge(int from, int to, int capacity) {
        this.from = from;
        this.to = to;
        this.capacity = capacity;
        this.flow = 0;
    }

    // Set the residual edge
    public void setResidual(Edge residual) {
        this.residual = residual;
    }

    // Get the residual capacity
    public int getResidualCapacity() {
        return capacity - flow;
    }

    // Add flow to this edge
    public void addFlow(int amount) {
        flow += amount;
        residual.flow -= amount;
    }

    // Getters
    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getFlow() {
        return flow;
    }

    public Edge getResidual() {
        return residual;
    }
}