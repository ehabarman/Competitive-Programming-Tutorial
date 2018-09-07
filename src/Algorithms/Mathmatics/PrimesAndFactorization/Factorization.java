package Algorithms.Mathmatics.PrimesAndFactorization;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ehab arman
 * @date 7-9-2018
 */
public class Factorization {

    /**
     * for very large numbers use the following algorithm to calculate factorization
     *
     * Less than 10^50: Lenstra elliptic curve factorization
     * Less than 2^70 or so: Richard Brent's modification of Pollard's rho algorithm.
     * Less than 10^100: Quadratic Sieve
     * More than 10^100: General Number Field Sieve
     *
     */
    public static int MAXN = 1000001; // MAXN = maximum number to be factorized + 1
    public static int spf[] = new int[MAXN]; // smallest prime factor of x is spf[x]

    public static void main(String[] args){

    }


    /**
     *
     *  this method return list contains all prime factors of a given number
     */
    public static ArrayList<Integer> primeFactors(int n) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        while (n%2==0)
        {
            list.add(2);
            n /= 2;
        }
        for (int i = 3; i <= Math.sqrt(n); i+= 2)
        {
            while (n%i == 0)
            {
                list.add(i);
                n /= i;
            }
        }
        if (n > 2)
            list.add(n);

        return list;
    }


    /**
     *  this method require:
     *  1-  initialize MAXN and spf array
     *  2- call sieve method
     *
     *  factorization for each query equals logn
     *
     *  return list contains factors of the given query
     */
    public static ArrayList<Integer> queryFactorization(int x)
    {
        ArrayList<Integer> list = new ArrayList<>();
        while (x != 1)
        {
            list.add(spf[x]);
            x = x / spf[x];
        }
        return list;
    }


    /**
     *  this method calculate SPF (Smallest Prime Factor) for every number
     *  up to MAXN ( equals highest number to be factorized + 1)
     *  Time Complexity : O(nloglogn)
     *  must be called before using [ queryFactorization ] and initialize MAXN and spf array as static values
     */
    public static void sieve()
    {
        spf[1] = 1;
        for (int i=2; i<MAXN; i++)
            spf[i] = i;
        for (int i=4; i<MAXN; i+=2)
            spf[i] = 2;
        for (int i=3; i*i<MAXN; i++)
        {
            if (spf[i] == i)
            {
                for (int j=i*i; j<MAXN; j+=i)
                    if (spf[j]==j)
                        spf[j] = i;
            }
        }
    }


    /**
     * method to count number of odd prime factors
     */
    public static int countOddPrimeFactors(int n)
    {
        int result = 1;
        while (n % 2 == 0)
            n /= 2;
        for (int i = 3; i * i <= n; i += 2) {
            int divCount = 0;
            while (n % i == 0) {
                n /= i;
                ++divCount;
            }
            result *= divCount + 1;
        }
        if (n > 2)
            result *= 2;
        return result;
    }

    /**
     *  this method return list contains distinct prime factors of the given number
     *  ex : input 180 returns [2, 3, 5]
     */
    public static List<Integer> distinctPrimeFactors(int n)
    {
        List<Integer> res = new ArrayList<Integer>();
        if (n % 2 == 0)
        {
            while (n % 2 == 0)
                n = n / 2;
            res.add(2);
        }

        for (int i = 3; i <= Math.sqrt(n);
             i = i + 2)
        {
            if (n % i == 0)
            {
                while (n % i == 0)
                    n = n / i;
                res.add(i);
            }
        }
        if (n > 2)
            res.add(n);
        return res;
    }


}

