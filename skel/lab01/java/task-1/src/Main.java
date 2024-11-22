import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    static class Task {
        public final static String INPUT_FILE = "in";
        public final static String OUTPUT_FILE = "out";

        int n;
        int[] v;
        int x;

        public void solve() {
            readInput();
            writeOutput(getResult());
        }

        private void readInput() {
            try {
                Scanner sc = new Scanner(new File(INPUT_FILE));
                n = sc.nextInt();
                v = new int[n];
                for (int i = 0; i < n; i++) {
                    v[i] = sc.nextInt();
                }
                x = sc.nextInt();
                sc.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private void writeOutput(int count) {
            try {
                PrintWriter pw = new PrintWriter(new File(OUTPUT_FILE));
                pw.printf("%d\n", count);
                pw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private int findFirst() {
            // TODO: Cautati prima pozitie din v pe care se afla valoarea x.
            // In cazul in care nu exista in sir, returnati -1.
            int left = 0;
            int right = v.length - 1;

            while(left <= right) {
                int mid = (left + right) / 2;
                
                if(x == v[mid]) {
                    while(mid >= 0 && x == v[mid]) {
                        mid--;
                    }
                    return mid + 1;
                }

                if(x < v[mid]) {
                    right = mid - 1;
                }

                if(x > v[mid]) {
                    left = mid + 1;
                }

            }

            return -1;
        }

        private int findLast() {
            // TODO: Cautati ultima pozitie din v pe care se afla valoarea x.
            // In cazul in care nu exista in sir, returnati -1.

            int left = 0;
            int right = v.length - 1;

            while(left <= right) {
                int mid = (left + right) / 2;
                
                if(x == v[mid]) {
                    while(mid < v.length && x == v[mid]) {
                        mid++;
                    }
                    return mid - 1;
                }

                if(x < v[mid]) {
                    right = mid - 1;
                }

                if(x > v[mid]) {
                    left = mid + 1;
                }

            }

            return -1;
        }

        private int getResult() {
            // TODO: Calculati numarul de aparitii a numarului x in vectorul v.
            System.out.println("x = " + x);

            for(int i = 0; i < v.length; i++) {
                System.out.print(v[i] + " ");
            }

            System.out.println(findLast() - findFirst() + 1);
            // System.out.println();
            // System.out.println(findLast() - findFirst() + 1);
            if(findFirst() == -1) {
                return 0;
            }
            return (findLast() - findFirst() + 1);
        }
    }

    public static void main(String[] args) {
        new Task().solve();
    }
}
