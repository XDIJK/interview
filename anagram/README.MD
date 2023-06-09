# Anagram

## Description
Project to do the following task:

Write a Java program that checks if two texts are anagrams of each other.
Please use the english wikipedia entry for the definition of anagram.
The solution has to be in Java or Kotlin.
Feel free to use your favorite IDE, unit test frameworks, automated build system etc.
You can prioritize however you like (performance, readability, extensibility, …).
Googling is a good thing :)

As requested as part of the recruiting process.

## Definition

An anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.[1] For example, the word anagram itself can be rearranged into nag a ram, as well as the word binary into brainy and the word adobe into abode.

The original word or phrase is known as the subject of the anagram. Any word or phrase that exactly reproduces the letters in another order is an anagram. Someone who creates anagrams may be called an "anagrammatist",[2] and the goal of a serious or skilled anagrammatist is to produce anagrams that reflect or comment on their subject. (https://en.wikipedia.org/wiki/Anagram)


## Solving

### Initial thoughts
Initial thoughts for solving it, take two input strings split the string into a character array compare the content.

#### ambiguity and edge cases

Few things that came to mind.
1. How to deal with special characters, are they allowed to be introduced? (This is seen on the wikipedia examples therefor will choose to do so.)
2. What about something like substitution of character for numbers such as seen in leetspeak (https://en.wikipedia.org/wiki/Leet)

## Performance
Planning to do the following for the initial solving.
1. string toCharArray() = O(N)
2. Arrays.Sort() = O(N log N)
3. Array.Equals() = O(N)

Therefor the runtime complexity worstcase for this solution would be O(N log N).



## Build env
Build via github codespaces, running their default codespaces container.