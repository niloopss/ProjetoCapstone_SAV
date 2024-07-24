import java.util.List;

public class Display {
    public static void show(List<Comparable> list) {
        for (Comparable value : list) {
            System.out.print("⬜" + value + " ");
        }
        System.out.println();
    }
}
