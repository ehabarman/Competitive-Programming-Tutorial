package Algorithms.Mathmatics.numbers;

import Algorithms.Mathmatics.CombinationsAndPermutations.Combinations;
import Algorithms.Mathmatics.PrimesAndFactorization.Factorization;
import java.util.List;

/**
 * @author ehab arman
 * @date 7-9-2018
 */
public class Numbers {


    /**
     *  this method return politeness of a given number
     *  politeness: the number of ways it can be expressed as the sum of consecutive integers.
     *  pliteness = count of odd prime factors - 1
     */
    static int politeness(int n)
    {
        return Factorization.countOddPrimeFactors(n) - 1;
    }


    /**
     *
     * return true if number is hoax number else false
     * Hoax number is a composite number, whose sum of digits is equal to the sum of digits of its distinct prime factors
     */
    public static boolean isHoax(int n)
    {
        List<Integer> pf = Factorization.distinctPrimeFactors(n);
        if (pf.get(0) == n)
            return false;
        int all_pf_sum = 0;
        for (int i = 0; i < pf.size(); i++)
        {
            int pf_sum;
            for (pf_sum = 0; pf.get(i) > 0;
                 pf_sum += pf.get(i) % 10,
                         pf.set(i,pf.get(i) / 10));

            all_pf_sum += pf_sum;
        }
        int sum_n;
        for (sum_n = 0; n > 0; sum_n += n % 10, n /= 10);
        return sum_n == all_pf_sum;
    }

    /**
     * if value of n too big then there is a chance to return minus value
     * thus BigInteger will be required
     *
     *  catalan(n) = nCr(n= 2n,r= n) / (n+1)
     *
     *  there is dynamic solution for the problem
     *  solution suitable for multiple queries with small numbers
     */
    public static long getCatalan(long n)
    {

        long c = Combinations.binomialCoeff(2*n, n);
        return c/(n+1);
    }
}
