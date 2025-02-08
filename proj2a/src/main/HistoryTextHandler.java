package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import ngrams.NGramMap;
import ngrams.TimeSeries;

import java.util.List;


public class HistoryTextHandler extends NgordnetQueryHandler {
    NGramMap ngm;
    public HistoryTextHandler(NGramMap map){
        ngm = map;
    }


    @Override
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        int startYear = q.startYear();
        int endYear = q.endYear();

        String response = "";
        for (String word : words){
            response += word + ": ";
            response += ngm.weightHistory(word, startYear, endYear).toString() + "\n";
        }
        return response;
    }
}
