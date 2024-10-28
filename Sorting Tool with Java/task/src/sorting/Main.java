package sorting;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class Main {

    public static void main(final String[] args) throws FileNotFoundException {
        processDefaultMode(args);
    }

    public static void processDefaultMode(final String[] args) throws FileNotFoundException {
        DataType dataType = DataType.WORD;
        SortType sortType = SortType.NATURAL;
        String inputFileName = null;
        String outputFileName = null;

        for (int i = 0; i < args.length;) {
            if ("-dataType".equals(args[i])) {
                try {
                    dataType = DataType.valueOf(args[i + 1].toUpperCase());
                    i += 2;
                } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("No data type defined!");
                    System.exit(0);
                }
            } else if ("-sortingType".equals(args[i])) {
                try {
                    sortType = SortType.valueOf(args[i + 1].toUpperCase());
                    i += 2;
                } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("No sorting type defined!");
                    System.exit(0);
                }
            } else if ("-inputFile".equals(args[i])) {
                inputFileName = args[i + 1];
                i += 2;
            } else if ("-outputFile".equals(args[i])) {
                outputFileName = args[i + 1];
                i += 2;
            } else {
                System.out.println("\"" + args[i] + "\" is not a valid parameter. It will be skipped.");
                i++;
            }
        }

        Calculable calculable = getCalculator(dataType);
        Scanner scanner;
        if (inputFileName != null) {
            scanner = new Scanner(new File(inputFileName));
        } else {
            scanner = new Scanner(System.in);
        }
        String result = calculable.calculate(sortType, scanner);
        if (outputFileName != null) {
            PrintWriter writer = new PrintWriter(outputFileName);
            writer.write(result);
            writer.flush();
            writer.close();
        } else {
            System.out.println(result);
        }
    }

    public static Calculable getCalculator(DataType type) {
        return switch (type) {
            case LONG -> new LongCalculator();
            case WORD -> new WordCalculator();
            case LINE -> new LineCalculator();
        };
    }
}
