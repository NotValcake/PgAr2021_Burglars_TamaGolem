import java.util.ArrayList;

public class Giocatore {

    private ArrayList<TamaGolem> tamaGolems;
    private String nome;

    public Giocatore(ArrayList<TamaGolem> tamaGolems, String nome) {
        this.tamaGolems = tamaGolems;
        this.nome = nome;
    }

    /**
     * @return true se almeno un TamaGolem in ArrayList tamaGolems del giocatore è vivo
     **/
    public boolean isAvailableTamaGolem() {
        for (TamaGolem tg : tamaGolems) {
            if (tg.isAlive())
                return true;
        }
        return false;
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
