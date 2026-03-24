class AdjacencyListDirectedGraph extends Graph {
    @Override
    void addEdge(Vertex v, Vertex w) {
        addDirectedEdge(v, w);

        v.setOutDegree(v.getInDegree() + 1);
        w.setInDegree(w.getInDegree() + 1);
    }
}
