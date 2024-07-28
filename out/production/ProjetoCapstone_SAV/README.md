'''
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

'''




'''
import java.util.Collections;
import java.util.List;

/**
* Classe que implementa o algoritmo Bubble Sort.
* @author Nivea Lins
  */
  public class BubbleSort implements Sorter {

  /**
    * Ordena a lista usando o algoritmo Bubble Sort.
    *
    * @param list      A lista a ser ordenada.
    * @param ascending Se true, ordena em ordem crescente, caso contrário, em ordem decrescente.
    * @param pauseTime Tempo de pausa entre as operações de troca, em milissegundos.
      */
      @Override
      public void sort(List<Comparable> list, boolean ascending, int pauseTime) {
      int n = list.size();
      boolean swapped;

      // Laço externo que percorre a lista até que não haja mais trocas
      for (int i = 0; i < n - 1; i++) {
      swapped = false;

           // Laço interno que realiza a comparação e a troca dos elementos
           for (int j = 0; j < n - i - 1; j++) {
               if ((ascending && list.get(j).compareTo(list.get(j + 1)) > 0) ||
                       (!ascending && list.get(j).compareTo(list.get(j + 1)) < 0)) {
                   Collections.swap(list, j, j + 1);
                   swapped = true;

                   // Exibe a lista atualizada e pausa a execução
                   Display.show(list);
                   try {
                       Thread.sleep(pauseTime);
                   } catch (InterruptedException e) {
                       Thread.currentThread().interrupt();
                   }
               }
           }

           // Se não houve trocas, a lista já está ordenada
           if (!swapped) break;
      }
      }
      }

'''




'''
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

'''




'''
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

'''





'''
import java.util.List;

/**
* Classe responsável por exibir a lista durante o processo de ordenação.
* @author Nivea Lins
  */
  public class Display {

  /**
    * Exibe a lista como uma série de quadrados e números.
    *
    * @param list A lista a ser exibida.
      */
      public static void show(List<Comparable> list) {
      int max = findMax(list); // Encontra o maior valor na lista

      // Imprime a representação gráfica da lista
      for (int i = max; i > 0; i--) {
      for (Comparable value : list) {
      if (value instanceof Integer && (int) value >= i) { // Verifica se é Integer
      System.out.print("⬜ ");
      } else if (value instanceof Character) { // Verifica se é Character
      System.out.print(value + " "); // Exibe o caractere sem quadrado
      } else {
      System.out.print("   "); // Espaço em branco para outros tipos (se houver)
      }
      }
      System.out.println();
      }

      // Imprime os valores da lista abaixo da representação gráfica
      for (Comparable value : list) {
      System.out.printf("%-2d ", value); // Imprime os valores alinhados abaixo dos quadrados
      }
      System.out.println();
      }

  /**
    * Exibe os detalhes da ordenação.
    *
    * @param algorithm O algoritmo de ordenação usado.
    * @param listType  O tipo de lista (números ou caracteres).
    * @param order     A ordem de ordenação (crescente ou decrescente).
    * @param inputType O tipo de input (manual ou aleatório).
    * @param list      A lista inicial.
      */
      public static void showSortingDetails(String algorithm, String listType, String order, String inputType, List<Comparable> list) {
      System.out.println("Tipo de ordenação: " + SAV.getAlgorithmName(algorithm));
      System.out.println("Ordem: " + (order.equalsIgnoreCase("az") ? "Crescente" : "Decrescente"));
      System.out.println("Tipo de Lista: " + (listType.equalsIgnoreCase("n") ? "Números" : "Caracteres"));
      System.out.println("Tipo de Input: " + (inputType.equalsIgnoreCase("r") ? "Aleatório" : "Manual"));
      System.out.println("Lista inicial: " + list);
      }

  /**
    * Encontra o maior valor na lista.
    *
    * @param list A lista a ser analisada.
    * @return O maior valor na lista.
      */
      private static int findMax(List<Comparable> list) {
      Comparable max = list.get(0); // Inicializa com o primeiro elemento da lista
      for (Comparable value : list) {
      if (value.compareTo(max) > 0) {
      max = value; // Atualiza o máximo com o tipo Comparable
      }
      }
      return (max instanceof Integer) ? (Integer) max : (int) (Character) max;
      }
      }

'''





'''
import java.util.Arrays;
import java.util.List;

/**
* Classe responsável por validar os argumentos de linha de comando.
* @author Nivea Lins
  */
  public class ArgumentValidator {

  // Listas de valores válidos para os diferentes tipos de argumentos
  private static final List<String> ALGORITHMS = Arrays.asList("b", "i", "m");
  private static final List<String> LIST_TYPES = Arrays.asList("n", "c");
  private static final List<String> ORDER_TYPES = Arrays.asList("az", "za");
  private static final List<String> INPUT_TYPES = Arrays.asList("r", "m");

  /**
    * Valida os argumentos de linha de comando.
    *
    * @param args Os argumentos a serem validados.
    * @return true se os argumentos são válidos, false caso contrário.
      */
      public static boolean validateArguments(String[] args) {
      if (args.length < 6) {
      System.out.println("Número insuficiente de argumentos.");
      return false;
      }

      try {
      // Extrai e valida cada argumento individualmente
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

'''




'''
import java.util.ArrayList;
import java.util.List;

/**
* Classe principal do programa de visualização e ordenação.
* @author Nivea Lins
  */
  public class SAV {

  public static void main(String[] args) {
  // Valida os argumentos da linha de comando
  if (!ArgumentValidator.validateArguments(args)) {
  System.out.println("Argumentos inválidos.");
  return;
  }

       // Parse arguments
       String algorithm = args[0].split("=")[1];
       String listType = args[1].split("=")[1];
       String order = args[2].split("=")[1];
       String inputType = args[3].split("=")[1];
       String values = args[4].split("=")[1];
       int pauseTime = Integer.parseInt(args[5].split("=")[1]);

       // Gera a lista a ser ordenada
       List<Comparable> list = ListGenerator.generateList(listType, inputType, values);
       List<Comparable> originalList = new ArrayList<>(list); // Armazena a lista original

       // Realiza a ordenação
       Sorter sorter = getSorter(algorithm);
       boolean ascending = order.equalsIgnoreCase("az");
       long startTime = System.currentTimeMillis();
       sorter.sort(list, ascending, pauseTime);
       long endTime = System.currentTimeMillis();

       // Exibe os resultados
       System.out.println();
       System.out.println("------------------------------------------------------------------");
       System.out.println("Argumentos da CLI: " + String.join(" ", args));
       System.out.println();
       Display.showSortingDetails(algorithm, listType, order, inputType, originalList);
       System.out.println("Lista ordenada: " + list);
       System.out.println("Tempo gasto: " + (endTime - startTime) + " ms");
       System.out.println("------------------------------------------------------------------");
  }

  /**
    * Retorna uma instância do algoritmo de ordenação especificado.
    *
    * @param algorithm O código do algoritmo de ordenação.
    * @return Uma instância do algoritmo de ordenação.
      */
      private static Sorter getSorter(String algorithm) {
      switch (algorithm.toLowerCase()) {
      case "b":
      return new BubbleSort();
      case "i":
      return new InsertionSort();
      case "m":
      return new MergeSort();
      default:
      throw new IllegalArgumentException("Algoritmo de ordenação inválido");
      }
      }

  /**
    * Retorna o nome completo do algoritmo de ordenação.
    *
    * @param algorithm O código do algoritmo de ordenação.
    * @return O nome completo do algoritmo de ordenação.
      */
      static String getAlgorithmName(String algorithm) {
      switch (algorithm.toLowerCase()) {
      case "b":
      return "Bubble Sort";
      case "i":
      return "Insertion Sort";
      case "m":
      return "Merge Sort";
      default:
      throw new IllegalArgumentException("Algoritmo de ordenação inválido");
      }
      }
      }

'''




'''
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
      } else if (type.equalsIgnoreCase("c")) { // Entrada manual
      for (String value : values.split(",")) {
      list.add((int) value.trim().charAt(0)); // Converte para código ASCII
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

'''