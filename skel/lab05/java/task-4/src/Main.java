import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static class Task {
        public final static String INPUT_FILE = "in";
        public final static String OUTPUT_FILE = "out";

        int n, k;
        char[] caractere;
        int[] freq;

        public void solve() {
            readInput();
            writeOutput(getResult());
        }

        private void readInput() {
            try {
                Scanner sc = new Scanner(new File(INPUT_FILE));
                n = sc.nextInt();
                k = sc.nextInt();
                String s = sc.next().trim();
                s = " " + s;
                caractere = s.toCharArray();
                freq = new int[n + 1];
                for (int i = 1; i <= n; i++) {
                    freq[i] = sc.nextInt();
                }
                sc.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private void writeOutput(ArrayList<ArrayList<Character>> result) {
            try {
                PrintWriter pw = new PrintWriter(new File(OUTPUT_FILE));
                pw.printf("%d\n", result.size());
                for (ArrayList<Character> arr : result) {
                    for (int i = 0; i < arr.size(); i++) {
                        pw.printf("%c", arr.get(i));
                    }
                    pw.printf("\n");
                }
                pw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    private boolean is_valid (ArrayList<Character> sir, int pos, int[] curr_freq) {

        for(int i = 1; i <= n; i++) {
            if(curr_freq[i] > freq[i]) {
                return false;
            }
        }

        if(sir.size() >= k) {
            int counter = 1;
            char c = sir.get(0);
            for(int i = 1; i <= pos; i++) { // verificare caractere consecutive
                if (sir.get(i) == c){
                    counter++;
                } else {
                    counter = 1;
                    c = sir.get(i);
                }
                if(counter > k) {
                    return false;
                }
            }
        }

        return true;
    }

        private int sum_arr(int[] arr) {
            int sum = 0;
            for(int i = 1; i < arr.length; i++) {
                sum+=arr[i];
            }
            return sum;
        }

        private void print_arr(int n, int[] arr, String s) {
            System.out.println(s);
            for(int i = 1; i <= n; i++) {
                System.out.println(arr[i]);
            }
        }

        private  boolean is_sol(int n, int pos) {
            // System.out.println("Comparam pos=" +  pos + " cu s_freq = " + sum_arr(freq));
            return ((pos + 1) == sum_arr(freq));
        }

        private  void back (ArrayList<ArrayList<Character>> finalSol, ArrayList<Character> sir, int n, int pos, int[] curr_freq) {
            int i;
            for(i = 1; i <= n; i++) {

                if(sir.size() == pos) {
                    sir.add('?');
                }

                
                if(sir.get(pos) != '?') {
                    curr_freq[i - 1]--;
                }
                sir.set(pos, caractere[i]);
                curr_freq[i]++;
                
                if(is_valid(sir, pos, curr_freq)) {
                    if(is_sol(n, pos)) {
                        finalSol.add(new ArrayList<>(sir));
                    } else {
                        back(finalSol, sir, n, pos + 1, curr_freq);
                    }
                } 
            }
            sir.remove(pos);
            curr_freq[i - 1]--;

        }

        private ArrayList<ArrayList<Character>> getResult() {
            ArrayList<ArrayList<Character>> all = new ArrayList<>();
            ArrayList<Character> sir = new ArrayList<>();
            sir.add('-');
            System.out.println("n = " + n);
            System.out.println("k = " + k);


            int[] curr_freq = new int[n + 1];
            back(all, sir, n, 0, curr_freq);

            for(ArrayList<Character> string : all) {
                System.out.println(string);
            }

            // TODO: Construiti toate sirurile cu caracterele in stringul
            // caractere (indexat de la 1 la n), si frecventele in vectorul freq
            // (indexat de la 1 la n), stiind ca nu pot fi mai mult de K
            // aparitii consecutive ale aceluiasi caracter.
            //
            // Pentru a adauga un nou sir:
            // ArrayList<Character> sir;
            //   all.add(sir);
            //

            return all;
        }
    }

    public static void main(String[] args) {
        new Task().solve();
    }
}
