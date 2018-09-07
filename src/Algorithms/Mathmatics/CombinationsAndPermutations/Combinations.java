package Algorithms.Mathmatics.CombinationsAndPermutations;

/**
 * @author ehab arman
 * @date 7-9-2018
 */
public class Combinations {

    public static void main(String[] args){


    }
    // Returns value of Binomial Coefficient C(n, k)

    /**
     *  this method returns nCr (number of combination that we can create from set n with size of r)
     *  this approach called binomial Coefficient approach which cost time O(n)
     *  if value of n too big then there is a chance to return minus value
     *  thus BigInteger will be required
     *
     */
    public static long binomialCoeff( long n,long r){

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
}
