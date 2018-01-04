import java.io.File;
import java.util.HashSet;
import java.util.Scanner;

public class problemF {
    public static HashSet<String> lamousse_dictionnary = new HashSet<>();

    public static void main(String[] args){
        try{
            File input = new File("/home/gillou/IdeaProjects/UclAlgorithmContest2017/F/F_2.txt");
            Scanner sc = new Scanner(input);
            int lamousse_words = sc.nextInt();
            sc.nextLine();
            for(int i=0; i < lamousse_words; i++){
                String word = sc.nextLine();
                lamousse_dictionnary.add(word);
            }
            String sentence = sc.nextLine();
            String sen_with_spaces = "";
            int start = 0;
            int end = 0;
            for(int i=1; i < sentence.length()+1; i++){
                String maybe_word = sentence.substring(start, end+1);
                /* word is actually one. */
                if(lamousse_dictionnary.contains(maybe_word)){
                    sen_with_spaces += maybe_word;
                    sen_with_spaces += " ";
                    start = end +1;
                }
                end++;
            }
            sen_with_spaces = sen_with_spaces.trim();
            System.out.println(sen_with_spaces);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
