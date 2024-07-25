import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Classe responsável por gerar listas de números ou caracteres.
 */
public class ListGenerator {

    /**
     * Gera uma lista de números ou caracteres, baseada nos argumentos fornecidos.
     *
     * @param type      O tipo de lista ("n" para números, "c" para caracteres).
     * @param inputType O tipo de entrada ("m" para manual, "r" para aleatório).
     * @param values    Os valores a serem usados (número de valores para aleatório ou lista de valores para manual).
     * @return A lista gerada.
     */
    public static List<Comparable> generateList(String type, String inputType, String values) {
        List<Comparable> list = new ArrayList<>();

        if (inputType.equalsIgnoreCase("m")) { // Entrada manual
            if (type.equalsIgnoreCase("n")) {
                for (String value : values.split(",")) {
                    list.add(Integer.parseInt(value.trim()));
                }
            } else if (type.equalsIgnoreCase("c")) {
                for (String value : values.split(",")) {
                    list.add(value.trim().charAt(0));
                }
            }
        } else if (inputType.equalsIgnoreCase("r")) { // Entrada aleatória
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
