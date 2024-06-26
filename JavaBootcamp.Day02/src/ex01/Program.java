package ex01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

import javax.management.RuntimeErrorException;

public class Program {
  public static void main(String[] args) {
    try {
      if (args.length < 2)
        throw new RuntimeErrorException(null, "Wrong arguments");
      HashMap<String, Integer> firstFileMap = readFile(args[0]);
      HashMap<String, Integer> secondFileMap = readFile(args[1]);
      Set<String> dictionary = unionDictionaries(firstFileMap.keySet(), secondFileMap.keySet());
      exportDictionary(dictionary);
      checkSimilarity(dictionary, firstFileMap, secondFileMap);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public static HashMap<String, Integer> readFile(String path) throws IOException {
    FileReader fr = new FileReader(path);
    BufferedReader br = new BufferedReader(fr);
    String line = null;
    HashMap<String, Integer> wordsMap = new HashMap<String, Integer>();
    while ((line = br.readLine()) != null) {
      String[] splittedLine = line.split("[-.?!)(,:]\s|\s|[-.?!)(,:]$");
      for (String s : splittedLine) {
        wordsMap.computeIfPresent(s.toLowerCase(), (k, v) -> ++v);
        wordsMap.putIfAbsent(s.toLowerCase(), 1);
      }
    }
    br.close();
    fr.close();
    return wordsMap;
  }

  public static Set<String> unionDictionaries(Set<String> dict1, Set<String> dict2) {
    Set<String> resDict = new TreeSet<String>();
    for (String s : dict1)
      resDict.add(s);
    for (String s : dict2)
      if (!resDict.contains(s))
        resDict.add(s);
    return resDict;
  }

  public static void exportDictionary(Set<String> dictionary) throws IOException {
    FileWriter fw = new FileWriter("dictionary.txt");
    for (String s : dictionary)
      fw.write(s + "\n");
    fw.close();
  }

  public static void checkSimilarity(Set<String> dictionary, HashMap<String, Integer> firstFileMap,
      HashMap<String, Integer> secondFileMap) {
    double numerator = 0f;
    double subValue1 = 0f;
    double subValue2 = 0f;
    double denominator = 0f;
    int firstFileContains = 0;
    int secondFileContains = 0;
    for (String s : dictionary) {
      firstFileContains = firstFileMap.containsKey(s) ? firstFileMap.get(s) : 0;
      secondFileContains = secondFileMap.containsKey(s) ? secondFileMap.get(s) : 0;
      numerator += firstFileContains * secondFileContains;
      subValue1 += Math.pow(firstFileContains, 2);
      subValue2 += Math.pow(secondFileContains, 2);
    }
    denominator = Math.sqrt(subValue1) * Math.sqrt(subValue2);
    System.out.printf("Similarity = %.2f\n", numerator / denominator);
  }
}