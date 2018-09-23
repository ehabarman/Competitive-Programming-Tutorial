package Algorithms.Mathmatics.CombinationsAndPermutations;

/**
 * @author Ehab Arman
 * @Date 23-9-2018
 */

public class Permutations {


    /**
     *  this method takes n and k and returns the result of n!/(n-k)!
     *  time complexity O(n)
     *  space O(1)     *
     */
    public static int PermutationCoeff(int n, int k)
    {
        int Fn = 1, Fk = 1;

        // Compute n! and (n-k)!
        for (int i = 1; i <= n; i++)
        {
            Fn *= i;
            if (i == n - k)
                Fk = Fn;
        }
        int coeff = Fn / Fk;
        return coeff;
    }
}
