import java.util.ArrayList;
import java.util.Stack;

public class Giocatore {

    private int tamagolem_in_campo;
    private ArrayList<TamaGolem> tamaGolems;
    private String nome;

    public Giocatore(ArrayList<TamaGolem> tamaGolems, String nome) {
        this.tamaGolems = tamaGolems;
        this.nome = nome;
    }

    /**rimuove dall'ArrayList i TamaGolems eliminati
     * @return true se almeno un TamaGolem in ArrayList tamaGolems del giocatore è vivo
     **/
    public boolean isAvailableTamaGolem() {
        for (int i = tamaGolems.size()-1; i >= 0; i--) {
            if(!tamaGolems.get(i).isAlive()){
                tamaGolems.remove(i);
            }else return true;
        }
        return false;
    }

    public void setTamagolemInCampo(int tg){
        this.tamagolem_in_campo = tg;
    }

    public int getTamagolemInCampoIndex(){
        return this.tamagolem_in_campo;
    }

    public TamaGolem getTamagolemInCampo(){
        return this.tamaGolems.get(tamagolem_in_campo);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {   //chiesto all'utente
        this.nome = nome;
    }

    public void setTamaGolems(ArrayList<TamaGolem> tamaGolems) {
        this.tamaGolems = tamaGolems;
    }

    public ArrayList<TamaGolem> getTamaGolems() {
        return tamaGolems;
    }

}
