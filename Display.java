import java.util.List;

/**
 * Classe responsável por exibir a lista durante o processo de ordenação.
 * @author Nivea Lins
 */
public class Display {

    /**
     * Exibe a lista como uma série de quadrados e números.
     *
     * @param list A lista a ser exibida.
     */
    public static void show(List<Comparable> list) {
        int max = findMax(list); // Encontra o maior valor na lista

        // Imprime a representação gráfica da lista
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

        // Imprime os valores da lista abaixo da representação gráfica
        for (Comparable value : list) {
            System.out.printf("%-2d ", value); // Imprime os valores alinhados abaixo dos quadrados
        }
        System.out.println();
    }

    /**
     * Exibe os detalhes da ordenação.
     *
     * @param algorithm O algoritmo de ordenação usado.
     * @param listType  O tipo de lista (números ou caracteres).
     * @param order     A ordem de ordenação (crescente ou decrescente).
     * @param inputType O tipo de input (manual ou aleatório).
     * @param list      A lista inicial.
     */
    public static void showSortingDetails(String algorithm, String listType, String order, String inputType, List<Comparable> list) {
        System.out.println("Tipo de ordenação: " + SAV.getAlgorithmName(algorithm));
        System.out.println("Ordem: " + (order.equalsIgnoreCase("az") ? "Crescente" : "Decrescente"));
        System.out.println("Tipo de Lista: " + (listType.equalsIgnoreCase("n") ? "Números" : "Caracteres"));
        System.out.println("Tipo de Input: " + (inputType.equalsIgnoreCase("r") ? "Aleatório" : "Manual"));
        System.out.println("Lista inicial: " + list);
    }

    /**
     * Encontra o maior valor na lista.
     *
     * @param list A lista a ser analisada.
     * @return O maior valor na lista.
     */
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
