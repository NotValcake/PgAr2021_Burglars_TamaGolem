import java.util.ArrayList;

public class TamaMain {
    //TODO creare classe di interazione per l'utente, altrimenti è impossibile verificare che funzioni correttamente
    public static void main(String[] args) {

        ArrayList<Integer> pietre1 = new ArrayList<>();
        pietre1.add(3);
        pietre1.add(2);
        pietre1.add(4);

        ArrayList<Integer> pietre2 = new ArrayList<>();
        pietre2.add(2);
        pietre2.add(1);
        pietre2.add(3);

        ArrayList<TamaGolem> tg1 = new ArrayList<>();
        tg1.add(new TamaGolem("tama1"));
        Giocatore g1 = new Giocatore(tg1, "io");

        ArrayList<TamaGolem> tg2 = new ArrayList<>();
        tg1.add(new TamaGolem("tama2"));
        Giocatore g2 = new Giocatore(tg2, "tu");

        Partita partita = new Partita(g1, g2, TamaConstants.BEGINNER_L);

        Equilibrio eq = partita.rivelaEquilibrio();

        for (int i = 0; i < partita.getDifficolta(); i++) {
            for (int j = 0; j < partita.getDifficolta(); j++) {
                System.out.print(String.format("%3d", eq.getEquilibrio(i, j)));
            }
            System.out.println();
        }

        if(partita.testaOCroce() == 0){
            partita.evocazione(g1, pietre1);
            partita.evocazione(g2, pietre2);
        }else{
            partita.evocazione(g2, pietre2);
            partita.evocazione(g1, pietre1);
        }

        int t = 0;

        while (partita.checkifPartitaContinua() == 0){
            while(partita.turno()){
                System.out.println("turno " + t++);
            }
            if(g1.isAvailableTamaGolem() && g2.isAvailableTamaGolem()) {
                if (g1.getTamagolemInCampo().isAlive()) {
                    partita.evocazione(g2, pietre1);
                } else partita.evocazione(g1, pietre2);
            }
        }

        for (int i = 0; i < partita.getDifficolta(); i++) {
            for (int j = 0; j < partita.getDifficolta(); j++) {
                System.out.print(String.format("%3d", eq.getEquilibrio(i, j)));
            }
            System.out.println();
        }

        System.out.println(partita.checkifPartitaContinua());

    }

}
