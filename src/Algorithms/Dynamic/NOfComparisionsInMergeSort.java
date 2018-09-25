package Algorithms.Dynamic;

/**
 * @author  Ehab Arman
 * @Date    25/9/2018
 */
public class NOfComparisionsInMergeSort {

    /**
     *
     *  Summary:
     *  This problem requires you to find the maximum and minimum number of comparisions required
     *  in merge sort to merge sub arrays
     *
     *  void merge(int arr[], int l, int m, int r)
     *     {
     *         int n1 = m - l + 1;
     *         int n2 = r - m;
     *         int L[] = new int [n1];
     *         int R[] = new int [n2];
     *         for (int i=0; i<n1; ++i)
     *             L[i] = arr[l + i];
     *         for (int j=0; j<n2; ++j)
     *             R[j] = arr[m + 1+ j];
     *         int i = 0, j = 0;
     *         int k = l;
     *         while (i < n1 && j < n2)
     *         {
     *             if (L[i] <= R[j])
     *             {
     *                 arr[k] = L[i];
     *                 i++;
     *             }
     *             else
     *             {
     *                 arr[k] = R[j];
     *                 j++;
     *             }
     *             k++;
     *         }
     *         while (i < n1)
     *         {
     *             arr[k] = L[i];
     *             i++;
     *             k++;
     *         }
     *         while (j < n2)
     *         {
     *             arr[k] = R[j];
     *             j++;
     *             k++;
     *         }
     *     }
     *
     *     void sort(int arr[], int l, int r)
     *     {
     *         if (l < r)
     *         {
     *             int m = (l+r)/2;
     *             sort(arr, l, m);
     *             sort(arr , m+1, r);
     *             merge(arr, l, m, r);
     *         }
     *     }
     *
     *     void mergeSort(int arr[], int l, int r)
     *     {
     *         if (l < r)
     *         {
     *             int m = l+(r-l)/2;
     *             mergeSort(arr, l, m);
     *             mergeSort(arr, m+1, r);
     *             merge(arr, l, m, r);
     *         }
     *     }
     *
     *
     */

    public static final double LOG2 = Math.log(2);//natural log of 2


    //Driver
    public static void main(String[] args){

        for(int i = 0 ; i< 12 ; i++)
            System.out.println(i+" ----- "+minimumNumberOfComparisions(i));

        System.out.println("\n\n\n==================================================\n\n\n");
        for(int i = 0 ; i< 12 ; i++)
            System.out.println(i+" ----- "+maximumNumberOfComparisions(i));
    }

    // calculate maximum number of comparision in merge sort with array of size n
    public static long maximumNumberOfComparisions(long n){

        if ( n < 2 )
            return 0;
        int pow =(int) Math.ceil(log2(n));
        return (n*pow)+1 - (2 << (pow-1) );
    }

    // calculate mimimum number of comparision in merge sort with array of size n
    public static long minimumNumberOfComparisions(long n){

        int lgn = (int) Math.floor(log2(n));
        double first = (n/2.0)*( lgn + 1);
        double sum = zigzag(n/2.0);


        for(int i =1; i <= lgn;i++){
            sum+= (2 << (i-1)) * zigzag( n/((double)(2<<i)) );
        }
        return (long)(first - sum);
    }

    // to calculate log (n) of base 2
    public static double log2( double n){
        return Math.log(n)/LOG2;
    }

    // zigzag function required in minimum calculations
    public static double zigzag (double x){
        return Math.min(  x - Math.floor(x) , Math.ceil(x) - x );
    }


}
