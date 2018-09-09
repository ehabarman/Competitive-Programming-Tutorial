package Algorithms.Mathmatics.numbers;

import Algorithms.Mathmatics.CombinationsAndPermutations.Combinations;
import Algorithms.Mathmatics.PrimesAndFactorization.Factorization;
import Algorithms.Mathmatics.PrimesAndFactorization.PrimeTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ehab arman
 * @date 7-9-2018
 */
public class Numbers {

    public static void main(String[] args){

        System.out.println(3*4%5);
        ArrayList<Integer> list = PrimeTest.primeSieveList((int)Math.sqrt(550));
        System.out.println(list.toString());
        for (int i=0; i<100; i++)
                System.out.print(getFibonacciValue(i) + " ");

    }
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
        long c = Combinations.nCrBinomialCoeff(2*n, n);
        return c/(n+1);
    }


    /**
     * this method return true if param n is smith number else false
     * Smith Number is a composite number whose sum of digits is equal to the sum of digits in its prime factorization.
     * primes is an arraylist contains primes below certain value in our case it should be sqrt(n)
     * primes can be calculated using PrimeTest.primeSieveList() method
     */
    public static boolean isSmith(int n, ArrayList<Integer> primes)
    {
        int original_no = n;
        int pDigitSum = 0;
        int size = primes.size();
        for (int i = 0; i< size && primes.get(i) <= n/2; i++)
        {
            while (n % primes.get(i) == 0)
            {
                int p = primes.get(i);
                n = n/p;
                while (p > 0)
                {
                    pDigitSum += (p % 10);
                    p = p/10;
                }
            }
        }
        if (n != 1 && n != original_no)
        {
            while (n > 0)
            {
                pDigitSum = pDigitSum + n%10;
                n = n/10;
            }
        }
        int sumDigits = 0;
        while (original_no > 0)
        {
            sumDigits = sumDigits + original_no % 10;
            original_no = original_no/10;
        }
        return (pDigitSum == sumDigits);
    }


    /**
     * this method return value of nth value in fibonacci sequence
     * this method use simple equation to calculate value
     * equation : nth value = round(fn-1 * golden ration value(=1.6180339) )
     */
    public static int getFibonacciValue (int n)
    {
        double PHI = 1.6180339;
        int f[] = { 0, 1, 1, 2, 3, 5 };
        if (n < 6)
            return f[n];
        int t = 5;
        int fn = 5;
        while (t < n) {
            fn = (int)Math.round(fn * PHI);
            t++;
        }
        return fn;
    }
}
