import java.util.Map;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public abstract class Graph {

    // You need to create instance variables to maintain the list of vertices
    // and the edges between them. You can do this in one data structure
    // or it might be more straighforward to have two seperate data structures:
    // one for the list of vertices and one for the adjaceny list.
    // But the choice is yours! You are the programmer!

    /**
     * A map to maintain the state of the Vertex objects represented in this Graph.
     * A Vertex can in one of these states: Vertex.VertexState
     *
     * @see Vertex.VertexState
     */
    private Map<String, Vertex.VertexState> stateMap = new TreeMap<>();
    
    /**
     * Set map of string to vertex object
     */
    private Map<String, Vertex> vertices = new HashMap<>();

    /**
     * Adjacency list, LC/technical interview style
     */
    private Map<String, ArrayList<Vertex>> adjacencyList = new HashMap<>();

    protected void addDirectedEdge(Vertex u, Vertex v) {
        adjacencyList.computeIfAbsent(u.getLabel(), k -> new ArrayList<>()).add(v);
    }

    public void clearState() {
        stateMap.clear();
    }

    public void setState(Vertex v, Vertex.VertexState state) {
        stateMap.put(v.getLabel(), state);
    }

    public Vertex.VertexState getState(Vertex v) {
        if (stateMap.containsKey(v.getLabel())) {
            return stateMap.get(v.getLabel());
        }

        // if the Vertex v has no state, return unvisited, maybe this should be an
        // invalid value?!
        return Vertex.VertexState.UNVISITED;
    }

    /**
     * Add a vertex to the graph with the label v
     * 
     * @param v the label of the vertex
     */
    void addVertex(String v) {
        addVertex(new Vertex(v));
    }

    /**
     * Add the given vertex to the graph. Allows a vertex to be added that might not
     * be connected.
     * Need to check if the Vertex v already exists to stop duplicates and add only
     * if it does not exist.
     * 
     * @param v the vertex to be added
     */
    void addVertex(Vertex v) {
        if (stateMap.containsKey(v.getLabel())) {
            return;
        }
        
        vertices.put(v.getLabel(), v);
        setState(v, Vertex.VertexState.UNVISITED);
    }

    /**
     * Add edge v-w. Will also add Vertex v and w if they do not already exist.
     * Need to check if the v and w already exists to stop duplicates and add only
     * if it does not exist.
     * 
     * @param v vertex v of the edge
     * @param w vertex w of the edge
     */
    void addEdge(String v, String w) {
        addVertex(v);
        addVertex(w);

        addEdge(new Vertex(v), new Vertex(w));
    }

    /**
     * Add edge v-w. Will also add Vertex v and w if they do not already exist.
     * Need to check if the v and w already exists to stop duplicates and add only
     * if it does not exist.
     * 
     * @param v vertex v of the edge
     * @param w vertex w of the edge
     */
    abstract void addEdge(Vertex v, Vertex w);

    /**
     * Neigbours of vertex v in lexicographic order. The method will return
     * null if there are no adjacent vertices.
     * 
     * @param v the String label of the vertex to find the neighbours of.
     * @return the list of adjacent vertices or null if no adjacent vertices.
     */
    List<Vertex> adjacentTo(String v) {
        return adjacentTo(new Vertex(v));
    }

    /**
     * Neigbours of vertex v in lexicographic order. The method will return
     * null if there are no adjacent vertices.
     * 
     * @param v the vertex to find the neighbours of.
     * @return the list of adjacent vertices or null if no adjacent vertices.
     */
    List<Vertex> adjacentTo(Vertex v) {
        ArrayList<Vertex> adjacent = adjacencyList.get(v.getLabel());

        if (adjacent == null) {
            return new ArrayList<>();
        }

        adjacent.sort(Comparator.comparing(Vertex::getLabel));
        return adjacent;
    }

    /**
     * number of neighbours of vertex v, labelled with the String v.
     * 
     * @param v the label of the vertex
     * @return
     */
    int degree(String v) {
        return degree(new Vertex(v));
    }

    /**
     * number of neighbours of vertex v.
     * 
     * @param v
     * @return
     */
    int degree(Vertex v) {
        return v.getInDegree();
    }

    /**
     * Get all the vertices associated with the graph in lexicographic order.
     * 
     * @return a list of adjacent vertices
     */
    List<Vertex> getVertices() {
        return new ArrayList<>(vertices.values());
    }

    /**
     * is v-w an edge in the graph
     * 
     * @param v
     * @param w
     * @return
     */
    boolean hasEdge(String v, String w) {
        return hasEdge(new Vertex(v), new Vertex(w));
    }

    /**
     * is v-w an edge in the graph
     * 
     * @param v
     * @param w
     * @return
     */
   boolean hasEdge(Vertex v, Vertex w) {
        if (adjacencyList.get(v.getLabel()) != null) {
            for (Vertex u : adjacencyList.get(v.getLabel())) {
                if (u.getLabel() == w.getLabel()) {
                    return true;
                }
            }
        }

        if (adjacencyList.get(w.getLabel()) != null) {
            for (Vertex u : adjacencyList.get(w.getLabel())) {
                if (u.getLabel() == v.getLabel()) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * is v a vertex in the graph
     * 
     * @param v
     * @return
     */
    boolean hasVertex(String v) {
        return hasVertex(new Vertex(v));
    }

    /**
     * is v a vertex in the graph?
     * 
     * @param vertex
     * @return
     */
    boolean hasVertex(Vertex vertex) {
        return stateMap.containsKey(vertex.getLabel());
    }

    /**
     * Gets the vertex in the graph with the label v
     * 
     * @param v
     * @return
     */
    Vertex getVertex(String v) {
        return vertices.get(v); 
    }

}
