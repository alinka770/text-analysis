
import core.*;
import view.View;

import java.io.File;
import java.util.*;

public class Application {

    private static final String[] VOWELS = {"a", "e", "i", "o", "u"};
    private View view;

    public Application() {

        this.view = new View();
    }

    public static void main(String[] args) {

        Application app = new Application();
        if (args.length < 1) {

            app.error("Too few parameters");
        } else {

            app.handleFilePaths(args);
        }
    }

    private void handleFilePaths(String... args) {

        boolean allExist = true;
        for (String filePath : args) {

            File file = new File(filePath);
            allExist &= file.exists();
            if (!allExist) {

                break;
            }
        }

        if (!allExist) {

            error("Not all file paths exist, cannot continue.");
            return;
        }

        long startTime = System.currentTimeMillis();
        for (String filePath : args) {

            carryOutStats(filePath);
        }
        long endTime = System.currentTimeMillis();

        view.printLine("Benchmark time: " + (double)(endTime - startTime) / 1000 + "secs");
    }
    private void carryOutStats(String filePath) {

        FileContent fc = new FileContent(filePath);
        view.printLine("==" + fc.getFilename() + "==");

        Iterator<String> charIterator = fc.charIterator();
        Iterator<String> wordIterator = fc.wordIterator();

        StatisticalAnalysis charAnalysis = new StatisticalAnalysis(charIterator);
        StatisticalAnalysis wordAnalysis = new StatisticalAnalysis(wordIterator);

        view.printLine("Char count: " + charAnalysis.size());
        view.printLine("Word count: " + wordAnalysis.size());
        view.printLine("Dict size: " + wordAnalysis.dictionarySize());

        Set<String> mostUsed = getMostUsedWords(wordAnalysis);

        int desiredTruncatedSize = mostUsed.size() > 100 ? 100 : mostUsed.size();

        Set<String> truncated = new TreeSet<>(new ArrayList<>(mostUsed).subList(0, desiredTruncatedSize));

        view.printLine("Most used words (>1%): " + truncated);

        wordCount(wordAnalysis, "love", "hate", "music");

        view.printLine("vowels %: " + String.format("%.2f", (double)charAnalysis.countOf(VOWELS) / charAnalysis.size() * 100));
        view.printLine("a:e count ratio: " + String.format("%.2f", (double)charAnalysis.countOf("a") / charAnalysis.countOf("e")));

        letterRatio(charAnalysis);
    }

    private void letterRatio(StatisticalAnalysis stat) {

        Set<String> uniqueLetters = stat.occurMoreThan(0); // => get whole dictionary
        final int overallCharacterCount = stat.size();

        String msg = "";
        for (String letter : uniqueLetters) {

            msg += "[ " + letter + " -> " + String.format("%.2f", (double)stat.countOf(letter) / overallCharacterCount * 100) + " ] ";
        }
        view.printLine(msg);
    }

    private void wordCount(StatisticalAnalysis stat, String... words) {

        for (String word : words) {

            view.printLine("'" + word + "' count: " + stat.countOf(word));
        }
    }

    private Set<String> getMostUsedWords(StatisticalAnalysis stat) {

        Set<String> uniqueWords = stat.occurMoreThan(0); // => get whole dictionary
        Set<String> mostUsed = new TreeSet<>();
        int wordCount = stat.size();

        for (String word : uniqueWords) {

            double occurrencePercent = ((double)stat.countOf(word) / wordCount) * 100;
            if (occurrencePercent > 1.0) {

               mostUsed.add(word);
            }
        }

        return mostUsed;
    }

    private void error(String msg) {

        view.printLine(msg);
    }
}
