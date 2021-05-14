import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import it.unibs.fp.mylib.*;


public class UserInterface {

    public static void startMatch() {
        System.out.println("Benvenuti, allievi! Siete pronti per cominciare un nuovo scontro? \nInserite i vostri nomi.\n");
    }

    public static int sceglidifficolta() {
        System.out.printf("Scegliere il livello di difficoltà tra i tre disponibili(0, 1, 2):" +
                "%n- 0) livello facile(%d elementi disponibili)" +
                "%n- 1) livello intermedio(%d elementi disponibili)" +
                "%n- 2) livello PRO(%d elementi disponibili)%n", TamaValues.BEGINNER_L, TamaValues.INTERMEDIATE_L, TamaValues.DIFFICULT_L);
        int scelta = controllaScelta(3);
        return scelta;
    }

    public static String getPlayerName(int p) {
        String name = InputDati.leggiStringaNonVuota("Inserisci il nome del Giocatore " + p + ": ");
        return name.strip();
    }

    public static String getTamaName(String g) {
        String name = InputDati.leggiStringaNonVuota(g + " Inserisci il nome del tamagolem : ");
        return name.strip();
    }

    public static ArrayList<Integer> getPietre(ArrayList<Integer> scorta, Giocatore g) throws IOException {
        ArrayList<Integer> temp = new ArrayList<>();
        temp.addAll(scorta);//inefficiente si, ma permette di mantenere separata UI da logica del programma

        System.out.printf("%s seleziona tre pietre da dare al tuo tamagolem tra quelle disponibili nella scorta:%n  (Seleziona il numero indice!) %n", g.getNome());
        for (int i = 0; i < scorta.size(); i++) {
            System.out.printf("%2d) %s: %d%n", i, TamaValues.ELEMENTI.get(i), scorta.get(i));
        }

        ArrayList<Integer> scelte = new ArrayList<>();
        while (scelte.size() < TamaValues.P) {
            int scelta = controllaSceltaSegreta(TamaValues.N);
            while (temp.get(scelta) <= 0) {
                System.out.println("Non ci sono pietre disponibili dell'elemento scelto! Scegli altre pietre...");
                scelta = controllaSceltaSegreta(TamaValues.N);
            }

            scelte.add(scelta);
            temp.set(scelta, temp.get(scelta) - 1);
        }

        return scelte;
    }


    /**
     * chiede in input scelta utente tra gli indici disponibili
     * e controlla se la scelta è valida in base alle opzioni disponibili
     *
     * @return scelta dell'utente valida
     */
    private static int controllaSceltaSegreta(int maxNotInclusve) throws IOException {
        //Console in = System.console();
        int scelta = Integer.parseInt(readPwd());
        while (scelta < 0 || scelta >= maxNotInclusve) {
            scelta = Integer.parseInt(readPwd());
        }
        return scelta;
    }

    private static String readPwd() throws IOException {
        Console c=System.console();
        if (c==null) { //IN ECLIPSE IDE
            System.out.print("Password: ");
            InputStream in=System.in;
            int max=50;
            byte[] b=new byte[max];

            int l= in.read(b);
            l--;//last character is \n
            if (l>0) {
                byte[] e=new byte[l];
                System.arraycopy(b,0, e, 0, l);
                return new String(e);
            } else {
                return null;
            }
        } else { //Outside Eclipse IDE
            return new String(c.readPassword("Password: "));
        }
    }

    /**
     * chiede in input scelta utente tra gli indici disponibili
     * e controlla se la scelta è valida in base alle opzioni disponibili
     *
     * @return scelta dell'utente valida
     */
    private static int controllaScelta(int maxNotInclusve) {

        int scelta = InputDati.leggiInteroNonNegativo("Inserire l'indice: ");
        while (scelta < 0 || scelta >= maxNotInclusve) {
            scelta = InputDati.leggiInteroNonNegativo("Scegliere un valore valido!\n");
        }
        return scelta;
    }


    public static void annuncioTurno(int turno) {
        System.out.printf("Ha inizio il %d turno%n", turno);
    }

    public static void annuncioDanni(TamaGolem vincente, TamaGolem perdente, int danni) {
        if (danni != 0) {
            System.out.printf("%1$s ha avuto la meglio! %2$s ha subito %3$d danni! %nPunti Vita rimasti:%n%1$s: %4$d %n%2$s: %5$d %n", vincente.getName(), perdente.getName(), danni, vincente.getHp(), perdente.getHp() < 0 ? 0 : perdente.getHp());
        } else System.out.println("Nessun tamagolem ha subito danni in questo turno!");
    }

    public static void annuncioEliminato(TamaGolem eliminato) {
        System.out.printf("%s non può più continuare lo scontro%n", eliminato.getName());
    }

    public static void partitaFinita(Giocatore vincitore, Giocatore sconfitto) {
        System.out.printf("%nSembra che %s non abbia più tamagolem! %s vince lo scontro!", sconfitto.getNome(), vincitore.getNome());
    }

    public static void rivelaEquilibrio(Equilibrio e) {
        System.out.printf("%nL'equilibrio del mondo è...%n");
        System.out.printf("%8s", "");
        for (int i = 0; i < e.getSize(); i++) {
            System.out.printf("%8s", TamaValues.ELEMENTI.get(i));
        }
        System.out.println();
        for (int i = 0; i < e.getSize(); i++) {
            System.out.printf("%8s", TamaValues.ELEMENTI.get(i));
            for (int j = 0; j < e.getSize(); j++) {
                System.out.printf("%8d", e.getEquilibrio(i, j));
            }
            System.out.println();
        }
    }

    /**
     * Scelta utente per cominciare una nuova partita
     */
    public static int nuovaPartita() {
        System.out.println("Premi 1 per fare un'altra partita oppure 0 per uscire!");
        return controllaScelta(2);
    }

    public static void stessePietre() {
        System.out.println("Ehy! non puoi copiare la strategia del tuo avversario!");
    }

    public static void nextTurn() {
        InputDati.leggiStringa("\nPremi invio per proseguire lo scontro!");
    }

    public static void printScore(Giocatore g1, Giocatore g2) {
        System.out.printf("%nPunteggi:%n%s: %d%n%s: %d", g1.getNome(), g1.getScore(), g2.getNome(), g2.getScore());
    }
}

