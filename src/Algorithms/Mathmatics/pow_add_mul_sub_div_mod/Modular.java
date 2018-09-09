package Algorithms.Mathmatics.pow_add_mul_sub_div_mod;

import Algorithms.Mathmatics.GCD_and_LCM.GCD;
import Algorithms.Mathmatics.PrimesAndFactorization.Factorization;
import Algorithms.Mathmatics.PrimesAndFactorization.PrimeTest;
import Algorithms.Mathmatics.numbers.TotientFunction;

import java.util.ArrayList;

/**
 * @author ehab arman
 * @date 9-9-2018
 */

public class Modular {

    public static void main(String[] args ){
    }


    /*
        this method returns pow after apply totient reduce function for modular:
        k^phi(n) = 1 mod n if GCD(n,k)=1
        best to be used before Power.fastModPow to reduce power
     */
    public static int reduceModPow(int num,int pow,int m){
        if(GCD.euclideanGCD(num,m)==1){
            if (PrimeTest.isPrime(m)){ // m is prime and totient= m-1
                return pow%(m-1);
            }

            ArrayList<Integer> p = PrimeTest.primeSieveList((int)Math.sqrt(m)*2);
            return (int) (pow%TotientFunction.computeTotientQuery(m,p));
        }

        // not relatively prime
        return pow;
    }
}
