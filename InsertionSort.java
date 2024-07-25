import java.util.List;

/**
 * Classe que implementa o algoritmo Insertion Sort.
 * @author Nivea Lins
 */
public class InsertionSort implements Sorter {

    /**
     * Ordena a lista usando o algoritmo Insertion Sort.
     *
     * @param list      A lista a ser ordenada.
     * @param ascending Se true, ordena em ordem crescente, caso contrário, em ordem decrescente.
     * @param pauseTime Tempo de pausa entre as operações de troca, em milissegundos.
     */
    @Override
    public void sort(List<Comparable> list, boolean ascending, int pauseTime) {
        // Laço que percorre a lista a partir do segundo elemento
        for (int i = 1; i < list.size(); i++) {
            Comparable key = list.get(i);
            int j = i - 1;

            // Laço que move os elementos maiores (ou menores) para a direita
            while (j >= 0 && ((ascending && list.get(j).compareTo(key) > 0) ||
                    (!ascending && list.get(j).compareTo(key) < 0))) {
                list.set(j + 1, list.get(j));
                j = j - 1;

                // Exibe a lista atualizada e pausa a execução
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
