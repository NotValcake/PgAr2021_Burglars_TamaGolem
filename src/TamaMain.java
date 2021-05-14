import java.util.ArrayList;

public class TamaMain {
    public static void main(String[] args) {
        UserInterface.startMatch();
        String g1_name = UserInterface.getPlayerName(1);
        String g2_name = UserInterface.getPlayerName(2);

        ArrayList<TamaGolem> t1 = new ArrayList<>();
        ArrayList<TamaGolem> t2 = new ArrayList<>();

        do {
            int level = UserInterface.sceglidifficolta(); //per selezionare livello da utente
            TamaValues.setValues(level);//per fissare livello selezionato per partita

            while (t1.size() < TamaValues.G) {
                String tama_name = UserInterface.getTamaName(g1_name);
                TamaGolem t = new TamaGolem(tama_name);
                t1.add(t);
            }

            while (t2.size() < TamaValues.G) {
                String tama_name = UserInterface.getTamaName(g2_name);
                TamaGolem t = new TamaGolem(tama_name);
                t2.add(t);
            }

            Giocatore g1 = new Giocatore(t1, g1_name);
            Giocatore g2 = new Giocatore(t2, g2_name);

            Partita p = new Partita(g1, g2, TamaValues.N);
            p.faiPartita();

            UserInterface.rivelaEquilibrio(p.rivelaEquilibrio());

        } while (UserInterface.nuovaPartita() == 1);

        System.out.println("100 punti? :) su 100");
    }
}