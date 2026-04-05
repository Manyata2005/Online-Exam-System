import java.util.*;

public class OnlineExamSystem {

    static Scanner sc = new Scanner(System.in);

    static class Student {
        int rollNo;
        String name;
        int marks;

        Student(int rollNo, String name, int marks) {
            this.rollNo = rollNo;
            this.name   = name;
            this.marks  = marks;
        }

        @Override
        public String toString() {
            return "Roll: " + rollNo + " | Name: " + name + " | Marks: " + marks;
        }
    }

    static class Exam {
        String subject;
        int startTime;
        int endTime;

        Exam(String subject, int startTime, int endTime) {
            this.subject   = subject;
            this.startTime = startTime;
            this.endTime   = endTime;
        }

        @Override
        public String toString() {
            return subject + " (" + startTime + ":00 - " + endTime + ":00)";
        }
    }

    static class Question {
        int id;
        String topic;
        String difficulty;
        int marks;

        Question(int id, String topic, String difficulty, int marks) {
            this.id         = id;
            this.topic      = topic;
            this.difficulty = difficulty;
            this.marks      = marks;
        }

        @Override
        public String toString() {
            return "Q" + id + ": " + topic + " [" + difficulty + ", " + marks + " marks]";
        }
    }

    static int binarySearch(Student[] students, int targetRoll) {
        int low  = 0;
        int high = students.length - 1;

        System.out.println("\n--- Binary Search Steps ---");
        while (low <= high) {
            int mid = (low + high) / 2;
            System.out.println("  low=" + low + "  high=" + high
                    + "  mid=" + mid + "  arr[mid].roll=" + students[mid].rollNo);

            if (students[mid].rollNo == targetRoll) return mid;
            else if (students[mid].rollNo < targetRoll) low = mid + 1;
            else high = mid - 1;
        }
        return -1;
    }

    static void demonstrateBinarySearch() {
        System.out.println("\n========================================");
        System.out.println("  MODULE 1: BINARY SEARCH — Search Student");
        System.out.println("========================================");

        System.out.print("\nEnter number of students: ");
        int n = sc.nextInt();
        sc.nextLine();

        Student[] students = new Student[n];
        System.out.println("Enter student details (Roll No must be in ascending order):");
        for (int i = 0; i < n; i++) {
            System.out.print("  Student " + (i + 1) + " — Roll No: ");
            int roll = sc.nextInt();
            sc.nextLine();
            System.out.print("  Student " + (i + 1) + " — Name: ");
            String name = sc.nextLine();
            System.out.print("  Student " + (i + 1) + " — Marks: ");
            int marks = sc.nextInt();
            sc.nextLine();
            students[i] = new Student(roll, name, marks);
        }

        System.out.println("\nStudent Database:");
        for (Student s : students) System.out.println("  " + s);

        System.out.print("\nEnter Roll No to search: ");
        int target = sc.nextInt();
        sc.nextLine();

        int index = binarySearch(students, target);
        System.out.println();
        if (index != -1) {
            System.out.println("RESULT: Student FOUND at index " + index);
            System.out.println("        " + students[index]);
        } else {
            System.out.println("RESULT: Roll No " + target + " NOT FOUND.");
        }
    }

    static void mergeSort(Student[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    static void merge(Student[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        Student[] L = new Student[n1];
        Student[] R = new Student[n2];

        System.arraycopy(arr, left,    L, 0, n1);
        System.arraycopy(arr, mid + 1, R, 0, n2);

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i].marks >= R[j].marks) arr[k++] = L[i++];
            else arr[k++] = R[j++];
        }
        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }

    static void demonstrateMergeSort() {
        System.out.println("\n========================================");
        System.out.println("  MODULE 2: MERGE SORT — Rank Students");
        System.out.println("========================================");

        System.out.print("\nEnter number of students: ");
        int n = sc.nextInt();
        sc.nextLine();

        Student[] students = new Student[n];
        System.out.println("Enter student details:");
        for (int i = 0; i < n; i++) {
            System.out.print("  Student " + (i + 1) + " — Roll No: ");
            int roll = sc.nextInt();
            sc.nextLine();
            System.out.print("  Student " + (i + 1) + " — Name: ");
            String name = sc.nextLine();
            System.out.print("  Student " + (i + 1) + " — Marks: ");
            int marks = sc.nextInt();
            sc.nextLine();
            students[i] = new Student(roll, name, marks);
        }

        System.out.println("\nBefore Sorting:");
        for (Student s : students) System.out.println("  " + s);

        mergeSort(students, 0, students.length - 1);

        System.out.println("\nAfter Sorting (Rank Order — Highest Marks First):");
        for (int i = 0; i < students.length; i++)
            System.out.println("  Rank " + (i + 1) + ": " + students[i]);
    }

    static List<Exam> greedySchedule(List<Exam> exams) {
        exams.sort(Comparator.comparingInt(e -> e.endTime));

        List<Exam> scheduled = new ArrayList<>();
        int lastEndTime = -1;

        System.out.println("\n--- Greedy Scheduling Steps ---");
        System.out.printf("  %-22s %-8s %-8s %s%n", "Subject", "Start", "End", "Decision");
        System.out.println("  " + "-".repeat(58));

        for (Exam exam : exams) {
            if (exam.startTime >= lastEndTime) {
                scheduled.add(exam);
                lastEndTime = exam.endTime;
                System.out.printf("  %-22s %-8s %-8s %s%n",
                        exam.subject, exam.startTime + ":00", exam.endTime + ":00", "SELECTED");
            } else {
                System.out.printf("  %-22s %-8s %-8s %s%n",
                        exam.subject, exam.startTime + ":00", exam.endTime + ":00", "SKIPPED (conflict)");
            }
        }
        return scheduled;
    }

    static void demonstrateGreedy() {
        System.out.println("\n========================================");
        System.out.println("  MODULE 3: GREEDY — Exam Slot Scheduling");
        System.out.println("========================================");

        System.out.print("\nEnter number of exam slots: ");
        int n = sc.nextInt();
        sc.nextLine();

        List<Exam> exams = new ArrayList<>();
        System.out.println("Enter exam details (time in 24h, e.g. 9 for 9:00):");
        for (int i = 0; i < n; i++) {
            System.out.print("  Exam " + (i + 1) + " — Subject: ");
            String subject = sc.nextLine();
            System.out.print("  Exam " + (i + 1) + " — Start time (hour): ");
            int start = sc.nextInt();
            System.out.print("  Exam " + (i + 1) + " — End time   (hour): ");
            int end = sc.nextInt();
            sc.nextLine();
            exams.add(new Exam(subject, start, end));
        }

        System.out.println("\nAll requested exam slots:");
        for (Exam e : exams) System.out.println("  " + e);

        List<Exam> result = greedySchedule(exams);

        System.out.println("\nFinal Schedule (" + result.size() + " exams selected):");
        for (int i = 0; i < result.size(); i++)
            System.out.println("  Slot " + (i + 1) + ": " + result.get(i));
    }

    static List<Question> questionBank = new ArrayList<>();

    static boolean backtrack(
            int index,
            int targetMarks,
            int currentSum,
            Set<String> usedDifficulty,
            List<Question> chosen,
            List<Question> result) {

        if (currentSum == targetMarks) {
            result.addAll(chosen);
            return true;
        }
        if (currentSum > targetMarks || index >= questionBank.size()) return false;

        for (int i = index; i < questionBank.size(); i++) {
            Question q = questionBank.get(i);

            if (usedDifficulty.contains(q.difficulty)) {
                System.out.println("  Skip  Q" + q.id + " (" + q.difficulty + " already used)");
                continue;
            }

            chosen.add(q);
            usedDifficulty.add(q.difficulty);
            System.out.println("  Try   Q" + q.id + " [" + q.marks + "m]  -> sum=" + (currentSum + q.marks));

            if (backtrack(i + 1, targetMarks, currentSum + q.marks, usedDifficulty, chosen, result))
                return true;

            System.out.println("  Back  Q" + q.id);
            chosen.remove(chosen.size() - 1);
            usedDifficulty.remove(q.difficulty);
        }
        return false;
    }

    static void demonstrateBacktracking() {
        System.out.println("\n========================================");
        System.out.println("  MODULE 4: BACKTRACKING — Question Paper");
        System.out.println("========================================");

        System.out.print("\nEnter number of questions in the bank: ");
        int n = sc.nextInt();
        sc.nextLine();

        questionBank.clear();
        System.out.println("Enter question details (difficulty: easy / medium / hard):");
        for (int i = 1; i <= n; i++) {
            System.out.print("  Q" + i + " — Topic: ");
            String topic = sc.nextLine();
            System.out.print("  Q" + i + " — Difficulty (easy/medium/hard): ");
            String diff = sc.nextLine().trim().toLowerCase();
            System.out.print("  Q" + i + " — Marks: ");
            int marks = sc.nextInt();
            sc.nextLine();
            questionBank.add(new Question(i, topic, diff, marks));
        }

        System.out.println("\nQuestion Bank:");
        for (Question q : questionBank) System.out.println("  " + q);

        System.out.print("\nEnter target total marks for the question paper: ");
        int targetMarks = sc.nextInt();
        sc.nextLine();

        System.out.println("Constraint: No two questions of same difficulty");
        System.out.println("\n--- Backtracking Steps ---");

        List<Question> result = new ArrayList<>();
        boolean found = backtrack(0, targetMarks, 0, new HashSet<>(), new ArrayList<>(), result);

        System.out.println();
        if (found) {
            int total = result.stream().mapToInt(q -> q.marks).sum();
            System.out.println("RESULT: Valid question paper found!");
            System.out.println("Selected Questions:");
            for (Question q : result) System.out.println("  " + q);
            System.out.println("Total Marks: " + total);
        } else {
            System.out.println("RESULT: No valid combination found for " + targetMarks + " marks.");
            System.out.println("        Try a different target value.");
        }
    }
    public static void main(String[] args) {
        System.out.println("############################################");
        System.out.println("#      ONLINE EXAM SYSTEM — DAA Project   #");
        System.out.println("############################################");

        while (true) {
            System.out.println("\n============ MAIN MENU ============");
            System.out.println("  1. Search Student   (Binary Search)");
            System.out.println("  2. Sort Rankings    (Merge Sort)");
            System.out.println("  3. Schedule Exams   (Greedy)");
            System.out.println("  4. Generate Paper   (Backtracking)");
            System.out.println("  5. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1: demonstrateBinarySearch(); break;
                case 2: demonstrateMergeSort();    break;
                case 3: demonstrateGreedy();       break;
                case 4: demonstrateBacktracking(); break;
                case 5:
                    System.out.println("\nThank you! Exiting...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please enter 1-5.");
            }
        }
    }
}