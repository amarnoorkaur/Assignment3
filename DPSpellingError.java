package assignent;

import java.util.Scanner;

public class DPSpellingError {

    public static int scoringSpelling(String corWord, String missWord) {
    	
        int m = corWord.length();
        int n = missWord.length();
        
        //Creating the matrix to store the edited distance
        
        int[][] dp = new int[m + 1][n + 1];
        
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
            	
            	//If the characters are the same nothing is needed 
            	
                if (corWord.charAt(i - 1) == missWord.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1],
                                   Math.min(dp[i - 1][j],
                                            dp[i][j - 1]));
                }
            }
        }
        
        return dp[m][n];
    }

    public static void main(String[] args) {
    	
    
    	 Scanner scanner = new Scanner(System.in);

         System.out.print("Enter the correct word: ");
         String corrWord = scanner.nextLine().trim();

         System.out.print("Enter the misspelled word: ");
         String missWord = scanner.nextLine().trim();
        
        //Calling the timer class
         
        Timer timer = new Timer();
       
        timer.start();
        
        // Run the algorithm
        int score = scoringSpelling(corrWord, missWord);
        
        // Stop the timer
        timer.stop();
        
        System.out.println("The correct word is: " + corrWord);
        System.out.println("The misspelled word is: " + missWord);
        System.out.println("The spelling error score using the Levenshtein Distance is: " + score);
        System.out.println("THe time taken by the algorithm is: " + timer.getElapsedTime() + " nanoseconds");
    }
}

class Timer {
    private long startTime;
    private long endTime;

    public void start() {
        startTime = System.nanoTime();
    }

    public void stop() {
        endTime = System.nanoTime();
    }

    public long getElapsedTime() {
        return endTime - startTime;
    }
}