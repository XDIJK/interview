# Anagram

## Build

It is assumed that `./interview/anagram` is the working directory.

### requisites
Java: openjdk version "17.0.7" 2023-04-18 LTS  
maven: version 3.9.1

### Run
To run the anagram comparison application you can simpy run:  
`mvn exec:java -Dexec.args="input1 input2 input3 input4" -X`

Where `-Dexec.args=` can be any space seperated pairs of words.  
The code takes into consideration any other utf-8 characters however cannot promise that the shell used to execute it will do the same.

### Run with docker
For convience I've also included a dockerfile which can be run the following way:  
`docker build -t anagram-comp .`  
`docker run --rm anagram-comp [input ....args]`

to remove the dockerfile run:  
`docker image rm anagram-comp`

### Tests
To run the unit tests run: `mvn test`


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

An anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.[1]  
For example, the word anagram itself can be rearranged into nag a ram, as well as the word binary into brainy and the word adobe into abode.

The original word or phrase is known as the subject of the anagram. Any word or phrase that exactly reproduces the letters in another order is an anagram.  
Someone who creates anagrams may be called an "anagrammatist",[2] and the goal of a serious or skilled anagrammatist is to produce anagrams that reflect or comment on their subject. (https://en.wikipedia.org/wiki/Anagram)


## Solving

### Initial thoughts
Initial thoughts for solving it, take two input strings split the string into a character array compare the content.

#### ambiguity and edge cases

Few things that came to mind.
1. How to deal with special characters, are they allowed to be introduced? (This is seen on the wikipedia examples therefor will choose to do so.)
    a. Should it only be punctiation or also other special characters such as
2. What about something like substitution of character for numbers such as seen in leetspeak (https://en.wikipedia.org/wiki/Leet)
3. What about non english languages

## Performance

Planning to do the following for the initial solving.  
1. string toCharArray() = O(N)  
2. string toLowerCase() = O(N)  
3. regex replace all, not 100% confident but if the regex and replaceAll implementation is sane = O(N)  
4. Arrays.Sort() = O(N log N)  
5. Array.Equals() = O(N)  

Therefor the worst case runtime complexity worst for this solution would be O(N log N).

### After googling performance
Seems like a common approach is to have a bounded array (count = int[num_of_supported_chars]) where the byte val of a char in the string is taken as the index of count.  
Which incremented for the first input and decremented for second input, leading to the count array being balanced at all values being zero if the two inputs are anagrams.  

Since I decided to support utf-8 word characters this would be quite the memory hungry approach, however the solution is O(N) instead of O(N log N)  
Another approach is by reading both strings into their own HashMap<Character, Interger> and compare both. This solution is also O(N) but more memory hungry than the naive first solution (not including the reduntant var assignments I did)  

## Build env
Build via github codespaces, running their default codespaces container.  
