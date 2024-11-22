import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    static class Task {
        public final static String INPUT_FILE = "in";
        public final static String OUTPUT_FILE = "out";

        double n;

        public void solve() {
            readInput();
            writeOutput(getResult());
        }

        private void readInput() {
            try {
                Scanner sc = new Scanner(new File(INPUT_FILE));
                n = sc.nextDouble();
                sc.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private void writeOutput(double x) {
            try {
                PrintWriter pw = new PrintWriter(new File(OUTPUT_FILE));
                pw.printf("%.4f\n", x);
                pw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private double getResult() {
            // TODO: Calculeaza sqrt(n) cu o precizie de 10^-3.
            // Precizie de 10^(-x) inseamna |valoarea_ta - valoarea_reala| < 10^(-x).

            double left = 1;
            double right = n;
            
            if(n < 1) {
                left = n;
                right = 1;
            }
            double mid = -1;

            System.out.println("n = " + n);
            System.out.println("right = " + right);

            while(left <= right) {
                mid = (left + right) / 2;
                System.out.println("mid = " + mid);

                if(mid * mid - n > 0.0001) {
                    right = mid;
                } else if(mid * mid - n < -0.0001) {
                    left = mid;
                } else{
                    break;
                }

                
            }


            return mid;
        }
    }

    public static void main(String[] args) {
        new Task().solve();
    }
}
