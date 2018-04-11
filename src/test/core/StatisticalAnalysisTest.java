package core;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class StatisticalAnalysisTest {

    private static final String[] VOWELS = {"a", "e", "i", "o", "u"};

    @Test
    public void testFirstTestFileResults() {

        FileContent fc = new FileContent("test.txt");

        Iterator<String> charIterator = fc.charIterator();
        Iterator<String> wordIterator = fc.wordIterator();

        StatisticalAnalysis charAnalysis = new StatisticalAnalysis(charIterator);
        StatisticalAnalysis wordAnalysis = new StatisticalAnalysis(wordIterator);

        assertEquals(charAnalysis.size(), 1031);
        assertEquals(wordAnalysis.size(), 268);
        assertEquals(wordAnalysis.dictionarySize(), 141);

        assertEquals(wordAnalysis.countOf("love"), 1);
        assertEquals(wordAnalysis.countOf("hate"), 0);
        assertEquals(wordAnalysis.countOf("music"), 3);

        assertEquals((double)charAnalysis.countOf(VOWELS) / charAnalysis.size(), 0.38, 0.01);
    }

    @Test
    public void testSecondTestFileResults() {

        FileContent fc = new FileContent("test2.txt");

        Iterator<String> charIterator = fc.charIterator();
        Iterator<String> wordIterator = fc.wordIterator();

        StatisticalAnalysis charAnalysis = new StatisticalAnalysis(charIterator);
        StatisticalAnalysis wordAnalysis = new StatisticalAnalysis(wordIterator);

        assertEquals(charAnalysis.size(), 939);
        assertEquals(wordAnalysis.size(), 235);
        assertEquals(wordAnalysis.dictionarySize(), 151);

        assertEquals(wordAnalysis.countOf("love"), 0);
        assertEquals(wordAnalysis.countOf("hate"), 2);
        assertEquals(wordAnalysis.countOf("music"), 1);

        assertEquals((double)charAnalysis.countOf(VOWELS) / charAnalysis.size(), 0.37, 0.01);
    }

    @Test
    public void testDickensTestFileResults() {

        FileContent fc = new FileContent("test_dickens_great.txt");

        Iterator<String> charIterator = fc.charIterator();
        Iterator<String> wordIterator = fc.wordIterator();

        StatisticalAnalysis charAnalysis = new StatisticalAnalysis(charIterator);
        StatisticalAnalysis wordAnalysis = new StatisticalAnalysis(wordIterator);

        assertEquals(charAnalysis.size(), 761674);
        assertEquals(wordAnalysis.size(), 188912);
        assertEquals(wordAnalysis.dictionarySize(), 10762);

        assertEquals(wordAnalysis.countOf("love"), 60);
        assertEquals(wordAnalysis.countOf("hate"), 4);
        assertEquals(wordAnalysis.countOf("music"), 4);

        assertEquals((double)charAnalysis.countOf(VOWELS) / charAnalysis.size(), 0.38, 0.01);
    }

    @Test
    public void testMobyTestFileResults() {

        FileContent fc = new FileContent("test_malville_moby.txt");

        Iterator<String> charIterator = fc.charIterator();
        Iterator<String> wordIterator = fc.wordIterator();

        StatisticalAnalysis charAnalysis = new StatisticalAnalysis(charIterator);
        StatisticalAnalysis wordAnalysis = new StatisticalAnalysis(wordIterator);

        assertEquals(charAnalysis.size(), 955387);
        assertEquals(wordAnalysis.size(), 219044);
        assertEquals(wordAnalysis.dictionarySize(), 16957);

        assertEquals(wordAnalysis.countOf("love"), 24);
        assertEquals(wordAnalysis.countOf("hate"), 9);
        assertEquals(wordAnalysis.countOf("music"), 4);

        assertEquals((double)charAnalysis.countOf(VOWELS) / charAnalysis.size(), 0.37, 0.01);
    }

}