import java.util.ArrayList;
import java.util.Queue;

public class TamaGolem {

    private String name;
    private final int max_pietre = TamaConstants.P;
    private Queue<Integer> pietre;
    private int hp = TamaConstants.V;
    private boolean alive = true;

    public TamaGolem(String name, ArrayList<Integer> pietre){
        this.name = name;
        setPietre(pietre);
    }

    public String getName() {
        return name;
    }

    public int throwPietre() {
        int to_be_thrown = pietre.peek();
        pietre.poll();
        pietre.offer(to_be_thrown);
        return to_be_thrown;
    }

    public void setPietre(ArrayList<Integer> pietre) {
        for (Integer p: pietre) {
            this.pietre.offer(p);
        }
    }

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
