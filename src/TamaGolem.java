import java.util.*;

public class TamaGolem {

    private String name;
    private final int max_pietre = TamaConstants.P;
    private Queue<Integer> pietre = new ArrayDeque<>();
    private int hp = TamaConstants.V;
    private boolean alive = true;

    /**
     * inizializza un nuovo  TamaGolem
     * @param name String contenente il nome da assegnare al nuovo tamagolem
     * @param pietre ArrayList contenente il numero corrispondente all'elemento delle pietre ingurgitate dal TamaGolem
     */
    public TamaGolem(String name, ArrayList<Integer> pietre){
        this.name = name;
        setPietre(pietre);
    }

    public TamaGolem(String name) {//todo temporaneo
    }
    public TamaGolem() {//todo temporaneo
    }

    public String getName() {
        return name;
    }

    /**
     * funzione che ogni turno lancia una pietra a rotazione
     * @return la pietra lanciata
     */
    public int throwPietre() {
        int to_be_thrown = pietre.peek();
        pietre.poll();
        pietre.offer(to_be_thrown);
        return to_be_thrown;
    }

    /**
     * Aggiunge le pietre passate nell'ArrayList alla Queue "pietre"
     * @param pietre ArrayList<Integer> contenente le pietre da assegnare al tamagolem
     */
    public void setPietre(ArrayList<Integer> pietre) {
            this.pietre.addAll(pietre);
    }

    /**
     * diminuisce i punti vita del tamagolem e controlla se il tamagolem è in grado di proseguire la lotta
     * @param hp l'ammontare di hp da sottrarre al tamagolem
     */
    public void setHp(int hp){
        this.hp -= hp;
        this.alive = this.hp > 0;
    }

    public int getHp(){
        return this.hp;
    }

    public boolean isAlive(){
        return this.alive;
    }

}
