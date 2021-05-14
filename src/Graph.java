import java.util.ArrayList;

public class Graph {

    private final ArrayList<ArrayList<Integer>> adjmat = new ArrayList<>();

    public Graph(int v) {
        for (int i = 0; i < v; i++) {
            ArrayList<Integer> edge = new ArrayList<>(v);
            adjmat.add(edge);
            for (int j = 0; j < v; j++) {
                adjmat.get(i).add(0);
            }
        }
    }

    public int getVertexNum() {
        return adjmat.size();
    }

    public ArrayList<Integer> getVertex(int v) {
        return adjmat.get(v);
    }

    public void addEdge(int from, int to, int weight) {
        adjmat.get(from).set(to, weight);
    }

    public int getEdge(int from, int to) {
        return adjmat.get(from).get(to);
    }
}
