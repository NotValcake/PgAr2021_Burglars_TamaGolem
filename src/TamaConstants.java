public class TamaConstants {


    /** N = num elementi
     * P = numero Pietre
     * G= numero TamaGolem per partita
     * S = set di pietre totali
     * V= vita TamaGolem
     **/
    public static int N = 5;
    public static int P = (int) Math.ceil((double) (N+1)/3 + 1);
    public static int G = (int) Math.ceil((double) (N-1)*(N-2)/(2*P));
    public static int S = (int) Math.ceil((double) (2*G*P)/N) * N;
    public static int V = 10;

}
