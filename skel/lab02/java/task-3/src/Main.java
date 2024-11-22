import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    static class Homework {
        public int deadline;
        public int score;

        public Homework() {
            deadline = 0;
            score = 0;
        }
    }

    static class Task {
        public final static String INPUT_FILE = "in";
        public final static String OUTPUT_FILE = "out";

        int n;
        Homework[] hws;

        public void solve() {
            readInput();
            writeOutput(getResult());
        }

        private void readInput() {
            try {
                Scanner sc = new Scanner(new File(INPUT_FILE));
                n = sc.nextInt();
                hws = new Homework[n];
                for (int i = 0; i < n; i++) {
                    hws[i] = new Homework();
                    hws[i].deadline = sc.nextInt();
                    hws[i].score = sc.nextInt();
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
            // TODO: Aflati punctajul maxim pe care il puteti obtine
            // planificand optim temele.
            Arrays.sort(hws,Comparator.comparingInt(o -> -o.deadline));
            for(int i = 0; i < n; i++) {
                System.out.println( "score: " + hws[i].score + " ddl:" + hws[i].deadline);
            }
            
            int max_ddl = -1;
            for(int i = 0; i < n; i++) {
                max_ddl = Math.max(max_ddl, hws[i].deadline);
            }

            int max_score = 0;
            PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
            
            int i = 0;
            int counter = 0;
            int curr_ddl;
            int prev_ddl = hws[0].deadline;
            while(i < n) {
                System.out.println("pq = " + pq);
                curr_ddl = hws[i].deadline;
                if(curr_ddl != prev_ddl) {
                    max_score+=pq.poll();
                    counter++;
                } 
                pq.add(Integer.valueOf(hws[i].score));
                i++;
                prev_ddl = curr_ddl;
                System.out.println("==================\npq = " + pq +"\n=================================\n");
            }

            while(pq.size() > 0 && counter < max_ddl) {
                max_score+=pq.poll();
                counter++;  
            }

            return max_score;
        }
    }

    public static void main(String[] args) {
        new Task().solve();
    }
}
