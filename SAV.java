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
        Sorter sorter;
        switch (algorithm.toLowerCase()) {
            case "b":
                sorter = new BubbleSort();
                break;
            case "i":
                sorter = new InsertionSort();
                break;
            case "m":
                sorter = new MergeSort();
                break;
            default:
                throw new IllegalArgumentException("Algoritmo de ordenação inválido");
        }

        boolean ascending = order.equalsIgnoreCase("az");
        long startTime = System.currentTimeMillis();
        sorter.sort(list, ascending, pauseTime);
        long endTime = System.currentTimeMillis();

        System.out.println("Lista ordenada: " + list);
        System.out.println("Tempo gasto: " + (endTime - startTime) + " ms");
    }
}
