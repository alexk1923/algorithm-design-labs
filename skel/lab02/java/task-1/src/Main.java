import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    static class Obj {
        public int weight;
        public int price;

        public Obj() {
            weight = 0;
            price = 0;
        }
    };

    static class Task {
        public final static String INPUT_FILE = "in";
        public final static String OUTPUT_FILE = "out";

        int n, w;
        Obj[] objs;

        public void solve() {
            readInput();
            writeOutput(getResult());
        }

        private void readInput() {
            try {
                Scanner sc = new Scanner(new File(INPUT_FILE));
                n = sc.nextInt();
                w = sc.nextInt();
                objs = new Obj[n];
                for (int i = 0; i < n; i++) {
                    objs[i] = new Obj();
                    objs[i].weight = sc.nextInt();
                    objs[i].price = sc.nextInt();
                }
                sc.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private void writeOutput(double result) {
            try {
                PrintWriter pw = new PrintWriter(new File(OUTPUT_FILE));
                pw.printf("%.4f\n", result);
                pw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private double getResult() {
            // TODO: Aflati profitul maxim care se poate obtine cu
            // obiectele date.
           Arrays.sort(objs, Comparator.comparingDouble(o -> (o.weight / o.price)));
           for(int i = 0; i < n; i++) {
                System.out.println("ob price: " + objs[i].price + " ob weight: " +  objs[i].weight);
           }
           int curr_weight = 0;
           int i = 0;
           double profit = 0.0;
        System.out.println("w = " + w);
           while(curr_weight < this.w && i < n) {
               if(curr_weight + objs[i].weight < this.w) {
                    profit+=objs[i].price;
                    curr_weight+=objs[i].weight;
               } else {
                    int rem_weight = this.w - curr_weight;
                    profit += objs[i].price * ((float)rem_weight / objs[i].weight);
                    break;
               }
               i++;
           }
           
            return profit;
        }
    }

    public static void main(String[] args) {
        new Task().solve();
    }
}
