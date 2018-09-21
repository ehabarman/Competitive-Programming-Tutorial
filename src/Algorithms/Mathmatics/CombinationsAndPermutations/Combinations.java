package Algorithms.Mathmatics.CombinationsAndPermutations;

import Algorithms.Mathmatics.pow_add_mul_sub_div_mod.Power;

import java.util.ArrayList;

/**
 * @author ehab arman
 * @date 7-9-2018
 */
public class Combinations {

    // Driver program
    public static void main(String[] args)
    {

    }
    // Returns value of Binomial Coefficient C(n, k)

    /**
     *  this method returns nCr (number of combination that we can create from set n with size of r)
     *  this approach called binomial Coefficient approach which cost time O(n)
     *  if value of n too big then there is a chance to return minus value
     *  thus BigInteger will be required
     *
     */
    public static long nCrBinomialCoeff( long n,long r){

        long res = 1;
        if (r > n - r)
            r = n - r;
        for (int i = 0; i < r; ++i)
        {
            res *= (n - i);
            res /= (i + 1);
        }
        return res;
    }


    /**
     *  dynamic part in nCrModlucas
     */
    private static int nCrModpDP(int n, int r, int p)
    {
        int [] C = new int[r+1];
        C[0] = 1;
        for (int i = 1; i <= n; i++)
        {
            for (int j = Math.min(i, r); j > 0; j--)
                C[j] = (C[j] + C[j-1])%p;
        }
        return C[r];
    }

    /**
     *  this method return value of nCr % p using lucas method
     *  time complexity is O(p2 * Logp n), space
     *  it can be improved for queries
     */
    public static int nCrModpLucas(int n, int r, int p)
    {

        if (r==0)
            return 1;
        int ni = n%p, ri = r%p;
        return (nCrModpLucas(n/p, r/p, p) * nCrModpDP(ni, ri, p)) % p;
    }

}
