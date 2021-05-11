import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {

    public static void startMatch(){
        System.out.println("Benvenuti, allievi! Siete pronti per cominciare un nuovo scontro? \nInserite i vostri nomi.\n");
    }

    public static Scanner scannerInit(){
        return new Scanner(System.in);
    }

    public static  String getPlayerName(int p){
        Scanner in = scannerInit();
        System.out.printf("Inserisci il nome del giocatore %d: ", p);
        String name = in.nextLine();
        return name.strip();
    }

    public static  String getTamaName(String g){
        Scanner in = scannerInit();
        System.out.printf("%s inserisci il nome dei tuoi tamagolems: ", g);
        String name = in.nextLine();
        return name.strip();
    }

    public static ArrayList<Integer> getPietre(ArrayList<Integer> scorta, Giocatore g){
        ArrayList <Integer> temp = new ArrayList<>();
        temp.addAll(scorta);//inefficiente si, ma permette di mantenere separata UI da logica del programma
        Scanner in = scannerInit();
        System.out.printf("%s seleziona tre pietre da dare al tuo tamagolem tra quelle disponibili nella scorta: %n", g.getNome());
        for (int i = 0; i < scorta.size(); i++) {
            System.out.printf("%2d) %s: %d%n", i, TamaConstants.ELEMENTI.get(i),scorta.get(i));
        }

        ArrayList<Integer> scelte = new ArrayList<>();

        while(scelte.size() < TamaConstants.P){
            int scelta = in.nextInt();
            while(temp.get(scelta) <= 0) {
                System.out.println("Non ci sono pietre disponibili dell'elemento scelto! Scegli altre pietre...");
                scelta = in.nextInt();
            }
            scelte.add(scelta);
            temp.set(scelta, temp.get(scelta)-1);
        }

        return scelte;
    }

    public static void annuncioTurno(int turno){
        System.out.printf("Ha inizio il %d turno%n", turno);
    }

    public static void annuncioDanni(TamaGolem vincente, TamaGolem perdente, int danni){
        if(danni != 0) {
            System.out.printf("%1$s ha avuto la meglio! %2$s ha subito %3$d danni! %nPunti Vita rimasti:%n%1$s: %4$d %n%2$s: %5$d %n", vincente.getName(), perdente.getName(), danni, vincente.getHp(), perdente.getHp() < 0? 0 : perdente.getHp());
        }else System.out.println("Nessun tamagolem ha subito danni in questo turno!");
    }

    public static void annuncioEliminato(TamaGolem eliminato){
        System.out.printf("%s non può più continuare lo scontro%n", eliminato.getName());
    }

    public static void partitaFinita(Giocatore vincitore, Giocatore sconfitto){
        System.out.printf("Sembra che %s non abbia più tamagolem! %s vince lo scontro!", sconfitto.getNome(), vincitore.getNome());
    }

    public static void rivelaEquilibrio(Equilibrio e){
        System.out.println("L'equilibrio del mondo è...");
        System.out.printf("%8s", "");
        for (int i = 0; i < e.getSize(); i++) {
            System.out.printf("%8s", TamaConstants.ELEMENTI.get(i));
        }
        System.out.println();
        for (int i = 0; i < e.getSize(); i++) {
            System.out.printf("%8s", TamaConstants.ELEMENTI.get(i));
            for (int j = 0; j < e.getSize(); j++) {
                System.out.printf("%8d", e.getEquilibrio(i, j));
            }
            System.out.println();
        }
    }

}
