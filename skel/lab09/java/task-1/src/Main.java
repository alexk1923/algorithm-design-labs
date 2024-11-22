import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    static class Task {
        public static final String INPUT_FILE = "in";
        public static final String OUTPUT_FILE = "out";

        // numarul maxim de noduri
        public static final int NMAX = 50005;

        // n = numar de noduri, m = numar de muchii
        int n, m;
        // nodul sursa
        int source;

        public class Edge {
            public int node;
            public int cost;

            Edge(int _node, int _cost) {
                node = _node;
                cost = _cost;
            }
        }

        public class Pair{
            public int node;
            public int currentCost;

            Pair(int _node, int _currentCost) {
                node = _node;
                currentCost = _currentCost;
            }
        }

        // adj[x] = lista de adiacenta a nodului x
        // Edge e inseamna ca exista muchie de la x la e.node (y) de cost e.cost (w): (x, y, w)
        @SuppressWarnings("unchecked")
        ArrayList<Edge> adj[] = new ArrayList[NMAX];

        public void solve() {
            readInput();
            writeOutput(getResult());
        }

        private void readInput() {
            try {
                Scanner sc = new Scanner(new BufferedReader(new FileReader(
                                INPUT_FILE)));
                n = sc.nextInt();
                m = sc.nextInt();
                source = sc.nextInt();

                for (int i = 1; i <= n; i++) {
                    adj[i] = new ArrayList<>();
                }
                for (int i = 1; i <= m; i++) {
                    int x, y, w;
                    x = sc.nextInt();
                    y = sc.nextInt();
                    w = sc.nextInt();
                    adj[x].add(new Edge(y, w));
                }
                sc.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private void writeOutput(ArrayList<Integer> result) {
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(
                                OUTPUT_FILE));
                StringBuilder sb = new StringBuilder();
                for (int i = 1; i <= n; i++) {
                    sb.append(result.get(i)).append(' ');
                }
                sb.append('\n');
                bw.write(sb.toString());
                bw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private ArrayList<Integer> getResult() {
            //
            // TODO: Gasiti distantele minime de la nodul source la celelalte noduri
            // folosind Dijkstra pe graful orientat cu n noduri, m arce stocat in adj.
            //    d[node] = costul minim / lungimea minima a unui drum de la source la
            //    nodul node;
            //    d[source] = 0;
            //    d[node] = -1, daca nu se poate ajunge de la source la node.
            // Atentie:
            // O muchie este tinuta ca o pereche (nod adiacent, cost muchie):
            //    adj[x].get(i) == Edge(y, w): muchie (x, y) de cost w -> (x, y, w)
            //
            boolean[] viz = new boolean[NMAX];
            ArrayList<Integer> d = new ArrayList<>();
            for (int i = 0; i <= n; i++)
                d.add(0);
            for(int i = 0; i <= n; i++) {
                if(i == source || i == 0) {
                    d.set(i, 0);
                } else {
                    d.set(i, -1);
                }
            }

            PriorityQueue<Pair> pq = new PriorityQueue<>(new Comparator<Pair>() {
                @Override
                public int compare(Pair arg0, Pair arg1) {
                    return arg0.currentCost - arg1.currentCost;
                }
            });
            System.out.println("n = " + n);
            pq.add(new Pair(source, 0));
            while(!pq.isEmpty()) {
                Pair p = pq.poll();
                int u = p.node;
                viz[u] = true;
                for (Edge edge : adj[u]) {
                    if((d.get(edge.node) > (d.get(u) + edge.cost)) && !viz[edge.node] || d.get(edge.node) == -1) {
                        d.set(edge.node, (d.get(u) + edge.cost));
                        pq.add(new Pair(edge.node, d.get(edge.node)));
                    }
                }
            }
            return d;
        }
    }

    public static void main(String[] args) {
        new Task().solve();
    }
}
