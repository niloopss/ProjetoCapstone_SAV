import java.util.ArrayList;
import java.util.List;

/**
 * Classe que implementa o algoritmo Merge Sort.
 * @author Nivea Lins
 */
public class MergeSort implements Sorter {

    /**
     * Ordena a lista usando o algoritmo Merge Sort.
     *
     * @param list      A lista a ser ordenada.
     * @param ascending Se true, ordena em ordem crescente, caso contrário, em ordem decrescente.
     * @param pauseTime Tempo de pausa entre as operações de troca, em milissegundos.
     */
    @Override
    public void sort(List<Comparable> list, boolean ascending, int pauseTime) {
        if (list.size() < 2) return; // Caso base: se a lista tem menos de 2 elementos, está ordenada

        int mid = list.size() / 2;
        List<Comparable> left = new ArrayList<>(list.subList(0, mid));
        List<Comparable> right = new ArrayList<>(list.subList(mid, list.size()));

        // Ordena as duas metades
        sort(left, ascending, pauseTime);
        sort(right, ascending, pauseTime);

        // Junta as duas metades ordenadas
        merge(list, left, right, ascending, pauseTime);
    }

    private void merge(List<Comparable> list, List<Comparable> left, List<Comparable> right, boolean ascending, int pauseTime) {
        int i = 0, j = 0, k = 0;

        // Junta os elementos na ordem correta
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

        // Copia os elementos restantes de left
        while (i < left.size()) {
            list.set(k++, left.get(i++));
        }

        // Copia os elementos restantes de right
        while (j < right.size()) {
            list.set(k++, right.get(j++));
        }
    }
}
