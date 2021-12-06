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

    Step-1:

    We know that our input will contain only 37 different(that is 26 [a-z], 10[0-9], 1[']) characters, 
    so we counted all the character in a word given in vocaulary file (used them to find if both word a and b are anagrams or not). 
    So, we created a custom datatype called as 'node', which essentially counts all the characters of the word(in vocabulary) 
    in an array (following 0 based indexing) and stores the word also. 
    
    We counted apostrope at 1st index then digits from the 2nd index till 11th index and 
    then from 12th index we stored alphabets till 37th index. 
    
    Also made some comparators to check if word is correct (abides to our conditions or not).

    For example,

    'node' class for 'abc' will look like this

    Increment index 12th index by 1 (beacuse of 'a' in 'abc'), increment index 13th by 1 then index 14th by 1. 

    Blueprint array  --> [0,0,0,0,0,......,1,1,1......,0,0,0] (length 38)

    Then, for each word in vocabulary file we calculated blueprint array then hashed them in an ArrayList of 
    List of List where the primary indexing was done by length of word from 0 to 11th index.

    Now, we have processed our vocabulary file and have stored it in a hashtable for quick check of anagrams.

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    
    Step-2:

    We have to read each input and figure out all the 'anagrams' possible.
    So, we divide 'anagrams' into 0 spaced, 1 spaced and 2 spaced. 
    
    For 0 spaced 'anagrams', What we did is we checked whether we have hashed an anagram of given word in the hashtable or not. eg. word-'abc' then we go to hashtable's section
    of length = 3 and check for every word of length = 3, and compare the blueprint of one word with 'abc'. If we found same then we have got a 0 spaced anagram of it.  
    
    For 1 spaced 'anagrams',  'Anagram' will be of form of "A B" then what we do is we assume all lengths of A from 1 to ceil(totallength/2), then we go to hashtable's section     of length = len(A). And check if our input word contains any word of hashtable, if yes then we check if we have an 'anagram' of remaining character of input word(to make B)
    . If yes then we have found an 'anagram' "A B" of input word also we have found an 'anagram' "B A" also if A!=B. Now we can store both "A B" and "B A".
    
    For 2 spaced 'anagrams', For this we also took similar approach as previous section, that is, 'anagram' will look like "A B C" and now we assume all valid lengths(such that
    B and C are not empty) of input word then we searched whether there is an 'anagram' inputword(some length) in hashtable if yes, then we searched for whether there is an '
    anagram' of remaining word in hashtable (B found successfully) if yes then is there an anagram of remainig words of input word in hashtable, if yes then we have found C.
    Now, we can form all the permutations of "A B C" to get all possible 'anagram' of input word.
    
     
     
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

The main heavy duty work got simpliied because of we found an easy efficient way of storing the words from vocabulary. As, any brute force algorithm will take at most factorial order to complete for each input word. But our designed algorithm can do this in linear time for each word. Space complexity is linear wrt to vocabulary size in both the cases.

### Main synopsis of storing-

We took each word then counted every character's count and storred them in a hashtable divided in 12 sections (each word goes in according to their length). Then in each section we hashed the words using a primary hashing technique with seperate hashing.(Dividing hashtable into 12 sections reduced number of collisions from a factor of 3 (eg earlier collisions were 1200 then new collisions were 300)). So at worse, each subpart will have 300 (small) number of words, hence making our search for anagrams very efficient.

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

### Primary Hashing Technique-
   
    loop 38 times on each word
        hashvalue += i*blueprint array[i]
        
    This hash value gives the index of where to store this word in hashtable.
    
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

### A deep discussion on problem and time complexity has been given in 'A4.pdf'.
