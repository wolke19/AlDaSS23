import java.util.Arrays;

public class Quicksort {
private static int teile(String[] a, int vorne, int hinten){
    String pivot = a[vorne];
    int i = vorne;
    int j = hinten + 1;

    while (true){
        while (a[--j].compareTo(pivot) > 0) if (j == vorne) break;
        while (a[++i].compareTo(pivot) < 0) if (i == hinten) break;
        if (i >= j) break;
        System.out.println(Arrays.toString(a));
        tausch(a, i, j);

    }
    tausch(a, vorne, j);
    return j;

}
    private static void tausch(String[] a, int i1, int i2){
        String temp = a[i1];
        a[i1] = a[i2];
        a[i2] = temp;
    }
    private static void sort(String[] a, int vorne, int hinten){
    if (hinten <= vorne) return;
    int j = teile(a, vorne, hinten);
    sort(a, vorne, j - 1);
    sort(a, j+1, hinten);
    }
    public static void sort(String[] a){sort(a, 0, a.length-1);}
    public static void main(String[] args) {
        String[] arr = {"H", "a", "p", "pi", "n", "e", "s", "s"};
        sort(arr);
        System.out.println(Arrays.toString(arr));

    }
}



