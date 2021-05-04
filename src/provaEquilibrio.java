
import java.util.Random;

public class provaEquilibrio {
    private final static int NUM_ELEMENTS = 4;
    private final static int MAX = 10;
    private final static int MIN = 1;

    public static void main(String[] args) {
        int[][] eq = new int[NUM_ELEMENTS][NUM_ELEMENTS];
        setZeros(eq);
        setArray(eq);
        //System.out.println(eq);
        for (int i = 0; i < eq.length; i++) {
            for (int j = 0; j < eq.length; j++) {
                System.out.printf("%2d  ", eq[i][j]);
            }
            System.out.printf("%n");
        }
    }

    //to set the zeros on diagonal
    public static void setZeros(int[][] eq){
        int i;
        for (i = 0; i < eq.length; i++) {
            eq[i][i] = 0;
        }
    }

    /**crea valori in array random**/
    public static void  setArray(int[][] eq){
        int i, j;
        int[] sommeR = new int[NUM_ELEMENTS];
        int[] sommeC = new int[NUM_ELEMENTS];
        //ctrl D

        for (i = 1; i < eq.length -1; i++) {
            for (j = 0; j < i; j++) {
                if (randomRangeMaxMin(MAX, MIN) % 2 == 0) {
                    eq[i][j] = randomRangeMaxMin(MAX, MIN);
                    eq[j][i] = 0;
                } else {
                    eq[j][i] = randomRangeMaxMin(MAX, MIN);
                    eq[i][j] = 0;

                }
            }

            for (int k = 0; k < sommeR.length; k++) {
                for (int l = 0; l < sommeR.length; l++) {
                    sommeR[k] += eq[l][k];
                    sommeC[k] += eq[k][l];
                }
            }

            for (j = 0; j < eq[0].length -1; j++)
                if ( sommeR[j] > sommeC[j])
                    eq[j][NUM_ELEMENTS-1] =  sommeR[j] - sommeC[j];
                else eq[NUM_ELEMENTS-1][j] =  sommeC[j] - sommeR[j];
        }
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
