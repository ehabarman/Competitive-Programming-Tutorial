package Algorithms.Dynamic;

/**
 * @author Ehab Arman
 * @Date 23-9-2018
 */
public class BinomialCoeff {

    /**
     * dynamic solution to find nCr problem
     * time O(n*k)
     * space O(k)
     */
    static int binomialCoeff(int n, int k)
    {
        int C[] = new int[k + 1];

        // nC0 is 1
        C[0] = 1;

        for (int i = 1; i <= n; i++)
        {
            for (int j = Math.min(i, k); j > 0; j--)
                C[j] = C[j] + C[j-1];
        }
        return C[k];
    }
}
