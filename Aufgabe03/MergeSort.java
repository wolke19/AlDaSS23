import javax.print.attribute.standard.Finishings;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Scanner;

public class MergeSort {
    public static double[] possibleGrades = {1.0, 1.3, 1.7, 2.0, 2.3, 2.7, 3.0, 3.3, 3.7, 4.0, 5.0};
    public static String[] adjectiveArr = new String[1347];
    public static String[] nounArr = new String[107];

    public static class Student{
        public String email;
        public double note;
        public Student(String email, double note){
            this.note = note;
            this.email = email;
        }
    }
//    _________ FUNKTIONEN _____________________________________________________________________________________________
    public static void prepareEmailBuilderArrays() throws IOException {
        Path adjPath = Paths.get("Aufgabe03/adjectiveList.txt");
        Path nounPath = Paths.get("Aufgabe03/nounList.txt");
        Scanner adjSc = new Scanner(adjPath);
        Scanner nounSc = new Scanner(nounPath);
        for (int i = 0; i < adjectiveArr.length; i++) adjectiveArr[i] = adjSc.next();
        for (int i = 0; i < nounArr.length; i++) nounArr[i] = nounSc.next();
        nounSc.close();
        adjSc.close();
    }
    public static Student[] initializeList(int n){
        Student[] ret = new Student[n];
        for (int i = 0; i < n; i++) {
            String email = adjectiveArr[new Random().nextInt(adjectiveArr.length)] + "_" +
                    nounArr[new Random().nextInt(nounArr.length)]+ "@htwg-konstanz.de";
                    ret[i] = new Student(email, possibleGrades[new Random().nextInt(possibleGrades.length)]);
        }
        return ret;
    }
    public static void printFinalList(Student[] finalList){
        for (Student student : finalList) {
            System.out.println(student.note + "   " + student.email);
//            System.out.printf("%-45s Note: %.1f %n", unsortedList[i].email, unsortedList[i].note);
        }
    }
    public static void insertionSort(Student[] arr, int start, int end){
        for (int i = start; i <= end; i++) {
            for (int j = i; j > start; j--) {
                if (arr[j - 1].note > arr[j].note) {
                    Student tempStudent = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = tempStudent;
                }
                else break;
            }
        }
    }
    public static void merge(Student[] arr, Student[] temp, int start, int mid, int end){
        for (int k = start ; k <= end; k++) {
            temp[k] = arr[k];
        }
        int i = start;
        int j = mid + 1;
        for (int k = start; k <= end; k++){
            if (i > mid) arr[k] = temp[j++];
            else if (j > end) arr[k] = temp[i++];
            else if (temp[j].note < temp[i].note) arr[k] = temp[j++];
            else arr[k] = temp[i++];
        }
    }
    public static void sort(Student[] arr, Student[] temp, int start, int end){
        if(end - start < 7) {
            insertionSort(arr, start, end);
        }
//        if (end <= start) return;
        else {
            int mid = start + (end - start) / 2;
            sort(arr, temp, start, mid);
            sort(arr, temp, mid+1, end);
            if (arr[mid].note < arr[mid+1].note) return;
            merge(arr, temp, start, mid, end);
        }
    }
//    __________ PSVM __________________________________________________________________________________________________
    public static void main(String[] args) throws IOException {
        int n = 100000;
        prepareEmailBuilderArrays();
        Student[] list = initializeList(n);

        Stopwatch stopwatch = new Stopwatch();
        sort(list, new Student[n], 0, n-1);
        printFinalList(list);
        System.out.println(stopwatch.elapsedTime() + "s");
    }
}