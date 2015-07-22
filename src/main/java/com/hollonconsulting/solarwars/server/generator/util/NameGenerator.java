package com.hollonconsulting.solarwars.server.generator.util;

import java.util.Random;

public class NameGenerator {
    private static final String VOWELS = "aaaiiueeeoy";
    private static final String CONSONANT = "bcdfghjklmnpqrstvwxz";
    private static Random rand;

    static {
        rand = new Random();
    }

    public static String getName(){
        int length = 1 + rand.nextInt(2) + rand.nextInt(3)/2 + rand.nextInt(4);

        StringBuilder name = new StringBuilder(length*2);

        for(int i = 0; i < length; i++){
            name.append(getConsonant());
            name.append(getVowel());
        }
        if(rand.nextBoolean()){
            name.append(getConsonant());
        }

        //uppercase first letter
        name.replace(0,1,Character.toString(name.charAt(0)).toUpperCase());

        return name.toString();
    }

    private static char getConsonant(){
        return VOWELS.charAt(rand.nextInt(VOWELS.length()));
    }
    private static char getVowel(){
        return CONSONANT.charAt(rand.nextInt(CONSONANT.length()));
    }
}
