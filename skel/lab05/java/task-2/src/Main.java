import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static class Task {
        public final static String INPUT_FILE = "in";
        public final static String OUTPUT_FILE = "out";

        int n;

        public void solve() {
            readInput();
            writeOutput(getResult());
        }

        private void readInput() {
            try {
                Scanner sc = new Scanner(new File(INPUT_FILE));
                n = sc.nextInt();
                sc.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private void writeOutput(ArrayList<ArrayList<Integer>> result) {
            try {
                PrintWriter pw = new PrintWriter(new File(OUTPUT_FILE));
                pw.printf("%d\n", result.size());
                for (ArrayList<Integer> arr : result) {
                    for (int i = 0; i < arr.size(); i++) {
                        pw.printf("%d%c", arr.get(i), i + 1 == arr.size() ?
                                '\n' : ' ');
                    }
                }
                pw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


        private boolean is_valid(Integer pos, ArrayList<Integer> submultime) {
            if(pos < 1) {
                return true;
            }

            if(submultime.get(pos) > submultime.get(pos - 1)) {
                return true;
            }
            return false;
        }

        void back(ArrayList<ArrayList<Integer>> all, ArrayList<Integer> submultime, int pos) {
            
            for(int i = 0; i < n; i++) { // iteram printre elementele pe care le vom incerca, i = element
                if(submultime.size() == pos) {
                    submultime.add(-1);
                }
                System.out.println("k = " + (pos + 1));
                System.out.println("Aranjament inainte de set");
                for(Integer e : submultime) {
                    System.out.print(e + " ");
                }
                System.out.println();

                submultime.set(pos, i + 1);

                System.out.println("Aranjament dupa set:");
                for(Integer e : submultime) {
                    System.out.print(e + " ");
                }
                System.out.println();

                if(is_valid(pos, submultime)) {
                    System.out.println("E VALID ! ");
                    all.add(new ArrayList<>(submultime));
                    back(all, submultime, pos + 1); 
                } 
            }
            submultime.remove(submultime.size() - 1);
        }

        private ArrayList<ArrayList<Integer>> getResult() {
            ArrayList<ArrayList<Integer>> all = new ArrayList<>();

            // TODO: Construiti toate submultimele multimii {1, ..., N}.
            //
            // Pentru a adauga o noua submultime:
            System.out.println("n = " + n);
              ArrayList<Integer> submultime = new ArrayList<>();
              all.add(submultime);

              back(all, submultime, 0);
              for(ArrayList<Integer> submult : all) {
                for(Integer e : submult) {
                    System.out.print(e + " ");
                }
                System.out.println();
              }

            return all;
        }
    }

    public static void main(String[] args) {
        new Task().solve();
    }
}
