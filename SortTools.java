import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class SortTools {

    public static int[] createSequenceInc(int n) {
        int[] arr = {};
        if (n > 0) {
            arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = i + 1;
            }
        }
        return arr;
    }

    public static int[] createSequenceDec(int n) {
        int[] arr = {};
        if (n > 0) {
            arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = n - i;
            }
        }
        return arr;
    }

    public static int[] createSequenceRand(int n) {
        int[] arr = {};
        if (n > 0) {
            arr = new int[n];
            Random rand = new Random();
            for (int i = 0; i < n; i++) {
                int value = rand.nextInt(n) + 1;
                arr[i] = value;
            }
        }
        return arr;
    }

    public static int[] createSequenceAlt(int n) {
        int[] arr = {};
        if (n > 0) {
            arr = new int[n];
            for (int i = 0; i < n; i++) {
                if (i%2 == 0) arr[i] = 1;
                else arr[i] = 2;
            }
        }
        return arr;
    }

    public static int[] insertionSort(int[] a) {
        for (int j = 1; j < a.length; j++) {
            int val = a[j];
            int i = j - 1;
            while(i >= 0 && a[i] > val) {
                a[i+1] = a[i];
                i -= 1;
            }
            a[i+1] = val;
        }
        return a;
    }

    public static <T extends Comparable<T>> T[] insertionSortGen(T[] GenArray) {
        for (int j = 1; j < GenArray.length; j++) {
            T val = GenArray[j];
            int i = j - 1;
            while(i >= 0 && GenArray[i].compareTo(val) > 0) {
                GenArray[i+1] = GenArray[i];
                i -= 1;
            }
            GenArray[i+1] = val;
        }
        return GenArray;
    }

    public static int[] bubbleSort(int[] a) {
        for (int i=a.length; i >= 1; i--) {
            for (int j = 0; j <= i-2; j++) {
                if (a[j] > a[j+1]) {
                    int s = a[j];
                    a[j] = a[j+1];
                    a[j+1] = s;
                }
            }
        }
        return a;
    }

    public static int[] bubbleSortNew(int[] a) {
        for (int i=a.length; i >= 1; i--) {
            for (int j = 0; j <= i-10; j++) {
                int[] temp = Arrays.copyOfRange(a,j,j+10);
                insertionSort(temp);
                for (int k = 0; k < 10; k++) {
                    a[j+k] = temp[k];
                }
            }
        }
        return a;
    }

    public static <T extends Comparable<T>> T[] bubbleSortGen(T[] GenArray) {
        for (int i=GenArray.length; i >= 1; i--) {
            for (int j = 0; j <= i-2; j++) {
                if (GenArray[j].compareTo(GenArray[j+1]) > 0) {
                    T s = GenArray[j];
                    GenArray[j] = GenArray[j+1];
                    GenArray[j+1] = s;
                }
            }
        }
        return GenArray;
    }

    public static int[] mergeSort(int[] A, int p, int r) {
        if (p<r) {
            int q = (p+r)/2;
            mergeSort(A,p,q);
            mergeSort(A,q+1,r);
            merge(A,p,q,r);
        }
        return A;
    }

    public static int[] merge(int[] A, int p, int q, int r) {
        // split Array in 2 halfs and generate subarrays
        int[] L = new int[q-p+2], R = new int[r-q+1];

        // fill subarrays ; last diggit = max value
        for (int i = 0; i<L.length; i++) {
            if (i == L.length-1) {
                L[i] = Integer.MAX_VALUE;
            } else {
                L[i] = A[p+i];
            }
        }
        //System.out.println(Arrays.toString(A));
        for (int j = 0; j<R.length; j++) {
            if (j == R.length-1) {
                R[j] = Integer.MAX_VALUE;
            } else {
                R[j] = A[q+j+1];
            }
        }

        // init counter vars for coming FOR
        int i = 0, j = 0;

        // sort Array
        for (int k = p; k<=r; k++) {
            if (L[i] <= R[j]) {
                A[k] = L[i];
                i++;
            } else {
                A[k] = R[j];
                j++;
            }
        }

        return A;
    }

    public static int[] mergeSortNew(int[] A, int p, int s) {
        if (p<s) {
            int q = (s - p) / 3 + p ;
            int r = q + ((s - p) / 3) + 1;
            mergeSortNew(A, p, q);
            mergeSortNew(A, q+1, r);
            mergeSortNew(A, r+1, s);
            mergeNew(A, p, q, r, s);
        }
        return A;
    }

    public static int[] mergeNew(int[] A, int p, int q, int r, int s) {
        // split Array in 2 halfs and generate subarrays
        int[] L = new int[q-p+2];
        int[] M = new int[r-q+1];
        int[] R = new int[s-r+1];

        // fill subarrays ; last diggit = max value
        for (int i = 0; i<L.length; i++) {
            if (i == L.length-1) {
                L[i] = Integer.MAX_VALUE;
            } else {
                L[i] = A[p+i];
            }
        }
        for (int j = 0; j<M.length; j++) {
            if (j == M.length-1) {
                M[j] = Integer.MAX_VALUE;
            } else {
                M[j] = A[q+j+1];
            }
        }
        for (int k = 0; k<R.length; k++) {
            if (k == R.length-1) {
                R[k] = Integer.MAX_VALUE;
            } else {
                R[k] = A[r+k+1];
            }
        }

        // init counter vars for coming FOR
        int i = 0, j = 0, k = 0;

        // sort Array
        for (int l = p; l<=s; l++) {
            int min = Math.min(Math.min(L[i], M[j]), R[k]);

            if (L[i] == min) {
                A[l] = L[i];
                i++;
            } else if (M[j] == min) {
                A[l] = M[j];
                j++;
            } else {
                A[l] = R[k];
                k++;
            }
        }

        return A;
    }

    public static <T extends Comparable<T>> T[] mergeSortGen(T[] A, int p, int r) {
        if (p<r) {
            int q = (p+r)/2;
            mergeSortGen(A,p,q);
            mergeSortGen(A,q+1,r);
            mergeGen(A,p,q,r);
        }
        return A;
    }

    public static <T extends Comparable<T>> T[] mergeGen(T[] A, int p, int q, int r) {
        ArrayList<T> arrList = new ArrayList<>();

        for (int i = p; i <= q; i++) {
            arrList.add(A[i]);
        }
        for (int i = r; i >= q + 1; i--) {
            arrList.add(A[i]);
        }

        int i = 0;
        int j = arrList.size() - 1;

        for (int k = p; k <= r; k++) {
            if (arrList.get(i).compareTo(arrList.get(j)) <= 0) {
                A[k] = arrList.get(i);
                i++;
            } else {
                A[k] = arrList.get(j);
                j--;
            }
        }

        return A;
    }

}
