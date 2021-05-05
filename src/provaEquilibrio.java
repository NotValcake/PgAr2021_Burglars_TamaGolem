
import java.util.ArrayList;
import java.util.Random;

public class provaEquilibrio {
    private final static int NUM_ELEMENTS = 4;
    private final static int MAX = 10;
    private final static int MIN = 1;

    public static void main(String[] args) {
        //int[][] eq = new int[NUM_ELEMENTS][NUM_ELEMENTS];
       ArrayList<ArrayList<Integer>> eq = new ArrayList<>();
        //setZeros(eq);

        //inizializzo array per riga
        for (int i = 0; i < NUM_ELEMENTS; i++) {
                eq.add(new ArrayList());
            }
//set mat con zeri
        for (int i = 0; i < NUM_ELEMENTS; i++) {
            for (int j = 0; j <NUM_ELEMENTS; j++) {
                eq.get(i).add(j,0);
            }
            System.out.printf("%n");
        }
  //stampa Arraylist
        System.out.printf("%n%n");
        for (int i = 0; i < NUM_ELEMENTS; i++) {
            for (int j = 0; j < eq.size(); j++) {
                System.out.printf("%2d", eq.get(i).get(j));
            }
            System.out.printf("%n");
        }


        setArray(eq);
        //System.out.println(eq);

        System.out.printf("%n%n");
      for (int i = 0; i < NUM_ELEMENTS; i++) {
            for (int j = 0; j < eq.size(); j++) {
                System.out.printf("%2d", eq.get(i).get(j));
            }
            System.out.printf("%n");
        }
    }

    //to set the zeros on diagonal
    public static void setZeros(ArrayList<ArrayList<Integer>> eq){
        int i;
        for (i = 0; i < NUM_ELEMENTS-1; i++) {
            eq.get(i).set(i, 0);
        }
    }

    /**crea valori in array random**/
    public static void  setArray(ArrayList<ArrayList<Integer>> eq){
        int i, j;
        int[] sommeR = new int[NUM_ELEMENTS];
        int[] sommeC = new int[NUM_ELEMENTS];
        //ctrl d

        for (i = 1; i < NUM_ELEMENTS -1; i++) {
            for (j = 0; j < i; j++) {
                if (randomRangeMaxMin(MAX, MIN) % 2 == 0) {
                    eq.get(i).set(j, randomRangeMaxMin(MAX, MIN));  //errore qui
                    eq.get(j).set(i, 0);
                } else {
                    eq.get(j).set(i, randomRangeMaxMin(MAX, MIN));
                    eq.get(i).set(j, 0);
                }
            }
        }
            for (int k = 0; k < sommeR.length; k++) {
                for (int l = 0; l < sommeR.length; l++) {
                    sommeR[k] += eq.get(l).get(k);
                    sommeC[k] += eq.get(k).get(l);
                }
            }

            for (j = 0; j < NUM_ELEMENTS -1; j++)
                if ( sommeR[j] > sommeC[j])
                    eq.get(j).set(NUM_ELEMENTS-1,sommeR[j] - sommeC[j]);
                else eq.get(NUM_ELEMENTS-1).set(j,sommeC[j] - sommeR[j]);
        }



    public static int randomRangeMaxMin(int max, int min){
        Random r = new Random();
        return r.nextInt((max - min ) + 1) + min ; //max and min inclusive
    }


   /* return tre if the cell contains 0
    public boolean checkIfZERO(int cell){
       if (cell == 0)
           return true;
        return false;
    }  */

}
