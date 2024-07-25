import java.util.List;

/**
 * Interface que define o método de ordenação para diferentes algoritmos.
 * @author Nivea Lins
 */
public interface Sorter {
    /**
     * Ordena a lista fornecida.
     *
     * @param list a lista a ser ordenada
     * @param ascending indica se a ordenação deve ser em ordem crescente (true) ou decrescente (false)
     * @param pauseTime o tempo de pausa em milissegundos entre as operações de troca para visualização
     */
    void sort(List<Comparable> list, boolean ascending, int pauseTime);
}
