import java.util.*;

public class MediSearch {

    // =====================================================
    // 1. KMP PATTERN MATCHING
    // =====================================================

    static void createLPS(String pattern, int[] lps) {

        int length = 0;
        int i = 1;

        lps[0] = 0;

        while (i < pattern.length()) {

            if (pattern.charAt(i) == pattern.charAt(length)) {
                length++;
                lps[i] = length;
                i++;
            } 
            else {

                if (length != 0) {
                    length = lps[length - 1];
                } 
                else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
    }

    static void kmpSearch(String text, String pattern) {

        int[] lps = new int[pattern.length()];

        createLPS(pattern, lps);

        int i = 0;
        int j = 0;

        while (i < text.length()) {

            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            }

            if (j == pattern.length()) {

                System.out.println("Pattern Found!");
                System.out.println("Starting Index: " + (i - j));
                return;

            } 
            else if (i < text.length()
                    && text.charAt(i) != pattern.charAt(j)) {

                if (j != 0) {
                    j = lps[j - 1];
                } 
                else {
                    i++;
                }
            }
        }

        System.out.println("Pattern Not Found");
    }


    // =====================================================
    // 2. FUZZY SEARCH - EDIT DISTANCE
    // =====================================================

    static int editDistance(String a, String b) {

        int m = a.length();
        int n = b.length();

        int[][] dp = new int[m + 1][n + 1];

        // Initialize first row
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

        // Initialize first column
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }

        // Fill DP table
        for (int i = 1; i <= m; i++) {

            for (int j = 1; j <= n; j++) {

                if (a.charAt(i - 1) == b.charAt(j - 1)) {

                    dp[i][j] = dp[i - 1][j - 1];

                } 
                else {

                    int insert = dp[i][j - 1];
                    int delete = dp[i - 1][j];
                    int replace = dp[i - 1][j - 1];

                    dp[i][j] = 1 + Math.min(
                            insert,
                            Math.min(delete, replace)
                    );
                }
            }
        }

        return dp[m][n];
    }


    // =====================================================
    // 3. SIMILARITY SEARCH - JACCARD SIMILARITY
    // =====================================================

    static double jaccardSimilarity(
            Set<String> patient1,
            Set<String> patient2) {

        // Find common symptoms
        Set<String> common = new HashSet<>(patient1);
        common.retainAll(patient2);

        // Find all unique symptoms
        Set<String> total = new HashSet<>(patient1);
        total.addAll(patient2);

        return (double) common.size() / total.size();
    }


    // =====================================================
    // MAIN PROGRAM
    // =====================================================

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("       MEDISEARCH SYSTEM");
        System.out.println("=================================");


        // -------------------------------------------------
        // KMP PATTERN MATCHING
        // -------------------------------------------------

        System.out.println("\n--- PATTERN MATCHING (KMP) ---");

        String text =
                "Patient has Diabetes Mellitus";

        String pattern =
                "Diabetes";

        System.out.println("Patient Record : " + text);
        System.out.println("Search Pattern : " + pattern);

        kmpSearch(text, pattern);


        // -------------------------------------------------
        // FUZZY SEARCH
        // -------------------------------------------------

        System.out.println("\n--- FUZZY SEARCH ---");

        String userInput = "Diabets";
        String medicalTerm = "Diabetes";

        int distance =
                editDistance(userInput, medicalTerm);

        System.out.println("User Input    : " + userInput);
        System.out.println("Medical Term  : " + medicalTerm);
        System.out.println("Edit Distance : " + distance);

        if (distance <= 2) {
            System.out.println(
                    "Did you mean: " + medicalTerm + "?"
            );
        }


        // -------------------------------------------------
        // SIMILARITY SEARCH
        // -------------------------------------------------

        System.out.println("\n--- SIMILARITY SEARCH ---");

        Set<String> patient1 = new HashSet<>();

        patient1.add("fever");
        patient1.add("cough");
        patient1.add("fatigue");


        Set<String> patient2 = new HashSet<>();

        patient2.add("fever");
        patient2.add("cough");
        patient2.add("headache");


        double similarity =
                jaccardSimilarity(patient1, patient2);


        System.out.println(
                "Patient 1 Symptoms : " + patient1
        );

        System.out.println(
                "Patient 2 Symptoms : " + patient2
        );

        System.out.println(
                "Similarity : " +
                (similarity * 100) + "%"
        );


        if (similarity >= 0.5) {

            System.out.println(
                    "Similar Medical Case Found!"
            );

        } 
        else {

            System.out.println(
                    "Cases are not highly similar."
            );
        }


        System.out.println("\n=================================");
        System.out.println("       SEARCH COMPLETED");
        System.out.println("=================================");
    }
}