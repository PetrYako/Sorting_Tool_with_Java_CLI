package sorting;

import java.util.*;

public class WordCalculator implements Calculable {
    @Override
    public String calculate(SortType sortType, Scanner scanner) {
        List<String> words = new ArrayList<>();
        while (scanner.hasNext()) {
            String word = scanner.next();
            words.add(word);
        }
        scanner.close();

        StringBuilder result = new StringBuilder();

        int count = words.size();
        result.append("Total words: %d.%n".formatted(count));
        if (sortType == SortType.NATURAL) {
            Collections.sort(words);
            StringBuilder sortedWords = new StringBuilder();
            for (String word : words) {
                sortedWords.append(word).append(" ");
            }
            result.append("Sorted data: ").append(sortedWords.toString().trim());
        } else {
            Map<String, Integer> wordCounts = new HashMap<>();
            for (String word : words) {
                wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
            }

            List<Map.Entry<String, Integer>> list = new ArrayList<>(wordCounts.entrySet());
            list.sort((e1, e2) -> {
                int cmp = e1.getValue().compareTo(e2.getValue());
                if (cmp != 0) {
                    return cmp;
                } else {
                    return e1.getKey().compareTo(e2.getKey());
                }
            });

            for (Map.Entry<String, Integer> entry : list) {
                double percentage = ((double) entry.getValue() / count) * 100;
                result.append("%s: %d time(s), %d%%\n".formatted(entry.getKey(), entry.getValue(), (int) percentage));
            }
        }

        return result.toString();
    }
}
