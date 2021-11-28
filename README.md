# Hash-Table-and-Anagram-Finding


Problem Statement- We want to exercise hashing techniques and find 'anagrams' of a string or bunch of a strings, this definition of 'Anagram' is different from the normal definition. We were given a vocabulary of English words (see 'vocabulary.txt' which contains lowercase words only [a-z], digits [0-9], and apostrophe[']). Our goal is to print out all the valid 'anagams' of given input string/strings (see 'input.txt') and print these all in 'output.txt' file. 

Now, what is 'Anagram'?

Anagram is two strings on which if you rearrange one of them you will get the second one. For example, 'art' and 'rat' are anagrams of each other. Now  additionally we can add spaces to these words, now  our definition becomes a little complicated. For example, 'bait' and 'a bit' are 'anagrams' of each other only if our vocabulary file contains 'bait', 'a' and 'bit' as a word (vocabulary contains a word in each line). So, we can add 0, 1, 2 spaces to produce all the valid 'anagrams'. And when you have found all the 'anagrams' then print -1 for each word.  
