# ProjetoCapstone_SAV
 O projeto SAV (Sorting Algorithm Viewer) visa criar um visualizador de algoritmos de ordenação em Java, seguindo os princípios da Programação Orientada a Objetos (POO). O SAV permitirá a visualização passo a passo da execução de algoritmos de ordenação em listas de números ou caracteres, com opções de ordenação crescente ou decrescente.



'''
import java.util.List;

public interface Sorter {
void sort(List<Comparable> list, boolean ascending, int pauseTime);
}
'''



'''
import java.util.Collections;
import java.util.List;

public class BubbleSort implements Sorter {
@Override
public void sort(List<Comparable> list, boolean ascending, int pauseTime) {
int n = list.size();
boolean swapped;
for (int i = 0; i < n - 1; i++) {
swapped = false;
for (int j = 0; j < n - i - 1; j++) {
if ((ascending && list.get(j).compareTo(list.get(j + 1)) > 0) ||
(!ascending && list.get(j).compareTo(list.get(j + 1)) < 0)) {
Collections.swap(list, j, j + 1);
swapped = true;
Display.show(list);
try {
Thread.sleep(pauseTime);
} catch (InterruptedException e) {
Thread.currentThread().interrupt();
}
}
}
if (!swapped) break;
}
}
}
'''



'''
import java.util.List;

public class InsertionSort implements Sorter {
@Override
public void sort(List<Comparable> list, boolean ascending, int pauseTime) {
for (int i = 1; i < list.size(); i++) {
Comparable key = list.get(i);
int j = i - 1;
while (j >= 0 && ((ascending && list.get(j).compareTo(key) > 0) ||
(!ascending && list.get(j).compareTo(key) < 0))) {
list.set(j + 1, list.get(j));
j = j - 1;
Display.show(list);
try {
Thread.sleep(pauseTime);
} catch (InterruptedException e) {
Thread.currentThread().interrupt();
}
}
list.set(j + 1, key);
}
}
}
'''



'''
import java.util.ArrayList;
import java.util.List;

public class MergeSort implements Sorter {
@Override
public void sort(List<Comparable> list, boolean ascending, int pauseTime) {
if (list.size() < 2) return;
int mid = list.size() / 2;
List<Comparable> left = new ArrayList<>(list.subList(0, mid));
List<Comparable> right = new ArrayList<>(list.subList(mid, list.size()));

        sort(left, ascending, pauseTime);
        sort(right, ascending, pauseTime);

        merge(list, left, right, ascending, pauseTime);
    }

    private void merge(List<Comparable> list, List<Comparable> left, List<Comparable> right, boolean ascending, int pauseTime) {
        int i = 0, j = 0, k = 0;
        while (i < left.size() && j < right.size()) {
            if ((ascending && left.get(i).compareTo(right.get(j)) <= 0) ||
                    (!ascending && left.get(i).compareTo(right.get(j)) >= 0)) {
                list.set(k++, left.get(i++));
            } else {
                list.set(k++, right.get(j++));
            }
            Display.show(list);
            try {
                Thread.sleep(pauseTime);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        while (i < left.size()) {
            list.set(k++, left.get(i++));
        }
        while (j < right.size()) {
            list.set(k++, right.get(j++));
        }
    }
}
'''



'''
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

'''



'''
import java.util.Arrays;
import java.util.List;

public class ArgumentValidator {

    private static final List<String> ALGORITHMS = Arrays.asList("b", "i", "m");
    private static final List<String> LIST_TYPES = Arrays.asList("n", "c");
    private static final List<String> ORDER_TYPES = Arrays.asList("az", "za");
    private static final List<String> INPUT_TYPES = Arrays.asList("r", "m");

    public static boolean validateArguments(String[] args) {
        if (args.length < 6) {
            System.out.println("Número insuficiente de argumentos.");
            return false;
        }

        try {
            String algorithm = args[0].split("=")[1].toLowerCase();
            String listType = args[1].split("=")[1].toLowerCase();
            String orderType = args[2].split("=")[1].toLowerCase();
            String inputType = args[3].split("=")[1].toLowerCase();
            String values = args[4].split("=")[1];
            int pauseTime = Integer.parseInt(args[5].split("=")[1]);

            if (!ALGORITHMS.contains(algorithm)) {
                System.out.println("Algoritmo de ordenação inválido.");
                return false;
            }

            if (!LIST_TYPES.contains(listType)) {
                System.out.println("Tipo de lista inválido.");
                return false;
            }

            if (!ORDER_TYPES.contains(orderType)) {
                System.out.println("Tipo de ordenamento inválido.");
                return false;
            }

            if (!INPUT_TYPES.contains(inputType)) {
                System.out.println("Tipo de entrada inválido.");
                return false;
            }

            if (pauseTime < 100 || pauseTime > 1000) {
                System.out.println("Tempo de pausa inválido. Deve estar entre 100 e 1000 milissegundos.");
                return false;
            }

            if (inputType.equals("r")) {
                int numValues = Integer.parseInt(values);
                if (numValues < 1 || numValues > 40) {
                    System.out.println("Número de valores aleatórios inválido. Deve estar entre 1 e 40.");
                    return false;
                }
            } else if (inputType.equals("m")) {
                if (listType.equals("n")) {
                    for (String value : values.split(",")) {
                        int num = Integer.parseInt(value.trim());
                        if (num < -1000 || num > 1000) {
                            System.out.println("Valor numérico fora do intervalo permitido (-1000 a 1000).");
                            return false;
                        }
                    }
                } else if (listType.equals("c")) {
                    for (String value : values.split(",")) {
                        char c = value.trim().charAt(0);
                        if (!Character.isLetter(c)) {
                            System.out.println("Valor de caractere inválido. Deve estar entre [a-z] e [A-Z].");
                            return false;
                        }
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Erro na validação dos argumentos: " + e.getMessage());
            return false;
        }

        return true;
    }
}

'''



'''
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

        
        // Sorting process
        Sorter sorter = getSorter(algorithm);
        boolean ascending = order.equalsIgnoreCase("az");
        long startTime = System.currentTimeMillis();
        sorter.sort(list, ascending, pauseTime);
        long endTime = System.currentTimeMillis();

        // Print results
        System.out.println();
        System.out.println("------------------------------------------------------------------");
        System.out.println("CLI: " + String.join(" ", args)); // Print CLI arguments
        System.out.println("Tipo de ordenação: " + getAlgorithmName(algorithm));
        System.out.println("Ordem: " + (order.equalsIgnoreCase("az") ? "Crescente" : "Decrescente"));
        System.out.println("Tipo de Lista: " + (listType.equalsIgnoreCase("n") ? "Números" : "Caracteres"));
        System.out.println("Tipo de Input: " + (inputType.equalsIgnoreCase("r") ? "Aleatório" : "Manual"));
        System.out.println("Lista inicial: " + list);
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

    private static String getAlgorithmName(String algorithm) {
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


'''



'''
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListGenerator {

    public static List<Comparable> generateList(String type, String inputType, String values) {
        List<Comparable> list = new ArrayList<>();

        if (inputType.equalsIgnoreCase("m")) {
            if (type.equalsIgnoreCase("n")) {
                for (String value : values.split(",")) {
                    list.add(Integer.parseInt(value.trim()));
                }
            } else if (type.equalsIgnoreCase("c")) {
                for (String value : values.split(",")) {
                    list.add(value.trim().charAt(0));
                }
            }
        } else if (inputType.equalsIgnoreCase("r")) {
            Random random = new Random();
            int numValues = Integer.parseInt(values);

            if (type.equalsIgnoreCase("n")) {
                for (int i = 0; i < numValues; i++) {
                    list.add(random.nextInt(2001) - 1000); // Gera valores entre -1000 e 1000
                }
            } else if (type.equalsIgnoreCase("c")) {
                for (int i = 0; i < numValues; i++) {
                    list.add((char) (random.nextInt(26) + 'a')); // Gera letras aleatórias de 'a' a 'z'
                }
            }
        }

        return list;
    }
}

'''