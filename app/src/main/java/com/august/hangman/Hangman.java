package com.august.hangman;

import java.util.ArrayList;
import java.util.Random;

public class Hangman {

    private ArrayList<String> words;
    private Random r;
    private String mysteryWord;
    private ArrayList<Character> wrongLetters;
    private ArrayList<Character> guessedCorrectly;
    private boolean[] correctLetters;
    private int attemptsLeft;

    public Hangman() {
        words = new ArrayList<String>();
        r = new Random();
        wrongLetters = new ArrayList<Character>();
        guessedCorrectly = new ArrayList<Character>();
        attemptsLeft = 10;
        fillWords();
        setMysteryWord();
    }

    public Hangman(String mysteryWord, int attemptsLeft, String guessedWord,String wrong) {
        this.mysteryWord = mysteryWord;
        this.attemptsLeft = attemptsLeft;
        wrongLetters = new ArrayList<Character>();
        guessedCorrectly = new ArrayList<Character>();
        correctLetters = new boolean[mysteryWord.length()];
        setWrongLetters(wrong);
        setGuessedWord(guessedWord);
    }

    private void fillWords() {
        words.add("aligator");
        words.add("laptop");
        words.add("airplane");
        words.add("tennis");
        words.add("europe");
        words.add("nose");
        words.add("jazz");
        words.add("alphabet");
        words.add("club");
        words.add("lemon");
        words.add("morning");
    }

    private void setMysteryWord() {
        mysteryWord = words.get(r.nextInt(words.size()));
        correctLetters = new boolean[mysteryWord.length()];
    }

    public void takeGuess(char guess) {
        if (isCorrectLetter(guess)) {
            guessedCorrectly.add(guess);
            setCorrectLetters(guess);
        }
        else {
            wrongLetters.add(guess);
            attemptsLeft--;
        }
    }

    public boolean alreadyGuessed(char guess) {
        for (int i=0;i<wrongLetters.size();i++) {
            if (guess==wrongLetters.get(i))
                return true;
        }
        for (int i=0;i<guessedCorrectly.size();i++) {
            if (guess==guessedCorrectly.get(i))
                return true;
        }

        return false;
    }

    private boolean isCorrectLetter(char guess) {
        for (int i=0;i<mysteryWord.length();i++) {
            if (guess==mysteryWord.charAt(i))
                return true;
        }
        return false;
    }

    private void setCorrectLetters(char guess) {
        for (int i=0;i<correctLetters.length;i++)
            correctLetters[i] = guess==mysteryWord.charAt(i);
    }

    private void setGuessedWord(String guessedWord) {
        for (int i=0;i<correctLetters.length;i++) {
            correctLetters[i] = guessedWord.charAt(i) != '_';
            if (guessedWord.charAt(i) != '_')
                guessedCorrectly.add(guessedWord.charAt(i));
        }
    }

    private void setWrongLetters(String wrong) {
        for (int i=0;i<wrong.length();i++) {
            if (wrong.charAt(i) != ' ')
                wrongLetters.add(wrong.charAt(i));
        }
    }

    public boolean hasWon() {
        for (int i=0;i<correctLetters.length;i++) {
            if (!correctLetters[i])
                return false;
        }
        return true;
    }
    public String getMysteryWord() {
        return mysteryWord;
    }
    public String getWrongLetters() {
        String result = "";
        for (int i=0;i<wrongLetters.size();i++)
            result += Character.toString(wrongLetters.get(i))+" ";
        return result;
    }
    public int getAttemptsLeft() {
        return attemptsLeft;
    }
    public String getGuessedWord() {
        String result = "";
        for (int i=0;i<correctLetters.length;i++)
            result += correctLetters[i]? Character.toString(mysteryWord.charAt(i)):"_";
        return result;
    }



}
