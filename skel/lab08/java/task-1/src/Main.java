import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Stack;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    static class Task {
        public static final String INPUT_FILE = "in";
        public static final String OUTPUT_FILE = "out";

        // numarul maxim de noduri
        public static final int NMAX = (int) 1e5 + 5; // 10^5 + 5 = 100.005

        // n = numar de noduri, m = numar de muchii/arce
        int n, m;

        // adj[node] = lista de adiacenta a nodului node
        // exemplu: daca adj[node] = {..., neigh, ...} => exista arcul (node, neigh)
        @SuppressWarnings("unchecked")
        ArrayList<Integer> adj[] = new ArrayList[NMAX];

        public void solve() {
            readInput();
            writeOutput(getResult());
        }

        private void readInput() {
            try {
                Scanner sc = new Scanner(new BufferedReader(new FileReader(INPUT_FILE)));
                n = sc.nextInt();
                m = sc.nextInt();

                for (int i = 1; i <= n; i++) {
                    adj[i] = new ArrayList<>();
                }
                for (int i = 1; i <= m; i++) {
                    int x, y;
                    x = sc.nextInt();
                    y = sc.nextInt();
                    adj[x].add(y); // arc (x, y)
                }
                sc.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private void writeOutput(ArrayList<ArrayList<Integer>> all_sccs) {
            try {
                PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE)));
                pw.printf("%d\n", all_sccs.size());
                for (ArrayList<Integer> scc : all_sccs) {
                    for (Integer node : scc) {
                        pw.printf("%d ", node);
                    }
                    pw.printf("\n");
                }
                pw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

private void tarjan(ArrayList<ArrayList<Integer>> all_sccs) {
            // Tarjan_SCC
// * visit all nodes with DFS
//      * compute found[node] and low_link[node]
//      * extract SCCs
//
// nodes     = list of all nodes from G
// adj[node] = the adjacency list of node
//             example: adj[node] = {..., neigh, ...} => edge (node, neigh)
    // STEP 1: initialize results
    // parent[node] = parent of node in the DFS traversal
    //
    // the timestamp when node was found (when started to visit its subtree)
    // Note: The global timestamp is incremented everytime a node is found.
    //
    // the minimum accessible timestamp that node can see/access
    // low_link[node] =  min { found[x] | x is node OR x in ancestors(node) OR x in descendants(node) };
    //

    // int[] parent = new int[n + 1];
    // int[] found = new int[n + 1];
    // int[] low_link = new int[n + 1];

    List<Integer> parent = new ArrayList<Integer>(Collections.nCopies(n + 1, null));
    List<Integer> found = new ArrayList<Integer>(Collections.nCopies(n + 1, Integer.MAX_VALUE));
    List<Integer> low_link = new ArrayList<Integer>(Collections.nCopies(n + 1, Integer.MAX_VALUE));
    

    Stack<Integer> nodes_stack = new Stack<>();
 
    // STEP 2: visit all nodes
    int timestamp = 0; // global timestamp
    for (int i = 1; i  <= n; i++) {
        if (parent.get(i) == null) { // node not visited
            parent.set(i, i); // convention: the parent of the root is actually the root
 
            // STEP 3: start a new DFS traversal this subtree
            DFS(all_sccs, i, adj, parent, timestamp, found, low_link, nodes_stack);
            
        }
    }
}
 
private void DFS(ArrayList<ArrayList<Integer>> all_sccs, int node, ArrayList<Integer> adj[], List<Integer> parent, int timestamp,
     List<Integer> found, List<Integer> low_link, Stack<Integer> nodes_stack) {
    // STEP 1: a new node is visited - increment the timestamp
    found.set(node, ++timestamp); // the timestamp when node was found
    low_link.set(node, found.get(node)); // node only knows its timestamp
    nodes_stack.add(node); // add node to the visiting stack
 
    // STEP 2: visit each neighbour
    for (Integer neigh : adj[node]) {
        // STEP 3: check if neigh is already visited
        if (parent.get(neigh) != null) {
            // STEP 3.1: update low_link[node] with information gained through neigh
            // note: neigh is in the same SCC with node only if it's in the visiting stack;
            // otherwise, neigh is from other SCC, so it should be ignored
            if (nodes_stack.contains(neigh)) {
                low_link.set(node, Math.min(low_link.get(node), found.get(neigh)));
            }
 
            continue;
        }
 
        // STEP 4: save parent
        parent.set(neigh, node);
 
        // STEP 5: recursively visit the child subtree
        DFS(all_sccs, neigh, adj, parent, timestamp, found, low_link, nodes_stack);
 
        // STEP 6: update low_link[node] with information gained through neigh
        low_link.set(node, Math.min(low_link.get(node), low_link.get(neigh)));
    }
 
    // STEP 7: node is root in a SCC if low_link[node] == found[node]
    // (there is no edge from a descendant to an ancestor)
    if (low_link.get(node) == found.get(node)) {
        // STEP 7.1: pop all elements above node from stack => extract the SCC where node is root
        ArrayList<Integer> new_scc = new ArrayList<>();
        Integer x = -1;
        while(x != node && !nodes_stack.isEmpty()) {
            x = nodes_stack.remove(0);
            new_scc.add(x);
        
        }
 
        // STEP 7.2: save / print the new SCC
        all_sccs.add(new ArrayList<>(new_scc));
        System.out.println(new_scc);
    }
}

        private ArrayList<ArrayList<Integer>> getResult() {
            //
            // TODO: Gasiti componentele tare conexe (CTC / SCC) ale grafului orientat cu n
            // noduri, stocat in adj.
            //
            // Rezultatul se va returna sub forma unui vector, fiecare element fiind un SCC
            // (adica tot un vector).
            // * nodurile dintr-un SCC pot fi gasite in orice ordine
            // * SCC-urile din graf pot fi gasite in orice ordine
            //
            // Indicatie: Folositi algoritmul lui Tarjan pentru SCC.
            //
            
            ArrayList<ArrayList<Integer>> all_sccs = new ArrayList<>();
            tarjan(all_sccs);
            return all_sccs;
        }
    }

    public static void main(String[] args) {
        new Task().solve();
    }
}
