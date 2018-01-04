import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

public class problemGbis {
    public static HashMap<Integer, Integer> last_seen = new HashMap<>();

    public static void main(String[] args) {
        try {
            File input = new File("/home/gillou/IdeaProjects/UclAlgorithmContest2017/G/G_1.txt");
            Scanner sc = new Scanner(input);
            int keyword_size = sc.nextInt();
            int message_size = sc.nextInt();
            int[] message = new int[message_size];
            for (int i = 0; i < keyword_size; i++) {
                int word = sc.nextInt();
                last_seen.put(word, Integer.MAX_VALUE);
            }
            for (int i = 0; i < message_size; i++) {
                message[i] = sc.nextInt();
            }
            boolean found = false;
            int best_min = Integer.MAX_VALUE;
            int best_max = Integer.MIN_VALUE;
            for (int i = 0; i < message_size; i++) {
                if (last_seen.containsKey(message[i])) {
                    last_seen.replace(message[i], i);
                    if (found) {
                        if (diffMinMax() < (best_max - best_min)) {
                            best_min = min();
                            best_max = max();
                        }
                    }
                    if (allFound() && !found) {
                        best_min = min();
                        best_max = max();
                        found = true;
                    }
                }
            }
            for(int i= best_min; i <= best_max; i++){
                System.out.print(message[i]+" ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean allFound() {
        for (int key : last_seen.keySet()) {
            if (last_seen.get(key) == Integer.MAX_VALUE) {
                return false;
            }
        }
        return true;
    }

    public static int max() {
        int max = Integer.MIN_VALUE;
        for (int value : last_seen.values()) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    public static int min() {
        int min = Integer.MAX_VALUE;
        for (int value : last_seen.values()) {
            if (value < min) {
                min = value;
            }
        }
        return min;
    }

    public static int diffMinMax() {
        return Math.abs(max() - min());
    }
}
