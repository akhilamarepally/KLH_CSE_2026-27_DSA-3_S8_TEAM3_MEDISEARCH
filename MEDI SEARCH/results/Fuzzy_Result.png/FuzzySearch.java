public class FuzzySearch {

    // Calculate Edit Distance
    static int editDistance(String word1, String word2) {

        int m = word1.length();
        int n = word2.length();

        int[][] dp = new int[m + 1][n + 1];

        // Base cases
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }

        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

        // Dynamic Programming
        for (int i = 1; i <= m; i++) {

            for (int j = 1; j <= n; j++) {

                if (word1.charAt(i - 1)
                        == word2.charAt(j - 1)) {

                    dp[i][j] = dp[i - 1][j - 1];

                } else {

                    int insert = dp[i][j - 1];
                    int delete = dp[i - 1][j];
                    int replace = dp[i - 1][j - 1];

                    dp[i][j] =
                            1 + Math.min(
                                    Math.min(insert, delete),
                                    replace
                            );
                }
            }
        }

        return dp[m][n];
    }

    public static void main(String[] args) {

        // User enters a misspelled medical term
        String userInput = "Diabets";

        // Correct medical terms
        String[] medicalTerms = {
                "Diabetes",
                "Asthma",
                "Hypertension",
                "Migraine",
                "Arthritis",
                "Allergy",
                "Gastritis",
                "Anemia"
        };

        System.out.println("User Input: " + userInput);

        String bestMatch = "";
        int minimumDistance = Integer.MAX_VALUE;

        // Find closest medical term
        for (String term : medicalTerms) {

            int distance =
                    editDistance(
                            userInput.toLowerCase(),
                            term.toLowerCase()
                    );

            if (distance < minimumDistance) {
                minimumDistance = distance;
                bestMatch = term;
            }
        }

        System.out.println(
                "Closest Medical Term: " + bestMatch
        );

        System.out.println(
                "Edit Distance: " + minimumDistance
        );

        System.out.println(
                "Did you mean: " + bestMatch + "?"
        );
    }
}