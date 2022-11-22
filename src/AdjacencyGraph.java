import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdjacencyGraph {
    ArrayList<Vertex> vertices;
    HashMap<String, Integer> surplus;
    public AdjacencyGraph(){
        vertices = new ArrayList<>();
    }
    public void addVertex(Vertex v){
        vertices.add(v);
    }

    private int totalCost = 0;

    // Throws exception if given uninitialized vertices.
    public void addEdge(Vertex from, Vertex to, Integer weight) throws Exception {
        if(!(vertices.contains(from) && vertices.contains(to)))
        {
            throw new Exception("Vertex is missing in vertices arraylist.");
        }
        new Edge(from, to, weight);

    }

    /**
     * Calculates the surplus of containers at each port, and adds this to a HashMap.
     */
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

    /**
     * Finds the port in the surplus HashMap with the least containers in surplus.
     * @return The Map.Entry with the least containers in surplus.
     */
    public Map.Entry<String, Integer> findMinSurplus() {
        // Get min with the Java stream API.
        return surplus.entrySet().stream().min(Map.Entry.comparingByValue()).orElse(null);
    }

    /**
     * Finds the port in the surplus HashMap with the most containers in surplus.
     * @return The Map.Entry with the most containers in surplus.
     */
    public Map.Entry<String, Integer> findMaxSurplus() {
        // Get max with the Java stream API.
        return surplus.entrySet().stream().max(Map.Entry.comparingByValue()).orElse(null);
    }

    /**
     * Calculates which ports in the HashMap has to send how many containers to where, and the order of the shipments.
     */
    public void minimumContainerFlow() {
        if (surplus.size() == 1) return;
        Map.Entry<String, Integer> maxContainers = findMaxSurplus();
        Map.Entry<String, Integer> minContainers = findMinSurplus();
        // Determine how to handle container shipment.
        if (minContainers.getValue() + maxContainers.getValue() > 0) {
            System.out.println("Send " + (-1 * minContainers.getValue()) + " containers from " + maxContainers.getKey() + " to " + minContainers.getKey());
            totalCost += -1 * minContainers.getValue() * 100;
            maxContainers.setValue(maxContainers.getValue() + minContainers.getValue());
            surplus.remove(minContainers.getKey());

        } else {
            System.out.println("Send " + maxContainers.getValue() + " containers from " + maxContainers.getKey() + " to " + minContainers.getKey());
            totalCost += maxContainers.getValue() * 100;
            minContainers.setValue(minContainers.getValue() + maxContainers.getValue());
            surplus.remove(maxContainers.getKey());
        }
        minimumContainerFlow();
    }

    public int getTotalCost() {
        return totalCost;
    }
}

class Vertex {
    String name;
    ArrayList<Edge> OutEdge;
    public Vertex(String name){
        this.name=name;
        OutEdge=new ArrayList<>();
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