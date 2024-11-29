def levenshtein_algorithm(string1, string2):
    # String length
    m = len(string1)
    n = len(string2)

    # Initializing the zero matrix to the length of m(rows) and n(columns)
    d = [[0 for _ in range(n + 1)] for _ in range(m + 1)]

    # Character cost converstion for first row and column
    for i in range(m + 1):
        d[i][0] = i
    for j in range(n + 1):
        d[0][j] = j

    for i in range(1, m + 1):
        for j in range(1, n + 1):
            # String character comparision 
            if string1[i - 1] == string2[j - 1]:
                d[i][j] = d[i - 1][j - 1]
            # minimum cost    
            else:   
                d[i][j] = min(d[i][j - 1] + 1, #insertion
                              d[i - 1][j] + 1, #deletion
                              d[i - 1][j - 1] + 1) #replace

    edit_distance = d[m][n]
     # Return the edit distance and obtained matrix
    return edit_distance, d
