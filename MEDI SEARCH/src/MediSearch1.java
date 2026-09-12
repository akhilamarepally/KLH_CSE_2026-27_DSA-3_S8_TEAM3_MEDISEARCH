import java.util.*;

public class MediSearch1 {

    // =========================================================
    // PATIENT CLASS
    // =========================================================

    static class Patient {

        String id;
        String name;
        int age;
        String gender;
        String phone;
        String bloodGroup;

        String[] symptoms;
        String disease;
        String diagnosis;
        String[] medicines;

        String doctor;
        String specialization;
        String department;

        String caregiver;
        String careInstructions;

        String lastVisit;
        String nextAppointment;

        double consultationFee;
        double treatmentCharges;
        double medicineCharges;
        String paymentStatus;


        Patient(
                String id,
                String name,
                int age,
                String gender,
                String phone,
                String bloodGroup,
                String[] symptoms,
                String disease,
                String diagnosis,
                String[] medicines,
                String doctor,
                String specialization,
                String department,
                String caregiver,
                String careInstructions,
                String lastVisit,
                String nextAppointment,
                double consultationFee,
                double treatmentCharges,
                double medicineCharges,
                String paymentStatus
        ) {

            this.id = id;
            this.name = name;
            this.age = age;
            this.gender = gender;
            this.phone = phone;
            this.bloodGroup = bloodGroup;

            this.symptoms = symptoms;
            this.disease = disease;
            this.diagnosis = diagnosis;
            this.medicines = medicines;

            this.doctor = doctor;
            this.specialization = specialization;
            this.department = department;

            this.caregiver = caregiver;
            this.careInstructions = careInstructions;

            this.lastVisit = lastVisit;
            this.nextAppointment = nextAppointment;

            this.consultationFee = consultationFee;
            this.treatmentCharges = treatmentCharges;
            this.medicineCharges = medicineCharges;
            this.paymentStatus = paymentStatus;
        }


        // =====================================================
        // COMPLETE SEARCHABLE TEXT
        // KMP searches this information
        // =====================================================

        String getSearchableText() {

            return (
                    id + " " +
                    name + " " +
                    String.join(" ", symptoms) + " " +
                    disease + " " +
                    diagnosis + " " +
                    String.join(" ", medicines) + " " +
                    doctor + " " +
                    department
            ).toLowerCase();
        }


        // =====================================================
        // TOTAL BILL
        // =====================================================

        double getTotalBill() {

            return consultationFee
                    + treatmentCharges
                    + medicineCharges;
        }


        // =====================================================
        // DISPLAY COMPLETE PATIENT DETAILS
        // =====================================================

        void displayPatient() {

            System.out.println();
            System.out.println(
                    "=================================================="
            );

            System.out.println(
                    "PATIENT ID       : " + id
            );

            System.out.println(
                    "NAME             : " + name
            );

            System.out.println(
                    "AGE              : " + age
            );

            System.out.println(
                    "GENDER           : " + gender
            );

            System.out.println(
                    "PHONE            : " + phone
            );

            System.out.println(
                    "BLOOD GROUP      : " + bloodGroup
            );


            System.out.println();
            System.out.println(
                    "--------------- MEDICAL DETAILS ----------------"
            );

            System.out.println(
                    "SYMPTOMS         : "
                            + String.join(", ", symptoms)
            );

            System.out.println(
                    "DISEASE          : " + disease
            );

            System.out.println(
                    "DIAGNOSIS        : " + diagnosis
            );

            System.out.println(
                    "MEDICINES        : "
                            + String.join(", ", medicines)
            );


            System.out.println();
            System.out.println(
                    "-------------- HOSPITAL DETAILS ---------------"
            );

            System.out.println(
                    "ASSIGNED DOCTOR  : " + doctor
            );

            System.out.println(
                    "SPECIALIZATION   : " + specialization
            );

            System.out.println(
                    "DEPARTMENT       : " + department
            );


            System.out.println();
            System.out.println(
                    "---------------- CARE DETAILS -----------------"
            );

            System.out.println(
                    "CAREGIVER        : " + caregiver
            );

            System.out.println(
                    "CARE INSTRUCTIONS: "
                            + careInstructions
            );


            System.out.println();
            System.out.println(
                    "------------ APPOINTMENT DETAILS --------------"
            );

            System.out.println(
                    "LAST VISIT       : " + lastVisit
            );

            System.out.println(
                    "NEXT APPOINTMENT : " + nextAppointment
            );


            System.out.println();
            System.out.println(
                    "--------------- BILLING DETAILS ---------------"
            );

            System.out.println(
                    "CONSULTATION FEE : Rs. "
                            + consultationFee
            );

            System.out.println(
                    "TREATMENT COST   : Rs. "
                            + treatmentCharges
            );

            System.out.println(
                    "MEDICINE COST    : Rs. "
                            + medicineCharges
            );

            System.out.println(
                    "TOTAL BILL       : Rs. "
                            + getTotalBill()
            );

            System.out.println(
                    "PAYMENT STATUS   : "
                            + paymentStatus
            );

            System.out.println(
                    "=================================================="
            );
        }
    }


    // =========================================================
    // 1. KMP PATTERN MATCHING
    // =========================================================

    static int[] createLPS(String pattern) {

        int[] lps = new int[pattern.length()];

        int length = 0;
        int i = 1;

        while (i < pattern.length()) {

            if (pattern.charAt(i)
                    == pattern.charAt(length)) {

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

        return lps;
    }


    static boolean kmpSearch(
            String text,
            String pattern
    ) {

        text = text.toLowerCase();
        pattern = pattern.toLowerCase();

        if (pattern.length() == 0) {
            return false;
        }

        int[] lps = createLPS(pattern);

        int i = 0;
        int j = 0;

        while (i < text.length()) {

            if (text.charAt(i)
                    == pattern.charAt(j)) {

                i++;
                j++;
            }


            if (j == pattern.length()) {

                return true;

            } else if (
                    i < text.length()
                            && text.charAt(i)
                            != pattern.charAt(j)
            ) {

                if (j != 0) {

                    j = lps[j - 1];

                } else {

                    i++;
                }
            }
        }

        return false;
    }


    // =========================================================
    // 2. EDIT DISTANCE
    // FUZZY MATCHING USING DYNAMIC PROGRAMMING
    // =========================================================

    static int editDistance(
            String a,
            String b
    ) {

        a = a.toLowerCase();
        b = b.toLowerCase();

        int m = a.length();
        int n = b.length();

        int[][] dp =
                new int[m + 1][n + 1];


        // Base cases

        for (int i = 0; i <= m; i++) {

            dp[i][0] = i;
        }

        for (int j = 0; j <= n; j++) {

            dp[0][j] = j;
        }


        // Dynamic Programming table

        for (int i = 1; i <= m; i++) {

            for (int j = 1; j <= n; j++) {

                if (
                        a.charAt(i - 1)
                                == b.charAt(j - 1)
                ) {

                    dp[i][j] =
                            dp[i - 1][j - 1];

                } else {

                    int insert =
                            dp[i][j - 1];

                    int delete =
                            dp[i - 1][j];

                    int replace =
                            dp[i - 1][j - 1];

                    dp[i][j] =
                            1 + Math.min(
                                    insert,
                                    Math.min(
                                            delete,
                                            replace
                                    )
                            );
                }
            }
        }

        return dp[m][n];
    }


    // =========================================================
    // 3. JACCARD SIMILARITY
    // =========================================================

    static double jaccardSimilarity(
            Set<String> set1,
            Set<String> set2
    ) {

        Set<String> intersection =
                new HashSet<>(set1);

        intersection.retainAll(set2);


        Set<String> union =
                new HashSet<>(set1);

        union.addAll(set2);


        if (union.size() == 0) {

            return 0;
        }


        return (double)
                intersection.size()
                / union.size();
    }


    // =========================================================
    // CONVERT SYMPTOMS TO SET
    // =========================================================

    static Set<String> getSymptomSet(
            Patient patient
    ) {

        Set<String> symptoms =
                new HashSet<>();

        for (
                String symptom
                : patient.symptoms
        ) {

            symptoms.add(
                    symptom.toLowerCase()
            );
        }

        return symptoms;
    }


    // =========================================================
    // GET CLOSEST MEDICAL TERM
    // FUZZY SEARCH
    // =========================================================

    static String getClosestTerm(
            String query,
            ArrayList<Patient> patients
    ) {

        String closestTerm = "";

        int minimumDistance =
                Integer.MAX_VALUE;


        for (
                Patient patient
                : patients
        ) {


            // Compare with symptoms

            for (
                    String symptom
                    : patient.symptoms
            ) {

                int distance =
                        editDistance(
                                query,
                                symptom
                        );

                if (
                        distance
                                < minimumDistance
                ) {

                    minimumDistance =
                            distance;

                    closestTerm =
                            symptom;
                }
            }


            // Compare with disease

            int diseaseDistance =
                    editDistance(
                            query,
                            patient.disease
                    );

            if (
                    diseaseDistance
                            < minimumDistance
            ) {

                minimumDistance =
                        diseaseDistance;

                closestTerm =
                        patient.disease;
            }
        }

        return closestTerm;
    }


    // =========================================================
    // KMP EXACT SEARCH
    // =========================================================

    static ArrayList<Patient>
    exactSearch(
            String query,
            ArrayList<Patient> patients
    ) {

        ArrayList<Patient> results =
                new ArrayList<>();


        for (
                Patient patient
                : patients
        ) {

            if (
                    kmpSearch(
                            patient.getSearchableText(),
                            query
                    )
            ) {

                results.add(patient);
            }
        }

        return results;
    }


    // =========================================================
    // DISPLAY SIMILAR PATIENTS
    // =========================================================

    static void showSimilarPatients(
            ArrayList<Patient> results
    ) {

        if (results.size() < 2) {

            System.out.println();
            System.out.println(
                    "Similarity Search: Not enough "
                            + "matching patients to compare."
            );

            return;
        }


        System.out.println();
        System.out.println(
                "=================================================="
        );

        System.out.println(
                "JACCARD SIMILARITY ANALYSIS"
        );

        System.out.println(
                "=================================================="
        );


        for (int i = 0;
             i < results.size();
             i++) {

            for (
                    int j = i + 1;
                    j < results.size();
                    j++
            ) {

                Patient p1 =
                        results.get(i);

                Patient p2 =
                        results.get(j);


                double similarity =
                        jaccardSimilarity(
                                getSymptomSet(p1),
                                getSymptomSet(p2)
                        );


                System.out.println();

                System.out.println(
                        p1.name
                                + " and "
                                + p2.name
                );

                System.out.println(
                        "Similarity: "
                                + String.format(
                                        "%.2f",
                                        similarity * 100
                                )
                                + "%"
                );


                if (
                        similarity >= 0.5
                ) {

                    System.out.println(
                            "Result: Similar Patient Cases Found"
                    );

                } else {

                    System.out.println(
                            "Result: Low Similarity"
                    );
                }
            }
        }
    }


    // =========================================================
    // MAIN SEARCH FUNCTION
    // =========================================================

    static void searchPatients(
            String query,
            ArrayList<Patient> patients
    ) {

        query =
                query.toLowerCase()
                        .trim();


        System.out.println();
        System.out.println(
                "=================================================="
        );

        System.out.println(
                "          MEDISEARCH SEARCH RESULT"
        );

        System.out.println(
                "=================================================="
        );

        System.out.println(
                "Search Query: " + query
        );


        // =====================================================
        // STEP 1 - KMP EXACT SEARCH
        // =====================================================

        System.out.println();
        System.out.println(
                "Algorithm 1: KMP Pattern Matching"
        );


        ArrayList<Patient> results =
                exactSearch(
                        query,
                        patients
                );


        if (!results.isEmpty()) {

            System.out.println(
                    "Exact Match Found!"
            );

            System.out.println(
                    "Patients Found: "
                            + results.size()
            );


            for (
                    Patient patient
                    : results
            ) {

                patient.displayPatient();
            }


            // =================================================
            // STEP 3 - SIMILARITY SEARCH
            // =================================================

            showSimilarPatients(results);

            return;
        }


        // =====================================================
        // STEP 2 - FUZZY SEARCH
        // =====================================================

        System.out.println(
                "No Exact Match Found."
        );

        System.out.println();
        System.out.println(
                "Algorithm 2: Edit Distance "
                        + "(Fuzzy Matching)"
        );


        String closestTerm =
                getClosestTerm(
                        query,
                        patients
                );


        int distance =
                editDistance(
                        query,
                        closestTerm
                );


        System.out.println(
                "Closest Medical Term: "
                        + closestTerm
        );

        System.out.println(
                "Edit Distance: "
                        + distance
        );


        if (distance <= 3) {

            System.out.println();

            System.out.println(
                    "Did you mean: "
                            + closestTerm
                            + "?"
            );


            results =
                    exactSearch(
                            closestTerm,
                            patients
                    );


            System.out.println();
            System.out.println(
                    "Patients Found: "
                            + results.size()
            );


            for (
                    Patient patient
                    : results
            ) {

                patient.displayPatient();
            }


            showSimilarPatients(results);

        } else {

            System.out.println();

            System.out.println(
                    "No similar medical record found."
            );
        }
    }


    // =========================================================
    // MAIN METHOD
    // =========================================================

    public static void main(
            String[] args
    ) {

        Scanner scanner =
                new Scanner(System.in);


        // =====================================================
        // PATIENT RECORDS
        // =====================================================

        ArrayList<Patient> patients =
                new ArrayList<>();


        // -----------------------------------------------------
        // P001
        // -----------------------------------------------------

        patients.add(
                new Patient(

                        "P001",

                        "Rahul Sharma",

                        45,

                        "Male",

                        "9876543210",

                        "B+",

                        new String[]{
                                "frequent urination",
                                "increased thirst",
                                "fatigue"
                        },

                        "Diabetes",

                        "Type 2 Diabetes Mellitus",

                        new String[]{
                                "Metformin",
                                "Insulin"
                        },

                        "Dr. Ravi Kumar",

                        "Endocrinologist",

                        "Endocrinology",

                        "Nurse Anjali",

                        "Monitor blood sugar regularly.",

                        "05 September 2026",

                        "20 September 2026",

                        500,

                        3000,

                        1500,

                        "Paid"
                )
        );


        // -----------------------------------------------------
        // P002
        // -----------------------------------------------------

        patients.add(
                new Patient(

                        "P002",

                        "Priya Reddy",

                        28,

                        "Female",

                        "9876543211",

                        "O+",

                        new String[]{
                                "shortness of breath",
                                "wheezing",
                                "cough"
                        },

                        "Asthma",

                        "Bronchial Asthma",

                        new String[]{
                                "Inhaler",
                                "Montelukast"
                        },

                        "Dr. Meena Rao",

                        "Pulmonologist",

                        "Pulmonology",

                        "Nurse Kavya",

                        "Avoid dust and smoke exposure.",

                        "06 September 2026",

                        "22 September 2026",

                        600,

                        2500,

                        1200,

                        "Paid"
                )
        );


        // -----------------------------------------------------
        // P003
        // -----------------------------------------------------

        patients.add(
                new Patient(

                        "P003",

                        "Arjun Kumar",

                        52,

                        "Male",

                        "9876543212",

                        "A+",

                        new String[]{
                                "headache",
                                "dizziness",
                                "fatigue"
                        },

                        "Hypertension",

                        "High Blood Pressure",

                        new String[]{
                                "Amlodipine"
                        },

                        "Dr. Suresh Patel",

                        "Cardiologist",

                        "Cardiology",

                        "Nurse Deepa",

                        "Monitor blood pressure daily.",

                        "04 September 2026",

                        "18 September 2026",

                        700,

                        2800,

                        800,

                        "Pending"
                )
        );


        // -----------------------------------------------------
        // P004
        // -----------------------------------------------------

        patients.add(
                new Patient(

                        "P004",

                        "Sneha Patel",

                        31,

                        "Female",

                        "9876543213",

                        "AB+",

                        new String[]{
                                "severe headache",
                                "nausea",
                                "light sensitivity"
                        },

                        "Migraine",

                        "Chronic Migraine",

                        new String[]{
                                "Sumatriptan",
                                "Paracetamol"
                        },

                        "Dr. Anil Varma",

                        "Neurologist",

                        "Neurology",

                        "Nurse Riya",

                        "Rest in a quiet and dark room.",

                        "07 September 2026",

                        "25 September 2026",

                        650,

                        2200,

                        900,

                        "Paid"
                )
        );


        // -----------------------------------------------------
        // P005
        // -----------------------------------------------------

        patients.add(
                new Patient(

                        "P005",

                        "Vikram Singh",

                        58,

                        "Male",

                        "9876543214",

                        "O-",

                        new String[]{
                                "joint pain",
                                "swelling",
                                "stiffness"
                        },

                        "Arthritis",

                        "Osteoarthritis",

                        new String[]{
                                "Ibuprofen",
                                "Vitamin D"
                        },

                        "Dr. Neha Kapoor",

                        "Orthopedic Specialist",

                        "Orthopedics",

                        "Nurse Sunita",

                        "Avoid excessive pressure on joints.",

                        "03 September 2026",

                        "19 September 2026",

                        700,

                        3500,

                        1000,

                        "Pending"
                )
        );


        // -----------------------------------------------------
        // P006
        // -----------------------------------------------------

        patients.add(
                new Patient(

                        "P006",

                        "Ananya Rao",

                        24,

                        "Female",

                        "9876543215",

                        "B-",

                        new String[]{
                                "skin rash",
                                "itching",
                                "sneezing"
                        },

                        "Allergy",

                        "Seasonal Skin Allergy",

                        new String[]{
                                "Cetirizine",
                                "Antihistamine"
                        },

                        "Dr. Priya Sharma",

                        "Dermatologist",

                        "Dermatology",

                        "Nurse Kavya",

                        "Avoid known allergens.",

                        "08 September 2026",

                        "21 September 2026",

                        500,

                        1800,

                        700,

                        "Paid"
                )
        );


        // -----------------------------------------------------
        // P007
        // -----------------------------------------------------

        patients.add(
                new Patient(

                        "P007",

                        "Kiran Verma",

                        34,

                        "Male",

                        "9876543216",

                        "A-",

                        new String[]{
                                "stomach pain",
                                "bloating",
                                "acidity"
                        },

                        "Gastritis",

                        "Chronic Gastritis",

                        new String[]{
                                "Omeprazole",
                                "Antacid"
                        },

                        "Dr. Ramesh Gupta",

                        "Gastroenterologist",

                        "Gastroenterology",

                        "Nurse Meera",

                        "Avoid spicy food.",

                        "06 September 2026",

                        "23 September 2026",

                        600,

                        2000,

                        800,

                        "Paid"
                )
        );


        // -----------------------------------------------------
        // P008
        // -----------------------------------------------------

        patients.add(
                new Patient(

                        "P008",

                        "Meera Nair",

                        27,

                        "Female",

                        "9876543217",

                        "B+",

                        new String[]{
                                "fatigue",
                                "weakness",
                                "pale skin"
                        },

                        "Anemia",

                        "Iron Deficiency Anemia",

                        new String[]{
                                "Iron Supplements",
                                "Folic Acid"
                        },

                        "Dr. Kavita Menon",

                        "General Physician",

                        "General Medicine",

                        "Nurse Anjali",

                        "Maintain an iron rich diet.",

                        "05 September 2026",

                        "20 September 2026",

                        500,

                        2200,

                        900,

                        "Paid"
                )
        );


        // -----------------------------------------------------
        // P009
        // -----------------------------------------------------

        patients.add(
                new Patient(

                        "P009",

                        "Rohit Das",

                        22,

                        "Male",

                        "9876543218",

                        "O+",

                        new String[]{
                                "fever",
                                "body pain",
                                "headache"
                        },

                        "Fever",

                        "Viral Fever",

                        new String[]{
                                "Paracetamol",
                                "ORS"
                        },

                        "Dr. Sanjay Kumar",

                        "General Physician",

                        "General Medicine",

                        "Nurse Deepa",

                        "Stay hydrated and rest.",

                        "09 September 2026",

                        "16 September 2026",

                        400,

                        1500,

                        500,

                        "Paid"
                )
        );


        // -----------------------------------------------------
        // P010
        // -----------------------------------------------------

        patients.add(
                new Patient(

                        "P010",

                        "Lakshmi Devi",

                        41,

                        "Female",

                        "9876543219",

                        "A+",

                        new String[]{
                                "dry skin",
                                "hair loss",
                                "weight gain"
                        },

                        "Thyroid Disorder",

                        "Hypothyroidism",

                        new String[]{
                                "Levothyroxine",
                                "Vitamin D"
                        },

                        "Dr. Ravi Kumar",

                        "Endocrinologist",

                        "Endocrinology",

                        "Nurse Sunita",

                        "Take medicine regularly.",

                        "07 September 2026",

                        "24 September 2026",

                        600,

                        3000,

                        1200,

                        "Pending"
                )
        );


        // =====================================================
        // APPLICATION START
        // =====================================================

        System.out.println();
        System.out.println(
                "=================================================="
        );

        System.out.println(
                "      MEDISEARCH - HOSPITAL PATIENT SEARCH"
        );

        System.out.println(
                "=================================================="
        );

        System.out.println();

        System.out.println(
                "Search by:"
        );

        System.out.println(
                "- Patient ID"
        );

        System.out.println(
                "- Patient Name"
        );

        System.out.println(
                "- Disease"
        );

        System.out.println(
                "- Symptoms"
        );

        System.out.println(
                "- Diagnosis"
        );

        System.out.println(
                "- Medicine"
        );


        // =====================================================
        // SEARCH LOOP
        // =====================================================

        while (true) {

            System.out.println();

            System.out.print(
                    "Enter Search Query "
                            + "(or type exit): "
            );


            String query =
                    scanner.nextLine();


            if (
                    query.equalsIgnoreCase(
                            "exit"
                    )
            ) {

                System.out.println();

                System.out.println(
                        "Thank you for using MediSearch."
                );

                break;
            }


            if (
                    query.trim().isEmpty()
            ) {

                System.out.println(
                        "Please enter a valid search query."
                );

                continue;
            }


            searchPatients(
                    query,
                    patients
            );
        }


        scanner.close();
    }
}