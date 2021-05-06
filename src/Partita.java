import java.util.ArrayList;//prova

public class Partita {

    private Giocatore g1;
    private Giocatore g2;

    private int difficolta;

    private Equilibrio equilibrio;

    private ArrayList<Integer> scorta_pietre = new ArrayList<>();

    private Giocatore vincitore;

    private ArrayList<String> ElementiG1 = new ArrayList<>();  //prendi arraylist di enum scelti
    private ArrayList<String> ElementiG2 = new ArrayList<>();

    public Partita(Giocatore g1, Giocatore g2, int difficolta) {

        this.g1 = g1;
        this.g2 = g2;
        this.difficolta = difficolta;

        generaEquilibrio();
        generaScorta();
    }

    public void generaScorta() {
        for (int i = 0; i < this.difficolta; i++) {
            this.scorta_pietre.add(TamaConstants.PIETRE_PER_ELEMENTO);
        }
    }

    public void generaEquilibrio() {
        this.equilibrio = new Equilibrio(this.difficolta);
    }

    public int testaOCroce(){
        return Equilibrio.randomRangeMaxMin(1,0);
    }

    /**
     * assegna pietre al nuovo tamagolem che deve scendere in campo, ogni volta scende in campo il tamagolem ultimo nella fila
     * @param g giocatore che deve effettuare l'evocazione
     * @param pietre arraylist contenente le pietre da dare al tamagolem
     * @return true se l'evocazione va a buon fine, false altrimenti
     */
    public boolean evocazione(Giocatore g, ArrayList<Integer> pietre){
        if(g.isAvailableTamaGolem()) {
            g.getTamaGolems().get(g.getTamaGolems().size() - 1).setPietre(pietre);
            g.setTamagolemInCampo(g.getTamaGolems().size() - 1);
            return true;
        }
        return false;
    }

    /**
     * metodo che gestisce un turno della lotta
     * @return false se un tamagolem viene sconfitto
     */
    public boolean turno() {
        int p1 = g1.getTamagolemInCampo().throwPietre();
        int p2 = g2.getTamagolemInCampo().throwPietre();

        if(this.equilibrio.getEquilibrio(p1, p2) != 0){
            g2.getTamagolemInCampo().setHp(this.equilibrio.getEquilibrio(p1, p2));
        }else{
            g1.getTamagolemInCampo().setHp(this.equilibrio.getEquilibrio(p2, p1));
        }
        return g1.getTamagolemInCampo().isAlive() && g2.getTamagolemInCampo().isAlive();
    }

    /**
     * metodo che controlla se i giocatori hanno ancora TamaGolems disponibili
     *
     * @return 1 se vince g1, -1 se vince g2, 0 se la partita continua
     */
    public int checkifPartitaContinua() {
        if (!g1.isAvailableTamaGolem())
            return 1;
        if (!g2.isAvailableTamaGolem())
            return -1;
        return 0;
    }

    public Equilibrio rivelaEquilibrio(){
        return this.equilibrio;
    }

    public int getDifficolta(){
        return this.difficolta;
    }

    //TODO fare in modo che per scegliere le pietre si attinga dalla scorta

}
