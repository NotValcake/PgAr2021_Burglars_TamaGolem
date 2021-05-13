public class EquilibrioTest {

    private static final int ELEMENTS_NUM = 20;

    public static void main(String[] args) {
        Equilibrio eq = new Equilibrio(ELEMENTS_NUM);
        for (int i = 0; i < ELEMENTS_NUM; i++) {
            for (int j = 0; j < ELEMENTS_NUM; j++) {
                System.out.print(String.format("%5d", eq.getEquilibrio(i,j)));
            }
            System.out.println();
        }
    }
}
