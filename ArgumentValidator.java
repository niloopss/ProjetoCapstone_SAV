import java.util.Arrays;
import java.util.List;

public class ArgumentValidator {

    private static final List<String> ALGORITHMS = Arrays.asList("b", "i", "m");
    private static final List<String> LIST_TYPES = Arrays.asList("n", "c");
    private static final List<String> ORDER_TYPES = Arrays.asList("az", "za");
    private static final List<String> INPUT_TYPES = Arrays.asList("r", "m");

    public static boolean validateArguments(String[] args) {
        if (args.length < 6) {
            System.out.println("Número insuficiente de argumentos.");
            return false;
        }

        try {
            String algorithm = args[0].split("=")[1].toLowerCase();
            String listType = args[1].split("=")[1].toLowerCase();
            String orderType = args[2].split("=")[1].toLowerCase();
            String inputType = args[3].split("=")[1].toLowerCase();
            String values = args[4].split("=")[1];
            int pauseTime = Integer.parseInt(args[5].split("=")[1]);

            if (!ALGORITHMS.contains(algorithm)) {
                System.out.println("Algoritmo de ordenação inválido.");
                return false;
            }

            if (!LIST_TYPES.contains(listType)) {
                System.out.println("Tipo de lista inválido.");
                return false;
            }

            if (!ORDER_TYPES.contains(orderType)) {
                System.out.println("Tipo de ordenamento inválido.");
                return false;
            }

            if (!INPUT_TYPES.contains(inputType)) {
                System.out.println("Tipo de entrada inválido.");
                return false;
            }

            if (pauseTime < 100 || pauseTime > 1000) {
                System.out.println("Tempo de pausa inválido. Deve estar entre 100 e 1000 milissegundos.");
                return false;
            }

            if (inputType.equals("r")) {
                int numValues = Integer.parseInt(values);
                if (numValues < 1 || numValues > 40) {
                    System.out.println("Número de valores aleatórios inválido. Deve estar entre 1 e 40.");
                    return false;
                }
            } else if (inputType.equals("m")) {
                if (listType.equals("n")) {
                    for (String value : values.split(",")) {
                        int num = Integer.parseInt(value.trim());
                        if (num < -1000 || num > 1000) {
                            System.out.println("Valor numérico fora do intervalo permitido (-1000 a 1000).");
                            return false;
                        }
                    }
                } else if (listType.equals("c")) {
                    for (String value : values.split(",")) {
                        char c = value.trim().charAt(0);
                        if (!Character.isLetter(c)) {
                            System.out.println("Valor de caractere inválido. Deve estar entre [a-z] e [A-Z].");
                            return false;
                        }
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Erro na validação dos argumentos: " + e.getMessage());
            return false;
        }

        return true;
    }
}
