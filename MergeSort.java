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
