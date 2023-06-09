package com.xdijk.anagram;

import java.util.HashMap;

/**
 * AnagramComp simple class containing static functions to determine if two
 * input strings are anagrams of oneother
 */
public class AnagramComp {

    static HashMap<Character, Integer> map = new HashMap<Character, Integer>();
    static HashMap<Character, Integer> map2 = new HashMap<Character, Integer>();

    public static void main(String[] args) throws Exception {
        // Check if each argument is even or rather dividible by 2 such as that we can
        // always form pairs and pass multiple arguments.
        if (args.length % 2 == 0) {
            for (var i = 0; i < args.length; i = i + 2) {
                System.out.println(anagram(args[i], args[i + 1]));
            }
        } else {
            // (args.length + 2) - (args.length % 2) return closest in dividible by 2
            // greater than arg.length
            throw new Exception(String.format("Invalid number of arguments, amount provided: %s expected: %s",
                    args.length, (args.length + 2) - (args.length % 2)));
        }
    }

    /*
     * There is argument to be made that this being a even protected function is a
     * code-smell since it is only used in Main()
     */
    protected static boolean anagram(String input, String input2) {
        if (input != null && !input.trim().isEmpty() || input2 != null && !input2.trim().isEmpty()) {
            // To lowercase() to ensure that sorting will be the same between words and
            // sentences
            input = input.toLowerCase();
            input2 = input2.toLowerCase();

            input = stripSpecialCharacters(input);
            input2 = stripSpecialCharacters(input2);

            // Since I am considering introducing punctiation in an anagram as valid it has
            // had to be sanitized before an early exit.
            if (input.length() != input2.length()) {
                return false;
            }

            HashMap<Character, Integer> map = readStringIntoMap(input);
            HashMap<Character, Integer> map2 = readStringIntoMap(input2);

            return map.equals(map2);
        }

        return false;
    }

    /*
     * reads string into HashMap<Character, Integer> where character is the key and
     * Integer is the amount of occurences of the character within the given string.
     */
    private static HashMap<Character, Integer> readStringIntoMap(String input) {
        var map = new HashMap<Character, Integer>();
        var charArr = input.toCharArray();
        for (char c : charArr) {
            if (map.get(c) == null) {
                map.put(c, 1);
            } else {
                var val = map.get(c);
                map.put(c, ++val);
            }
        }

        return map;
    }

    /*
     * Since it is just a small technical test this is all written in a single
     * class.
     * If util functions like this are common throughout an applicationt his would
     * be extracted into a utils package.
     * There is argument to be made that this being a even protected function is a
     * code-smell since it is only used in anagram()
     * However going against convention I believe that a validation function like
     * this should be tested.
     */
    protected static String stripSpecialCharacters(String input) {
        // [\\P{LD}+^] is a regex that matches any unicode non-word character or digit
        // Instead of a common a-zA-Z0-9 based match which may not deal with non-roman
        // characters
        var output = input.replaceAll("[\\P{LD}+^]", "");
        return output;
    }
}
