import java.lang.reflect.Array;
import java.util.ArrayList;

public class TamaMain {
    public static void main(String[] args) {
        UserInterface.startMatch();
//FIXME facciamo prima input info di un giocatore poi il seconda in metodo così da non ripetere le istruzioni?
//TODO NB puoi avere la lista dei TODO e FIXME cliccando su TODO in basso a sinistra per non perderne nessuno (accanto a Git Run Terminal ecc)
        String g1_name = UserInterface.getPlayerName(1);
        String g2_name = UserInterface.getPlayerName(2);

        ArrayList<TamaGolem> t1 = new ArrayList<>();
        ArrayList<TamaGolem> t2 = new ArrayList<>();

        while(t1.size() < TamaConstants.G){
            String tama_name = UserInterface.getTamaName(g1_name);
            TamaGolem t = new TamaGolem(tama_name);
            t1.add(t);
        }

        while(t2.size() < TamaConstants.G){
            String tama_name = UserInterface.getTamaName(g2_name);
            TamaGolem t = new TamaGolem(tama_name);
            t2.add(t);
        }

        Giocatore g1 = new Giocatore(t1, g1_name);
        Giocatore g2 = new Giocatore(t2, g2_name);

        Partita p = new Partita(g1, g2, TamaConstants.BEGINNER_L);

        ArrayList<Integer> pietre_1 = UserInterface.getPietre(p.getScorta(), g1); //pietre_1 contiene pietre scelte dal gioc.
        ArrayList<Integer> pietre_2 = UserInterface.getPietre(p.getScorta(), g2);

        p.evocazione(g1, pietre_1);  //ctrl che tamagolem sia vivo, e setPietre(pietre_1) SCELTE
        p.aggiornaScortaP(pietre_1);//per togliere le pietre usate e aggiornare la scorta comune per elemento
        p.evocazione(g2, pietre_2);
        p.aggiornaScortaP(pietre_2);
 //  TODO add scorta per elemento avrà <= 0 pietre se finita, in userinterface while( valorescortaPietre <= 0 allora stampa successiva)
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
