import java.io.File;
import java.util.LinkedList;
import java.util.Scanner;

public class ProblemB {
    public static int[][] matrice;
    public static boolean[][] marked;
    public static int poids;
    public static void main(String[] args){
        try{
            File problem = new File("/home/anonymous/IdeaProjects/UclAlgorithmContest2017/B/B_1.txt");
            Scanner sc = new Scanner(problem);
            int hauteur = sc.nextInt();
            int largeur = sc.nextInt();
            matrice = new int[largeur][hauteur];
            marked = new boolean[largeur][hauteur];
            poids = sc.nextInt();
            Coord depart = new Coord(sc.nextInt(), sc.nextInt());
            Coord arrivee = new Coord(sc.nextInt(), sc.nextInt());
            for(int i=0; i < largeur; i++){
                for(int j=0; j < hauteur; j++){
                    matrice[i][j] = sc.nextInt();
                }
            }
            bfs(depart);
            System.out.println(hasPathTo(arrivee));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void bfs(Coord start){
        marked[start.x][start.y] = true;
        LinkedList<Coord> queue = new LinkedList<>();
        queue.addLast(start);
        while(!(queue.isEmpty())){
            Coord actual_pos = queue.removeFirst();
            for(Coord neighbor: adj(actual_pos)){
                if (!marked[neighbor.x][neighbor.y]){
                    marked[neighbor.x][neighbor.y] = true;
                    queue.addLast(neighbor);
                }
            }
        }
    }

    public static LinkedList<Coord> adj(Coord to_move){
        LinkedList<Coord> to_return = new LinkedList<>();
        /* Case de droite. */
        if(to_move.x + 1 < matrice.length){
            Coord right = new Coord(to_move.x + 1, to_move.y);
            if(checkNeighbors(right)){
                to_return.addLast(right);
            }
        }
        /* Case de gauche. */
        if(to_move.x -1 >= 0){
            Coord left = new Coord(to_move.x - 1, to_move.y);
            if(checkNeighbors(left)){
                to_return.addLast(left);
            }
        }
        /* Case du haut. */
        if(to_move.y + 1 < matrice[0].length){
            Coord up = new Coord(to_move.x, to_move.y + 1);
            if(checkNeighbors(up)){
                to_return.addLast(up);
            }
        }
        /* Case du bas. */
        if(to_move.y -1 >= 0){
            Coord bottom = new Coord(to_move.x, to_move.y - 1);
            if(checkNeighbors(bottom)){
                to_return.addLast(bottom);
            }
        }
        return to_return;
    }
    public static boolean checkNeighbors(Coord center){
        /* Case centrale. */
        if(matrice[center.x][center.y] < poids){
            return false;
        }
        /* Cases à droite. */
        if(center.x + 1 < matrice.length){
            if ( matrice[center.x + 1][center.y] < poids){
                return false;
            }
            if(center.y + 1 < matrice[0].length){
                if (matrice[center.x + 1][center.y + 1] < poids){
                    return false;
                }
            }
            if(center.y - 1 >= 0){
                if(matrice[center.x + 1][center.y - 1] < poids){
                    return false;
                }
            }
        }
        /* Cases à gauche. */
        if(center.x - 1 >= 0){
            if ( matrice[center.x - 1][center.y] < poids){
                return false;
            }
            if(center.y + 1 < matrice[0].length){
                if (matrice[center.x - 1][center.y + 1] < poids){
                    return false;
                }
            }
            if(center.y - 1 >= 0){
                if(matrice[center.x - 1][center.y - 1] < poids){
                    return false;
                }
            }
        }
        /* Case dessus. */
        if(center.y + 1 < matrice[0].length ){
            if(matrice[center.x][center.y + 1] < poids){
                return false;
            }
        }
        /* Case dessous. */
        if(center.y - 1 >= 0 ){
            if(matrice[center.x][center.y - 1] < poids){
                return false;
            }
        }
        return true;
    }

    public static String hasPathTo(Coord arrivee){
        return marked[arrivee.x][arrivee.y]? "oui":"non";
    }
    public static class Coord{
        int x;
        int y;
        public Coord(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
