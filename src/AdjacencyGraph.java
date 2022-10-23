import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdjacencyGraph {
    ArrayList<Vertex> vertices;
    HashMap<String, Integer> surplus;
    public AdjacencyGraph(){
        vertices = new ArrayList<Vertex>();
    }
    public void addVertex(Vertex v){
        vertices.add(v);
    }
    public void addEdge(Vertex from, Vertex to, Integer weight){
        if(!(vertices.contains(from) && vertices.contains(to)))
        {
            System.out.println("Vertices missing from graph");
            return;
        }
        Edge newE=new Edge(from, to, weight);

    }
    public void addUnDirectedEdge(Vertex from, Vertex to, Integer weight){
        if(!(vertices.contains(from) && vertices.contains(to)))
        {
            System.out.println("Vertices missing from graph");
            return;
        }
        Edge newE=new Edge(from, to, weight);
        Edge newE2=new Edge( to, from, weight);
    }
    public void PrintGraph(){
        for(int i = 0; i< vertices.size(); i++){
            System.out.println("Vertex "+ vertices.get(i).name+" is connected to: ");
            Vertex current= vertices.get(i);
            for (Edge e: current.OutEdge) {
                System.out.println(e.to.name +" with weight: "+e.weight);
            }
        }
    }

    public void calculateSurplus() {
        HashMap<String, Integer> surplus = new HashMap<>();
        for (Vertex vertex : vertices) {
            if (!surplus.containsKey(vertex.name)) {
                surplus.put(vertex.name, 0);
            }
            for(Edge edge : vertex.OutEdge) {
                if (!surplus.containsKey(edge.to.name)) {
                    surplus.put(edge.to.name, 0);
                }
                surplus.put(vertex.name, surplus.get(vertex.name) - edge.weight);
                surplus.put(edge.to.name, surplus.get(edge.to.name) + edge.weight);
            }
        }
        this.surplus = surplus;
    }

    public HashMap<String, Integer> getSurplus() {
        return surplus;
    }

    public Map.Entry<String, Integer> findMinSurplus() {
        // Get min with the Java stream API.
        return surplus.entrySet().stream().min(Map.Entry.comparingByValue()).orElse(null);
    }

    public Map.Entry<String, Integer> findMaxSurplus() {
        // Get max with the Java stream API.
        return surplus.entrySet().stream().max(Map.Entry.comparingByValue()).orElse(null);
    }

    public void  minimumContainerFlow() {
        if (surplus.size() == 1) return;
        Map.Entry<String, Integer> maxContainers = findMaxSurplus();
        Map.Entry<String, Integer> minContainers = findMinSurplus();

        System.out.println("Send " + maxContainers.getValue() + " containers from " + maxContainers.getKey() + " to " + minContainers.getKey());
        minContainers.setValue(minContainers.getValue() + maxContainers.getValue());
        surplus.remove(maxContainers.getKey());
        minimumContainerFlow();
    }
}

class Vertex {
    String name;
    ArrayList<Edge> OutEdge;
    public Vertex(String name){
        this.name=name;
        OutEdge=new ArrayList<Edge>();
    }

}

class Edge{
    Vertex from;
    Vertex to;
    Integer weight;
    public Edge(Vertex from,Vertex to, Integer weight){
        this.from=from;
        this.to=to;
        this.weight=weight;
        from.OutEdge.add(this);
    }
}