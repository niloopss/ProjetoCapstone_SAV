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
