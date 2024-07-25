import java.util.ArrayList;
import java.util.List;

public class SAV {
    public static void main(String[] args) {
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

        List<Comparable> list = ListGenerator.generateList(listType, inputType, values);
        List<Comparable> originalList = new ArrayList<>(list); // Armazena a lista original


        // Sorting process
        Sorter sorter = getSorter(algorithm);
        boolean ascending = order.equalsIgnoreCase("az");
        long startTime = System.currentTimeMillis();
        sorter.sort(list, ascending, pauseTime);
        long endTime = System.currentTimeMillis();

        // Print results
        System.out.println();
        System.out.println("------------------------------------------------------------------");
        System.out.println("Argumentos da CLI: " + String.join(" ", args));
        System.out.println();
        Display.showSortingDetails(algorithm, listType, order, inputType, originalList);
        System.out.println("Lista ordenada: " + list);
        System.out.println("Tempo gasto: " + (endTime - startTime) + " ms");
        System.out.println("------------------------------------------------------------------");
    }

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
}
