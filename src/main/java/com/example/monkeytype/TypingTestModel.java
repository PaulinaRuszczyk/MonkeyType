package com.example.monkeytype;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class TypingTestModel {
    private List<String> words;

    public TypingTestModel(String languageFileName) {
        this.words = new ArrayList<>();
        loadWordsFromFile(languageFileName);
    }

    private void loadWordsFromFile(String languageFileName) {
        try(BufferedReader br = new BufferedReader(new FileReader(languageFileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                words.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getRandomWords(int count) {
        StringBuilder randomWords = new StringBuilder();
        Random random = new Random();
        for(int i = 0; i < count; i++) {
            randomWords.append(words.get(random.nextInt(words.size()))).append(" ");
        }
        return randomWords.toString();
    }
}