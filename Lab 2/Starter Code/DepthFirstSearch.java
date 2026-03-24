import java.util.ArrayList;
import java.util.List;

class DepthFirstSearch {
    private List<Vertex> postOrder;
    private Graph graph;

    DepthFirstSearch(Graph g, String v) {
        postOrder = new ArrayList<>();
        graph = g;
       
        Vertex u = new Vertex(v);

        graph.clearState();
        graph.setState(u, Vertex.VertexState.DISCOVERED);

        dfs(u);
    }


    private void dfs(Vertex v) {
        if (v == null) {
            return;
        }

        List<Vertex> adjacency = graph.adjacentTo(v);
        if (adjacency != null) {
            for (Vertex u : adjacency) {
                if (graph.getState(u) == Vertex.VertexState.UNVISITED) {
                    graph.setState(u, Vertex.VertexState.DISCOVERED);
                    dfs(u);
                }
            }
        }
        graph.setState(v, Vertex.VertexState.FINISHED);
        postOrder.add(v);
    }

    public List<Vertex> getTraversal() {
        return postOrder;
    }
}
