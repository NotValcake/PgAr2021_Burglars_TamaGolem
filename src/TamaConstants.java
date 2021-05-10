import java.util.HashMap;
import java.util.Map;

public class TamaConstants {


    /** N = num elementi
     *  P = numero Pietre
     *  G= numero TamaGolem per partita
     *  S = set di pietre totali
     *  V= vita TamaGolem
     **/

    public static int BEGINNER_L = 5 ;
    public static int INTERMEDIATE_L = 7 ;
    public static int DIFFICULT_L = 10 ;

    public static int N = 5 ;
    public static int P = (int) Math.ceil((double) (N+1)/3 + 1);
    public static int G = (int) Math.ceil((double) (N-1)*(N-2)/(2*P));
    public static int S = (int) Math.ceil((double) (2*G*P)/N) * N;
    public static int PIETRE_PER_ELEMENTO = (int) Math.ceil((double) (2*G*P)/N);
    public static int V = 10;

    public static final Map<Integer, String> ELEMENTI = Map.ofEntries( Map.entry(0, "Fuoco"),
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
