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
