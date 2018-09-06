package Algorithms.Mathmatics.factorial;
import java.math.BigInteger;

public class Factorial {

    public static void main(String [] args){

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
}
