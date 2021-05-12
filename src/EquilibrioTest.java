public class EquilibrioTest {
    public static void main(String[] args) {
        Equilibrio eq = new Equilibrio(4);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(String.format("%5d", eq.getEquilibrio(i,j)));
            }
            System.out.println();
        }
    }
}
