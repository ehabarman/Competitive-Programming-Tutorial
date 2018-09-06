package Algorithms.Mathmatics.GCD_and_LCM;
import Algorithms.Mathmatics.factorial.*;

public class LCM {

    public static void main(String[] args){
        long[] num = {2,3,5};
        long[] den = {7,14,3};
        System.out.println(lcmOfFractions(num,den));
    }
    /**
     * calculate LCM using GCD
     */
    public static long lcm(long a, long b)
    {
        return (a*b)/GCD.euclideanGCD(a, b);
    }

    /**
     *  calculate LCM of array
     */
    public static long arrayLCM(long[] arr)
    {
        long lcm_of_array_elements = 1;
        int divisor = 2;
        while (true) {
            int counter = 0;
            boolean divisible = false;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == 0) {
                    return 0;
                }
                else if (arr[i] < 0) {
                    arr[i] = arr[i] * (-1);
                }
                if (arr[i] == 1) {
                    counter++;
                }
                if (arr[i] % divisor == 0) {
                    divisible = true;
                    arr[i] = arr[i] / divisor;
                }
            }
            if (divisible) {
                lcm_of_array_elements = lcm_of_array_elements * divisor;
            }
            else {
                divisor++;
            }
            if (counter == arr.length) {
                return lcm_of_array_elements;
            }
        }
    }

    /**
     * this method returns the result of LCM((n-1)!, n!, (n+1)! ))
     * note: the answer is always (n+1)! because ( (n-1)! * n * n+1 ) ==  (n! * n+1) == (n+1)!
     */
    public static String neighbourFactorialLCM(int n)
    {
        return Factorial.calculateFactorial(n + 1);
    }

    /**
     *  this method calculates LCM of group of fractions number and return result as string
     *  size of num[] and den[] must be the same
     *
     *  result = LCM of all the numerator of Rational number's / GCD of all the denominator of Rational number's
     */
    public static String lcmOfFractions(long []num, long[]den)
    {
        return arrayLCM(num)+ "/" +GCD.arrayGCD(den) ;
    }
}
