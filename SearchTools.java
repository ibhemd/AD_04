import java.util.Arrays;
import java.util.Random;

public class SearchTools {

    public static int linSearch(int[] A, int x) {
        int res = -1;
        for(int i = 0; i < A.length; i++) {
           if (A[i] == x) res = i;
        }
        return res;
    }

    public static int binSearch(int[] A, int x, int l, int r) {
        int res;
        int q = r/2;
        if (A.length == 0) {
            res = -1;
        } else if (x == A[q]) {
            res = q;
        } else if (x < A[q]) {
            int[] tempArr = Arrays.copyOfRange(A,0,q);
            res = binSearch(tempArr,x,l,tempArr.length);
        } else {
            int[] tempArr = Arrays.copyOfRange(A,q+1,r);
            int tempRes = binSearch(tempArr,x,q+1,tempArr.length);
            if (tempRes == -1) {
                res = tempRes;
            } else {
                res = q + 1 + tempRes;
            }
        }
        return res;
    }

    public static int binSearchNew(int[] A, int x, int l, int r) {
        int res;
        int s = (r - l) / 3 + l;
        int t = l + ((r - l) / 3) + 1;
        /*System.out.println(Arrays.toString(A));
        System.out.println("l: " + l);
        System.out.println("r: " + r);
        System.out.println("s: " + s);
        System.out.println("t: " + t);*/
        if (A.length == 0) {
            res = -1;
        } else if (A.length == 1) {
            if (x == A[0]) {
                res = 0;
            } else {
                res = -1;
            }
        } else if (x == A[s]) {
            res = s;
        } else if (x == A[t]) {
            res = t;
        } else if (x < A[s]) {
            int[] tempArr = Arrays.copyOfRange(A,0,s);
            res = binSearchNew(tempArr,x,l,tempArr.length);
        } else if (x < A[t]) {
            int[] tempArr = Arrays.copyOfRange(A,s,t);
            int tempRes = binSearchNew(tempArr,x,s,tempArr.length);
            if (tempRes == -1) {
                res = tempRes;
            } else {
                res = s + tempRes;
            }
        } else {
            int[] tempArr = Arrays.copyOfRange(A,t,r);
            int tempRes = binSearchNew(tempArr,x,0,tempArr.length);
            if (tempRes == -1) {
                res = tempRes;
            } else {
                res = t + tempRes;
            }
        }
        return res;
    }

    public static void main(String[] args) {

        /*

        int[] testArr = {1,3,5,9,12,15,24,27,32};

        // test linSearch
        System.out.println(linSearch(testArr,3)); // 1
        System.out.println(linSearch(testArr,7)); // -1
        System.out.println(linSearch(testArr,15)); // 5

        // test binSearch
        System.out.println(binSearch(testArr, 3, 0, testArr.length)); // 1
        System.out.println(binSearch(testArr, 5, 0, testArr.length)); // 2
        System.out.println(binSearch(testArr, 7, 0, testArr.length)); // -1
        System.out.println(binSearch(testArr, 15, 0, testArr.length)); // 5
        System.out.println(binSearch(testArr, 27, 0, testArr.length)); // 7
        System.out.println(binSearch(testArr, 32, 0, testArr.length)); // 8

        // test binSearchNew
        System.out.println(binSearchNew(testArr, 3, 0, testArr.length)); // 1
        System.out.println(binSearchNew(testArr, 5, 0, testArr.length)); // 2
        System.out.println(binSearchNew(testArr, 7, 0, testArr.length)); // -1
        System.out.println(binSearchNew(testArr, 15, 0, testArr.length)); // 5
        System.out.println(binSearchNew(testArr, 27, 0, testArr.length)); // 7
        System.out.println(binSearchNew(testArr, 32, 0, testArr.length)); // 8

         */

        int[] htI = SortTools.createSequenceInc(100000);
        int[] mI = SortTools.createSequenceInc(1000000);
        int[] hmI = SortTools.createSequenceInc(100000000);
        int[] xxI = SortTools.createSequenceInc(685154321);

        long[] Begins = new long[500];
        long[] Ends = new long[500];
        long[] Durations = new long[500];
        long Duration = 0;

        int[] testArray = xxI;

        for (int i = 0; i < 500; i++) {
            Random random = new Random();
            int rnd = random.nextInt(testArray.length);
            Begins[i] = System.nanoTime();
            linSearch(testArray,rnd);                          // -->> manual changes here for tests: methods and arrays
            Ends[i] = System.nanoTime();
            Durations[i] = Ends[i] - Begins[i];
            Duration += Durations[i];
        }

        System.out.println(Duration);

        // Test: 100.000 Inc Random
        // Duration 500x linSearch:    15665000
        // Duration 500x binSearch:    68292500
        // Duration 500x binSearchNew: 93613400

        // Test: 100.000 Inc Negative
        // Duration 500x linSearch:    14949100
        // Duration 500x binSearch:    71013900
        // Duration 500x binSearchNew: 28827600

        // Test: 1.000.000 Inc Random
        // Duration 500x linSearch:    114337900
        // Duration 500x binSearch:    404890300
        // Duration 500x binSearchNew: 482901900

        // Test: 1.000.000 Inc Negative
        // Duration 500x linSearch:    110682500
        // Duration 500x binSearch:    364393600
        // Duration 500x binSearchNew: 204560200

        // Test: 100.000.000 Inc Random
        // Duration 500x linSearch:    14232642000
        // Duration 500x binSearch:    39694626300
        // Duration 500x binSearchNew: 49775697600

        // Test: 100.000.000 Inc Negative
        // Duration 500x linSearch:    14330221200
        // Duration 500x binSearch:    38378406400
        // Duration 500x binSearchNew: 21804507900

        // Test: 685.154.321 Inc Random
        // Duration 500x linSearch:    97497164000
        // Duration 500x binSearch:    java.lang.OutOfMemoryError: Java heap space
        // Duration 500x binSearchNew: java.lang.OutOfMemoryError: Java heap space

        // Test: 685.154.321 Inc Negative
        // Duration 500x linSearch:    97854883900
        // Duration 500x binSearch:    java.lang.OutOfMemoryError: Java heap space
        // Duration 500x binSearchNew: java.lang.OutOfMemoryError: Java heap space

    }

}
