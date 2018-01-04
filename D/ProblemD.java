import java.io.File;
import java.util.*;

public class ProblemD {
    public static HashMap<String, Integer> profemons = new HashMap<>();
    public static HashMap<String, Integer> profs_to_index = new HashMap<>();
    public static HashMap<Integer, String> index_to_prof = new HashMap<>();
    public static QF myqf;

    public static void main(String[] args) {
        try {
            File input = new File("/home/anonymous/IdeaProjects/UclAlgorithmContest2017/D/D_2.txt");
            Scanner sc = new Scanner(input);
            int evolutions = sc.nextInt();
            int profemon_count = sc.nextInt();
            int profemon_output = sc.nextInt();
            sc.nextLine();
            String all_profemons = "";
            int count_nbr_prof = 0;
            /* Count the number of profemons. */
            for (int i = 0; i < evolutions; i++) {
                String line = sc.nextLine();
                String[] evolution = line.split(" ");
                if(!all_profemons.contains(evolution[0])){
                    all_profemons += " "+evolution[0];
                    profs_to_index.put(evolution[0], count_nbr_prof);
                    index_to_prof.put(count_nbr_prof, evolution[0]);
                    count_nbr_prof++;
                }
                if(!all_profemons.contains(evolution[1])){
                    all_profemons += " "+evolution[1];
                    profs_to_index.put(evolution[1], count_nbr_prof);
                    index_to_prof.put(count_nbr_prof, evolution[1]);
                    count_nbr_prof++;
                }

            }
            sc.close();
            sc = new Scanner(input);
            sc.nextLine();
            myqf = new QF(count_nbr_prof);
            for (int i=0; i < evolutions; i++){
                String line = sc.nextLine();
                String[] evolution = line.split(" ");
                connect2prof(evolution[0], evolution[1]);
            }
            for (int i = 0; i < profemon_count; i++) {
                String[] line = sc.nextLine().split(" ");
                String profemon = line[0];
                int count = Integer.valueOf(line[1]);
                if(profemons.containsKey(profemon)){
                    count += profemons.get(profemon);
                    profemons.replace(profemon, count);
                }else{
                    profemons.put(profemon, count);
                }
            }
            for (int i = 0; i < profemon_output; i++) {
                String profemon_out = sc.nextLine();
                int count = 0;
                int index = profs_to_index.get(profemon_out);
                int familly_index = myqf.qf_array[index];
                for(int j = 0; j < myqf.qf_array.length; j++){
                    /* Same familly. */
                    if(myqf.isConnected(myqf.qf_array[j],familly_index)){
                        /* Get profemon name. */
                        String name = index_to_prof.get(j);
                        if( profemons.containsKey(name)){
                            count += profemons.get(name);
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

    public static void connect2prof(String prof1, String prof2){
        int index1 = profs_to_index.get(prof1);
        int index2 = profs_to_index.get(prof2);
        myqf.union(index1, index2);

    }

    public static class QF{
        public int[] qf_array;
        public int count;
        public QF(int number){
            this.count = number;
            this.qf_array = new int[number];
            for(int i=0; i< count; i++){
                this.qf_array[i] = i;
            }
        }
        public int find(int p){
            while(p != qf_array[p]){
                p = qf_array[p];
            }
            return p;
        }
        public void union(int p, int q){
            int i = find(p);
            int j = find(q);
            if(i == j) return; //already connected
            qf_array[i] = j;

            count--;
        }
        public boolean isConnected(int p, int q){
            return find(p) == find(q);
        }
    }
}
