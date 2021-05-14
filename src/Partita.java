import java.util.ArrayList;

public class Partita {

    private Giocatore g1;
    private Giocatore g2;

    private int difficolta;

    private Equilibrio equilibrio;

    private ArrayList<Integer> scorta_pietre = new ArrayList<>();

    public Partita(Giocatore g1, Giocatore g2, int difficolta) {

        this.g1 = g1;
        this.g2 = g2;
        this.difficolta = difficolta;

        generaEquilibrio();
        generaScorta();
    }

    /**
     * difficolta = num livello (matrice)
     */
    private void generaScorta() {
        for (int i = 0; i < this.difficolta; i++) {
            this.scorta_pietre.add(TamaValues.PIETRE_PER_ELEMENTO);
        }
    }


    public ArrayList<Integer> getScorta() {
        return this.scorta_pietre;
    }


    private void generaEquilibrio() {
        this.equilibrio = new Equilibrio(this.difficolta);
    }

    public int testaOCroce() {
        return Equilibrio.randomRangeMaxMin(1, 0);
    }

    /**
     * assegna pietre al nuovo tamagolem che deve scendere in campo, ogni volta scende in campo il tamagolem ultimo nella fila
     *
     * @param g      giocatore che deve effettuare l'evocazione
     * @param pietre arraylist contenente le pietre da dare al tamagolem
     * @return true se l'evocazione va a buon fine, false altrimenti
     */
    public boolean evocazione(Giocatore g, ArrayList<Integer> pietre) {
        if (g.isAvailableTamaGolem()) {
            for (int i = 0; i < g.getTamaGolems().size(); i++) {
                if (g.getTamaGolems().get(i).isAlive()) {
                    g.getTamaGolems().get(i).setPietre(pietre);
                    g.setTamagolemInCampo(i);
                    for (int j = 0; j < pietre.size(); j++) {
                        scorta_pietre.set(pietre.get(j), scorta_pietre.get(pietre.get(j)) - 1);
                    }
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * metodo che gestisce un turno della lotta
     *
     * @return false se un tamagolem viene sconfitto
     */
    public boolean turno() {
        int p1 = g1.getTamagolemInCampo().throwPietre();
        int p2 = g2.getTamagolemInCampo().throwPietre();

        g2.getTamagolemInCampo().setHp(this.equilibrio.getEquilibrio(p1, p2));
        g1.getTamagolemInCampo().setHp(this.equilibrio.getEquilibrio(p2, p1));

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

    public Equilibrio rivelaEquilibrio() {
        return this.equilibrio;

    }

    public int getDifficolta() {
        return this.difficolta;

    }

    /**
     * gestisce il flusso di gioco di una singola partita
     */
    public void faiPartita() {
        ArrayList<Integer> pietre_1;
        ArrayList<Integer> pietre_2;

        //simula il lancio di una moneta per decidere chi sceglie per primo
        if (testaOCroce() == 0) {
            pietre_1 = UserInterface.getPietre(getScorta(), g1);
            evocazione(g1, pietre_1);
            pietre_2 = UserInterface.getPietre(getScorta(), g2);
            //se i giocatori scelgono le stesse pietre nello stesso ordine loop infinito
            while (pietre_1.equals(pietre_2)) {
                UserInterface.stessePietre();
                pietre_2 = UserInterface.getPietre(getScorta(), g2);
            }
            evocazione(g2, pietre_2);
        } else {
            pietre_2 = UserInterface.getPietre(getScorta(), g2);
            evocazione(g2, pietre_2);
            pietre_1 = UserInterface.getPietre(getScorta(), g1);
            evocazione(g1, pietre_1);
            while (pietre_2.equals(pietre_1)) {
                UserInterface.stessePietre();
                pietre_1 = UserInterface.getPietre(getScorta(), g1);
            }
        }

        int turno = 1;

        while (checkifPartitaContinua() == 0) {
            while (g1.getTamagolemInCampo().isAlive() && g2.getTamagolemInCampo().isAlive()) {
                int hp1 = g1.getTamagolemInCampo().getHp();
                int hp2 = g2.getTamagolemInCampo().getHp();

                UserInterface.annuncioTurno(turno);
                turno++;
                turno();

                if (hp1 != g1.getTamagolemInCampo().getHp()) {
                    UserInterface.annuncioDanni(g2.getTamagolemInCampo(), g1.getTamagolemInCampo(), hp1 - g1.getTamagolemInCampo().getHp());
                } else if (hp2 != g2.getTamagolemInCampo().getHp()) {
                    UserInterface.annuncioDanni(g1.getTamagolemInCampo(), g2.getTamagolemInCampo(), hp2 - g2.getTamagolemInCampo().getHp());
                } else UserInterface.annuncioDanni(g1.getTamagolemInCampo(), g2.getTamagolemInCampo(), 0);
                UserInterface.nextTurn();
            }

            if (!g1.getTamagolemInCampo().isAlive() && g1.isAvailableTamaGolem()) {
                if (canSummon(g1, g2)) break;
            } else if (!g2.getTamagolemInCampo().isAlive() && g2.isAvailableTamaGolem()) {
                if (canSummon(g2, g1)) break;
            }
        }
        if (checkifPartitaContinua() == -1) {
            UserInterface.partitaFinita(g2, g1);
            g2.setScore();
        } else {
            UserInterface.partitaFinita(g1, g2);
            g1.setScore();
        }
    }

    /**
     * Controlla che un giocatore non assegni al proprio tamagolem le stesse pietre dell'avversario, nello stesso ordine
     * @param g1 giocatore che deve effettuare l'evocazione
     * @param g2 altro giocatore
     * @return true se l'evocazione è possibile
     */
    private boolean canSummon(Giocatore g1, Giocatore g2) {
        ArrayList<Integer> pietre_1;
        UserInterface.annuncioEliminato(g1.getTamagolemInCampo());
        pietre_1 = UserInterface.getPietre(getScorta(), g1);
        while (g2.getTamagolemInCampo().getPietre().equals(pietre_1)) {
            UserInterface.stessePietre();
            pietre_1 = UserInterface.getPietre(getScorta(), g1);
        }
        return !evocazione(g1, pietre_1);
    }


}
