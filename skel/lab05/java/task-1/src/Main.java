import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    static class Task {
        public final static String INPUT_FILE = "in";
        public final static String OUTPUT_FILE = "out";

        int n, k;

        public void solve() {
            readInput();
            writeOutput(getResult());
        }

        private void readInput() {
            try {
                Scanner sc = new Scanner(new File(INPUT_FILE));
                n = sc.nextInt();
                k = sc.nextInt();
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

        private boolean is_valid(Integer pos, ArrayList<Integer> aranjament) {
            for(int i = 0; i < pos; i++) {
                if(aranjament.get(i) == aranjament.get(pos)) {
                    return false;
                }
            }
            return true;
        }

        void back(ArrayList<ArrayList<Integer>> all, ArrayList<Integer> aranjament, int pos) {
            
            for(int i = 0; i < n; i++) { // iteram printre elementele pe care le vom incerca, i = element
                if(aranjament.size() == pos) {
                    aranjament.add(-1);
                }
                aranjament.set(pos, i + 1);
                if(is_valid(pos, aranjament)) {
                    if(pos == k - 1) {
                        all.add(new ArrayList<>(aranjament));
                        aranjament.remove(aranjament.size() - 1);
                    } else {
                        back(all, aranjament, pos + 1); 
                    }
                }
            }
        }

        private ArrayList<ArrayList<Integer>> getResult() {
            ArrayList<ArrayList<Integer>> all = new ArrayList<>();

            // TODO: Construiti toate aranjamentele de N luate cate K ale
            // multimii {1, ..., N}.
            //
            // Pentru a adauga un nou aranjament:
            System.out.println("n = " + n);
            System.out.println("k = " + k);
            int counter = 0;
            ArrayList<Integer> aranjament = new ArrayList<>();
        
            back(all, aranjament, 0);

            return all;
        }
    }

    public static void main(String[] args) {
        new Task().solve();
    }
}
