import java.util.*;

public class SimilaritySearch {

    // Calculate similarity using common symptoms
    static double calculateSimilarity(
            String[] patient1,
            String[] patient2
    ) {

        Set<String> set1 = new HashSet<>();
        Set<String> set2 = new HashSet<>();

        // Add symptoms to first set
        for (String symptom : patient1) {
            set1.add(symptom.toLowerCase());
        }

        // Add symptoms to second set
        for (String symptom : patient2) {
            set2.add(symptom.toLowerCase());
        }

        // Find common symptoms
        Set<String> intersection =
                new HashSet<>(set1);

        intersection.retainAll(set2);

        // Find all unique symptoms
        Set<String> union =
                new HashSet<>(set1);

        union.addAll(set2);

        // Jaccard Similarity
        return
                (double) intersection.size()
                        / union.size();
    }

    public static void main(String[] args) {

        String[] patient1Symptoms = {
                "fatigue",
                "fever",
                "cough",
                "dry skin"
        };

        String[] patient2Symptoms = {
                "fever",
                "headache",
                "cough",
                "dry skin"
        };

        double similarity =
                calculateSimilarity(
                        patient1Symptoms,
                        patient2Symptoms
                );

        System.out.println(
                "Patient 1 Symptoms: "
                        + Arrays.toString(patient1Symptoms)
        );

        System.out.println(
                "Patient 2 Symptoms: "
                        + Arrays.toString(patient2Symptoms)
        );

        System.out.println(
                "Similarity: "
                        + (similarity * 100) + "%"
        );

        if (similarity >= 0.5) {

            System.out.println(
                    "Similar Medical Case Found!"
            );

        } else {

            System.out.println(
                    "Medical Cases Are Not Highly Similar."
            );
        }
    }
}