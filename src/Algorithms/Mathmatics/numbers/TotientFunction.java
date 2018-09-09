package Algorithms.Mathmatics.numbers;

import Algorithms.Mathmatics.PrimesAndFactorization.PrimeTest;

import java.util.ArrayList;

public class TotientFunction {

    public static void main(String[] args) {

        long t = System.currentTimeMillis();
        ArrayList<Integer> p = PrimeTest.primeSieveList(200);
        for(int i = 0 ; i < 10000 ; i+=2)
            System.out.print(computeTotientQuery(i,p)+"  ");
        System.out.println(System.currentTimeMillis()-t);

        t = System.currentTimeMillis();
        long [] phi = computeTotient(10000);
        for(int i = 0 ; i < 10000 ; i++)
            System.out.print(phi[i]+"  ");
        System.out.println(System.currentTimeMillis()-t);

    }


    /**
     * this method return list contains all totient values of all numbers below n
     * suitable when pre calculate values or when we have big sequence
     */
    public static long[] computeTotient(int n) {
        long phi[] = new long[n + 1];
        for (int i = 1; i <= n; i++)
            phi[i] = i;
        for (int p = 2; p <= n; p++) {
            if (phi[p] == p) {
                phi[p] = p - 1;
                for (int i = 2 * p; i <= n; i += p) {
                    phi[i] = (phi[i] / p) * (p - 1);
                }
            }
        }
        return phi;
    }


    /**
     *      this method calculate Totient of given query n
     *      this method suitable for queries when n is not a continuous sequence 0-> n
     * @param n     value of query
     * @param p     list contains all primes below sqrt(MAXN) [we can get it from PrimeTest.primeSieveList]
     */
    public static long computeTotientQuery(long n, ArrayList<Integer> p)
    {
        long res = n;
        for (int i=0; p.get(i)*p.get(i) <= n; i++)
        {
            if (n % p.get(i)== 0)
            {
                res -= (res / p.get(i));
                while (n % p.get(i)== 0)
                    n /= p.get(i);
            }
        }
        if (n > 1)
            res -= (res / n);
        return res;
    }
}
