import java.sql.SQLOutput;
import java.util.*;

public class Main {
    // The program uses the adjacency graph example from Line and the lectures as a baseline.
    public static void main(String[] args) {
        AdjacencyGraph shipG = createDirectedAdj();
        shipG.calculateSurplus();
        HashMap<String, Integer> surplus = shipG.getSurplus();
        // Print surpluses.
        for(String port : surplus.keySet()) {
            System.out.println(port + ": " + surplus.get(port));
        }
        System.out.println();
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

    // The following function was originally developed to be a part of finding a solution to the minimal containerflow problem.
    // However, it didn't end up being used. I left it in to show part of the development process.
    // The function takes a hashmap, and sorts it into a linked hashmap.
    public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm) {
        ArrayList<Map.Entry<String, Integer>> list = new ArrayList<>(hm.entrySet());
        list.sort(Map.Entry.comparingByValue());
        // Remap to Linked HashMap
        HashMap<String, Integer> out = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : list) {
            out.put(entry.getKey(), entry.getValue());
        }
        return out;
    }
}