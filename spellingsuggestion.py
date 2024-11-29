import json
import time
from levenshtein_algo import levenshtein_algorithm

# Loading dict2.json 
with open('dict2.json', 'r') as file:
    data = json.load(file)

#  dictionary (dict2.json) 
dictionary = data['words']

def spelling_suggestions(input_string, dictionary):
    suggestions = {}
    for words in dictionary:
        distance = levenshtein_algorithm(input_string, words)
        suggestions[words] = distance[0]
        
    word_match = []
    # Find the minimum distance
    min_distance = min(suggestions.values())

    # Find the matching words 
    for word, distance in suggestions.items():
        if distance == min_distance: 

            # Recording word(s), edit distance(s) 
            word_match.append((word, distance)) 

    return word_match

# main
#num_words = int(input("Enter the number of words: "))
string_input = str.lower((input("Enter the string/sentence: ")))

# System time performance counter - start
start_time = time.perf_counter()

text = string_input.split()
text_list = text

for i in text_list:
    word_match = spelling_suggestions(i, dictionary)

    # Printing suggestions
    print(f"Input word: {i}")
    print(f"Matching word(s): {word_match}")

# System time performance counter - end
end_time = time.perf_counter()
execution_time = end_time - start_time
print(f"Execution time: {execution_time:8f} seconds ")

# Reference 
# https://pkeilbach.github.io/htwg-practical-nlp/lectures/minimum_edit_distance/
# https://www.youtube.com/watch?v=LxUtFClVYKc&t=1s
# 10000 words repo for dict2.json
# https://github.com/first20hours/google-10000-english
