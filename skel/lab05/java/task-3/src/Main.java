import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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

        private void writeOutput(ArrayList<Integer> result) {
            try {
                PrintWriter pw = new PrintWriter(new File(OUTPUT_FILE));
                for (int i = 1; i <= n; i++) {
                    pw.printf("%d%c", result.get(i), i == n ? '\n' : ' ');
                }
                pw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private boolean is_valid(ArrayList<Integer> sol, int pos) {
            for(int i = 1; i < pos; i++) {
                if ( sol.get(i) == sol.get(pos) ||
                   Math.abs(i - pos) == Math.abs(sol.get(i) - sol.get(pos))) {
                    return false;
                }
            }

            return true;
        }

        private boolean is_sol(int n, int pos) {
            return (pos == n);
        }

        private void back (ArrayList<ArrayList<Integer>> finalSol, ArrayList<Integer> sol, int n, int pos) {

            for(int i = 1; i <= n; i++) {
                // System.out.println("Linia: " + pos);
                // System.out.println("Inainte de set:");
                // for(Integer dama : sol) {
                //     System.out.println(dama);
                // }
                
                sol.set(pos, i);
                // System.out.println("Dupa set:");
                // for(Integer dama : sol) {
                //     System.out.println(dama);
                // }

                if(is_valid(sol, pos)) {
                //System.out.println("E valid");
                    if(is_sol(n, pos)) {
                        finalSol.add(new ArrayList<>(sol));
                    } else {
                        //System.out.println("Nu e solutie, intram in back de " + (pos + 1));
                        back(finalSol, sol, n, pos + 1);
                    }
                } 
                //System.out.println("Nu a fost valid, deci merg mai departe in for pt i = " + (i + 1));
            }
            sol.set(pos, 0);
        }

        // void test_func(ArrayList<Integer> list, int p) {
        //     if(p == 2) {
        //         ArrayList<Integer> cpy = new ArrayList<>();
        //         cpy.add(18);
        //         cpy.add(21);
        //         list = new ArrayList<>(cpy);
        //     } else {
        //         test_func(list, p + 1);
        //     }
        // }

        private ArrayList<Integer> getResult() {


            
            ArrayList<Integer> sol = new ArrayList<Integer>();
            for (int i = 0; i <= n; i++)
                sol.add(0);
            System.out.println("n = " + n);
            
            // TODO: Gasiti o solutie pentru problema damelor pe o tabla de
            // dimensiune n x n.
            //
            // Pentru a plasa o dama pe randul i, coloana j:
            //     sol[i] = j.
            // Randurile si coloanele se indexeaza de la 1 la n.
            //
            // ArrayList<Integer> myList = new ArrayList<>();
            // test_func(myList, 0);


            // System.out.println("Test lista:");
            // for(Integer t : myList) {
            //     System.out.println(t);
            // }

            ArrayList<ArrayList<Integer>> finalSol = new ArrayList<>();
            back(finalSol, sol, n, 1);
            sol = new ArrayList<Integer>(finalSol.get(0));
            // De exemplu, configuratia (n = 5)
            // X----
            // --X--
            // ----X
            // -X---
            // ---X-
            // se va reprezenta prin sol[1..5] = {1, 3, 5, 2, 4}.

            return sol;
        }
    }

    public static void main(String[] args) {
        new Task().solve();
    }
}
