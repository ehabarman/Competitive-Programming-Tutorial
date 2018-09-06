package Algorithms.Mathmatics.GCD_and_LCM;

public class LCM {

    public static void main(String[] args){
        long[] arr = {2,3,5,7};
        System.out.println(arrayLCM(arr));
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
}
