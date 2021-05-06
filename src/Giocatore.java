import java.util.ArrayList;
//TODO teniamo solo checkTamaGolems e cancelliamo l'attributo TamaGolemVivi con il suo getter? che fa stessa cosa di checkTamaGolems
public class Giocatore {
    private ArrayList <TamaGolem> tamaGolems = new ArrayList<TamaGolem>();
    private String nome;
    private boolean TamaGolemsVivi = checkTamaGolems();

    public Giocatore(ArrayList<TamaGolem> tamaGolems, String nome) {
        tamaGolems = tamaGolems;
        this.nome = nome;
    }
    /**
     * @return true se almeno un TamaGolem in ArrayList tamaGolems del giocatore è vivo
     * **/
    public boolean checkTamaGolems(){
        for (int i = 0; i < tamaGolems.size(); i++) {
            if (tamaGolems.get(i).isAlive())
                return true;
        }
        return false;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTamaGolems(ArrayList<TamaGolem> tamaGolems) {
        this.tamaGolems = tamaGolems;
    }

    public ArrayList<TamaGolem> getTamaGolems() {
        return tamaGolems;
    }

    public boolean isTamaGolemsVivi() {
        return TamaGolemsVivi;
    }

}
