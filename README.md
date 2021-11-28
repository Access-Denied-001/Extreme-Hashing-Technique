# Hash-Table-and-Anagram-Finding

 
### Problem Statement- 

We want to exercise hashing techniques and find 'anagrams' of a string or bunch of strings, this definition of 'Anagram' is different from the normal definition. We were given a vocabulary of English words (see 'vocabulary.txt' which contains lowercase words only [a-z], digits [0-9], and apostrophe[']). Our goal is to print out all the valid 'anagrams' of given input string/strings (see 'input.txt') and print these all in 'output.txt' file in lexicographic order done on ASCII codes. 

Now, what is an 'Anagram'?

Anagram is two strings on which if you rearrange one of them you will get the second one. For example, 'art' and 'rat' are anagrams of each other. Now  additionally we can add spaces to these words, now  our definition becomes a little complicated. For example, 'bait' and 'a bit' are 'anagrams' of each other only if our vocabulary file contains 'bait', 'a' and 'bit' as a word (vocabulary contains a word in each line). So, we can add 0, 1, 2 spaces to produce all the valid 'anagrams'. And when you have found all the 'anagrams' then print '-1' for each input word (length from 0 to 12 character only) in 'input.txt'.  

Also, we want to do this in least time possible. Lets take one example to understand it correctly,

### Vocabulary File-

6

a

it

bit

bat

tab

i

### Input File-

2

bait

bb

### Output File-

a bit

bat i

bit a

i bat

i tab

tab i

-1

-1

### Note- 

In example(bait), we used 1 space only because of our vocabulary file(any other breaking of word will not produce a word belonging to the vocaulary file). If we break our 'anagram' 'a bit' into 'a b it' then 'b' and 'it' are not in vocabulary file. So, we cannot have more than 1 space here but in general we can have 0,1,2 space for all 'anagarams'. Also, 0 space word is not possible since 'bait' is not in our vocabulary file.

In example(bb), 'bb' is not is vocabulary also 'b' is not in vocabulary so no 'anagram' of 'bb' possible.

### Approach (Source code- Anagram.java)

    ### Step-1:

    We know that our input will contain only 37 different(that is 26 [a-z], 10[0-9], 1[']) characters, so we counted all the character in a word given in vocaulary file (used them to find if both word a and b are anagrams or not). 
    So, we created a custom datatype called as 'node', which essentially counts all the characters in an array(following 0 based indexing) and stores the word also. We counted apostrope at 1st index then digits from 
    2nd index till 11th index and then from 12th index we stored alphabets till 37th index. Also made some comparators to check if word is correct (abides to our conditions or not).

    For example,

    'node' for 'abc' will look like this

    Increment index 12th index by 1 (beacuse of 'a' in 'abc'), increment index 13th by 1 then index 14th by 1. 

    Blueprint array  --> [0,0,0,0,0,......,1,1,1......,0,0,0]

    Then, for each word in vocabulary file we calculated blueprint array then hashed them in an ArrayList of List of List where the primary indexing was done by length of word from 0 to 11th index.

    Now, we have processed our vocabulary file and have stored it in a hashtable for quick check of anagrams.

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    
    ### Step-2:

    We have to read each input and figure out all the 'anagrams' possible

