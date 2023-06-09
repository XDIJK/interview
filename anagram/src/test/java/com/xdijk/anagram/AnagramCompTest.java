package com.xdijk.anagram;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

/**
 * Unit test for AnagramComp.
 */
public class AnagramCompTest {

    @Test
    public void MainTest() {
        assertDoesNotThrow(() -> AnagramComp.main(new String[] { "New York Times", "monkeys write" }));
        Exception exception = assertThrows(Exception.class, () -> AnagramComp.main(new String[] { "New York Times" }));

        var msg = exception.getMessage();
        assertTrue(msg.contains("Invalid number of arguments"));
    }

    /**
     * Non exhaustive list of anagrams attempting to cover a range of input
     * including unicode characters and special characters.
     */
    @ParameterizedTest
    @CsvSource({
            "New York Times, monkeys write",
            "Church of Scientology, rich-chosen goofy cult",
            "McDonald's restaurants, Uncle Sam's standard rot",
            "evil, vile",
            "William Shakespeare, I am a weakish speller",
            "IM FEARLESS, LE SSERAFIM",
            "Anagrams, Ars magna",
            "가나, 나가",
            "국왕, 왕국"
    })
    public void AnagramCompTestTrue(String input1, String input2) {
        assertTrue(AnagramComp.anagram(input1, input2));
    }

    /**
     * Non exhaustive list of non-anagrams attempting to cover a range of input
     * including unicode characters and special characters.
     */
    @ParameterizedTest
    @CsvSource({
            "New York Times, monkey write",
            "Church of Scientology, rich-chosen loafy cult",
            "McDonald's restaurants, Uncle Sam's standard dance",
            "evil, great",
            "William Shakes, I am a weakish speller",
            "Anagrams, magna",
            "가나, 가\uC655",
            "국왕, 왕\uAC00"
    })
    public void AnagramCompTestFalse(String input1, String input2) {
        assertFalse(AnagramComp.anagram(input1, input2));
    }

    @ParameterizedTest
    @NullAndEmptySource
    public void AnagramCompTestCheckNullAndEmpty(String input) {
        assertFalse(AnagramComp.anagram(input, input));
    }

    /**
     * 
     */
    @ParameterizedTest
    @CsvSource({
            "New York Times', NewYorkTimes",
            "Church@Scientology, ChurchScientology",
            "McDonald/restaurants, McDonaldrestaurants",
            "evil\u3002great, evilgreat",
    })
    public void stripSpecialCharactersTest(String input, String expected) {
        assertEquals(expected, AnagramComp.stripSpecialCharacters(input));
    }

    /*
     * @CsvSource doesn't play nice with some special characters and breaks the
     * Parameterized input
     * Therefor it is split into two seperate tests.
     */
    @Test
    public void stripSpecialCharactersTest2() {
        assertEquals("Anagramsmagna", AnagramComp.stripSpecialCharacters("Anagrams \n magna"));

        assertEquals("\u6BDB\u7403", 
            AnagramComp.stripSpecialCharacters("\u300C\u6BDB\u7403\u300D")); // Chinese 「毛球」to 毛球

        assertEquals("\uADF8\uB807\uAD6C\uB098",
            AnagramComp.stripSpecialCharacters("\uADF8\uB807\uAD6C\uB098~")); // Korean: 그렇구나~ to 그렇구나
    }
}
