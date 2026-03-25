import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class TopologicalSort {
    List<Vertex> topoOrdering;
    Graph graph;

    public TopologicalSort(Graph g) {
        topoOrdering = new ArrayList<>();

        graph = g;

        List<Vertex> vertices = g.getVertices();
        for (Vertex v : vertices) {
            if (graph.degree(v) == 0) {
                dfs(v);
            }
        }
    }

    private void dfs(Vertex v) {
        if (graph.getState(v) == Vertex.VertexState.FINISHED) {
            return;
        }

        graph.setState(v, Vertex.VertexState.DISCOVERED);
        for (Vertex u : graph.adjacentTo(v)) {
            if (graph.getState(u) == Vertex.VertexState.DISCOVERED) {
                throw new IllegalArgumentException("Cycle detected, no valid toposort");
            }
            dfs(u);
        }

        graph.setState(v, Vertex.VertexState.FINISHED);
        topoOrdering.add(v);
    }

    public List<Vertex> getTopoSort() {
        Collections.reverse(topoOrdering);
        return topoOrdering;
    }
}
