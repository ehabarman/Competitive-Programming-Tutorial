package Algorithms.Dynamic;

import java.util.Arrays;

/**
 * @author  Ehab Arman
 * @Date    23-9-2018
 */
public class LargestDivisiblePairsSubset {

    /**
     *
     * Summary:
     *
     * Given an array of n distinct elements,
     * find length of the largest subset such that every pair in the subset is such
     * that the larger element of the pair is divisible by smaller element.
     *
     * Examples:
     *
     * Input : arr[] = {10, 5, 3, 15, 20}
     * Output : 3
     * Explanation: The largest subset is 10, 5, 20.
     * 10 is divisible by 5, and 20 is divisible by 10.
     *
     * Input : arr[] = {18, 1, 3, 6, 13, 17}
     * Output : 4
     * Explanation: The largest subset is 18, 1, 3, 6,
     * In the subsequence, 3 is divisible by 1,
     * 6 by 3 and 18 by 6.
     *
     */


    // function to find the longest Subsequence
    public static int largestSubset(int a[], int n)
    {
        Arrays.sort(a);
        // dp[i] is going to store size of largest
        // divisible subset beginning with a[i].
        int []dp = new int [n];

        // Since last element is largest, d[n-1] is 1
        dp[n - 1] = 1;

        // Fill values for smaller elements.
        for (int i = n - 2; i >= 0; i--) {

            // Find all multiples of a[i] and consider
            // the multiple that has largest subset
            // beginning with it.
            int mxm = 0;
            for (int j = i + 1; j < n; j++)
                if (a[j] % a[i] == 0)
                    mxm = Math.max(mxm, dp[j]);

            dp[i] = 1 + mxm;
        }

        int max = dp[0];
        for(int i=1; i<dp.length;i++)
            max = max > dp[i] ? max : dp[i];
        return max;
    }

    // driver code to check the above function
    public static void main(String[] args)
    {
        int a[] = { 10, 3, 5, 15, 20};
        int n = a.length;
        System.out.println(largestSubset(a, n));
    }
}
