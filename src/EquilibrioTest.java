public class EquilibrioTest {

    private static final int ELEMENTS_NUM = 5;

    public static void main(String[] args) {
        TamaValues.setValues(2); //mettere scelta tra 0 1 2 (difficoltÃ )
        Equilibrio eq = new Equilibrio(TamaValues.N);
        for (int i = 0; i < TamaValues.N; i++) {
            for (int j = 0; j < TamaValues.N; j++) {
                System.out.print(String.format("%5d", eq.getEquilibrio(i, j)));
            }
            System.out.println();
        }
    }
}
