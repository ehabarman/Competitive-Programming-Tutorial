package Algorithms.Mathmatics.GCD_and_LCM;

import java.math.BigInteger;

/**
 * @author ehab arman
 * @date 7-9-2018
 */

public class GCD {
    private static final BigInteger TWO = new BigInteger("2");
    public static final BigInteger RES = BigInteger.ONE;
    public static void main(String[] args){

    }


    /**
     * calculate GCD using Euclidean algorithm
     * and return the result as long value
     */
    public static long euclideanGCD(long a, long b)
    {
        return a == 0 ? b : euclideanGCD(b % a, a);
    }


    /**
     *
     * calculate GCD using Euclidean algorithm
     * and return the result as BigInteger
     * only suitable for huge numbers ( n > 10^30 )
     * initial value of res is defined as static constant in class with name RES
     */
    public static BigInteger binaryEuclideanGCD(BigInteger a, BigInteger b, BigInteger res){
        int compare = a.compareTo(b);
        if ( compare == 0)
            return res.multiply(a);
        boolean first = a.mod(TWO).compareTo(BigInteger.ZERO)==0;
        boolean second = b.mod(TWO).compareTo(BigInteger.ZERO)==0;
        if ( first && second )
            return binaryEuclideanGCD( a.divide(TWO),b.divide(TWO),res.multiply(TWO));
        else if (first)
            return binaryEuclideanGCD(a.divide(TWO),b,res);
        else if (second)
            return binaryEuclideanGCD(a,b.divide(TWO),res);
        else if ( compare > 0)
            return binaryEuclideanGCD(a.subtract(b),b,res);
        else
            return binaryEuclideanGCD(a,b.subtract(a),res);
    }


    /**
     * calculate gcd of the array (arr[n]) using euclideanGCD n times
     * it returns result as long value
     */
    public static long arrayGCD(long arr[])
    {
        long result = arr[0];
        for (int i = 1; i < arr.length; i++)
            result = euclideanGCD(arr[i], result);

        return result;
    }


    /**
     * this method is using to reduce large number in largeGCD to a small long number
     * can't used it out of this class
     */
    private static long reduceB(long a, String b)
    {
        long result = 0;
        for (int i = 0; i < b.length(); i++)
        {
            result = (result * 10 +
                    b.charAt(i) - '0') % a;
        }
        return result;
    }


    /**
     *  this method return GCD(a,b) when b very large (10^12 <= b < 10^250)
     */
    public static long largeGCD(long a, String b)
    {
        long num = reduceB(a, b);
        return euclideanGCD(num, a);
    }


    /**
     *  this method is to calculate result of multiplying fractions n/d
     *  input is the number fractions (N)
     *  num[i]/den[i] = ith fraction
     *  result printed on console within fucntion as n/d in it's simplest form
     */
    public static void fractionsProductReduce(int n, long num[],long den[]){
        int new_num = 1, new_den = 1;

        for (int i = 0; i < n; i++) {
            new_num *= num[i];
            new_den *= den[i];
        }
        long GCD =  euclideanGCD(new_num, new_den);
        new_num /= GCD;
        new_den /= GCD;

        System.out.println(new_num + "/" +new_den);
    }


    /**
     *  this method return minimum number of operations to make GCD of a given array equals one
     *
     * @param arr     given array
     * @return      return number of operations
     */
    public static long minimumOpeationsForGCDToEqualOne(long arr[])
    {
        long one = 0;
        int size = arr.length;
        for (int i = 0; i < size; i++)
            if (arr[i] == 1)
                one++;
        if (one != 0)
            return size - one;
        int minimum = Integer.MAX_VALUE;
        for (int i = 0; i < size; i++) {
            long g = arr[i];
            for (int j = i + 1; j < size; j++) {
                g = euclideanGCD(arr[j], g);
                if (g == 1) {
                    minimum = Math.min(minimum, j - i);
                    break;
                }
            }
        }
        if (minimum == Integer.MAX_VALUE)
            return -1;
        else
            return size + minimum - 1;
    }


    /**
     *  this method replace matrix elements with maxGCD of (GCD(col,row)) for each element
     */
    public static void replaceMatrixElementsWithMaxGCD(long [][]mat, int row, int col)
    {
        long []rgcd = new long[row] ;
        long []cgcd = new long[col];
        for (int i = 0; i < row; i++)
        {
            for (int j = 0; j < col; j++)
            {
                rgcd[i] = euclideanGCD(rgcd[i], mat[i][j]);
                cgcd[j] = euclideanGCD(cgcd[j], mat[i][j]);
            }
        }
        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++)
                mat[i][j] = Math.max(rgcd[i], cgcd[j]);
    }


    /**
     *  method return result of GCD between 2 numbers formed by repeating n x and y times
     *  (ex:n,x,y = 12,2,3 -> GCD(1212,121212))
     * @param n     required number
     * @param x     first repetitions
     * @param y     second repetitions
     * @return  return result as string
     */
    public static String findGCDOfNRepeatitions(int n, long x, long y) {
        long g = euclideanGCD(x, y);
        String s ="";
        for (int i = 0; i < g; i++)
            s+=n;
        return s;
    }


    /**
     * This method counts number of pairs (A <= N, B <= N) such that gcd (A , B) is B
     * and return count number as result
     */
    public static int countPairsWithGCDEqualsB(int n) {
        int k = n;
        int imin = 1;
        int ans = 0;
        while (imin <= n) {
            int imax = n / k;
            ans += k * (imax - imin + 1);
            imin = imax + 1;
            k = n / imin;
        }
        return ans;
    }


    /**
     * this method is given N and M
     * task is to find whether numbers 1 to N can be divided into two sets such that the absolute difference between the
     * sum of two sets is M and gcd of the sum of two sets is 1
     * return true if splittable else false
     */
    public static boolean isSplittable(int n, int m)
    {
        int totalSum = (n * (n + 1)) / 2;
        int sum1 = (totalSum + m) / 2;
        int sum2 = totalSum - sum1;
        if (totalSum < m)
            return false;
        if (sum1 + sum2 == totalSum &&
                sum1 - sum2 == m)
            return (euclideanGCD(sum1, sum2) == 1);
        return false;
    }
}
