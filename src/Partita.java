import java.util.ArrayList;//prova

public class Partita {
        Giocatore g1;
        Giocatore g2;
        int livello;
        Giocatore vincitore;
        ArrayList<String> ElementiG1 = new ArrayList<>();  //prendi arraylist di enum scelti
        ArrayList<String> ElementiG2 = new ArrayList<>();

    public Partita(Giocatore g1, Giocatore g2, int livello) {
        this.g1 = g1;
        this.g2 = g2;
        this.livello = livello;
    }

    /*public void setLivello(int livello) {
        this.livello = livello;
    }*/

    public void faiPartita(){
        Equilibrio eq = new Equilibrio(livello);  //setEquilibrio() fatto
        TamaGolem tm1= new TamaGolem();  //da cambiare aggiungendo parametri richiesti dall'utente
        TamaGolem tm2 = new TamaGolem();







        }


    public void scegliElementi(){

    }

    public void faiTurnoNew(){

    }

   public boolean checkifPartitaContinua(){
        if (g1.isAvailableTamaGolem() && g2.isAvailableTamaGolem())
             return true;
        return false;
   }

}
