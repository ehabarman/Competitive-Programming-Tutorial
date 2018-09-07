package Algorithms.Dynamic;

/**
 * @author ehab arman
 * @date 7-9-2018
 */
public class catalanDP {

    /**
     * dynamic solution for the problem
     * if numbers to huge then will need BigInteger
     */


    public static void main(String[] args){
        for (int i = 0; i < 10; i++)
            System.out.println(catalanDP(i));
    }



    public static long catalanDP(int n)
    {
        if ( n < 2)
            return 1;
        long[] catalan = new long[n+1];
        catalan[0] = 1;
        catalan[1] = 1;
        for (int i=2; i<=n; i++)
        {
            catalan[i] = 0;
            for (int j=0; j<i; j++)
                catalan[i] += catalan[j] * catalan[i-j-1];
        }
        return catalan[n];
    }
}
