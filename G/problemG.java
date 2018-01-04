import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.File;
import java.util.*;

public class problemG {
    public static ArrayList<Item> keyword = new ArrayList<>();

    public static void main(String[] args) {

        try {
            File input = new File("/home/gillou/IdeaProjects/UclAlgorithmContest2017/G/G_2.txt");
            Scanner sc = new Scanner(input);
            int keyword_size = sc.nextInt();
            int message_size = sc.nextInt();
            int[] message = new int[message_size];
            for (int i = 0; i < keyword_size; i++) {
                keyword.add(new Item(sc.nextInt()));
            }
            for (int i = 0; i < message.length; i++) {
                message[i] = sc.nextInt();
            }
            boolean found = false;
            int nbr_match = 0;
            int start = 0;
            boolean reverse = false;
            int size = Integer.MAX_VALUE;
            ArrayList<Integer> final_sequence = null;
            ArrayList<Integer> sequence = new ArrayList<>();
            int pointer_message = 0;
            while (!found) {
                if (pointer_message < 0){
                    pointer_message = start;
                    reverse = !reverse;
                }
                if(pointer_message >= message_size) {
                    break;
                }
                Item current = new Item(message[pointer_message], false);
                if (keyword.contains(current)) {
                    int index = keyword.indexOf(current);
                    current.taken = true;
                    keyword.set(index, current);

                    nbr_match++;
                }
                if (nbr_match > 0) {
                    sequence.add(message[pointer_message]);
                }
                /* Check if all integers of the keyword are found. */
                if (nbr_match == keyword.size()) {
                    if (sequence.size() < size) {
                        size = sequence.size();
                        final_sequence = new ArrayList<>(sequence);
                        sequence.clear();
                    }
                    resetItems();
                    nbr_match = 0;
                    reverse = !reverse;
                    if (reverse) {
                        start = pointer_message;
                    }else{
                        pointer_message = start;
                    }
                    /* Continue to keep pointer at right place. */
                    continue;
                }
                if (reverse) {
                    pointer_message--;
                } else {
                    pointer_message++;
                }


            }
            Iterator arrayIter = final_sequence.iterator();
            for(Iterator iter = arrayIter; iter.hasNext();){
                System.out.print(iter.next()+ " ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void resetItems() {
        for (Item item : keyword) {
            item.taken = false;
        }
    }

    public static class Item {
        public int item;
        public boolean taken;

        public Item(int value, boolean status) {
            this.item = value;
            this.taken = status;
        }

        public Item(int value) {
            this.item = value;
            this.taken = false;
        }

        @Override
        public boolean equals(Object that) {
            if (that == null) return false;
            if (!(that instanceof Item)) return false;
            Item thatItem = (Item) that;
            if (thatItem.taken != this.taken) return false;
            if (thatItem.item != this.item) return false;
            return true;
        }
    }

}
