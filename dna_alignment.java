import java.util.Scanner;

public class DNAAlignment {
    // Scoring the constants to choose which alignment to be used
    private static final int MATCH = 1;
    private static final int MISMATCH = -1;
    private static final int GAP = -2;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Inputing the DNA sequences
        System.out.println("Enter first DNA sequence:");
        String seq1 = scanner.nextLine().toUpperCase();

        System.out.println("Enter second DNA sequence:");
        String seq2 = scanner.nextLine().toUpperCase();

        // Deciding the algorithm to use based on sequence lengths
        boolean useLocalAlignment = shouldUseLocalAlignment(seq1, seq2);

        System.out.println("\nSelected Algorithm: " +
                (useLocalAlignment ? "Local Alignment" : "Global Alignment"));

        // Measuring the execution time
        long startTime = System.nanoTime();

        // Performing alignment
        int[][] scores;
        String[] alignment;

        if (useLocalAlignment) {
            scores = calculateLocalAlignmentMatrix(seq1, seq2);
            alignment = tracebackLocal(scores, seq1, seq2);
        } else {
            scores = calculateAlignmentMatrix(seq1, seq2);
            alignment = traceback(scores, seq1, seq2);
        }

        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;

        // Display results
        System.out.println("\nAlignment Score: " + scores[seq1.length()][seq2.length()]);
        System.out.println("Aligned Sequences:");
        System.out.println(alignment[0]);
        System.out.println(alignment[1]);

        // score matrix
        System.out.println("\nAlignment Score Matrix:");
        printScoreMatrix(scores);

        // alignment visualization
        String visualization = visualizeAlignment(alignment[0], alignment[1]);
        System.out.println("Alignment Visualization:");
        System.out.println(visualization);

        // execution time in milliseconds
        System.out.printf("\nExecution Time: %.3f ms%n", executionTime / 1e6);
    }

    // Function to decide if local alignment should be used
    private static boolean shouldUseLocalAlignment(String seq1, String seq2) {
        // If sequences are significantly different in length, use local alignment
        return Math.abs(seq1.length() - seq2.length()) > 10;
    }

    // Global alignment using Needleman-Wunsch algorithm
    private static int[][] calculateAlignmentMatrix(String seq1, String seq2) {
        int m = seq1.length();
        int n = seq2.length();
        int[][] dp = new int[m + 1][n + 1];

        // Initialize matrix
        for (int i = 0; i <= m; i++) dp[i][0] = i * GAP;
        for (int j = 0; j <= n; j++) dp[0][j] = j * GAP;

        // Fill the matrix
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                int matchScore = seq1.charAt(i - 1) == seq2.charAt(j - 1) ? MATCH : MISMATCH;
                dp[i][j] = Math.max(dp[i - 1][j - 1] + matchScore, // Match/Mismatch
                        Math.max(dp[i - 1][j] + GAP,              // Gap in seq2
                                dp[i][j - 1] + GAP));            // Gap in seq1
            }
        }

        return dp;
    }

    // Local alignment using Smith-Waterman algorithm
    private static int[][] calculateLocalAlignmentMatrix(String seq1, String seq2) {
        int m = seq1.length();
        int n = seq2.length();
        int[][] dp = new int[m + 1][n + 1];

        // Initialize matrix
        for (int i = 0; i <= m; i++) dp[i][0] = 0;
        for (int j = 0; j <= n; j++) dp[0][j] = 0;

        // Fill the matrix
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                int matchScore = seq1.charAt(i - 1) == seq2.charAt(j - 1) ? MATCH : MISMATCH;
                dp[i][j] = Math.max(0, // Local alignment: scores can't be negative
                        Math.max(dp[i - 1][j - 1] + matchScore, // Match/Mismatch
                                Math.max(dp[i - 1][j] + GAP,    // Gap in seq2
                                        dp[i][j - 1] + GAP)));  // Gap in seq1
            }
        }

        return dp;
    }

    // Traceback for global alignment
    private static String[] traceback(int[][] scores, String seq1, String seq2) {
        StringBuilder alignedSeq1 = new StringBuilder();
        StringBuilder alignedSeq2 = new StringBuilder();

        int i = seq1.length();
        int j = seq2.length();

        while (i > 0 && j > 0) {
            int score = scores[i][j];
            int diag = scores[i - 1][j - 1];
            int up = scores[i - 1][j];
            int left = scores[i][j - 1];

            if (score == diag + (seq1.charAt(i - 1) == seq2.charAt(j - 1) ? MATCH : MISMATCH)) {
                alignedSeq1.append(seq1.charAt(i - 1));
                alignedSeq2.append(seq2.charAt(j - 1));
                i--;
                j--;
            } else if (score == up + GAP) {
                alignedSeq1.append(seq1.charAt(i - 1));
                alignedSeq2.append('-');
                i--;
            } else {
                alignedSeq1.append('-');
                alignedSeq2.append(seq2.charAt(j - 1));
                j--;
            }
        }

        while (i > 0) {
            alignedSeq1.append(seq1.charAt(i - 1));
            alignedSeq2.append('-');
            i--;
        }
        while (j > 0) {
            alignedSeq1.append('-');
            alignedSeq2.append(seq2.charAt(j - 1));
            j--;
        }

        return new String[]{alignedSeq1.reverse().toString(), alignedSeq2.reverse().toString()};
    }

    // Traceback for local alignment
    private static String[] tracebackLocal(int[][] scores, String seq1, String seq2) {
        StringBuilder alignedSeq1 = new StringBuilder();
        StringBuilder alignedSeq2 = new StringBuilder();

        // Find the max score position
        int maxI = 0, maxJ = 0, maxScore = 0;
        for (int i = 1; i <= seq1.length(); i++) {
            for (int j = 1; j <= seq2.length(); j++) {
                if (scores[i][j] > maxScore) {
                    maxScore = scores[i][j];
                    maxI = i;
                    maxJ = j;
                }
            }
        }

        int i = maxI, j = maxJ;
        while (i > 0 && j > 0 && scores[i][j] > 0) {
            int score = scores[i][j];
            int diag = scores[i - 1][j - 1];
            int up = scores[i - 1][j];
            int left = scores[i][j - 1];

            if (score == diag + (seq1.charAt(i - 1) == seq2.charAt(j - 1) ? MATCH : MISMATCH)) {
                alignedSeq1.append(seq1.charAt(i - 1));
                alignedSeq2.append(seq2.charAt(j - 1));
                i--;
                j--;
            } else if (score == up + GAP) {
                alignedSeq1.append(seq1.charAt(i - 1));
                alignedSeq2.append('-');
                i--;
            } else {
                alignedSeq1.append('-');
                alignedSeq2.append(seq2.charAt(j - 1));
                j--;
            }
        }

        return new String[]{alignedSeq1.reverse().toString(), alignedSeq2.reverse().toString()};
    }

    // Method to visualize the alignment
    private static String visualizeAlignment(String alignedSeq1, String alignedSeq2) {
        StringBuilder visualization = new StringBuilder();

        for (int i = 0; i < alignedSeq1.length(); i++) {
            if (alignedSeq1.charAt(i) == alignedSeq2.charAt(i)) {
                visualization.append('|');  // Match
            } else {
                visualization.append(' ');  // Mismatch or gap
            }
        }

        return visualization.toString();
    }

    // Method to print the score matrix
    private static void printScoreMatrix(int[][] scores) {
        for (int i = 0; i < scores.length; i++) {
            for (int j = 0; j < scores[i].length; j++) {
                System.out.print(scores[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
