import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class ProblemDbis {
    public static HashMap<String, String> profemon_familly = new HashMap<>();
    public static HashMap<String, Integer> profemons = new HashMap<>();

    public static void main(String[] args) {
        try {
            File input = new File("/home/anonymous/IdeaProjects/UclAlgorithmContest2017/D/D_1.txt");
            Scanner sc = new Scanner(input);
            int evolutions = sc.nextInt();
            int profemon_count = sc.nextInt();
            int profemon_output = sc.nextInt();
            sc.nextLine();
            for (int i = 0; i < evolutions; i++) {
                String line = sc.nextLine();
                String[] evolution = line.split(" ");
                insertFamilly(evolution[0], evolution[1]);
            }
            for (int i = 0; i < profemon_count; i++) {
                String[] line = sc.nextLine().split(" ");
                String profemon = line[0];
                if (profemons.containsKey(profemon)){
                    int count = profemons.get(profemon);
                    profemons.replace(profemon, count+ Integer.valueOf(line[1]));
                }
                else{
                    profemons.put(profemon, Integer.valueOf(line[1]));
                }
            }
            for (int i = 0; i < profemon_output; i++) {
                String profemon_out = sc.nextLine();
                String profemon_iter = profemon_out;
                int count = 0;
                if(profemons.containsKey(profemon_out)){
                    count = profemons.get(profemon_out);
                }
                if(profemon_familly.containsKey(profemon_out)){
                    while(!profemon_familly.get(profemon_iter).equals(profemon_out)){
                        profemon_iter = profemon_familly.get(profemon_iter);
                        if(profemons.containsKey(profemon_iter)){
                            count += profemons.get(profemon_iter);
                        }
                    }
                }
                System.out.println(profemon_out + " " + count);
            }
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insertFamilly(String first_profemon, String second_profemon) {
        if (profemon_familly.containsKey(first_profemon)) {
            String profemon_value = profemon_familly.get(first_profemon);
            profemon_familly.replace(first_profemon, second_profemon);
            profemon_familly.put(second_profemon, profemon_value);
        } else if (profemon_familly.containsKey(second_profemon)) {
            profemon_familly.put(first_profemon, second_profemon);
            String profemon_parcours = second_profemon;
            while(!profemon_familly.get(profemon_parcours).equals(second_profemon)){
                profemon_parcours = profemon_familly.get(profemon_parcours);
            }
            profemon_familly.replace(profemon_parcours, first_profemon);
        } else {
            LinkedList<String> familly = new LinkedList<>();
            familly.add(second_profemon);
            profemon_familly.put(first_profemon, second_profemon);
            profemon_familly.put(second_profemon, first_profemon);
        }
    }
}
