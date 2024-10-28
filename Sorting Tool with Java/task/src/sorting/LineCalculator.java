package sorting;

import java.util.*;

public class LineCalculator implements Calculable {

    @Override
    public String calculate(SortType sortType, Scanner scanner) {
        List<String> lines = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            lines.add(line);
        }
        scanner.close();

        StringBuilder result = new StringBuilder();

        int count = lines.size();
        result.append("Total lines: %d.%n".formatted(count));
        if (sortType == SortType.NATURAL) {
            Collections.sort(lines);
            StringBuilder sortedWords = new StringBuilder();
            for (String word : lines) {
                sortedWords.append(word).append(" ");
            }
            result.append("Sorted data: ").append(sortedWords.toString().trim());
        } else {
            Map<String, Integer> wordCounts = new HashMap<>();
            for (String word : lines) {
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
