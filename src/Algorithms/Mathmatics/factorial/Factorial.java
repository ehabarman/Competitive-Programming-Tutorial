package Algorithms.Mathmatics.factorial;

import Algorithms.Mathmatics.PrimesAndFactorization.Factorization;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * @author ehab arman
 * @date 7-9-2018
 */
public class Factorial {


    public static void main(String [] args){
        Factorization.MAXN=101;
        Factorization.sieve();
        for (int i=1; i<100; i++)
            if (isSphenic(i))
                System.out.println(i);
    }


    /**
     * this method returns result of n! in the form of string
     */
    public static String calculateFactorial(int n) {
        BigInteger answer=BigInteger.ONE;
        boolean oddUptoValue=((n&1)==1);
        int tempUptoValue=n;
        if(oddUptoValue){
            tempUptoValue=n-1;
        }
        int nextSum = tempUptoValue;
        int nextMulti = tempUptoValue;
        while (nextSum >= 2){
            answer=answer.multiply(BigInteger.valueOf(nextMulti));
            nextSum -= 2;
            nextMulti += nextSum;
        }
        if(oddUptoValue){
            answer=answer.multiply(BigInteger.valueOf(n));
        }
        return answer.toString();
    }


    /**
     *  method to check if given number is sphenic number
     *  Sphenic Number is a positive integer n which is product of exactly three distinct primes.
     *
     *  VERY IMPORTANT: before running method you need to use factorization
     *  in case of queries use sieve from Factorization
     *  else use normal factorization
     *
     */
    public static boolean isSphenic(int n){
        Set<Integer> s = new HashSet<Integer>();
        while (n > 1)
        {
            int lpf = Factorization.spf[n];
            int init_size = s.size();
            s.add(lpf);
            if (s.size() == init_size || s.size() > 3)
                return false;
            n /= lpf;
        }
        return (s.size() == 3);
    }



}
