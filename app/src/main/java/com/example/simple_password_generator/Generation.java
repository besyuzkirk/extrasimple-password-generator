package com.example.simple_password_generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generation {

    private boolean upperCheck;
    private boolean lowerCheck;
    private boolean symbolsCheck;
    private boolean numbersCheck;

    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String PUNCTUATION = "!@#$%&*()_+-=[]|,./?><";

    private int size;

    List<String> charCategories = new ArrayList<>(4);

    public Generation(boolean upperCheck , boolean lowerCheck, boolean symbolsCheck , boolean numbersCheck , int size) {
        this.upperCheck = upperCheck;
        this.lowerCheck = lowerCheck;
        this.symbolsCheck= symbolsCheck;
        this.numbersCheck = numbersCheck;
        this.size = size;
    }
    public void setChecks() {
        if(upperCheck){
            charCategories.add(UPPER);
        }
        if(lowerCheck){
            charCategories.add(LOWER);
        }
        if(numbersCheck) {
            charCategories.add(DIGITS);
        }
        if (symbolsCheck) {
            charCategories.add(PUNCTUATION);
        }
    }
    StringBuilder password = new StringBuilder(size);
    Random random = new Random(System.nanoTime());

    public String generate(){

        for (int i = 0; i < size; i++) {
            String charCategory = charCategories.get(random.nextInt(charCategories.size()));
            password.append(charCategory.charAt( random.nextInt(charCategory.length())));
        }
        return new String(password);
    }

}
