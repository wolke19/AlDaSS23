import java.util.Scanner;

public class ThreeSum {

    public static int count(int[] a){

        int n = a.length;
        int counter = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                for (int k = j+1; k < n; k++) {
                    if (a[i] + a[j] + a[k] == 0) counter++;
                }
            }
        }
        return counter;
    }

    public static void beispielVorlesung(){
        int a[] = {30,-40,-20,-10,40,0,10,5};
        System.out.println("Three Sum: " + count(a));
    }

    public static int[] createArray(int N){
        int[] intArr = new int[N];
        for (int i = 0; i < N; i++) {
            intArr[i] = (int) (Math.floor(Math.random()*200)-100);
        }
        return intArr;
    }

    public static double[] runTestLoop(int[] nArr){
        double[] timeArr = new double[nArr.length];

        for (int i = 0; i < nArr.length; i++) {
            System.out.printf("starting run %d of %d (N=%d)%n",i+1, nArr.length, nArr[i]);

            int[] intArr = createArray(nArr[i]);
            Stopwatch stopwatch = new Stopwatch();
            double result = count(intArr);
            double elapsedTime = stopwatch.elapsedTime();
            timeArr[i] = elapsedTime;
            System.out.printf("run finished. elapsed time = %f%n", elapsedTime);
        }
        return timeArr;
    }

    public static int[] readInput(){
        Scanner scannerIn = new Scanner(System.in);
        System.out.println("How many runs?");
        int nrOfRuns = scannerIn.nextInt();

        int[] nArr = new int[nrOfRuns];

        for (int i = 0; i < nrOfRuns; i++) {
            System.out.println("N for run" + (i + 1) + "?");
            int n = scannerIn.nextInt();
            nArr[i] = n;
        }
        scannerIn.close();
        return nArr;
    }

    public static void printResults(int[] nArr, double[] timeArr){
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("------------- RESULTS------------- ");
        System.out.println();


        System.out.printf("%10s | %15s%n", "N", "Laufzeit");
        for (int i = 0; i < nArr.length; i++) {
            System.out.printf("%10d | %15.5f s %n", nArr[i],timeArr[i]);
        }



    }

    public static void main(String[] args) {
        int[] nArr = readInput();
        double[] timeArr = runTestLoop(nArr);
        printResults(nArr,timeArr);
    }

}
