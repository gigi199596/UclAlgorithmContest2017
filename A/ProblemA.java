import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class ProblemA {
    public static void main(String[] args){
        try{
            Scanner sc = new Scanner(System.in);
            int nbr_links = sc.nextInt() -1;
            System.out.println("Solution: "+ nbr_links);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
