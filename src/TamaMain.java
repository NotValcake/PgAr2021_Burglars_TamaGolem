import java.util.ArrayList;



/*TODO eliminiamo tipi enum? ce hashmap
* TODO richiesta info e new di un giocatore tutto in un metodo cosi lo richiamo due volte
* FIXME error in flusso gioco dopo primo turno, al momento di selezionare secondo tamagolem
*
* */
public class TamaMain {
    public static void main(String[] args) {
        UserInterface.startMatch();
        String g1_name = UserInterface.getPlayerName(1);
        String g2_name = UserInterface.getPlayerName(2);

        ArrayList<TamaGolem> t1 = new ArrayList<>();
        ArrayList<TamaGolem> t2 = new ArrayList<>();

        int level = UserInterface.sceglidifficolta(); //per selezionare livello da utente
        TamaValues.setN(level);//per fissare livello selezionato per partita

        while(t1.size() < TamaValues.G){
            String tama_name = UserInterface.getTamaName(g1_name);
            TamaGolem t = new TamaGolem(tama_name);
            t1.add(t);
        }

        while(t2.size() < TamaValues.G){
            String tama_name = UserInterface.getTamaName(g2_name);
            TamaGolem t = new TamaGolem(tama_name);
            t2.add(t);
        }

        Giocatore g1 = new Giocatore(t1, g1_name);
        Giocatore g2 = new Giocatore(t2, g2_name);

        Partita p = new Partita(g1, g2, TamaValues.N);

        ArrayList<Integer> pietre_1;
        ArrayList<Integer> pietre_2;
        if (p.testaOCroce() == 0){
            pietre_1 = UserInterface.getPietre(p.getScorta(), g1);
            p.evocazione(g1, pietre_1);
            pietre_2= UserInterface.getPietre(p.getScorta(), g2);
            p.evocazione(g2, pietre_2);
        }
        else {
            pietre_2= UserInterface.getPietre(p.getScorta(), g2);
            p.evocazione(g2, pietre_2);
            pietre_1 = UserInterface.getPietre(p.getScorta(), g1);
            p.evocazione(g1, pietre_1);
        }
        int turno = 1;

        while(p.checkifPartitaContinua() == 0){
            while(g1.getTamagolemInCampo().isAlive() && g2.getTamagolemInCampo().isAlive()){
                int hp1 = g1.getTamagolemInCampo().getHp();
                int hp2 = g2.getTamagolemInCampo().getHp();

                UserInterface.annuncioTurno(turno);
                turno++;
                p.turno();

                if(hp1 != g1.getTamagolemInCampo().getHp()){
                    UserInterface.annuncioDanni(g2.getTamagolemInCampo(), g1.getTamagolemInCampo(), hp1-g1.getTamagolemInCampo().getHp());
                }else if(hp2 != g2.getTamagolemInCampo().getHp()){
                    UserInterface.annuncioDanni(g1.getTamagolemInCampo(), g2.getTamagolemInCampo(), hp2-g2.getTamagolemInCampo().getHp());
                }else UserInterface.annuncioDanni(g1.getTamagolemInCampo(), g2.getTamagolemInCampo(), 0);
            }
            if(!g1.getTamagolemInCampo().isAlive() && g1.isAvailableTamaGolem()){
                UserInterface.annuncioEliminato(g1.getTamagolemInCampo());
                pietre_1 = UserInterface.getPietre(p.getScorta(), g1);
                if(p.evocazione(g1, pietre_1)){
                    break;
                }
            }else if(!g2.getTamagolemInCampo().isAlive() && g2.isAvailableTamaGolem()){
                UserInterface.annuncioEliminato(g2.getTamagolemInCampo());
                pietre_2 = UserInterface.getPietre(p.getScorta(), g2);
                if(!p.evocazione(g2, pietre_2)){
                    break;
                }
            }
        }

        UserInterface.rivelaEquilibrio(p.rivelaEquilibrio());

        if(p.checkifPartitaContinua() == -1){
            UserInterface.partitaFinita(g2, g1);
        }else{
            UserInterface.partitaFinita(g1, g2);
        }

    }



}
