package sorting;

import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

public class LongCalculator implements Calculable {
    @Override
    public String calculate(SortType sortType, Scanner scanner) {
        List<Long> numbers = new ArrayList<>();

        while (scanner.hasNext()) {
            String token = scanner.next();
            try {
                long number = Long.parseLong(token);
                numbers.add(number);
            } catch (NumberFormatException e) {
                System.out.println("\"" + token + "\" is not a long. It will be skipped.");
            }
        }
        scanner.close();

        StringBuilder result = new StringBuilder();

        int count = numbers.size();
        result.append("Total numbers: %d.%n".formatted(count));
        if (sortType == SortType.NATURAL) {
            Collections.sort(numbers);
            result.append("Sorted data: %s".formatted(numbers.stream().map(Object::toString).collect(Collectors.joining(" "))));
        } else {
            Map<Long, Integer> numberCounts = new HashMap<>();
            for (Long num : numbers) {
                numberCounts.put(num, numberCounts.getOrDefault(num, 0) + 1);
            }

            List<Map.Entry<Long, Integer>> list = new ArrayList<>(numberCounts.entrySet());
            list.sort((e1, e2) -> {
                int cmp = e1.getValue().compareTo(e2.getValue());
                if (cmp != 0) {
                    return cmp;
                } else {
                    return e1.getKey().compareTo(e2.getKey());
                }
            });
            for (Map.Entry<Long, Integer> entry : list) {
                double percentage = ((double) entry.getValue() / count) * 100;
                result.append("%d: %d time(s), %d%%\n".formatted(entry.getKey(), entry.getValue(), (int) percentage));
            }
        }

        return result.toString();
    }
}
