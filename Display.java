import java.util.List;

/**
 * Classe responsável por exibir a lista durante o processo de ordenação.
 * @autor Nivea Lins
 */
public class Display {

    /**
     * Exibe a lista como uma série de quadrados para números e espaços para caracteres.
     *
     * @param list A lista a ser exibida.
     */
    public static void show(List<Comparable> list) {
        boolean containsNumbers = list.stream().anyMatch(item -> item instanceof Integer && !(item instanceof Character));

        if (containsNumbers) {
            int max = findMax(list); // Encontra o maior valor na lista

            // Imprime a representação gráfica da lista
            for (int i = max; i > 0; i--) {
                for (Comparable value : list) {
                    if (value instanceof Integer && (int) value >= i) {
                        System.out.print("⬜ ");
                    } else {
                        System.out.print("   ");
                    }
                }
                System.out.println();
            }
        }

        // Imprime os valores da lista abaixo da representação gráfica
        for (Comparable value : list) {
            if (value instanceof Character) {
                System.out.printf("%-2c ", (char) value);
            } else {
                System.out.printf("%-2d ", (int) value);
            }
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
        System.out.println("Lista inicial: " + convertListToString(list, listType));
    }

    /**
     * Encontra o maior valor na lista.
     *
     * @param list A lista a ser analisada.
     * @return O maior valor na lista.
     */
    private static int findMax(List<Comparable> list) {
        Comparable max = list.get(0); // Inicializa com o primeiro elemento da lista
        for (Comparable value : list) {
            if (value.compareTo(max) > 0) {
                max = value; // Atualiza o máximo com o tipo Comparable
            }
        }
        return (max instanceof Integer) ? (Integer) max : (int) (Character) max;
    }

    /**
     * Converte a lista de valores para uma string representando os caracteres ou números.
     *
     * @param list A lista de valores.
     * @param listType O tipo de lista (caracteres ou números).
     * @return A lista convertida em string.
     */
    private static String convertListToString(List<Comparable> list, String listType) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < list.size(); i++) {
            Comparable value = list.get(i);
            if (listType.equals("c")) {
                sb.append((char) ((int) value));
            } else {
                sb.append(value);
            }
            if (i < list.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
