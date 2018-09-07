package Algorithms.Mathmatics.Primes;

import java.math.BigInteger;
import java.util.ArrayList;

public class PrimeTest {

    public static void main(String[] args){

        ArrayList<Integer> list = primeSieveList(100);
        System.out.println(list.toString());
    }

    /**
     * This method ckeck if given number is prime and return true if prime else false
     * This method is suitable when number is small and you need to do test only for few numbers
     * 0 and 1 considered non-primes
     */
    public static boolean isPrime(int n){

        if ( n == 0 || n == 1)
            return false;
        long sqrtN = (long) Math.sqrt(n);
        for (long i =2 ; i<= sqrtN;i++)
            if( n%i == 0)
                return false;
        return true;
    }


    /**
     *  This method return boolean array of size n+1
     *  index refer to natural number while boolean value is prime or not
     *  if prine[index] = false -> index is prime
     *  else if prine[index] = true -> index is non-prime
     *
     *  0 and 1 considered non-primes
     */
    public static boolean[] primeSieveArray(int n){
        boolean[] isPrime = new boolean[n+1];
        isPrime[0] = true; isPrime[1] = true;
        for (int factor = 2; factor*factor <= n; factor++) {
            if (!isPrime[factor]) {
                for (int j = factor; factor*j <= n; j++) {
                    isPrime[factor*j] = true;
                }
            }
        }
        return isPrime;
    }


    /**
     * this method uses Sieve prime Algorithm to get only prime numbers in a list
     * good to use when you need to search through prime numbers linearly
     */
    public static ArrayList<Integer> primeSieveList(int n){
        ArrayList<Integer> list = new ArrayList<Integer>();
        boolean[] isPrime = new boolean[n+1];
        for (int factor = 2; factor*factor <= n; factor++) {
            if (!isPrime[factor]) {
                for (int j = factor; factor*j <= n; j++) {
                    isPrime[factor*j] = true;
                }
            }
        }
        for (int i =0;i<=n;i++)
            if (!isPrime[i])
                list.add(i);
        return list;
    }


    /**
     * this method implements miller rabin primality test
     * test is 100% correct for n < 341,550,071,728,321
     * after that there is a chance of error
     */
    public static boolean MillerRabinPrimalityTest(BigInteger n) {

        if (n.compareTo(new BigInteger("341550071728321")) >= 0) {
            return n.isProbablePrime(100);
        }

        int intN = n.intValue();
        if (intN == 1 || intN == 4 || intN == 6 || intN == 8) return false;
        if (intN == 2 || intN == 3 || intN == 5 || intN == 7) return true;

        int[] primesToTest = getPrimesToTest(n);
        if (n.equals(new BigInteger("3215031751"))) {
            return false;
        }
        BigInteger d = n.subtract(BigInteger.ONE);
        BigInteger s = BigInteger.ZERO;
        while (d.mod(BigInteger.valueOf(2)).equals(BigInteger.ZERO)) {
            d = d.shiftRight(1);
            s = s.add(BigInteger.ONE);
        }
        for (int a : primesToTest) {
            if (try_composite(a, d, n, s)) {
                return false;
            }
        }
        return true;
    }


    /**
     *  this method array of primes according to value of n for composite test
     */
    private static int[] getPrimesToTest(BigInteger n) {
        if (n.compareTo(new BigInteger("3474749660383")) >= 0) {
            return new int[]{2, 3, 5, 7, 11, 13, 17};
        }
        if (n.compareTo(new BigInteger("2152302898747")) >= 0) {
            return new int[]{2, 3, 5, 7, 11, 13};
        }
        if (n.compareTo(new BigInteger("118670087467")) >= 0) {
            return new int[]{2, 3, 5, 7, 11};
        }
        if (n.compareTo(new BigInteger("25326001")) >= 0) {
            return new int[]{2, 3, 5, 7};
        }
        if (n.compareTo(new BigInteger("1373653")) >= 0) {
            return new int[]{2, 3, 5};
        }
        return new int[]{2, 3};
    }


    /**
     *  this method is used in MillerRabinPrimalityTest to try compsite
     */
    private static boolean try_composite(int a, BigInteger d, BigInteger n, BigInteger s) {
        BigInteger aB = BigInteger.valueOf(a);
        if (aB.modPow(d, n).equals(BigInteger.ONE)) {
            return false;
        }
        for (int i = 0; BigInteger.valueOf(i).compareTo(s) < 0; i++) {
            // if pow(a, 2**i * d, n) == n-1
            if (aB.modPow(BigInteger.valueOf(2).pow(i).multiply(d), n).equals(n.subtract(BigInteger.ONE))) {
                return false;
            }
        }
        return true;
    }

}
