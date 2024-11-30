@Work Logging

2024-11-05 Eleandro started researching Dynamic Programming.
2024-11-10 Eleandro started researching Minimum Distance Edit Algorithm. 
2024-11-12 Eleandro started researching Levenshtein Weighting.
2024-11-14 ELeandro started implementing dynamic programming for spelling correction in Java.
2024-11-16 Eleandro started debugging and testing code.
2024-11-20 Eleandro finalized debugging and testing the code.
2024-11-24 Eleandro drafted a small wiki about dynamic programming and displaying his code results. 








Eleandro's References: 

Introduction to Levenshtein distance. (2023, July 16). GeeksforGeeks. https://www.geeksforgeeks.org/introduction-to-levenshtein-distance/

Edit Distance Between 2 Strings - The Levenshtein Distance (“Edit Distance” on LeetCode). (n.d.). Www.youtube.com. https://www.youtube.com/watch?v=MiqoA-yF-0M

Edit Distance | DP-5. (2011, July 6). GeeksforGeeks. https://www.geeksforgeeks.org/edit-distance-dp-5/

Jurafsky, D., & Martin, J. (2020). Speech and Language Processing An Introduction to Natural Language Processing, Computational Linguistics, and Speech Recognition Third Edition draft. https://web.stanford.edu/~jurafsky/slp3/ed3book.pdf



Amarnoor's work:
Amarnoor worked on:
DNA scoring and alignment in Java language:

Global and Local alignment algorithms

1) Dynamic scoring

2) Decision making mechanism to choose which algorithm to use based on the difference in length. 

3) Traceback algorithm that extracts the optimal alignment

4) Visualization to depict the alignment

5) Execution time to measure the performance of algorithm

References:
https://theautomatic.net/2018/11/28/how-to-measure-dna-similarity-with-python-and-dynamic-programming/ 

https://www.youtube.com/watch?v=ipp-pNRIp4g&pp=ygUaTmVlZGxlbWFuLVd1bnNjaCBhbGdvcml0aG0%3D 

https://youtu.be/WRKQGwh_Mw0


 Balkar Singh

COMP 359 AB1
Assignment 3 
Topic - Dynamic Programming for scoring spelling errors (or DNA alignment scores)

## Tools Used for Assignment
- MS Visual Studio Code
- Jet Brains Pycharm 
- MS Word (for Rearch and Final Repot)
- VS Code Build-in Regex Module   
- Programming Language - Python
- Github

## Programs Developed
    - levenshtein_algo.py ( Initial implement of Dynamic programming for Levenshtein algorithm)
    - spellingsuggestion.py ( Application of Levenshtein algorithm for spelling error suggestion)


## Work Flow
- NOV 19, 2024 - Research on Dynamic Programming for scoring spelling error and Levenshtein algorithm for string comparisons. 
- Nov 22, 2024 - Implementation of levenshtein_algorithm function to perform operation: insertion, deletion, and substitution.
- Nov 23, 2024 - Matrix generation and manipulation 
               - Change from static strings to UserInput Strings
- Nov 25, 2024 - Exploring to develop a program of spelling suggestion
               - Extended the Levenshtein algorithm to implement spelling suggestions function.
               - creating a dictionary file containing 10000 common English words.
- Nov 27, 2024 - Testing the levenshteinalgo.py and recording data for Final Report
- Nov 28, 2024 - Uploading/Updating Github Repository.
               - Testing spelling suggestion with different dictionary files (changing words and range) and Worked on enhancing the spelling suggestion by moving from single length Strings to sentences 
               - Writing on Final Report
- Nov 29, 2024 - Finalizing Everything for submission

## Reference - (Research - Code - Final Reprot)
The complete beginners guide to dynamic programming -  https://stackoverflow.blog/2022/01/31/the-complete-beginners-guide-to-dynamic-programming/

The Levenshtein Distance Algorithm - https://medium.com/@tejaswiyadav221/the-levenshtein-distance-algorithm-a-string-metric-for-measuring-the-difference-between-two-269afbbddd34

Type error resarch - https://medium.com/@bhawana.prs/types-of-spelling-errors-de156517f226

Types of spelling erros - https://www.mw-literacy.com/blog/2022/4/17/the-three-types-of-spelling-errors

Github 10000 word Repo - https://github.com/first20hours/google-10000-english

Regex for VS code - https://learn.microsoft.com/en-us/visualstudio/ide/using-regular-expressions-in-visual-studio?view=vs-2022

MS COPILOT - make more spelling error for this sentence

Generated - The recived invitaton to the ceremony was mispalced, causing unnecesary confsion and delaays.

You 
count words in "I defintely think we should recieve the package tommorow. "
and "The recived invitaton to the ceremony was mispalced, causing unnecesary confsion and delaays."


Generated - The word count for each sentence is as follows:

"I defintely think we should recieve the package tommorow." — 9 words
"The recived invitaton to the ceremony was mispalced, causing unnecesary confsion and delaays." — 13 words ​
