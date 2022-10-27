import java.sql.SQLOutput;
import java.util.*;

public class Main {
    // The program uses the adjacency graph example from Line and the lectures as a baseline.
    public static void main(String[] args) {
        AdjacencyGraph shipG = createDirectedAdj();
        shipG.calculateSurplus();
        System.out.println("Surpluses for each port.");
        HashMap<String, Integer> surplus = shipG.getSurplus();
        // Print surpluses.
        for(String port : surplus.keySet()) {
            System.out.println(port + ": " + surplus.get(port));
        }
        System.out.println();
        System.out.println("Minimum container flow to return containers.");
        shipG.minimumContainerFlow();
    }


    public static AdjacencyGraph createDirectedAdj(){
        AdjacencyGraph newG = new AdjacencyGraph();
        Vertex jarwaharlal = new Vertex("Jawaharlal Nehru");
        Vertex tanjung = new Vertex("Tanjung Pelepas");
        Vertex dar = new Vertex("Dar Es Salaam");
        Vertex mombasa = new Vertex("Mombasa");
        Vertex zanzibar = new Vertex("Zanzibar");
        Vertex jebel = new Vertex("Jebel Ali Dubai");
        Vertex salalah = new Vertex("Salalah");
        newG.addVertex(jarwaharlal);
        newG.addVertex(tanjung);
        newG.addVertex(dar);
        newG.addVertex(mombasa);
        newG.addVertex(zanzibar);
        newG.addVertex(jebel);
        newG.addVertex(salalah);

        newG.addEdge(jarwaharlal, mombasa,2000);
        newG.addEdge(jarwaharlal, dar,2000);
        newG.addEdge(tanjung, mombasa,5000);
        newG.addEdge(tanjung, dar,3000);
        newG.addEdge(tanjung, zanzibar,2000);
        newG.addEdge(tanjung, jebel,7000);
        newG.addEdge(tanjung, salalah,7000);
        newG.addEdge(dar, tanjung,5000);
        newG.addEdge(dar, jarwaharlal,3000);
        newG.addEdge(dar, jebel,2000);
        newG.addEdge(mombasa, salalah, 2000);
        newG.addEdge(mombasa, jebel, 500);

        return newG;
    }
}