import java.io.File;
import java.util.*;

public class ProblemC {
    public static void main(String[] args){
        try{
            File input = new File("/home/anonymous/IdeaProjects/UclAlgorithmContest2017/C/C_2.txt");
            Scanner sc = new Scanner(input);
            int gem_number = sc.nextInt();
            HashSet<String> gem_ordering = new HashSet<>();
            for(int i=0; i < gem_number; i++){
                int number_of_specs = sc.nextInt();
                ArrayList<Integer> to_order = new ArrayList<>();
                for(int j=0; j< number_of_specs; j++){
                    to_order.add(sc.nextInt());
                }
                Collections.sort(to_order);
                String checksum = "";
                for (Integer integer : to_order) {
                    checksum += Integer.toString(integer);
                }
                //System.out.println(checksum);
                gem_ordering.add(checksum);
            }
            System.out.println(gem_ordering.size());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
