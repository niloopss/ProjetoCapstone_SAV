import java.util.List;

public class Display {
    public static void show(List<Comparable> list) {
        int max = findMax(list); // Encontra o maior valor na lista

        for (int i = max; i > 0; i--) { // Itera de cima para baixo
            for (Comparable value : list) {
                if ((int) value >= i) {
                    System.out.print("⬜ "); // Imprime quadrado se o valor for maior ou igual à linha atual
                } else {
                    System.out.print("   "); // Imprime espaços em branco caso contrário
                }
            }
            System.out.println(); // Nova linha após cada iteração
        }

        for (Comparable value : list) {
            System.out.printf("%-2d ", value); // Imprime os valores alinhados abaixo dos quadrados
        }
        System.out.println();
    }

    private static int findMax(List<Comparable> list) {
        int max = 0;
        for (Comparable value : list) {
            if ((int) value > max) {
                max = (int) value;
            }
        }
        return max;
    }
}
