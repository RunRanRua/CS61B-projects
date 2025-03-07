package ngrams;

import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.Collection;

/**
 * An object that provides utility methods for making queries on the
 * Google NGrams dataset (or a subset thereof).
 *
 * An NGramMap stores pertinent data from a "words file" and a "counts
 * file". It is not a map in the strict sense, but it does provide additional
 * functionality.
 *
 * @author Josh Hug
 */
public class NGramMap {
    // TODO: Add any necessary static/instance variables.
    private ArrayList<String> words;
    private ArrayList<TimeSeries> tsList;
    private TimeSeries totalTS;


    /**
     * Constructs an NGramMap from WORDSFILENAME and COUNTSFILENAME.
     */
    public NGramMap(String wordsFilename, String countsFilename) {
        // TODO: Fill in this constructor. See the "NGramMap Tips" section of the spec for help.
        words = new ArrayList<>();
        tsList = new ArrayList<>();
        totalTS = new TimeSeries();

        readFile_w(wordsFilename);
        readFile_c(countsFilename);
    }
    private void readFile_w(String filename){
        // load file data
        In in = new In(filename);
        while (!in.isEmpty()){
            String[] splitLine = in.readLine().split("\t");
            String w = splitLine[0];
            int ind;
            // check if already in the list
            if (!words.contains(w)){
                words.add(w);
                tsList.add(new TimeSeries());
            }
            ind = words.indexOf(w);

            // treat ts
            Integer y = Integer.valueOf(splitLine[1]);
            Double v = Double.valueOf(splitLine[2]);
            TimeSeries wordTS = tsList.get(ind);
            wordTS.put(y,v + wordTS.getOrDefault(y,0.));
        }
    }
    private void readFile_c(String filename){
        In in = new In(filename);
        while (!in.isEmpty()){
            String nextLine = in.readLine();
            String[] splitLine = nextLine.split(",");
            totalTS.put(Integer.valueOf(splitLine[0]), Double.valueOf(splitLine[1]));
        }
    }

    /**
     * Provides the history of WORD between STARTYEAR and ENDYEAR, inclusive of both ends. The
     * returned TimeSeries should be a copy, not a link to this NGramMap's TimeSeries. In other
     * words, changes made to the object returned by this function should not also affect the
     * NGramMap. This is also known as a "defensive copy". If the word is not in the data files,
     * returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word, int startYear, int endYear) {
        // TODO: Fill in this method.
        // find word index
        int ind = words.indexOf(word);
        // find corresponding ts
        return (ind == -1) ? new TimeSeries() : new TimeSeries(tsList.get(ind), startYear, endYear);
    }

    /**
     * Provides the history of WORD. The returned TimeSeries should be a copy, not a link to this
     * NGramMap's TimeSeries. In other words, changes made to the object returned by this function
     * should not also affect the NGramMap. This is also known as a "defensive copy". If the word
     * is not in the data files, returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word) {
        // TODO: Fill in this method.
        // find word index
        int ind = words.indexOf(word);
        // find corresponding ts
        TimeSeries ts = new TimeSeries();
        return (ind== -1) ? ts : ts.plus(tsList.get(ind));
    }

    /**
     * Returns a defensive copy of the total number of words recorded per year in all volumes.
     */
    public TimeSeries totalCountHistory() {
        // TODO: Fill in this method.
        return new TimeSeries().plus(totalTS);
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD between STARTYEAR
     * and ENDYEAR, inclusive of both ends. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word, int startYear, int endYear) {
        // TODO: Fill in this method.
        if (!words.contains(word)){
            return new TimeSeries();
        }
        TimeSeries wordTS = countHistory(word,startYear,endYear);
        return wordTS.dividedBy(totalTS);
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD compared to all
     * words recorded in that year. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word) {
        // TODO: Fill in this method.
        if (!words.contains(word)){
            return new TimeSeries();
        }
        TimeSeries wordTS = countHistory(word);
        return wordTS.dividedBy(totalTS);
    }

    /**
     * Provides the summed relative frequency per year of all words in WORDS between STARTYEAR and
     * ENDYEAR, inclusive of both ends. If a word does not exist in this time frame, ignore it
     * rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words,
                                          int startYear, int endYear) {
        // TODO: Fill in this method.
        TimeSeries sumTS = new TimeSeries();
        for (String word : words){
            TimeSeries wordTS = weightHistory(word, startYear, endYear);
            sumTS.plus(wordTS);
        }
        return sumTS;
    }

    /**
     * Returns the summed relative frequency per year of all words in WORDS. If a word does not
     * exist in this time frame, ignore it rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words) {
        // TODO: Fill in this method.
        TimeSeries sumTS = new TimeSeries();
        for (String word : words){
            TimeSeries wordTS = weightHistory(word);
            sumTS.plus(wordTS);
        }
        return sumTS;
    }

    // TODO: Add any private helper methods.
    // TODO: Remove all TODO comments before submitting.
}
