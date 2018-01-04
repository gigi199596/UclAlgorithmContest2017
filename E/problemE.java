import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class problemE {
    public static HashMap<Integer, ArrayList<Integer>> mikado_array = new HashMap<>();

    public static void main(String[] args) {
        try {
            File input = new File("/home/gillou/IdeaProjects/UclAlgorithmContest2017/E/E_2.txt");
            Scanner sc = new Scanner(input);
            int sticks = sc.nextInt();
            int links = sc.nextInt();
            /* Insert empty AL for each mikado; */
            for (int i = 0; i < sticks; i++) {
                mikado_array.put(i, new ArrayList<>());
            }
            /* Fill the AL with deps. */
            for (int i = 0; i < links; i++) {
                int understick = sc.nextInt();
                int abovestick = sc.nextInt();
                /* Insert mikado dependencies inside the array list. */
                ArrayList<Integer> current_al = mikado_array.get(understick);
                current_al.add(abovestick);
                mikado_array.replace(understick, current_al);
            }
            int[] order_to_remove = new int[sticks];
            for (int i = 0; i < sticks; i++) {
                order_to_remove[i] = Integer.MIN_VALUE;
            }
            for (int i = 0; i < sticks; i++) {
                for (int j = 0; j < sticks; j++) {
                    if (mikado_array.containsKey(j)) {
                        ArrayList<Integer> check_al = mikado_array.get(j);
                        if (check_al.size() == 0) {
                            /* This mikado can be removed. */
                            order_to_remove[i] = j;
                            removeDepInMap(j, sticks);
                            break;
                        }
                    }
                }
                if (order_to_remove[i] == Integer.MIN_VALUE) {
                    System.out.println("impossible");
                    System.exit(0);
                }
            }
            for(int i=0; i < order_to_remove.length; i++){
                System.out.println(order_to_remove[i]);
            }
        } catch (
                Exception e)

        {
            e.printStackTrace();
        }

    }

    /**
     * Remove the mikado from everywhere in the Array list
     */
    public static void removeDepInMap(int mikado, int sticks) {
        for (int i = 0; i < sticks; i++) {
            /* Delete the mikado from HM. */
            if (i == mikado) {
                mikado_array.remove(i);
                continue;
            }
            if (mikado_array.containsKey(i)) {
                ArrayList<Integer> current = mikado_array.get(i);
                if (current.contains(mikado)) {
                /* Remove it. */
                    current.remove((Integer) mikado);
                    mikado_array.replace(i, current);
                }
            }

        }
    }
}
