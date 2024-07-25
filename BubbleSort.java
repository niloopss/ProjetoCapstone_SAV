import java.util.Collections;
import java.util.List;

/**
 * Classe que implementa o algoritmo Bubble Sort.
 * @author Nivea Lins
 */
public class BubbleSort implements Sorter {

    /**
     * Ordena a lista usando o algoritmo Bubble Sort.
     *
     * @param list      A lista a ser ordenada.
     * @param ascending Se true, ordena em ordem crescente, caso contrário, em ordem decrescente.
     * @param pauseTime Tempo de pausa entre as operações de troca, em milissegundos.
     */
    @Override
    public void sort(List<Comparable> list, boolean ascending, int pauseTime) {
        int n = list.size();
        boolean swapped;

        // Laço externo que percorre a lista até que não haja mais trocas
        for (int i = 0; i < n - 1; i++) {
            swapped = false;

            // Laço interno que realiza a comparação e a troca dos elementos
            for (int j = 0; j < n - i - 1; j++) {
                if ((ascending && list.get(j).compareTo(list.get(j + 1)) > 0) ||
                        (!ascending && list.get(j).compareTo(list.get(j + 1)) < 0)) {
                    Collections.swap(list, j, j + 1);
                    swapped = true;

                    // Exibe a lista atualizada e pausa a execução
                    Display.show(list);
                    try {
                        Thread.sleep(pauseTime);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }

            // Se não houve trocas, a lista já está ordenada
            if (!swapped) break;
        }
    }
}
