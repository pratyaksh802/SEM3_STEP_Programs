import java.io.*;
import java.util.*;

public class CSVAnalyzer {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter CSV file path: ");
        String path = sc.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String header = br.readLine();
            String[] cols = header.split(",");

            int n = cols.length;
            double[] sum = new double[n];
            int[] count = new int[n];
            double[] min = new double[n];
            double[] max = new double[n];

            Arrays.fill(min, Double.MAX_VALUE);
            Arrays.fill(max, Double.MIN_VALUE);

            String line;
            while ((line = br.readLine()) != null) {
                String[] vals = line.split(",");
                for (int i = 0; i < vals.length; i++) {
                    try {
                        double v = Double.parseDouble(vals[i]);
                        sum[i] += v;
                        count[i]++;
                        min[i] = Math.min(min[i], v);
                        max[i] = Math.max(max[i], v);
                    } catch (Exception e) {
                        // ignore non-numeric
                    }
                }
            }

            System.out.printf("%-15s %-10s %-10s %-10s %-10s%n", "Column", "Avg", "Min", "Max", "Count");
            for (int i = 0; i < n; i++) {
                if (count[i] > 0) {
                    System.out.printf("%-15s %-10.2f %-10.2f %-10.2f %-10d%n",
                            cols[i], sum[i] / count[i], min[i], max[i], count[i]);
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
