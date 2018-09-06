package Algorithms.Mathmatics.GCD_and_LCM;

public class GCD {


    /**
     * calculate GCD using Euclidean algorithm
     */

    public static long euclideanGCD(long a, long b)
    {
        if (a == 0)
            return b;
        return euclideanGCD(b % a, a);
    }

    /**
     * calculate gcd of the array (arr[n]) using euclideanGCD n times
     */
    public static long arrayGCD(long arr[], long n)
    {
        long result = arr[0];
        for (int i = 1; i < n; i++)
            result = euclideanGCD(arr[i], result);

        return result;
    }
}
