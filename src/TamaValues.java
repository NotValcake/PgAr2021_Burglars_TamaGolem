import java.util.Map;

public class TamaValues {

    /**
     * N = num elementi
     * P = numero Pietre
     * G= numero TamaGolem per partita
     * S = scorta comune pietre (set di pietre totali disponibili per entrambi i giocatori in tot)
     * V= vita TamaGolem
     **/

    public static int BEGINNER_L = 5;
    public static int INTERMEDIATE_L = 7;
    public static int DIFFICULT_L = 10;
    public static int N;
    public static int P;
    public static int G;
    public static int S;
    public static int PIETRE_PER_ELEMENTO;
    public static int V;


    public static void setValues(int scelta) {
        if (scelta == 0) {
            N = BEGINNER_L;
            V = 25;
        } else if (scelta == 1) {
            N = INTERMEDIATE_L;
            V = 50;
        } else if (scelta == 2) {
            N = DIFFICULT_L;
            V = 100;
        }

        P = (int) Math.ceil((double) (N + 1) / 3 + 1);
        G = (int) Math.ceil((double) (N - 1) * (N - 2) / (2 * P));
        S = (int) Math.ceil((double) (2 * G * P) / N) * N;
        PIETRE_PER_ELEMENTO = (int) Math.ceil((double) (2 * G * P) / N);
    }

    public static final Map<Integer, String> ELEMENTI = Map.ofEntries(Map.entry(0, "Fuoco"),
            Map.entry(1, "Acqua"),
            Map.entry(2, "Aria"),
            Map.entry(3, "Terra"),
            Map.entry(4, "Elettro"),
            Map.entry(5, "Erba"),
            Map.entry(6, "Veleno"),
            Map.entry(7, "Acciaio"),
            Map.entry(8, "Buio"),
            Map.entry(9, "Vino")
    );

}
