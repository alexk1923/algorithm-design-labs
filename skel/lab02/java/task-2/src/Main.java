import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    static class Task {
        public final static String INPUT_FILE = "in";
        public final static String OUTPUT_FILE = "out";

        int n, m;
        int[] dist;

        public void solve() {
            readInput();
            writeOutput(getResult());
        }

        private void readInput() {
            try {
                Scanner sc = new Scanner(new File(INPUT_FILE));
                n = sc.nextInt();
                m = sc.nextInt();
                dist = new int[n];
                for (int i = 0; i < n; i++) {
                    dist[i] = sc.nextInt();
                }
                sc.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private void writeOutput(int result) {
            try {
                PrintWriter pw = new PrintWriter(new File(OUTPUT_FILE));
                pw.printf("%d\n", result);
                pw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private int getResult() {
            // TODO: Aflati numarul minim de opriri necesare pentru
            // a ajunge la destinatie.
            System.out.println("benzinarii de pe drum: " + n);
            System.out.println("m: " + m);
            for(int i = 0; i < n; i++) {
                System.out.println("benzinarie: " + dist[i]);    
            }
            
            int counter = 0;
            int curr_dist = 0;
            int i = 0;
            while(curr_dist < dist[n - 1] && i < n - 1) {
                System.out.println("curr dist: " + curr_dist);
                // daca trebuie sa se opreasca la benzinarie
                if(curr_dist + m < dist[i + 1]) {
                    System.out.println("oprire la : " + dist[i]);
                    counter++;
                    curr_dist = dist[i];
                }
                i++;
            }
            return counter;
        }
    }

    public static void main(String[] args) {
        new Task().solve();
    }
}
