class AdjacencyListUndirectedGraph extends Graph {

	@Override
	void addEdge(Vertex v, Vertex w) {
        addDirectedEdge(v, w);
        addDirectedEdge(w, v);

        v.setInDegree(v.getInDegree() + 1);
        v.setOutDegree(v.getOutDegree() + 1);

        w.setInDegree(w.getInDegree() + 1);
        w.setOutDegree(w.getOutDegree() + 1);
	}
}
