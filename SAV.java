import java.util.ArrayList;
import java.util.List;

/**
 * Classe principal do programa de visualização e ordenação.
 * @author Nivea Lins
 */
public class SAV {

    public static void main(String[] args) {
        // Valida os argumentos da linha de comando
        if (!ArgumentValidator.validateArguments(args)) {
            System.out.println("Argumentos inválidos.");
            return;
        }

        // Parse arguments
        String algorithm = args[0].split("=")[1];
        String listType = args[1].split("=")[1];
        String order = args[2].split("=")[1];
        String inputType = args[3].split("=")[1];
        String values = args[4].split("=")[1];
        int pauseTime = Integer.parseInt(args[5].split("=")[1]);

        // Gera a lista a ser ordenada
        List<Comparable> list = ListGenerator.generateList(listType, inputType, values);
        List<Comparable> originalList = new ArrayList<>(list); // Armazena a lista original

        // Realiza a ordenação
        Sorter sorter = getSorter(algorithm);
        boolean ascending = order.equalsIgnoreCase("az");
        long startTime = System.currentTimeMillis();
        sorter.sort(list, ascending, pauseTime);
        long endTime = System.currentTimeMillis();

        // Exibe os resultados
        System.out.println();
        System.out.println("------------------------------------------------------------------");
        System.out.println("Argumentos da CLI: " + String.join(" ", args));
        System.out.println();
        Display.showSortingDetails(algorithm, listType, order, inputType, originalList);
        System.out.println("Lista ordenada: " + convertListToString(list, listType));
        System.out.println("Tempo gasto: " + (endTime - startTime) + " ms");
        System.out.println("------------------------------------------------------------------");
    }

    /**
     * Retorna uma instância do algoritmo de ordenação especificado.
     *
     * @param algorithm O código do algoritmo de ordenação.
     * @return Uma instância do algoritmo de ordenação.
     */
    private static Sorter getSorter(String algorithm) {
        switch (algorithm.toLowerCase()) {
            case "b":
                return new BubbleSort();
            case "i":
                return new InsertionSort();
            case "m":
                return new MergeSort();
            default:
                throw new IllegalArgumentException("Algoritmo de ordenação inválido");
        }
    }

    /**
     * Retorna o nome completo do algoritmo de ordenação.
     *
     * @param algorithm O código do algoritmo de ordenação.
     * @return O nome completo do algoritmo de ordenação.
     */
    static String getAlgorithmName(String algorithm) {
        switch (algorithm.toLowerCase()) {
            case "b":
                return "Bubble Sort";
            case "i":
                return "Insertion Sort";
            case "m":
                return "Merge Sort";
            default:
                throw new IllegalArgumentException("Algoritmo de ordenação inválido");
        }
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
