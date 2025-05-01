class Edge {
    private final int from;
    private final int to;
    private final int capacity;
    private int flow;
    private Edge residual;

    public Edge(int from, int to, int capacity) {
        this.from = from;
        this.to = to;
        this.capacity = capacity;
        this.flow = 0;
    }

    public void setResidual(Edge residual) {
        this.residual = residual;
    }

    public int getResidualCapacity() {
        return capacity - flow;
    }

    public void addFlow(int amount) {
        flow += amount;
        residual.flow -= amount;
    }

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