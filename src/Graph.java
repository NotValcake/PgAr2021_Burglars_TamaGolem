import java.util.ArrayList;

public class Graph {

    private final ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();

    public Graph(int v){
        for (int i = 0; i < v; i++) {
            ArrayList<Integer> edge = new ArrayList<>(v);
            adjList.add(edge);
        }
    }

    public ArrayList<Integer> getVertex(int v){
        return adjList.get(v);
    }

    public void addEdge(int from, int to, int weight){
        adjList.get(from).set(to, weight);
    }

    public int getEdge(int from, int to){
        return adjList.get(from).get(to);
    }
}
