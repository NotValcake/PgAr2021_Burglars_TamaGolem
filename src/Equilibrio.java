
import java.util.Random;

public class Equilibrio {

    private final int MAX = (TamaValues.V / TamaValues.N - 2);
    private final int MIN = 1;

    private Graph equilibrio;

    public Equilibrio(int elements_num) {
        this.equilibrio = new Graph(elements_num);
        setEquilibrio();
    }

    /**
     * crea valori in array random
     **/
    private void setEquilibrio() {
        int[] sommeR = new int[equilibrio.getVertexNum()];
        int[] sommeC = new int[equilibrio.getVertexNum()];

        for (int i = 1; i < equilibrio.getVertexNum() - 1; i++) {
            for (int j = 0; j < i; j++) {
                inserisciCoppia(i, j);
            }
        }

        for (int k = 0; k < sommeR.length - 1; k++) {
            //faccio la somma di ogni riga e colonna
            for (int l = 0; l < sommeR.length - 1; l++) {
                sommeR[k] += equilibrio.getVertex(l).get(k);
                sommeC[k] += equilibrio.getVertex(k).get(l);
            }
            //ogni volta controllo che la somma di righe e colonne sia diversa
            while (sommeC[k] == sommeR[k]) {
                //se somme di righe e colonne sono uguali prima di sostituire i valori li rimuovo dalle somme
                sommeR[k] -= equilibrio.getVertex(equilibrio.getVertexNum() - 2).get(k);
                sommeC[k] -= equilibrio.getVertex(k).get(equilibrio.getVertexNum() - 2);
                //quindi sostituisco i valori
                inserisciCoppia(k, equilibrio.getVertexNum() - 2);
                //riaggiorno le somme di righe e colonne
                sommeR[k] += equilibrio.getVertex(equilibrio.getVertexNum() - 2).get(k);
                sommeC[k] += equilibrio.getVertex(k).get(equilibrio.getVertexNum() - 2);
            }
        }

        for (int j = 0; j < equilibrio.getVertexNum() - 1; j++) {
            if (sommeR[j] > sommeC[j])
                equilibrio.getVertex(j).set(equilibrio.getVertexNum() - 1, sommeR[j] - sommeC[j]);
            else equilibrio.getVertex(equilibrio.getVertexNum() - 1).set(j, sommeC[j] - sommeR[j]);
        }
    }


    private void inserisciCoppia(int i, int j) {
        if (randomRangeMaxMin(MAX, MIN) % 2 == 0) {
            equilibrio.getVertex(i).set(j, randomRangeMaxMin(MAX, MIN));
            equilibrio.getVertex(j).set(i, 0);
        } else {
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

}
