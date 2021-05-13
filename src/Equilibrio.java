
import java.util.Random;

public class Equilibrio {

    private final int MAX = TamaValues.V;
    private final int MIN = 1;

    private Graph equilibrio;

    public Equilibrio(int elements_num) {
        this.equilibrio = new Graph(elements_num);
        setEquilibrio();
    }
    //TODO modificare algoritmo in modo che sia rispettata la condizione sup(W)<= V

    /**
     * crea valori in array random
     **/
    private void setEquilibrio() {
        int[] sommeR = new int[equilibrio.getVertexNum()];
        int[] sommeC = new int[equilibrio.getVertexNum()];

        for (int i = 1; i < equilibrio.getVertexNum() - 1; i++) {
            for (int j = 0; j < i; j++) {
               inserisciCoppia(i,j);
            }
        }

        for (int k = 0; k < sommeR.length - 1; k++) {
            do{
                if (sommeR[k] == sommeC[k])
                    inserisciCoppia(k , equilibrio.getVertexNum() - 2);
                for (int l = 0; l < sommeR.length - 1; l++) {
                  sommeR[k] += equilibrio.getVertex(l).get(k);
                  sommeC[k] += equilibrio.getVertex(k).get(l);
                }
            }while  (sommeR[k] == sommeC[k]);

        }

        for (int j = 0; j < equilibrio.getVertexNum() - 1; j++) {
            if (sommeR[j] > sommeC[j])
                equilibrio.getVertex(j).set(equilibrio.getVertexNum() - 1, sommeR[j] - sommeC[j]);
            else equilibrio.getVertex(equilibrio.getVertexNum() - 1).set(j, sommeC[j] - sommeR[j]);
        }
    }

    private void inserisciCoppia(int i, int j){
        if (randomRangeMaxMin(MAX, MIN) % 2 == 0) {
                equilibrio.getVertex(i).set(j, randomRangeMaxMin(MAX, MIN));
                equilibrio.getVertex(j).set(i, 0);
            }
        else {
                equilibrio.getVertex(j).set(i, randomRangeMaxMin(MAX, MIN));
                equilibrio.getVertex(i).set(j, 0);
        }
    }






    public static int randomRangeMaxMin(int max, int min) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min; //max and min inclusive
    }

    public int getEquilibrio(int from, int to) {
        return equilibrio.getEdge(from, to);
    }

    public int getSize() {
        return equilibrio.getVertexNum();
    }




    /*
     if (randomRangeMaxMin(MAX, MIN) % 2 == 0) {
                    do {
                        equilibrio.getVertex(i).set(j, randomRangeMaxMin(MAX, MIN));
                        equilibrio.getVertex(j).set(i, 0);
                    } while (prev == equilibrio.getVertex(i).get(j));
                    prev = equilibrio.getVertex(i).get(j);
                } else {
                    do {
                        equilibrio.getVertex(j).set(i, randomRangeMaxMin(MAX, MIN));
                        equilibrio.getVertex(i).set(j, 0);
                    } while (prev == equilibrio.getVertex(j).get(i));
                    prev = equilibrio.getVertex(j).get(i);
                }
                    */
}
