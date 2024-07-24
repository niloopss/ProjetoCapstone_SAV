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
