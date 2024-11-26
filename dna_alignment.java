import java.util.Scanner;

public class DNAAlignment {
    // Scoring constants
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

        // To measure the execution time take for alignment
        long startTime = System.nanoTime();

        // aligining
        int[][] scores = calculateAlignmentMatrix(seq1, seq2);
        String[] alignment = traceback(scores, seq1, seq2);

        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;

        // Displaying  results
        System.out.println("\nAlignment Score: " + scores[seq1.length()][seq2.length()]);
        System.out.println("Aligned Sequences:");
        System.out.println(alignment[0]);
        System.out.println(alignment[1]);

        // Displaying execution time in milliseconds
        System.out.printf("\nExecution Time: %.3f ms%n", executionTime / 1e6);
    }

    // Calculating the alignment matrix using Needleman-Wunsch algorithm
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

    // Traceback to generate the aligned sequences
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

        // Reverse the sequences as we constructed them backwards
        return new String[]{alignedSeq1.reverse().toString(), alignedSeq2.reverse().toString()};
    }
}
