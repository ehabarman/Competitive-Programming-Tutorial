package Algorithms.Mathmatics.pow_add_mul_sub_div_mod;

/**
 * @author ehab arman
 * @date 9-9-2018
 */
public class Power {


    /**
     *  this method returns the result of num^pow in time O(log pow)
     */
    public static long fastPower(long num, long pow){
        if (pow == 0)
            return 1;
        if ( pow ==1 || num == 1 || num == 0)
            return num;
        if (pow < 0)
            return 1/fastPower(num, -pow);
        if (pow%2 == 0){
            long temp = fastPower(num,pow/2);
            return temp * temp;
        }
        else
            return num * fastPower(num,pow-1);
    }

    /**
     *  this mehtod returns the result of (num^pow)%m
     */
    public static long fastModularPower(long num, long pow, long m)
    {
        long res = 1;
        num = num % m;
        while (pow > 0)
        {
            if((pow & 1)==1)
                res = (res * num) % m;
            pow = pow >> 1;
            num = (num * num) % m;
        }
        return res;
    }


}
