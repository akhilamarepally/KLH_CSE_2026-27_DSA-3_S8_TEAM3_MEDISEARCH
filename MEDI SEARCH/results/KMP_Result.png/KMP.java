public class KMP {

    // Build LPS array
    static void computeLPS(String pattern, int[] lps) {
        int length = 0;
        int i = 1;

        while (i < pattern.length()) {

            if (pattern.charAt(i) == pattern.charAt(length)) {
                length++;
                lps[i] = length;
                i++;
            } else {

                if (length != 0) {
                    length = lps[length - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
    }

    // KMP Pattern Search
    static void search(String text, String pattern) {

        int n = text.length();
        int m = pattern.length();

        int[] lps = new int[m];

        computeLPS(pattern, lps);

        int i = 0;
        int j = 0;

        boolean found = false;

        while (i < n) {

            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            }

            if (j == m) {
                System.out.println("Pattern Found");
                System.out.println("Starting Index: " + (i - j));
                found = true;

                j = lps[j - 1];
            }

            else if (i < n && text.charAt(i) != pattern.charAt(j)) {

                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }

        if (!found) {
            System.out.println("Pattern Not Found");
        }
    }

    public static void main(String[] args) {

        String patientRecord =
                "Patient P001 Rahul Sharma has Diabetes Mellitus";

        String searchPattern = "Diabetes";

        System.out.println("Patient Record: " + patientRecord);
        System.out.println("Search Pattern: " + searchPattern);

        search(patientRecord, searchPattern);
    }
}